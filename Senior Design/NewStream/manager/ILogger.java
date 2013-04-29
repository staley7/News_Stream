package manager;
/**
 * Contains methods logging information during the runtime of the system.
 * @author Jamison Bradley
 */
public interface ILogger {
	
	/**
	 * Logs an error report consisting of a time that the error occurred, the location that it occurred at, and a message describing the error.
	 * @param className is the location in the system that the error occurred at.
	 * @param message is the description of the error that occurred.
	 */
	public void logError(String className, String message);
	
}
