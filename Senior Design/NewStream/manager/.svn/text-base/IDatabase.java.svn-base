package manager;

import java.util.List;

import database.Article;
import database.ArticleID;
import database.Story;
import database.StoryID;

/**
 * Interface that defines the calls to the storage mechanism being used for data storage.
 * @author Jamison Bradley
 */
public interface IDatabase {
	
	/**
	 * Takes an Article object and creates a database entry for it. This method should choose the ID for the article
	 * regardless if one has already been set on the Article or not so that the user of this method doesn't have to have
	 * knowledge of what id's are available in the database.
	 * @param article to be stored in the database.
	 * @return the ArticleID that was associated with the Article object in the database.
	 */
	public ArticleID storeArticle(Article article);
	
	/**
	 * Retrieves an article with the given articleID from the database.
	 * @param articleID that identifies the article entry in the database.
	 * @return Article object that is a representation of the entry in the database with the provided article id.
	 */
	public Article getArticle(ArticleID articleID);
	
	/**
	 * Retrieves all Story entries in the database that have a date value between start and end.
	 * @param start the start time for the time range.
	 * @param end the end time for the time range.
	 * @return All of the Story entries in the database whose date value is between start and end.
	 */
	public List<Story> getStoriesByDate(long start, long end);
	
	/**
	 * This creates a new Story entry in the database, this should only be used for new entries the updateStory will
	 * be used for when fields of an already existing Story need to be updated.
	 * @param story is the new entry that is to be made in the database.
	 * @return The StoryID that was associated with the given Story in the database.
	 */
	public StoryID storeStory(Story story);
	
	/**
	 * This updates an already existing Story entry in the database.
	 * @param story is the Story that is to be updated.
	 */
	public void updateStory(Story story);
	
	/**
	 * Determines whether an Article already exists in the database for a given url.
	 * @param url is the url to the article in question.
	 * @return True if the article already has an entry in the Article table, false if it doesn't.
	 */
	public boolean articleExists(String url);
	
	/**
	 * Retrieves all Article objects form the Article table that match to the Story provided.
	 * @param story is the Story that all Articles returned belong to.
	 * @return A list of all Articles in the database that are associated with story.
	 */
	public List<Article> getArticlesInStory(Story story);
	
	//neither of these methods are required, they are meant to print a text representation of a table in the database for 
	//testing purposes.
	public void printArticleTable();
	public void printStoryTable();
}
