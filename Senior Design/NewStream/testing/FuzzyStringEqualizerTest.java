package testing;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import analysis.FuzzyStringEqualizer;


/**
 * Unit test for FuzzyStringEqualizer class
 * @author Jamison Bradley
 */
public class FuzzyStringEqualizerTest {

	@Test
	//test the longest common substring code using Strings
	public void longestCommonSubstringWordsTest(){
		String sOne = "Jack be nimble Jack be quick Jack jumped over a candle stick";
		String sTwo = "Random text random random Jack jumped over a candle stick more random text";
		FuzzyStringEqualizer<String> fuzzy = new FuzzyStringEqualizer<String>();
		List<String> list = fuzzy.getLongestCommonSubsequence(sOne.split(" "), sTwo.split(" "));
		assertEquals(list.size(), 6);
		assertEquals(list.get(0), "Jack");
		assertEquals(list.get(1), "jumped");
		assertEquals(list.get(2), "over");
		assertEquals(list.get(3), "a");
		assertEquals(list.get(4), "candle");
		assertEquals(list.get(5), "stick");
	}
	
	@Test
	//test with chars and have the pattern not be in sequential order
	public void longestCommonSubstringCharTest(){
		Character listOne[] = {'y', 'b', 'c', 'z', 'm', 'c', 'a', 's'};
		Character listTwo[] = {'y', 'a', 'm', 'e', 'f', 'b', 'c', 'a'};
		FuzzyStringEqualizer<Character> fuzzy = new FuzzyStringEqualizer<Character>();
		List<Character> list = fuzzy.getLongestCommonSubsequence(listOne, listTwo);
		assertEquals(list.size(), 4);
		assertEquals(list.get(0).equals('y'), true);
		assertEquals(list.get(1).equals('m'), true);
		assertEquals(list.get(2).equals('c'), true);
		assertEquals(list.get(3).equals('a'), true);
	}
	
	@Test
	public void editDistanceTest(){
		
	}
}
