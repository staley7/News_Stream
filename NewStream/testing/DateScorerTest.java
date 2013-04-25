package testing;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import database.Article;

import analysis.DateScorer;


/**
 * Unit test for the DateScorer class
 * @author Jamison Bradley
 */
public class DateScorerTest {
	
	
	@Test
	//test various possible time differences between article publications to see if they are being scored correclty
	public void scoringTest(){
		//setting an epsilon value since comparison of real values will be needed
		double epsilon = 0.001;
		
		long currentTime = System.currentTimeMillis();
		long oneHour = 1000 * 3600;
		Article aOne = new Article();
		aOne.setDate(new Date(currentTime));
		
		DateScorer scorer = new DateScorer();
		//article posted a hour later
		Article aTwo = new Article();
		aTwo.setDate(new Date(currentTime + oneHour));
		double score = scorer.score(aOne, aTwo);
		//diff is using hand calulated values for the hardcoded number
		double diff = Math.abs((100 * 0.994047) - score);
		boolean test = diff < epsilon;
		assertEquals(test, true);
		
		//article posted a day later
		aTwo.setDate(new Date(currentTime + (24 * oneHour)));
		score = scorer.score(aOne, aTwo);
		diff = Math.abs((100 * 0.857143) - score);
		test = diff < epsilon;
		assertEquals(test, true);
		
		//article posted 107 hours later
		aTwo.setDate(new Date(currentTime + (107 * oneHour)));
		score = scorer.score(aOne, aTwo);
		diff = Math.abs((100 * 0.363095) - score);
		test = diff < epsilon;
		assertEquals(test, true);
	}
}
