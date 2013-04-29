package manager;

/**
 * Provides an interface of what preferences are needed for this program to run correctly.
 * @author Jamison Bradley
 */
public interface IPreferences {
	
	/**
	 * Gets the edit distance coefficient from stored preferences.
	 * @return Edit distance coefficient used for QuotationsScorer class to weight quotes comparison.
	 */
	public double getEditDistanceCoefficient();
	
	/**
	 * Gets the longest common subsequence coefficient.
	 * @return Longest common subsequence coefficient used for the QuotationsScorer class to weight quotes comparison.
	 */
	public double getLongestCommonSubsequenceCoefficient();
	
	/**
	 * Gets the path for where error messages should be logged to.
	 * @return Path to file for error logging.
	 */
	public String getErrorLogLocation();

	/**
	 * Returns the host that the database is currently being hosted on
	 * @return Location of the database host.
	 */
	public String getDatabaseHost();
	
	/**
	 * Gets the username that should be used when connecting to the database.
	 * @return The username for the database connection.
	 */
	public String getDatabaseUsername();
	
	/**
	 * Gets the password that should be used when connecting to the database.
	 * @return The password for the database connection.
	 */
	public String getDatabasePassword();
	
	/**
	 * The maximum amount of time in days that two Articles can be separated by to be considered part of the same Story.
	 * @return Max time that two Articles can be separated by to be part of same Story.
	 */
	public int getMaximumDateMatch();
	
	/**
	 * Gets the rss feeds that are to be monitored by this program.
	 * @return The rss feeds that are to be monitored.
	 */
	public String[] getRssFeeds();
	
	/**
	 * Gets the amount of time in milliseconds that should elapse between checking the rss feeds that are being monitored.
	 * @return Amount of time in milliseconds between rss feed checks.
	 */
	public long getTimeBetweenRssChecksMs();
	
	/**
	 * Gets the minimum value that must be achieved by an Article when being matched with a Story in ArticleAggregator in order
	 * for the match to be considered legitimate.
	 * @return Minimum score needed in ArticleAggregator for Article to be considered a match with a Story.
	 */
	public double getThresholdScoreForMatch();
	
	/**
	 * Gets the coefficient that modifies the score returned by the DateScorer object in ArticleAggregator.
	 * @return The coefficient that modifies the score returned by the DateScorer object in ArticleAggregator.
	 */
	public double getDateScorerCoefficient();
	
	/**
	 * Gets the coefficient that modifies the score returned by the QuotationsScorer object in ArticleAggregator.
	 * @return The coefficient that modifies the score returned by the QuotationsScorer object in ArticleAggregator.
	 */
	public double getQuotationsScorerCoefficient();
	
	/**
	 * Get the coefficient that modifies the score returned by the QuotationsScorer object in ArticleAggregator.
	 * @return The coefficient that modifies the score returned by the KeywordScorer object in ArticleAggregator.
	 */
	public double getKeywordScorerCoefficient();
}
