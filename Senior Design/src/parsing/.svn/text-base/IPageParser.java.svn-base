package parsing;
import utility.Source;
import database.Article;

/**
 * @author Jamison Bradley
 */
public interface IPageParser {
	
	/**
	 * Parses the source code for a given web site to determine certain information about the article. If null is returned then it was unable
	 * to find all the data it expected to be able to find.
	 * @param source
	 * @return An article object containing relevant information about the page that was parsed will be returned if all possible information was
	 * correctly gathered, some fields might be blank Strings though if that particular site doesn't provide that type of info (this is a common
	 * occurrence for the location information several sites don't provide this). Null will be returned if there was an error parsing the source code 
	 * an error will be logged if this is the case and you can view the error log to see what went wrong.
	 */
	public Article parse(Source source);
	
}
