package analysis;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Provides for comparision when two Objects aren't necessarily exactly equal. Provides Edit Distance (Levenshtein Distance) and Longest Common
 * Substring algorithms.
 * @author Jamison Bradley
 * @param <E> 
 */
public class FuzzyStringEqualizer<E> {
	
	/**
	 * Solves the Levenshtein Distance/edit distance between two arrays see http://www.levenshtein.net/ for algorithm used.
	 * The value returned is how many insertion, deletions and substitutions would be required to make the two Strings equal.
	 * @param one the first array that is to be compared.
	 * @param two the second array that is to be compared.
	 * @return Levenshtein Distance
	 */
	public int getLevenshteinDistance(E[] one, E[] two){
		int arr[][] = new int[one.length + 1][two.length + 1];
		//fill in first row and column that are being compared to an empty array so their value is just the length of array at that point
		for (int i = 0; i < arr.length; i++){
			arr[i][0] = i;
		}
		//0,0 has already been filled in so can start with i = 1
		for (int i = 1; i < arr[0].length; i++){
			arr[0][i] = i;
		}
		
		int minLength = one.length;
		if (minLength > two.length){
			minLength = two.length;
		}
		
		for (int i = 1; i <= minLength; i++){
			int cost = 0;
			//see if the char in row i and column i aren't the same cost should be 1
			if (!one[i - 1].equals(two[i - 1])){
				cost = 1;
			}
			//calculate possible values that could be put into square
			int diagonal = arr[i - 1][i - 1] + cost;
			int above = arr[i - 1][i] + 1;
			int left = arr[i][i - 1] + 1;
			arr[i][i] = minimum(diagonal, above, left);
			
			//fill in rest of row
			for (int j = i + 1; j < arr[0].length; j++){
				cost = 0;
				if (!one[i - 1].equals(two[j - 1])){
					cost = 1;
				}
				diagonal = arr[i - 1][j - 1] + cost;
				above = arr[i - 1][j] + 1;
				left = arr[i][j - 1] + 1;
				arr[i][j] = minimum(diagonal, above, left);
			}
			
			//fill in rest of column
			for (int j = i + 1; j < arr.length; j++){
				cost = 0;
				if (!one[j - 1].equals(two[i - 1])){
					cost = 1;
				}
				diagonal = arr[j - 1][i - 1] + cost;
				above = arr[j - 1][i] + 1;
				left = arr[j][i - 1] + 1;
				arr[j][i] = minimum(diagonal, above, left);
			}
		}
		
		return arr[one.length][two.length];
	}
	
	/**
	 * Solves the longest common subsequence between two arrays of objects.
	 * @param one array of objects that is being searched.
	 * @param two array of objects that is being searched.
	 * @return A List containing the sequence of objects that form the longest common subsequence.
	 */
	public List<E> getLongestCommonSubsequence(E[] one, E[] two){
		List<E> ret = new ArrayList<E>();
		//make the dynamic array
		int arr[][] = new int[one.length + 1][two.length + 1];
		
		int minLength = arr.length;
		if (minLength > arr[0].length){
			minLength = arr[0].length;
		}
		
		for (int i = 1; i < minLength; i++){
			int diagonal = arr[i - 1][i - 1];
			int left = arr[i][i - 1];
			int above = arr[i - 1][i];
			if (one[i - 1].equals(two[i - 1])){
				arr[i][i] = lcsValue(true, diagonal, left, above);
			}
			else{
				arr[i][i] = lcsValue(false, diagonal, left, above);
			}
			
			//fill in rest of column
			for (int j = i + 1; j < arr.length; j++){
				diagonal = arr[j - 1][i - 1];
				left = arr[j][i - 1];
				above = arr[j - 1][i];
				if (one[j - 1].equals(two[i - 1])){
					arr[j][i] = lcsValue(true, diagonal, left, above);
				}
				else{
					arr[j][i] = lcsValue(false, diagonal, left, above);
				}
			}
			//fill in rest of row
			for (int j = i + 1; j < arr[0].length; j++){
				diagonal = arr[i - 1][j - 1];
				left = arr[i][j - 1];
				above = arr[i - 1][j];
				if (two[j - 1].equals(one[i - 1])){
					arr[i][j] = lcsValue(true, diagonal, left, above);
				}
				else{
					arr[i][j] = lcsValue(false, diagonal, left, above);
				}
			}
			
		}
		
//		//printing out pretty formatted grid
//		int WHITE_SPACE = 11;
//		String two_out[] = new String[two.length];
//		for (int i = 0; i < two.length; i++){
//			two_out[i] = two[i].toString();
//		}
//		for (int i = 0; i < WHITE_SPACE; i++){
//			for (int j = 0; j < WHITE_SPACE + 2; j++){
//				System.out.print(" ");
//			}
//			for (int j = 0; j < two_out.length; j++){
//				if (two_out[j].length() > i){
//					System.out.print(two_out[j].charAt(i) + " ");
//				}
//				else{
//					System.out.print("  ");
//				}
//			}
//			System.out.println();
//		}
//		for (int i = 0; i < arr.length; i++){
//			if (i != 0){
//				String out = one[i - 1].toString();
//				System.out.print(out);
//				for (int k = 0; k < WHITE_SPACE - out.length(); k++){
//					System.out.print(" ");
//				}
//			}
//			else{
//				for (int k = 0; k < WHITE_SPACE; k++){
//					System.out.print(" ");
//				}
//			}
//			for (int j = 0; j < arr[0].length; j++){
//				System.out.print(arr[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		//backtracking through the matrix to pick out the longest common sub sequence
		Stack<E> stack = new Stack<E>();
		int i = arr.length - 1;
		int j = arr[0].length - 1;
		while (i != 0 && j != 0){
			//if they are equal push the value onto stack and then move up and to the left (diagonal)
			if (one[i - 1].equals(two[j - 1])){
				stack.push(one[i - 1]);
				i--;
				j--;
			}
			//if not equal check the spot above and to the left and choose the larger value
			else{
				if (arr[i - 1][j] >= arr[i][j - 1]){
					i--;
				}
				else{
					j--;
				}
			}
		}
		while (!stack.isEmpty()){
			ret.add(stack.pop());
		}
		
		return ret;
	}
	
