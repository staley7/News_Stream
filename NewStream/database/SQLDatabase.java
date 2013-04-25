package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import manager.IDatabase;

/**
 * Implementation of Database interface that uses a MySQL database to store the information.
 * @author Charles Litfin
 */
public class SQLDatabase implements IDatabase {
	/**
	 * Singleton instance of the SQLDatabase class.
	 */
	private static SQLDatabase instance;
	
	/**
	 * ID of next article
	 */
	private int nextID;
	
	/**
	 * ID of next Story
	 */
	private int nextStory;
	
	/**
	 * Private constructor access to object should be made via the getInstance method.
	 */
	private SQLDatabase(){
		FirstCon con = new FirstCon();
		Connection connection = con.returnCon();
		
			try
			{
				ResultSet result = null;
				Statement stmt = connection.createStatement();
				
				try
				{
					result = stmt.executeQuery("SELECT MAX(Story_ID) FROM story;");
				}
				catch(SQLException e)
				{
					System.out.println(e.getMessage());
				}
				
				result.next();
				if(result == null){
					nextStory = 1;
				}else{
					nextStory = result.getInt("Story_ID");
					nextStory++;
				}
				
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}			
			FirstCon con2 = new FirstCon();
			Connection connection2 = con2.returnCon();
			
				try
				{
					ResultSet result = null;
					Statement stmt = connection2.createStatement();
					
					try
					{
						result = stmt.executeQuery("SELECT MAX(Article_ID) FROM article;");
					}
					catch(SQLException e)
					{
						System.out.println(e.getMessage());
					}
					
					result.next();
					if(result == null){
						nextID = 1;
					}else{
						nextID = result.getInt("Article_ID");
						nextID++;
					}
					
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
	}
	
	@Override
	public ArticleID storeArticle(Article article) {
		article.setID(new ArticleID(nextID));
		nextID++;
		System.out.println("Successfully accessed the store article function");
		FirstCon con = new FirstCon();
		System.out.println("Successfully made a connector object");
		Connection connection = con.returnCon();
		System.out.println("Successfully setup connection");
		try
		{
			PreparedStatement st = connection.prepareStatement("INSERT INTO article (Article_ID, Story_ID, URL, Source, Date, Title, Article_Keywords, Category, Quotations, Text) VALUES ('" + article.getID().getID() + "'," + "'" + article.getStoryID() + "'," + "'" + article.getUrl() + "'," + "'" + article.getSource() + "'," + "'" + article.getDate().getTime() + "'," + "'" + article.getTitle() + "'," + "'" + article.getKeywords().toArray() + "'," + "'" + article.getCategory() + "'," + "'" + article.getQuotes().toArray() + "'," + "'" + "" + "');");  
		    st.executeUpdate();
		    nextID++;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return article.getID();
	}

	@Override
	public Article getArticle(ArticleID articleID) {
		FirstCon con = new FirstCon();
		Connection connection = con.returnCon();
		Article temp = new Article();
		
			try
			{
				String sql = "SELECT Article_ID, Story_ID, URL, Source, Date, Title, Article_Keywords, Category, Quotations, Text FROM article WHERE Article_ID = ? ORDER BY Article_ID;";
				PreparedStatement prep = connection.prepareStatement(sql);
				prep.setInt(1, articleID.getID());
				ResultSet result = null;
				//Statement stmt = connection.createStatement();
				
				try
				{
					result = prep.executeQuery();
					result.next();
				}
				catch(SQLException e)
				{
					System.out.println(e.getMessage());
				}
				
				temp.setID(new ArticleID(result.getInt("Article_ID")));
				System.out.println(temp.getID().getID());
				temp.setUrl(result.getString("URL"));
				System.out.println(temp.getUrl());
				temp.setSource(result.getString("Source"));
				System.out.println(temp.getSource());
				temp.setDate(new Date(result.getLong("Date")));
				temp.setTitle(result.getString("Title"));
				System.out.println(temp.getTitle());
				temp.setKeywords((List<String>) result.getArray("Article_Keywords"));
				temp.setCategory(result.getString("Category"));
				System.out.println(temp.getCategory());
				temp.setQuotes((List<String>) result.getArray("Quotations"));
				//temp.setText(result.getString("Text"));
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			
			return temp;
	}

	@Override
	public List<Story> getStoriesByDate(long start, long end) {
		ArrayList<Story> stories = new ArrayList<Story>();
		
		FirstCon con = new FirstCon();
		Connection connection = con.returnCon();
		
			try
			{
				ResultSet result = null;
				
				
				try
				{
					PreparedStatement stmt = connection.prepareStatement("SELECT Story_ID, Category, Date, Title FROM story WHERE Date >= ? AND Date <= ? ORDER BY Date;");
					stmt.setLong(1, start);
					stmt.setLong(2, end);
					result = stmt.executeQuery();
				}
				catch(SQLException e)
				{
					System.out.println(e.getMessage());
				}
				
				while(result.next())
				{
					Story temp = new Story();
					temp.setID(new StoryID(result.getInt("Story_ID")));
					temp.setCategory(result.getString("Category"));
					temp.setDate(new Date(result.getLong("Date")));
					temp.setTitle(result.getString("Title"));
					stories.add(temp);
				}
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}			
			
		return stories;
	}

	@Override
	public StoryID storeStory(Story story) {
		story.setID(new StoryID(nextStory));
		nextStory++;
		FirstCon con = new FirstCon();
		Connection connection = con.returnCon();
		
		try
		{
			PreparedStatement st = connection.prepareStatement("INSERT INTO story (Story_ID, Category, Date, Title) VALUES ('" + story.getID().getID() + "'," + "'" + story.getCategory() + "'," + "'" + story.getDate().getTime() + "'," + "'" + story.getTitle() + "');");  
		    st.executeUpdate();
		    nextStory++;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return story.getID();
		
	}

	@Override
	public void updateStory(Story story) {
		FirstCon con = new FirstCon();
		Connection connection = con.returnCon();
					
		try
		{
			PreparedStatement st = connection.prepareStatement("DELETE FROM story WHERE Story_ID = ?;"); 
			st.setInt(1, story.getID().getID());
		    st.executeUpdate();
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public boolean articleExists(String url) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public List<Article> getArticlesInStory(Story story) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Retrieves the singleton instance of this class.
	 * @return The singleton instance of this class.
	 */
	public static SQLDatabase getInstance(){
		if (instance == null){
			instance = new SQLDatabase();
		}
		return instance;
	}

	@Override
	public void printArticleTable() {
		System.out.println("not supported");
	}

	@Override
	public void printStoryTable() {
		System.out.println("not supported");
	}


}
