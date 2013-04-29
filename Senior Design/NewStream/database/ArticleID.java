package database;

/**
 * Wrapper class for an integer that will represent an Article ID from the Article table of the database.
 * Wrapper is used so if an extension to using longs instead of ints was needed it would make things easier.
 * @author Jamison Bradley
 */
public class ArticleID {
	/**
	 * The Article id value.
	 */
	private int id;
	
	public ArticleID(int id){
		this.id = id;
	}
	
	public int getID(){
		return id;
	}
	
	@Override
	public boolean equals(Object other){
		if (other instanceof ArticleID){
			if (id == ((ArticleID) other).getID()){
				return true;
			}
		}
		return false;
	}
}
