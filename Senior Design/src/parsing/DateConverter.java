package parsing;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Singleton this class so that the patterns only have to be compiled the first time it gets used.
 * @author Jamison Bradley
 */
public class DateConverter {
	/**
	 * Singleton instance of this class.
	 */
	private static DateConverter instance;
	/**
	 * Several patterns that are precompiled so they can be reused look in constructor to see what type of date format they are actually
	 * being used for.
	 */
	private Pattern pOne;
	private Pattern pTwo;
	private Pattern pThree;
	private Pattern pFour;
	
	private DateConverter(){
		String dateSplit = "(\\-|/)";
		//2013-01-28
		pOne = Pattern.compile("\\d{4}" + dateSplit + "\\d{2}" + dateSplit + "\\d{2}");
		//2013/01/28T22:36:28+00:00
		//2013-01-14T23:37:27+00:00
		pTwo = Pattern.compile("\\d{4}" + dateSplit + "\\d{2}" + dateSplit + "\\d{2}T\\d{2}:\\d{2}:\\d{2}\\+\\d{2}:\\d{2}");
		//2012-11-25T05:43:19Z
		pThree = Pattern.compile("\\d{4}" + dateSplit + "\\d{2}" + dateSplit + "\\d{2}T\\d{2}:\\d{2}:\\d{2}Z");
		//2013/01/15 01:45:50
		pFour = Pattern.compile("\\d{4}" + dateSplit + "\\d{2}" + dateSplit + "\\d{2} \\d{2}:\\d{2}:\\d{2}");
	}
	
	public Date convert(String date) throws DateException{
		Matcher mOne = pOne.matcher(date);
		Matcher mTwo = pTwo.matcher(date);
		Matcher mThree = pThree.matcher(date);
		Matcher mFour = pFour.matcher(date);
		
		int year, month, day, hour, minute, second = 0;
		//2013-01-28
		if (mOne.matches()){
			Calendar calendar = Calendar.getInstance();
			calendar.clear();
			year = Integer.parseInt(date.substring(0, 4));
			month = Integer.parseInt(date.substring(5, 7));
			day = Integer.parseInt(date.substring(8, 10));
			calendar.set(year, month - 1, day);
			return calendar.getTime();
		}
		//2013/01/28T22:36:28+00:00
		else if (mTwo.matches()){
			year = Integer.parseInt(date.substring(0, 4));
			month = Integer.parseInt(date.substring(5, 7));
			day = Integer.parseInt(date.substring(8, 10));
			hour = Integer.parseInt(date.substring(11, 13));
			minute = Integer.parseInt(date.substring(14, 16));
			second = Integer.parseInt(date.substring(17, 19));
			String zone = date.substring(19, date.length());
			Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT" + zone));
			calendar.clear();
			calendar.set(year, month - 1, day, hour, minute, second);
			return calendar.getTime();
		}
		//2012-11-25T05:43:19Z
		//2013/01/15 01:45:50
		else if (mThree.matches() | mFour.matches()){
			year = Integer.parseInt(date.substring(0, 4));
			month = Integer.parseInt(date.substring(5, 7));
			day = Integer.parseInt(date.substring(8, 10));
			hour = Integer.parseInt(date.substring(11, 13));
			minute = Integer.parseInt(date.substring(14, 16));
			second = Integer.parseInt(date.substring(17, 19));
			Calendar calendar = Calendar.getInstance();
			calendar.clear();
			calendar.set(year, month - 1, day, hour, minute, second);
			return calendar.getTime();
		}
		else{
			throw new DateException("Unrecognized date format " + date);
		}
	}
	
	public static DateConverter getInstance(){
		if (instance == null){
			instance = new DateConverter();
		}
		return instance;
	}
}
