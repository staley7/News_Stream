package parsing;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.text.BadLocationException;
import javax.swing.text.ElementIterator;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import manager.IDatabase;
import manager.Manager;
import manager.IPreferences;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import analysis.ArticleAggregator;
import analysis.KeywordFilter;

import database.Article;
import database.ArticleID;
import database.FakeDatabase;
import database.Story;
import database.StoryID;

import utility.Source;

/**
 * Watches RSS Feeds and starts the processing of links when new links are discovered on a feed. New RSS feeds can be added in the
 * preferences file note that the new line character is the split character for the feeds when they are placed in preferences.xml.
 * @author Jamison Bradley
 */
public class RSSMonitor {
	
	/**
	 * The list of feeds that are being monitored.
	 */
	private List<String> feeds;
	
	/**
	 * The amount of time in milliseconds between checking the rss feeds.
	 */
	private long timeBetweenChecks;
	
	/**
	 * The set of links that were found last time a check was ran
	 */
	private Set<String> lastCheckLinks;
	
	public RSSMonitor(List<String> feeds){
		this.feeds = feeds;
	}
	
	public List<String> getNewLinks(){
		List<String> ret = new ArrayList<String>();
		
		Set<String> oldSet = lastCheckLinks;
		lastCheckLinks = new HashSet<String>();
		
		//check to see if it is the first time that this method has been called since construction of the object
		boolean firstTime = false;
		if (oldSet == null){
			firstTime = true;
		}
		
		for (String feed: feeds){
			List<String> links = checkFeed(feed);
			//if it is the first time running check with database to see if a link has been added or not
			if (firstTime){
				IDatabase db = Manager.getDatabase();
				for (String link: links){
					if (!db.articleExists(link)){
						ret.add(link);
						lastCheckLinks.add(link);
					}
				}
			}
			//if it isn't the first time running use the already cached set to determine repeats.
			else{
				for (String link: links){
					//just add it to the new lastCheckLinks set don't add it to the return array since it's already been returned before
					if (oldSet.contains(link)){
						lastCheckLinks.add(link);
					}
					//add to both new lastCheckLinks set and the return List
					else{
						lastCheckLinks.add(link);
						ret.add(link);
					}
				}
			}
		}
		
		return ret;
	}
	
	private static List<String> checkFeed(String feed){
		List<String> ret = new ArrayList<String>();
		Source source = new Source(feed);
		
		try{
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(source.getSource());
			doc.getDocumentElement().normalize();
			
			NodeList nodes = doc.getElementsByTagName("item");
			
			for (int i = 0; i < nodes.getLength(); i++){
				Node n = nodes.item(i);
				
				if (n.getNodeType() == Node.ELEMENT_NODE){
					Element ele = (Element) n;
					ret.add(ele.getElementsByTagName("link").item(0).getTextContent());
				}
			}
		}
		catch (IOException e){
			e.printStackTrace();
		} 
		catch (SAXException e){
			e.printStackTrace();
		} 
		catch (ParserConfigurationException e){
			e.printStackTrace();
		}
		
		return ret;
	}
	
	public static void main(String[] args){
		List<String> feeds = new ArrayList<String>();
		IPreferences pref = Manager.getPreferences();
		String[] rssFeeds = pref.getRssFeeds();
		for (String url: rssFeeds){
			feeds.add(url);
		}
		RSSMonitor monitor = new RSSMonitor(feeds);
		
		long timeSplit = pref.getTimeBetweenRssChecksMs();
		long nextCheck = System.currentTimeMillis() + timeSplit;
		
		while (true){
			List<String> links = monitor.getNewLinks();
			Runnable analyzer = monitor.new LinkAnalyzerRunnable(links);
			Thread thread = new Thread(analyzer);
			thread.start();
//			System.out.println("New Links\n-------------------------------------------------------------------------");
//			for (String link: links){
//				System.out.println(link);
//			}
			
			while (System.currentTimeMillis() < nextCheck){}
			nextCheck += timeSplit;
		}
	}
	
//	public static void main(String[] args){
//		List<String> links = new ArrayList<String>();
//		links.add("http://www.cnn.com/2013/04/18/us/texas-explosion/index.html?hpt=hp_c3");
//		links.add("http://www.bbc.co.uk/news/world-us-canada-22204391");
//		links.add("http://www.guardian.co.uk/world/2013/apr/18/texas-fertiliser-plant-explosion-devastates-town");
//		links.add("http://www.guardian.co.uk/world/2013/apr/18/pervez-musharraf-flees-court-avoids-arrest");
//		links.add("http://www.bbc.co.uk/news/world-us-canada-22210035");
//		links.add("http://www.cnn.com/2013/04/18/politics/tainted-letter-intercepted/index.html?hpt=hp_t3");
//		RSSMonitor monitor = new RSSMonitor(new ArrayList<String>());
//		Runnable analyzer = monitor.new LinkAnalyzerRunnable(links);
//		Thread thread = new Thread(analyzer);
//		thread.start();
//	}
	
	
	public class LinkAnalyzerRunnable implements Runnable{
		
		/**
		 * List of links that need to be analyzed.
		 */
		private List<String> links;
		
		public LinkAnalyzerRunnable(List<String> links){
			this.links = links;
		}
		
		@Override
		public void run() {
			ArticleAggregator agg = new ArticleAggregator();
			IDatabase db = Manager.getDatabase();
			SourceParser parser = new SourceParser();
			int counter = 0;
			for (String link: links){
				Article article = parser.parse(new Source(link));
				//if the article object is null that means an error occurred while parsing the link look in error log file for reason
				if (article != null){
					counter++;
					System.out.println(counter + " " + link);
					Story matchedStory = agg.matchArticle(article);
					//if null was returned from the aggregator a new Story entry must be made because there is no good match in the database 
					if (matchedStory == null){
						//use the values of the article to set up the Story
						Story story = new Story();
						//TODO come up with better way of choosing category than taking the category from the article
						story.setCategory(article.getCategory());
						story.setTitle(article.getTitle());
						story.setDate(article.getDate());
						//id will be set when the store method is carried out
						db.storeStory(story);
						article.setStoryID(story.getID());
					}
					//if matchedStory isn't null it was matched to an already existing Story entry
					else{
						article.setStoryID(matchedStory.getID());
					}
					db.storeArticle(article);
				}
//				if (counter > ){
////					db.printArticleTable();
//					System.exit(0);
//				}
			}
		}
		
	}
}
