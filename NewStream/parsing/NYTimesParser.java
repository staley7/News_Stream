package parsing;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

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


public class NYTimesParser implements IPageParser{
	
	private KeywordFilter filter;
	
	public NYTimesParser(){
		filter = new KeywordFilter();
	}
	
	public Article parse(Source source) {
		Article article = new Article();
		article.setUrl(source.getUrl());
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
				if ("meta".equals(elem.getName())){
					try{
						AttributeSet attr = elem.getAttributes();
						if ("datePublished".equals((String) attr.getAttribute("itemprop"))){
							String date = (String) attr.getAttribute(HTML.Attribute.CONTENT);
							DateConverter dc = DateConverter.getInstance();
							article.setDate(dc.convert(date));
						}
						else if ("articleSection".equals((String) attr.getAttribute("itemprop"))){
							article.setCategory((String) attr.getAttribute(HTML.Attribute.CONTENT));
						}
						else if ("geo".equals((String) attr.getAttribute(HTML.Attribute.NAME))){
							article.setLocation((String) attr.getAttribute(HTML.Attribute.CONTENT));
						}
						else if ("og:title".equals((String) attr.getAttribute("property"))){
							article.setTitle((String) attr.getAttribute(HTML.Attribute.CONTENT));
						}
					}
					catch (DateException e){
						ILogger log = Manager.getLogger();
						log.logError("NYTimesParser", e.getMessage());
						return null;
					}
				}
				else if ("p".equals(elem.getName())){
					if ("articleBody".equals((String) elem.getAttributes().getAttribute("itemprop"))){
						builder.append(doc.getText(elem.getStartOffset(), elem.getEndOffset() - elem.getStartOffset()));
					}
				}
			}
			article.setQuotes(Util.getQuotes(builder.toString()));

			//set quotes and keywords while still having access to the text of the article
			String articleText = builder.toString();
			if ("".equals(articleText)){
				ILogger log = Manager.getLogger();
				log.logError("NYTimesParser", "Was unable to find article text of " + source.getUrl() + ".");
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
		
		return Util.checkForErrorPageParser(article, "NYTimesParser");
	}
}
