package database;

/**
 * Wrapper class for an integer that will represent a Story ID from the Story table of the database.
 * @author Jamison Bradley
 */
public class StoryID {
	/**
	 * The Story id value.
	 */
	private int id;
	
	public StoryID(int id){
		this.id = id;
	}
	
	public int getID(){
		return id;
	}
	
	@Override
	public boolean equals(Object obj){
		if (obj instanceof StoryID){
			StoryID other = (StoryID) obj;
			if (other.getID() == id){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "" + id;
	}
}
