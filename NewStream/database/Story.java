package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 * This is a representation of a Story entry in the database.
 * @author Jamison Bradley
 */
public class Story {
	/**
	 * The id that is associated with this story in the database.
	 */
	private StoryID id;
	/**
	 * The category on our website that this story is listed under.
	 */
	private String category;
	/**
	 * The time that the story was created will be stored in db as a long.
	 */
	private Date date;
	/**
	 * The title of the story on our website.
	 */
	private String title;
	
	public Story(){}
	
	public void setID(StoryID id){
		this.id = id;
	}
	
	public StoryID getID(){
		return id;
	}
	
	public void setCategory(String category){
		this.category = category;
	}
	
	public String getCategory(){
		return category;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setDate(Date date){
		this.date = date;
	}
	
	public Date getDate(){
		return date;
	}
	/**
	public ArrayList<Story> getAllStories()
	{
		ArrayList<Story> stories = new ArrayList<Story>();
		
		FirstCon con = new FirstCon();
		Connection connection = con.returnCon();
		
			try
			{
				ResultSet result = null;
				Statement stmt = connection.createStatement();
				
				try
				{
					result = stmt.executeQuery("SELECT Story_ID, Category, Date, Title FROM Story ORDER BY Story_ID;");
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
	}*/
	
	public void insertStory()
	{
		FirstCon con = new FirstCon();
		Connection connection = con.returnCon();
		
		try
		{
			PreparedStatement st = connection.prepareStatement("INSERT INTO story (Story_ID, Category, Date, Title) VALUES ('" + id.getID() + "'," + "'" + category + "'," + "'" + date.getTime() + "'," + "'" + title + "');");  
		    st.executeUpdate();
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void updateStory()
	{
		FirstCon con = new FirstCon();
		Connection connection = con.returnCon();
					
		try
		{
			PreparedStatement st = connection.prepareStatement("UPDATE story SET Story_ID = ?, Category = ?, Date = ?, Title = ? WHERE Story_ID = ?;"); 
			st.setInt(1, id.getID());
			st.setString(2, category);
			st.setLong(3, date.getTime());
			st.setString(4, title);
			st.setInt(5, id.getID());
		    st.executeUpdate();
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void removeStory()
	{
		FirstCon con = new FirstCon();
		Connection connection = con.returnCon();
					
		try
		{
			PreparedStatement st = connection.prepareStatement("DELETE FROM story WHERE Story_ID = ?;"); 
			st.setInt(1, id.getID());
		    st.executeUpdate();
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
}
