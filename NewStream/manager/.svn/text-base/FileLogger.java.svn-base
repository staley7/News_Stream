package manager;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;


/**
 * Implementation of the Logger interface that stores the logged information into a text file on the local computer.
 * @author Jamison Bradley
 */
class FileLogger implements ILogger {

	/**
	 * The file that error information will be logged in.
	 */
	private File errorFile;
	
	private static FileLogger instance;
	
	private FileLogger(){
		IPreferences prefs = Manager.getPreferences();
		errorFile = new File(prefs.getErrorLogLocation());
	}
	
	@Override
	public void logError(String className, String message) {
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(errorFile, true));
			writer.write("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
			writer.write("Time: " + new Date(System.currentTimeMillis()) + "\n");
			writer.write("Location: " + className + "\n");
			writer.write("Message: " + message + "\n");
			writer.write("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
			writer.close();
		} 
		catch (IOException e){
			e.printStackTrace();
		}
	}

	/**
	 * Access method to acquire the singleton of this class.
	 * @return An instance of the FileLogger class.
	 */
	public static FileLogger getInstance(){
		if (instance == null){
			instance = new FileLogger();
		}
		return instance;
	}
}
