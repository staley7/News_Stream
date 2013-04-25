package database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * This is a representation of an Article entry in the database.
 * @author Jamison Bradley
 */
public class Article {
	/**
	 * The id of the article's story.
	 */
	private StoryID storyId;
	/**
	 * The title of article on the webpage.
	 */
	private String title;
	/**
	 * The category that the article is listed under on the website.
	 */
	private String category;
	/**
	 * The date that the article was published on. In database this will be a long so when reading in just construct date with that long and
	 * when storing just call Date.getTime().
	 */
	private Date date;
	/**
	 * The location of the event taking place that the story occurred at, this is optional and may be null for many sources.
	 */
	private String location;
	/**
	 * The news organization from which the article came
	 */
	private String source;
	/**
	 * The url that the article is located at.
	 */
	private String url;
	/**
	 * The keywords that have been detected and associated with this article.
	 */
	private List<String> keywords;
	/**
	 * The quotations that were found in the article text.
	 */
	private List<String> quotes;
	/**
	 * The id that is associated with this article or null if no id has been associated with it yet.
	 */
	private ArticleID id;
	
	public Article(){
		
	}
	
	public void setStoryID(StoryID storyId){
		this.storyId = storyId;
	}
	
	public StoryID getStoryID(){
		return storyId;
	}
	
	public void setKeywords(List<String> keywords){
		this.keywords = keywords;
	}
	
	public List<String> getKeywords(){
		return keywords;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setCategory(String category){
		this.category = category;
	}
	
	public String getCategory(){
		return category;
	}
	
	public void setDate(Date date){
		this.date = date;
	}
	
	public Date getDate(){
		return date;
	}
	
	public void setLocation(String location){
		this.location = location;
	}
	
	public String getLocation(){
		return location;
	}
	
	public void setSource(String source){
		this.source = source;
	}
	
	public String getSource(){
		return source;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	
	public String getUrl(){
		return url;
	}
	
	public void setQuotes(List<String> quotes){
		this.quotes = quotes;
	}
	
	public List<String> getQuotes(){
		return quotes;
	}
	
	public void setID(ArticleID id){
		this.id = id;
	}
	
	public ArticleID getID(){
		return id;
	}
	
	
	/**
	public ArrayList<Article> getAllArticles()
	{
		ArrayList<Article> articles = new ArrayList<Article>();
		
		FirstCon con = new FirstCon();
		Connection connection = con.returnCon();
		
			try
			{
				ResultSet result = null;
				Statement stmt = connection.createStatement();
				
				try
				{
					result = stmt.executeQuery("SELECT Article_ID, Story_ID, URL, Source, Date, Title, Article_Keywords, Category, Quotations, Text FROM article ORDER BY Article_ID;");
				}
				catch(SQLException e)
				{
					System.out.println(e.getMessage());
				}
				
				while(result.next())
				{
					Article temp = new Article();
					temp.setID(new ArticleID(result.getInt("Article_ID")));
					temp.setUrl(result.getString("URL"));
					temp.setSource(result.getString("Source"));
					temp.setDate(new Date(result.getLong("Date")));
					temp.setTitle(result.getString("Title"));
					temp.setKeywords(result.getArray("Article_Keywords"));
					temp.setCategory(result.getString("Category"));
					temp.setQuotes(result.getArray("Quotations"));
					temp.setText(result.getString("Text"));
					articles.add(temp);
				}
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}			
			
		return articles;
	}*/
	
//	public void insertArticle()
//	{
//		FirstCon con = new FirstCon();
//		Connection connection = con.returnCon();
//		
//		try
//		{
//			PreparedStatement st = connection.prepareStatement("INSERT INTO article (Article_ID, Story_ID, URL, Source, Date, Title, Article_Keywords, Category, Quotations, Text) VALUES ('" + id.getID() + "'," + "'" + storyId + "'," + "'" + url + "'," + "'" + source + "'," + "'" + date.getTime() + "'," + "'" + title + "'," + "'" + keywords + "'," + "'" + category + "'," + "'" + quotes + "'," + "'" + text + "');");  
//		    st.executeUpdate();
//		}
//		catch(SQLException e)
//		{
//			System.out.println(e.getMessage());
//		}
//	}
	/**
	public void updateArticle()
	{
		FirstCon con = new FirstCon();
		Connection connection = con.returnCon();
					
		try
		{
			PreparedStatement st = connection.prepareStatement("UPDATE article SET Article_ID = ?, Story_ID = ?, URL = ?, Source = ?, Date = ?, Title = ?, Article_Keywords = ?, Category = ?, Quotations = ?, Text = ? WHERE Article_ID = ?;"); 
			st.setInt(1, id.getID());
			st.setString(2, storyId);
			st.setString(3, url);
			st.setString(4, source);
			st.setLong(5, date.getTime());
			st.setString(6, title);
			st.setArray(7, keywords);
			st.setString(8, category);
			st.setArray(9, quotes);
			st.setString(10, text);
			st.setInt(11, id.getID());
		    st.executeUpdate();
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}*/
	
	public void removeArticle()
	{
		FirstCon con = new FirstCon();
		Connection connection = con.returnCon();
					
		try
		{
			PreparedStatement st = connection.prepareStatement("DELETE FROM article WHERE Article_ID = ?;"); 
			st.setInt(1, id.getID());
		    st.executeUpdate();
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
}
