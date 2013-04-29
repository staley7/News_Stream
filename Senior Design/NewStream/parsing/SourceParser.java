package parsing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import manager.ILogger;
import manager.Manager;

import utility.Source;


import database.Article;

/**
 * Determines what parser should be used to parse a given Source object.
 * @author Jamison Bradley
 */
public class SourceParser {
	
	//CNN patterns
	private Pattern cnnOne = Pattern.compile("www\\.cnn\\.com");
	//New York Times patterns
	private Pattern nyTimesOne = Pattern.compile("www\\.nytimes\\.com");
	private Pattern nyTimesTwo = Pattern.compile("theater\\.nytimes\\.com");
	//BBC patterns
	private Pattern bbcOne = Pattern.compile("www\\.bbc\\.co\\.uk");
	private Pattern bbcTwo = Pattern.compile("www\\.bbc\\.com");
	//The Guardian patterns
	private Pattern theGuardianOne = Pattern.compile("www\\.guardian\\.co\\.uk");
	
	public SourceParser(){
		
	}
	
	public Article parse(Source source){
		Article ret = null;
		String url = source.getUrl();
		if (url == null){
			//log error here return null
		}
		
		IPageParser parser = null;
		
		//find the correct PageParser to use
		if (cnnTest(url)){
			parser = new CNNParser();
		}
		else if (nyTimesTest(url)){
			parser = new NYTimesParser();
		}
		else if (bbcTest(url)){
			parser = new BBCParser();
		}
		else if (theGuardianTest(url)){
			parser = new TheGuardianParser();
		}
		//correct parser could not be determined, log error
		else{
			ILogger log = Manager.getLogger();
			String message = "Could not determine which parser to use for " + url;
			log.logError("SourceParser", message);
			return null;
		}
		
		if (parser != null){
			ret = parser.parse(source);
		}

		return ret;
	}
	
	private boolean nyTimesTest(String url){
		Matcher m = nyTimesOne.matcher(url);
		Matcher m2 = nyTimesTwo.matcher(url);
		return m.find() || m2.find();
	}
	
	private boolean cnnTest(String url){
		Matcher m = cnnOne.matcher(url);
		return m.find();
	}
	
	private boolean bbcTest(String url){
		Matcher m = bbcOne.matcher(url);
		Matcher m2 = bbcTwo.matcher(url);
		return m.find() || m2.find();
	}
	
	private boolean theGuardianTest(String url){
		Matcher m = theGuardianOne.matcher(url);
		return m.find();
	}
}