	/**
	 * Solves for the minimum integer among the given values.
	 * @param values array of integers from which the minimum is to be chosen.
	 * @return The smallest value in values.
	 */
	private int minimum(int... values){
		if (values.length <= 0){
			throw new IllegalArgumentException("no values in array");
		}
		int min = values[0];
		for (Integer value: values){
			if (value < min){
				min = value;
			}
		}
		return min;
	}
	
	/**
	 * Solves for the maximum integer among the given values.
	 * @param values array of integers from which the maximum is to be chosen.
	 * @return The largest value in values.
	 */
	private int maximum(int... values){
		if (values.length <= 0){
			throw new IllegalArgumentException("no values in array");
		}
		int max = values[0];
		for (Integer value: values){
			if (value > max){
				max = value;
			}
		}
		return max;
	}
	
	/**
	 * Determines what the value of a cell should be for the longest common substring algorithm.
	 * @param equal if the values being compared in a given cell are equal.
	 * @param values the value of the cell to the left, above and diagonal (left and above).
	 * @return The value that should be inserted into the cell in question.
	 */
	private int lcsValue(boolean equal, int... values){
		int ret;
		if (!equal){
			ret = maximum(values);
		}
		else{
			ret = maximum(values) + 1;
			//this accounts for situations where repeat values can throw off the matrix
			for (int i = 0; i < values.length; i++){
				if (ret - values[i] == 2){
					ret--;
					break;
				}
			}
		}
		return ret;
	}
	
//	public static void main(String[] args){
//		String one = "It's a shame the manager's not here. He might know more than me but as far as we are concerned I think he is still a Manchester City player.";
////		String two = "I saw that speculation yesterday. It's a shame Robbie's [Roberto Mancini] not here. He might know more than me but he [Balotelli] is still a Manchester City player. I don't think anything is going to happen.";
//		String two = "He was in the squad for the weekend [at Stoke City], it was only a 24-hour virus he had so he'll be up for selection.";
////		String one = "sitting";
////		String two = "kitten";
//		Character arrOne[] = new Character[one.length()];
//		Character arrTwo[] = new Character[two.length()];
//		for (int i = 0; i < arrOne.length; i++){
//			arrOne[i] = one.charAt(i);
//		}
//		for (int i = 0; i < arrTwo.length; i++){
//			arrTwo[i] = two.charAt(i);
//		}
//		
//		int diff = one.length() - two.length();
//		if (diff < 0){
//			diff *= -1;
//		}
//		
//		FuzzyStringEqualizer<Character> fuzzy = new FuzzyStringEqualizer<Character>();
//		System.out.println(fuzzy.getLevenshteinDistance(arrOne, arrTwo));
//		
//		String strOne[] = one.split(" ");
//		String strTwo[] = two.split(" ");
//		FuzzyStringEqualizer<String> fuzzy2 = new FuzzyStringEqualizer<String>();
//		System.out.println(fuzzy2.getLevenshteinDistance(strOne, strTwo));
//	}
	
	public static void main(String args[]){
//		String one = "It's a shame the manager's not here. He might know more than me but as far as we are concerned I think he is still a Manchester City player.";
//		String two = "I saw that speculation yesterday. It's a shame Robbie's [Roberto Mancini] not here. He might know more than me but he [Balotelli] is still a Manchester City player. I don't think anything is going to happen.";
////		String two = "He was in the squad for the weekend [at Stoke City], it was only a 24-hour virus he had so he'll be up for selection.";
//		
//		FuzzyStringEqualizer<String> fuzzy = new FuzzyStringEqualizer<String>();
//		System.out.println(fuzzy.getLongestCommonSubsequence(one.split(" "), two.split(" ")));
		
		String one = "xmjyauz";
		String two = "mzjawxu";
		Character arrOne[] = new Character[one.length()];
		Character arrTwo[] = new Character[two.length()];
		
		for (int i = 0; i < one.length(); i++){
			arrOne[i] = one.charAt(i);
		}
		for (int i = 0; i < two.length(); i++){
			arrTwo[i] = two.charAt(i);
		}
		FuzzyStringEqualizer<Character> fuzzy = new FuzzyStringEqualizer<Character>();
		System.out.println(fuzzy.getLongestCommonSubsequence(arrOne, arrTwo));
	}
}
