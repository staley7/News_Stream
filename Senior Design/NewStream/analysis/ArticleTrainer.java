package analysis;

import java.util.ArrayList;
import java.util.List;

import manager.Manager;
import manager.TestingPreferences;
import parsing.SourceParser;
import utility.Source;
import database.Article;
import database.StoryID;

public class ArticleTrainer {
	public static void main(String[] args){
		List<Article> articles = new ArrayList<Article>();
		SourceParser parser = new SourceParser();
		Article article = parser.parse(new Source("http://www.cnn.com/2013/04/18/us/texas-explosion/index.html?hpt=hp_c3"));
		article.setStoryID(new StoryID(0));
		articles.add(article);
		article = parser.parse(new Source("http://www.bbc.co.uk/news/world-us-canada-22204391"));
		article.setStoryID(new StoryID(0));
		articles.add(article);
		article = parser.parse(new Source("http://www.guardian.co.uk/world/2013/apr/18/texas-fertiliser-plant-explosion-devastates-town"));
		article.setStoryID(new StoryID(0));
		articles.add(article);
		article = parser.parse(new Source("http://www.guardian.co.uk/world/2013/apr/18/pervez-musharraf-flees-court-avoids-arrest"));
		article.setStoryID(new StoryID(1));
		articles.add(article);
		article = parser.parse(new Source("http://www.bbc.co.uk/news/world-us-canada-22210035"));
		article.setStoryID(new StoryID(2));
		articles.add(article);
		article = parser.parse(new Source("http://www.cnn.com/2013/04/18/politics/tainted-letter-intercepted/index.html?hpt=hp_t3"));
		article.setStoryID(new StoryID(3));
		articles.add(article);
		article = parser.parse(new Source("http://www.cnn.com/2013/04/18/us/boston-blasts/index.html?hpt=hp_t1"));
		article.setStoryID(new StoryID(4));
		articles.add(article);
		article = parser.parse(new Source("http://www.bbc.co.uk/news/world-us-canada-22211190"));
		article.setStoryID(new StoryID(4));
		articles.add(article);
		article = parser.parse(new Source("http://www.guardian.co.uk/world/2013/apr/18/boston-bombing-fbi-images-suspects-live"));
		article.setStoryID(new StoryID(4));
		articles.add(article);
		
		TestingPreferences trainerPrefs = new TestingPreferences();
		Manager.setPreferences(trainerPrefs);
		ArticleAggregator scorer = new ArticleAggregator();
		double quotationCoe = 0.5;
		double keywordCoe = 0.5;
		double bestQuotationCoe = 0;
		double bestKeywordCoe = 1;
		//should be high
		double matchPointsBest = 0;
		//should be low
		double penaltyPointsBest = Double.MAX_VALUE;
		for (int i = 0; i < 100; i++){
			//find the best possible coefficients goal is to maximize good points minimize bad points
			trainerPrefs.setKeywordScorerCoefficient(keywordCoe + ((i / 10) * .1));
			trainerPrefs.setQuotationsScorerCoefficient(quotationCoe + ((i % 10) * .1));
			double matchPoints = 0;
			double penaltyPoints = 0;
			for (int j = 0; j < articles.size(); j++){
				Article aOne = articles.get(j);
				for (int k = j + 1; k < articles.size(); k++){
					double threshold = trainerPrefs.getThresholdScoreForMatch();
					Article aTwo = articles.get(k);
					double score = scorer.score(aOne, aTwo);
					if (aOne.getStoryID().getID() == aTwo.getStoryID().getID()){
						//these articles are suppose to match so add points above the threshold or if it is below threshold subtract points
						if (score >= threshold){
							matchPoints += score - threshold;
						}
						//penalize since missed matches is a big deal
						else{
							penaltyPoints += 5 * (threshold - score);
						}
					}
					else{
						//articles aren't suppose to match so add points when below threshold penalize when above
						if (score < threshold){
							matchPoints += threshold - score;
						}
						//penalize since missed matches is a big deal
						else{
							penaltyPoints += 5 * (score - threshold);
						}
					}
				}
			}
			System.out.println();
			System.out.println("quotationsCoe = " + trainerPrefs.getQuotationsScorerCoefficient());
			System.out.println("keywordCoe = " + trainerPrefs.getKeywordScorerCoefficient());
			System.out.println("match points = " + matchPoints);
			System.out.println("penalty points = " + penaltyPoints);
			
			double matchDif = matchPoints - matchPointsBest;
			double penaltyDif = penaltyPointsBest - penaltyPoints;
			if ((matchDif + penaltyDif) > 0){
				matchPointsBest = matchPoints;
				penaltyPointsBest = penaltyPoints;
				bestQuotationCoe = trainerPrefs.getQuotationsScorerCoefficient();
				bestKeywordCoe = trainerPrefs.getKeywordScorerCoefficient();
			}
		}
		
		System.out.println("\n\n\n");
		System.out.println("best quotationsCoe = " + bestQuotationCoe);
		System.out.println("best keywordCoe = " + bestKeywordCoe);
		System.out.println("match points = " + matchPointsBest);
		System.out.println("penalty points = " + penaltyPointsBest);
	}
}
