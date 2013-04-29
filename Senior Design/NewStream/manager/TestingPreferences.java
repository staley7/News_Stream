package manager;

public class TestingPreferences implements IPreferences {
	private IPreferences pref;
	private double editCoe;
	private double substringCoe;
	private double keywordCoe;
	private double quotationsCoe;
	
	public TestingPreferences(){
		editCoe = -1;
		substringCoe = -1;
		keywordCoe = -1;
		quotationsCoe = -1;
		
		pref = Manager.getPreferences();
		editCoe = pref.getEditDistanceCoefficient();
		substringCoe = pref.getLongestCommonSubsequenceCoefficient();
	}
	
	/**
	 * Set edit distance coefficient to a new value. Or to a negative value to set it back to default value.
	 * @param editCoe new coefficient or a negative value to take the default value.
	 */
	public void setEditDistanceCoefficient(double editCoe){
		this.editCoe = editCoe;
	}
	
	@Override
	public double getEditDistanceCoefficient(){
		if (editCoe > 0){
			return editCoe;
		}
		return pref.getEditDistanceCoefficient();
	}

	/**
	 * Set longest common substring coefficient to a new value. Or to a negative value to set it back to default value.
	 * @param substringCoe new coefficient or a negative value to take the default value.
	 */
	public void setLongestCommonSubstringCoefficient(double substringCoe){
		this.substringCoe = substringCoe;
	}
	
	@Override
	public double getLongestCommonSubsequenceCoefficient() {
		if (substringCoe > 0){
			return substringCoe;
		}
		return pref.getLongestCommonSubsequenceCoefficient();
	}

	@Override
	public String getErrorLogLocation() {
		return pref.getErrorLogLocation();
	}

	@Override
	public String getDatabaseHost() {
		return pref.getDatabaseHost();
	}

	@Override
	public String getDatabaseUsername() {
		return pref.getDatabaseUsername();
	}

	@Override
	public String getDatabasePassword() {
		return pref.getDatabasePassword();
	}

	@Override
	public int getMaximumDateMatch() {
		return pref.getMaximumDateMatch();
	}

	@Override
	public String[] getRssFeeds() {
		return pref.getRssFeeds();
	}

	@Override
	public long getTimeBetweenRssChecksMs() {
		return pref.getTimeBetweenRssChecksMs();
	}

	@Override
	public double getThresholdScoreForMatch() {
		return pref.getThresholdScoreForMatch();
	}

	@Override
	public double getDateScorerCoefficient() {
		return pref.getDateScorerCoefficient();
	}
	
	/**
	 * Set quotations scorer coefficient to a new value. Or to a negative value to set it back to default value.
	 * @param substringCoe new coefficient or a negative value to take the default value.
	 */
	public void setQuotationsScorerCoefficient(double value){
		quotationsCoe = value;
	}
	
	@Override
	public double getQuotationsScorerCoefficient() {
		if (quotationsCoe > 0){
			return quotationsCoe;
		}
		return pref.getQuotationsScorerCoefficient();
	}

	/**
	 * Set keyword scorer coefficient to a new value. Or to a negative value to set it back to default value.
	 * @param substringCoe new coefficient or a negative value to take the default value.
	 */
	public void setKeywordScorerCoefficient(double value){
		keywordCoe = value;
	}
	
	@Override
	public double getKeywordScorerCoefficient(){
		if (keywordCoe > 0){
			return keywordCoe;
		}
		return pref.getKeywordScorerCoefficient();
	}
}
