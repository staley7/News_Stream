package testing;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.ElementIterator;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import manager.IDatabase;
import manager.Manager;

import parsing.DateConverter;
import parsing.SourceParser;

import utility.Source;
import utility.Util;

import database.Article;
import database.ArticleID;
import database.StoryID;





public class TestMain {
//	public static void main(String[] args){
//		try{
//			String list[] = {
////							 "http://www.nytimes.com/2012/11/26/world/asia/us-planning-a-force-to-stay-in-afghanistan.html?hp&_r=0",
////							 "http://www.nytimes.com/2012/11/26/us/from-the-man-who-insulted-islam-no-retreat.html?hp",
////							 "http://theater.nytimes.com/2012/11/26/theater/reviews/love-in-the-time-of-cholera-at-the-gramercy-arts-theater.html?ref=theater",
////							 "http://www.nytimes.com/2012/11/27/world/middleeast/egypts-president-said-to-limit-scope-of-judicial-decree.html?_r=0",
////							 "http://www.nytimes.com/2012/11/27/nyregion/house-featured-in-radio-days-survives-hurricane.html?ref=nyregion",
//							 
////					         "http://www.cnn.com/2012/11/23/politics/fiscal-cliff/index.html?hpt=hp_t2",
////							 "http://www.cnn.com/2012/11/25/world/meast/egypt-protests/index.html?hpt=hp_t2",
////							 "http://www.cnn.com/2012/11/25/world/meast/gaza-israel-ceasefire/index.html?hpt=hp_t2",
////							 "http://www.cnn.com/2012/11/25/world/asia/bangladesh-factory-fire/index.html?hpt=hp_t2",
////							 "http://www.cnn.com/2012/11/25/world/asia/pakistan-violence/index.html?hpt=hp_t2",
//					
//							 "http://www.bbc.co.uk/news/world-africa-21021132",
//							 "http://www.bbc.com/future/story/20130114-slums-and-the-future-of-cities",
//							 "http://www.bbc.co.uk/news/world-latin-america-21021581",
//							 "http://www.bbc.co.uk/news/world-us-canada-21020745",
//							 "http://www.bbc.co.uk/sport/0/cycling/20946301",
//							 
////							 "http://www.guardian.co.uk/world/2013/jan/15/francois-hollande-islamist-rebels-mali",
////							 "http://www.guardian.co.uk/football/2013/jan/15/sunderland-bolton-fa-cup-report",
////							 "http://www.guardian.co.uk/tv-and-radio/tvandradioblog/2013/jan/15/carrie-diaries-premiere-cw-network",
////							 "http://www.guardian.co.uk/film/filmblog/2013/jan/15/argo-best-picture-oscars-affleck",
//							 
////							 "http://www.guardian.co.uk/business/2013/feb/21/us-unemployment-claims-rise-fed",
////							 "http://www.guardian.co.uk/technology/2013/feb/21/white-house-cyber-threat-russia-china",
////							 "http://www.guardian.co.uk/politics/video/2013/feb/21/william-hague-hezbollah-lebanese-video",
////							 "http://www.guardian.co.uk/world/2013/feb/21/syria-blames-terrorists-damascus-bomb",
////							 "http://www.guardian.co.uk/sport/2013/feb/21/six-nations-2013-england-team-france",
//							 
//							 //two quotes are pretty similar between these two
////						 	 "http://www.guardian.co.uk/football/2013/jan/28/mario-balotelli-milan-loan-manchester-city",
////							 "http://www.bbc.co.uk/sport/0/football/21240782"
//			};
//			
//			SourceParser parser = new SourceParser();
//			for (String str: list){
////				URL url = new URL(str); 
////				CNNParser parser = new CNNParser();
//////				NYTimesParser parser = new NYTimesParser();
////				Article article = parser.parse(new Source(str));
//				Article article = parser.parse(new Source(str));
//				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//				if (article != null){
////					System.out.println(article.getUrl());
////					System.out.println("\t" + article.getTitle());
////					System.out.println("\t" + article.getCategory());
//					System.out.println("\t" + article.getDate());
////					System.out.println("\t" + article.getLocation());
////					System.out.println(article.getText());
//					List<String> quotes = article.getQuotes();
//					for (String quote: quotes){
//						System.out.println(quote);
//					}
//				}
//			}
////			HTMLEditorKit kit = new HTMLEditorKit(); 
////			HTMLDocument doc = (HTMLDocument) kit.createDefaultDocument(); 
////			doc.putProperty("IgnoreCharsetDirective", Boolean.TRUE);
////			Reader HTMLReader = new InputStreamReader(url.openConnection().getInputStream()); 
////			kit.read(HTMLReader, doc, 0); 
////
////			//  Get an iterator for all HTML tags.
////			ElementIterator it = new ElementIterator(doc); 
////			Element elem; 
////
////			while( (elem = it.next()) != null   )
////			{ 
////				if( elem.getName().equals(  "meta") )
////				{ 
////					String s = (String) elem.getAttributes().getAttribute(HTML.Attribute.CONTENT);
////					if( s != null ) 
////						System.out.println (s );
////				} 
////			}
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		System.exit(0);
//	}
	
	public static void main(String[] args){
//		String[] arr = {"http://www.guardian.co.uk/world/2013/apr/04/connecticut-strictest-gun-control-laws",
//						"http://www.bbc.co.uk/news/world-us-canada-22024289",
//						"http://www.cnn.com/2013/04/04/us/connecticut-gun-law-overhaul/index.html?hpt=hp_t1",
//		};
//		
//		SourceParser parser = new SourceParser();
//		for (String str: arr){
//			Article article = parser.parse(new Source(str));
//			System.out.println(article.getTitle());
//		}
		ArticleID currentId = new ArticleID(6);
		Article article = new Article();
		article.setCategory("World");
		article.setDate(new Date(System.currentTimeMillis()));
		article.setID(currentId);
		List<String> list = new ArrayList<String>();
		list.add("keyword");
		article.setKeywords(list);
		article.setLocation("test");
		List<String> quotes = new ArrayList<String>();
		quotes.add("quote");
		article.setQuotes(quotes);
		article.setSource("BBC");
		article.setStoryID(new StoryID(1));
		article.setTitle("test title");
		article.setUrl("http://www.bbc.co.uk/news/world-us-canada-22024289");
		System.out.println("Successfully setup article");
		IDatabase db = Manager.getDatabase();
		System.out.println("Successfully setup database");
		db.storeArticle(article);
		System.out.println("Successfully commited article");
		db.getArticle(currentId);
		System.out.println("Successfully retrieved article");
	}
}


