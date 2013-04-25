package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import database.Article;
import database.ArticleID;

/**
 * @author Jamison Bradley
 */
public class ArticleTest {

	@Test
	public void test(){
		Article article = new Article();
		article.setCategory("category");
		article.setDate(new Date(100000));
		article.setID(new ArticleID(0));
		List<String> keywords = new ArrayList<String>();
		keywords.add("keyword1");
		keywords.add("keyword2");
		article.setKeywords(keywords);
		article.setLocation("location");
		List<String> quotes = new ArrayList<String>();
		quotes.add("quote1");
		quotes.add("quote2");
		article.setQuotes(quotes);
		article.setSource("source");
		article.setTitle("title");
		article.setUrl("url");
		
		//make sure all values are the same when you get them back out
		assertEquals(article.getCategory(), "category");
		assertEquals(article.getDate(), new Date(100000));
		assertEquals(article.getID(), new ArticleID(0));
		assertEquals(article.getKeywords().get(0), "keyword1");
		assertEquals(article.getKeywords().get(1), "keyword2");
		try{
			//this should fail and be caught
			article.getKeywords().get(2);
			//shouldn't ever be ran since previous line should fail
			assertEquals(true, false);
		}
		catch (IndexOutOfBoundsException e){
			//do nothing failing was the desired outcome
		}
		assertEquals(article.getLocation(), "location");
		assertEquals(article.getQuotes().get(0), "quote1");
		assertEquals(article.getQuotes().get(1), "quote2");
		try{
			//this should fail and be caught
			article.getQuotes().get(2);
			//shouldn't ever be ran since previous line should fail
			assertEquals(true, false);
		}
		catch (IndexOutOfBoundsException e){
			//do nothing failing was the desired outcome
		}
		assertEquals(article.getSource(), "source");
		assertEquals(article.getTitle(), "title");
		assertEquals(article.getUrl(), "url");
	}
	
	public void run(){
		test();
	}
}
