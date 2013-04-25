package utility;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Abstraction of a web page source. This class allows for multiple ways of getting a hold of a pages source with out having to change other code.
 * @author Jamison Bradley
 */
public class Source {
	/**
	 * Stream that provides the actual code for a given url
	 */
	private InputStream source;
	/**
	 * The url that the source object is going to get the page source data from
	 */
	private String url;
	
	/**
	 * Constructor that allows the source code to be passed in as a String along with the url as a String.
	 * @param source a String that contains the code for the web page at the corresponding url location.
	 * @param url the location on the web that the source String is coming from.
	 */
	public Source(String source, String url){
		if (source == null || url == null){
			throw new IllegalArgumentException("Neither source nor url are allowed to be null.");
		}
		this.source = new ByteArrayInputStream(source.getBytes());
		this.url = url;
	}
	
	/**
	 * Constructor that allows just the url to be passed to it and it will fetch the source code located at that url.
	 * @param url
	 */
	public Source(String url){
		if (url == null){
			throw new IllegalArgumentException("Url is not allowed to be null.");
		}
		this.url = url;
		try{
			URL temp = new URL(url);
			source = temp.openStream();
		} 
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Retrieves the url that is associated with this Source object.
	 * @return String of the url associated with this Source object.
	 */
	public String getUrl(){
		return url;
	}
	
	/**
	 * Retrieves an InputStream for the source code associated with this Source object.
	 * @return Stream that can be used to get the source code associated with this Source object.
	 */
	public InputStream getSource(){
		return source;
	}
}
