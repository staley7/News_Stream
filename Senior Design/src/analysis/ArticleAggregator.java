package analysis;

import java.util.Date;
import java.util.List;

import manager.IDatabase;
import manager.Manager;
import manager.IPreferences;
import database.Article;
import database.Story;
import database.StoryID;

public class ArticleAggregator {
	
	private IScorer dateScorer;
	private IScorer quotationsScorer;
	private IScorer keywordScorer;
	
	public ArticleAggregator(){
		dateScorer = new DateScorer();
		quotationsScorer = new QuotationsScorer();
		keywordScorer = new KeywordScorer();
	}
	
	/**
	 * Returns the story in the database that an article matches to, or null if it doesn't match any of the stories in the database.
	 * There will be a certain threshold for matching that must be meet if it isn't meet than null is returned.
	 * @param article that is to be matched to a story.
	 * @return The Story from the database that the Article matches to best or null if there is no match in the database.
	 */
	public Story matchArticle(Article article){
		IDatabase db = Manager.getDatabase();
		IPreferences pref = Manager.getPreferences();
		
		int days = pref.getMaximumDateMatch();
		//days * secondsInHour * hoursInDay * 1000(to get to ms)
		long timeDif = days * 3600 * 24 * 1000;
		long middle = article.getDate().getTime();
		long start = middle - timeDif;
		long end = middle + timeDif;
		
		List<Story> stories = db.getStoriesByDate(start, end);
		Story bestStory = null;
		double bestScore = 0;
		if (stories.size() > 0){
			for (Story story: stories){
				List<Article> articlesInStory = db.getArticlesInStory(story);
				double score = 0;
				for (Article a: articlesInStory){
//					System.out.println("---------------------------------------------------------");
//					System.out.println("article = " + article.getTitle());
//					System.out.println("story article = " + story.getTitle());
					score += score(article, a);
//					System.out.println("total score = " + score);
				}
				double scoreAvg = score / articlesInStory.size();
				if (scoreAvg > bestScore){
					bestScore = scoreAvg;
					bestStory = story;
				}
			}
		}
		
		if (bestScore > pref.getThresholdScoreForMatch()){
			return bestStory;
		}
		//the default return if there are either no matches in the database or no Stories at all in the database.
		return null;
	}
	
	public double score(Article aOne, Article aTwo){
		IPreferences pref = Manager.getPreferences();
		double dateScore = 0;
		double quoteScore = 0;
		double keywordScore = 0;
		
		//if neither the quote or keyword lists are empty
		if (aOne.getKeywords().size() > 0 && aOne.getQuotes().size() > 0 && aTwo.getKeywords().size() > 0 && aTwo.getQuotes().size() > 0){
			dateScore = pref.getDateScorerCoefficient() * dateScorer.score(aOne, aTwo);
			quoteScore = pref.getQuotationsScorerCoefficient() * quotationsScorer.score(aOne, aTwo);
			keywordScore = pref.getKeywordScorerCoefficient() * keywordScorer.score(aOne, aTwo);
		}
		//if one or both happen to have empty quotes list than just dateScorer and keywordScorer should be used
		//shouldn't have to ever worry about quotes being empty I enforce that keywords being empty isn't allowed in the parsers
		if (aOne.getQuotes().size() == 0 && aTwo.getQuotes().size() == 0){
			dateScore = pref.getDateScorerCoefficient() * dateScorer.score(aOne, aTwo);
			keywordScore = (pref.getKeywordScorerCoefficient() + pref.getQuotationsScorerCoefficient()) * keywordScorer.score(aOne, aTwo);
		}
		
//		System.out.println("date score = " + dateScore);
//		System.out.println("quote score = " + quoteScore);
//		System.out.println("keyword score = " + keywordScore);
		return dateScore + quoteScore + keywordScore;
	}
	
}
