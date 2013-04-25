package analysis;

import java.util.HashSet;
import java.util.Set;

import database.Article;

/**
 * Implementation of the IScorer interface that scores how similar two Article's keywords are.
 * @author Jamison Bradley
 */
public class KeywordScorer implements IScorer {

	@Override
	public double score(Article aOne, Article aTwo){
		if (aOne.getKeywords() == null || aTwo.getKeywords() == null){
			throw new IllegalArgumentException("One of the articles that was provided has a keyword list that is null.");
		}
		
		int totalCounter = 0;
		Set<String> hashOne = new HashSet<String>();
		//add all of aOne's keywords to the hash
		for (String keyword: aOne.getKeywords()){
			if (hashOne.add(keyword)){
				totalCounter++;
			}
		}
		
		Set<String> hashTwo = new HashSet<String>();
		//add all of aTwo's keywords to a hash as well, this protects against a possible scenario where there are accidently repeats.
		for (String keyword: aTwo.getKeywords()){
			if (hashTwo.add(keyword)){
				totalCounter++;
			}
		}
		
		//run through aTwo's list of keywords and count the number of matches
		int matchCounter = 0;
		for (String keyword: hashTwo){
			if (hashOne.contains(keyword)){
				matchCounter++;
			}
		}
		
		//100 * (numberOfKeywordsWithAMatch / totalKeywords)
		return 100 * ( (((double) matchCounter) * 2) / ((double) totalCounter) );
	}

}
