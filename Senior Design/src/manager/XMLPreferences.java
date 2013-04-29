package manager;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Implementation of the Preferences interface. Retrieves preferences from an xml file on local machine.
 * XML file format should be as follows. Note for the preference of which rss feeds are to be monitored they individual feeds
 * are seperated by new line characters.
 * <preferences>
 * 		<pref1>value</pref1>
 * 		<pref2>value</pref2>
 * </preferences>
 * @author Jamison Bradley
 */
class XMLPreferences implements IPreferences {
	/**
	 * Coefficient used in QuotationScorer for quotations comparison.
	 */
	private double editDistanceCoefficient;
	/**
	 * Coefficient used in QuotationScorer for quotations comparison.
	 */
	private double longestCommonSubsequenceCoefficient;
	/**
	 * The location of the file that errors are to be logged to.
	 */
	private String errorLogLocation;
	/**
	 * Location of the database host.
	 */
	private String databaseHost;
	/**
	 * Username for the database.
	 */
	private String databaseUsername;
	/**
	 * Password for the database.
	 */
	private String databasePassword;
	/**
	 * Maximum amount of time in hours that two Articles can be separated by to be considered part of the same Story.
	 */
	private int maxDateDifference;
	/**
	 * An array of rss feeds that are being monitored by this program.
	 */
	private String[] rssFeeds;
	/**
	 * The amount of time should be allowed to elapse between rss feed checks.
	 */
	private long timeBetweenRssChecksMs;
	/**
	 * The minimum score in ArticleAggregator that must be achieved in order for an Article to match a Story.
	 */
	private double thresholdScoreForMatch;
	/**
	 * The coefficient used to modify the score coming out of the DateScorer in ArticleAggregator.
	 */
	private double dateScorerCoefficient;
	/**
	 * The coefficient used to modify the score coming out of the QuotationsScorer in ArticleAggregator.
	 */
	private double quotationsScorerCoefficient;
	/**
	 * The coefficient used to modify the score coming out of the KeywordScorer in ArticleAggregator.
	 */
	private double keywordScorerCoefficient;
	/**
	 * Singleton instance of class.
	 */
	private static XMLPreferences instance;
	

	private XMLPreferences(){
		try{
			InputStream xml = getClass().getResourceAsStream("/manager/preferences.xml"); 
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xml);
			
			doc.getDocumentElement().normalize();
			
			//retrieve and store all of the different preferences.
			String xmlTags[] = {"database_host",
								"database_username",
								"database_password",
								"error_log_location",
								"longest_common_subsequence_coe",
								"edit_distance_coe",
								"maximum_date_match",
								"rss_feeds",
								"time_between_rss_checks_ms",
								"threshold_score_for_match",
								"date_scorer_coe",
								"quotations_scorer_coe",
								"keyword_scorer_coe"
			};
			
			for (String tag: xmlTags){
				NodeList nodes = doc.getElementsByTagName(tag);
				if (nodes.getLength() == 1){
					Node n = nodes.item(0);
					if (n.getNodeType() == Node.ELEMENT_NODE){
						Element ele = (Element) n;
						if ("database_host".equals(tag)){
							databaseHost = ele.getTextContent();
						}
						else if ("database_username".equals(tag)){
							databaseUsername = ele.getTextContent();
						}
						else if ("database_password".equals(tag)){
							databasePassword = ele.getTextContent();
						}
						else if ("error_log_location".equals(tag)){
							errorLogLocation = ele.getTextContent();
						}
						else if ("longest_common_subsequence_coe".equals(tag)){
							longestCommonSubsequenceCoefficient = Double.parseDouble(ele.getTextContent());
						}
						else if ("edit_distance_coe".equals(tag)){
							editDistanceCoefficient = Double.parseDouble(ele.getTextContent());
						}
						else if ("maximum_date_match".equals(tag)){
							maxDateDifference = Integer.parseInt(ele.getTextContent());
						}
						else if ("time_between_rss_checks_ms".equals(tag)){
							timeBetweenRssChecksMs = Long.parseLong(ele.getTextContent());
						}
						else if ("threshold_score_for_match".equals(tag)){
							thresholdScoreForMatch = Double.parseDouble(ele.getTextContent());
						}
						else if ("date_scorer_coe".equals(tag)){
							dateScorerCoefficient = Double.parseDouble(ele.getTextContent());
						}
						else if ("quotations_scorer_coe".equals(tag)){
							quotationsScorerCoefficient = Double.parseDouble(ele.getTextContent());
						}
						else if ("keyword_scorer_coe".equals(tag)){
							keywordScorerCoefficient = Double.parseDouble(ele.getTextContent());
						}
						else if ("rss_feeds".equals(tag)){
							String temp = ele.getTextContent();
							//the urls should be seperated by new line characters
							rssFeeds = temp.split("\n");
						}
					}
					else{
						throw new IllegalStateException("Parsing " + tag + " failed");
					}
				}
				else{
					throw new IllegalStateException("Could not find preference " + tag + " in xml file");
				}
			}
			
		} 
		catch (ParserConfigurationException e){
			e.printStackTrace();
		}
		catch (SAXException e){
			e.printStackTrace();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public double getEditDistanceCoefficient(){
		return editDistanceCoefficient;
	}

	@Override
	public double getLongestCommonSubsequenceCoefficient(){
		return longestCommonSubsequenceCoefficient;
	}
	
	@Override
	public String getErrorLogLocation(){
		return errorLogLocation;
	}
	
	@Override
	public int getMaximumDateMatch() {
		return maxDateDifference;
	}

	@Override
	public String getDatabaseHost() {
		return databaseHost;
	}

	@Override
	public String getDatabaseUsername() {
		return databaseUsername;
	}

	@Override
	public String getDatabasePassword() {
		return databasePassword;
	}
	
	@Override
	public String[] getRssFeeds() {
		return rssFeeds;
	}

	@Override
	public long getTimeBetweenRssChecksMs() {
		return timeBetweenRssChecksMs;
	}
	
	@Override
	public double getThresholdScoreForMatch() {
		return thresholdScoreForMatch;
	}

	@Override
	public double getDateScorerCoefficient() {
		return dateScorerCoefficient;
	}

	@Override
	public double getQuotationsScorerCoefficient() {
		return quotationsScorerCoefficient;
	}

	@Override
	public double getKeywordScorerCoefficient() {
		return keywordScorerCoefficient;
	}
	
	/**
	 * Retrieves the singleton instance of this class.
	 * @return The singleton instance of this class.
	 */
	public static IPreferences getInstance(){
		if (instance == null){
			instance = new XMLPreferences();
		}
		return instance;
	}

}
