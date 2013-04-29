package parsing;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.ElementIterator;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import analysis.KeywordFilter;

import manager.ILogger;
import manager.Manager;

import utility.Source;
import utility.Util;


import database.Article;


/**
 * Parses the needed content out of a Source object constructed using a link to an article on CNN's website.
 * @author Jamison Bradley
 */
public class CNNParser implements IPageParser{
	
	private Pattern pClassValue;
	
	private KeywordFilter filter;
	
	public CNNParser(){
		pClassValue = Pattern.compile("cnn_storypgraphtxt cnn_storypgraph[\\p{Digit}]+");
		filter = new KeywordFilter();
	}

	public Article parse(Source source) {
		Article article = new Article();
		article.setUrl(source.getUrl());
		article.setSource("CNN");
		try{
			HTMLEditorKit kit = new HTMLEditorKit(); 
			HTMLDocument doc = (HTMLDocument) kit.createDefaultDocument();
			doc.putProperty("IgnoreCharsetDirective", Boolean.TRUE);
			Reader HTMLReader = new InputStreamReader(source.getSource()); 
			kit.read(HTMLReader, doc, 0); 

			//Get an iterator for all HTML tags.
			ElementIterator it = new ElementIterator(doc); 
			Element elem = null; 
			
			StringBuilder builder = new StringBuilder(1000);
			//start reading the page
			while((elem = it.next()) != null){ 
				if("meta".equals(elem.getName())){
					try{
						AttributeSet attr = elem.getAttributes();
						String name = (String) attr.getAttribute(HTML.Attribute.NAME);
						String itemprop = (String) attr.getAttribute("itemprop");
						if ("title".equals(name)){
							article.setTitle((String) attr.getAttribute(HTML.Attribute.CONTENT));
						}
						else if ("section".equals(name)){
							article.setCategory((String) attr.getAttribute(HTML.Attribute.CONTENT));
						}
						else if ("pubdate".equals(name)){
							String date = (String) attr.getAttribute(HTML.Attribute.CONTENT);
							DateConverter dc = DateConverter.getInstance();
							article.setDate(dc.convert(date));
						}
						else if ("contentLocation".equals(itemprop)){
							article.setLocation((String) attr.getAttribute(HTML.Attribute.CONTENT));
						}
					}
					catch (DateException e){
						ILogger log = Manager.getLogger();
						log.logError("CNNParser", e.getMessage());
						return null;
					}
				}
				else if ("p".equals(elem.getName())){
					AttributeSet attr = elem.getAttributes();
					String cls = (String) attr.getAttribute(HTML.Attribute.CLASS);
					if (cls != null){
						Matcher m = pClassValue.matcher(cls);
						if (m.matches()){
							builder.append(doc.getText(elem.getStartOffset(), elem.getEndOffset() - elem.getStartOffset()));
						}
					}
				}
			}

			//set quotes and keywords while still having access to the text of the article
			String articleText = builder.toString();
			if ("".equals(articleText)){
				ILogger log = Manager.getLogger();
				log.logError("CNNParser", "Was unable to find article text of " + source.getUrl() + ".");
				return null;
			}
			List<String> keywords = filter.getKeywordsList(articleText);
			List<String> quotes = Util.getQuotes(articleText);
			//if keyword list is size 0 then it is probably a image gallery or video, and there is no point in passing the
			//article back since there won't be anyway to analyze it.
			if (keywords.size() == 0){
				//no point in logging the error since it didn't really fail at parsing just not right type of article
				return null;
			}
			article.setKeywords(keywords);
			article.setQuotes(quotes);
		}
		catch (IOException e){
			e.printStackTrace();
		} 
		catch (BadLocationException e){
			e.printStackTrace();
		}
		
		return Util.checkForErrorPageParser(article, "CNNParser");
	}

}
