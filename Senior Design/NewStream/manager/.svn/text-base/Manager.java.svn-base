package manager;

import java.util.prefs.Preferences;

import database.FakeDatabase;
import database.SQLDatabase;

/**
 * This class is used to access implementations of certain interfaces so that if at any point a switch in implementations occurs it can be done
 * easily by making the change and here and not having to track it down in all of the other classes.
 * @author Jamison Bradley
 */
public class Manager {
	
	/**
	 * Can be set to a different preferences for testing purposes this variable is only returned as the choosen preference when it is not
	 * null, which can only occur when a set call has been made. Is only meant to be used for testing so that test don't have to be rewritten
	 * every time that a preference is changed.
	 */
	private static IPreferences preferences;
	
	/**
	 * Gets an implementation of the Preferences interface.
	 * @return An implementation of the Preferences interface.
	 */
	public static IPreferences getPreferences(){
		if (preferences != null){
			return preferences;
		}
		return XMLPreferences.getInstance();
//		return TestingManager.getPreferences();
	}
	
	/**
	 * Gets an implementation of the Logger interface.
	 * @return An implementation of the Logger interface.
	 */
	public static ILogger getLogger(){
		return FileLogger.getInstance();
	}
	
	/**
	 * Gets an implementation of the Database interface.
	 * @return An implementation of the Database interface.
	 */
	public static IDatabase getDatabase(){
		return SQLDatabase.getInstance();
		//return FakeDatabase.getInstance();
	}
	
	/**
	 * Sets the preference object to something other than the currently defined return value, should only be used for testing purposes.
	 * If at any point during the test you want to switch back to default preferences than call this and pass in null.
	 * @param pref the IPrefence object that is being used for testing purposes to take the place of the normal default return value.
	 */
	public static void setPreferences(IPreferences pref){
		preferences = pref;
	}
}
