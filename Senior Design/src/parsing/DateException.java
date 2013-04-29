package parsing;

/**
 * Exception for when the Date is unable to be parsed correctly.
 * @author Jamison Bradley
 */
public class DateException extends Exception {

	/**
	 * Default serialization
	 */
	private static final long serialVersionUID = 1L;

	public DateException(String message){
		super(message);
	}
}
