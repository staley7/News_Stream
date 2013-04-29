package analysis;
import java.util.ArrayList;
import java.util.List;

import manager.Manager;
import manager.TestingPreferences;

import parsing.SourceParser;

import database.Article;
import database.StoryID;

import utility.Source;



public class QuotationsTrainer {
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
		QuotationsScorer scorer = new QuotationsScorer();
		double editDistCoe = 0;
		double substringCoe = 1;
		double bestEditDistCoe = 0;
		double bestSubstringCoe = 1;
		//should be high
		double goodPointsBest = 0;
		//should be low
		double badPointsBest = Double.MAX_VALUE;
		for (int i = 0; i < 100; i++){
			//find the best possible coefficients goal is to maximize good points minimize bad points
			trainerPrefs.setEditDistanceCoefficient(editDistCoe + (i * .01));
			trainerPrefs.setLongestCommonSubstringCoefficient(substringCoe - (i * .01));
			double goodPoints = 0;
			double badPoints = 0;
			for (int j = 0; j < articles.size(); j++){
				Article aOne = articles.get(j);
				for (int k = j + 1; k < articles.size(); k++){
					Article aTwo = articles.get(k);
					double score = scorer.score(aOne, aTwo);
					if (aOne.getStoryID().getID() == aTwo.getStoryID().getID()){
						goodPoints += score;
					}
					else{
						badPoints += score;
					}
				}
			}
			System.out.println();
			System.out.println("editDistCoe = " + trainerPrefs.getEditDistanceCoefficient());
			System.out.println("substringCoe = " + trainerPrefs.getLongestCommonSubsequenceCoefficient());
			System.out.println("good points = " + goodPoints);
			System.out.println("bad points = " + badPoints);
			
			double goodDif = goodPoints - goodPointsBest;
			double badDif = badPointsBest - badPoints;
			if ((goodDif + badDif) > 0){
				goodPointsBest = goodPoints;
				badPointsBest = badPoints;
				bestEditDistCoe = trainerPrefs.getEditDistanceCoefficient();
				bestSubstringCoe = trainerPrefs.getLongestCommonSubsequenceCoefficient();
			}
		}
		
		System.out.println("\n\n\n");
		System.out.println("best editCoe = " + bestEditDistCoe);
		System.out.println("best substringCoe = " + bestSubstringCoe);
		System.out.println("good points = " + goodPointsBest);
		System.out.println("bad points = " + badPointsBest);
	}
	
}
