package analysis;

import database.Article;

/**
 * @author Jamison Bradley
 */
public interface IScorer {
	
	/**
	 * Returns a score of similarity between two Articles, the score must be between 0 and 100 with 0 representing worst possible
	 * similarity between the two Articles and 100 being the best similarity between the two Articles.
	 * @param aOne is the first Article being compared.
	 * @param aTwo is the second Article being compared.
	 * @return A score between 0 and 100 with 0 being lowest similarity and 100 being best.
	 */
	public double score(Article aOne, Article aTwo);
	
}
