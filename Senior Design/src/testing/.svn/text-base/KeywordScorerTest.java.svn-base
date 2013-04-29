package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import analysis.KeywordScorer;

import database.Article;

public class KeywordScorerTest {

	@Test
	public void scoringTest(){
		KeywordScorer scorer = new KeywordScorer();
		//setting an epsilon value since comparison of real values will be needed
		double epsilon = 0.001;
		//no matches
		double resultOne = 0;
		//all matches
		double resultTwo = 100;
		//list one 7 words list two 4 words 3 matches
		double resultThree = 54.545454;
		
		List<String> listOne = new ArrayList<String>();
		listOne.add("ackley");
		listOne.add("britt");
		listOne.add("corning");
		
		List<String> listTwo = new ArrayList<String>();
		listTwo.add("alden");
		listTwo.add("bradford");
		listTwo.add("carroll");
		
		Article aOne = new Article();
		aOne.setKeywords(listOne);
		Article aTwo = new Article();
		aTwo.setKeywords(listTwo);
		
		//no matches in the two lists
		boolean testOne = (resultOne - scorer.score(aOne, aTwo)) < epsilon;
		assertEquals(true, testOne);
		
		listOne = new ArrayList<String>();
		listOne.add("alden");
		listOne.add("carroll");
		listOne.add("bradford");
		listOne.add("dows");
		listOne.add("eldora");
		listTwo.add("dows");
		listTwo.add("eldora");
		aOne.setKeywords(listOne);
		
		//all matches in the two lists
		boolean testTwo = (resultTwo - scorer.score(aOne, aTwo)) < epsilon;
		assertEquals(true, testTwo);
		
		listOne.add("fort dodge");
		listOne.add("granger");
		listTwo = new ArrayList<String>();
		listTwo.add("hampton");
		listTwo.add("alden");
		listTwo.add("fort dodge");
		listTwo.add("dows");
		aTwo.setKeywords(listTwo);
		
		//7 and 4 for words 3 matches
		boolean testThree = (resultThree - scorer.score(aOne, aTwo)) < epsilon;
		assertEquals(true, testThree);
		
		try{
			aOne = new Article();
			scorer.score(aOne, aTwo);
			//should throw exception due to null keywords and this code should never execute
			assertEquals(true, false);
		}
		catch (IllegalArgumentException e){
			//do nothing was suppose to be caught
		}
	}
}
