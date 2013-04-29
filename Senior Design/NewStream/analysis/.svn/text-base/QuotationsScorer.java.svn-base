package analysis;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import database.Article;

import manager.Manager;
import manager.IPreferences;

import utility.Pair;


public class QuotationsScorer implements IScorer {
	
	private static QuotationsScorer qs;
	
	public QuotationsScorer(){
		
	}
	
	public double score(Article aOne, Article aTwo){
		List<String> quotesOne = aOne.getQuotes();
		List<String> quotesTwo = aTwo.getQuotes();
		
		IPreferences prefs = Manager.getPreferences();
		double arr[][] = new double[quotesOne.size()][quotesTwo.size()];
		FuzzyStringEqualizer<String> fuzzy = new FuzzyStringEqualizer<String>();
		for (int i = 0; i < quotesOne.size(); i++){
			for (int j = 0; j < quotesTwo.size(); j++){
				String arrOne[] = quotesOne.get(i).split(" ");
				String arrTwo[] = quotesTwo.get(j).split(" ");
				double editDist = fuzzy.getLevenshteinDistance(arrOne, arrTwo);
				double lcs = fuzzy.getLongestCommonSubsequence(arrOne, arrTwo).size();
				//both equations being used result in score of 1 for best possible score and 0 for worst possible score
				//editDistRatio = 1 - (editDist / largest possible edit distance for two strings of given size)
				double lengthDiff = arrOne.length - arrTwo.length;
				if (lengthDiff < 0){
					lengthDiff *= -1;
				}
				double shorter = arrOne.length;
				if (arrTwo.length < shorter){
					shorter = arrTwo.length;
				}
				//editDist / (# String needed to be deleted in worst case + # Strings needed to be changed in worst case)
				double editDistRatio = 1 - (editDist / (lengthDiff + shorter));
				
				//lcsRatio = lcs / average size of two arrays
				double averageSize = (arrOne.length + arrTwo.length) / 2;
				double lcsRatio = lcs / averageSize;
				//use coefficients to determine how to weight value put into array, 2 is best possible score 0 is worst possible
				arr[i][j] = prefs.getEditDistanceCoefficient() * editDistRatio + prefs.getLongestCommonSubsequenceCoefficient() * lcsRatio;
			}
		}
		return calculateScore(arr) * 100;
	}
	
	private double calculateScore(double[][] arr){
		if (arr.length == 0 || arr[0].length == 0){
			throw new IllegalArgumentException("Neither rows or columns are allowed to have a size of zero.");
		}
		List<Organizer> orgs = new ArrayList<Organizer>();
		//rows are longer than columns
		if (arr.length <= arr[0].length){
			for (int i = 0; i < arr.length; i++){
				double row[] = new double[arr[0].length];
				for (int j = 0; j < arr[0].length; j++){
					row[j] = arr[i][j];
				}
				orgs.add(new Organizer(row));
			}
			return maximizeScore(orgs) / arr.length;
		}
		else{
			for (int i = 0; i < arr[0].length; i++){
				double column[] = new double[arr.length];
				for (int j = 0; j < arr.length; j++){
					column[j] = arr[j][i];
				}
				orgs.add(new Organizer(column));
			}
			return maximizeScore(orgs) / arr[0].length;
		}
	}
	
	/**
	 * Maximizes the score from a list of Organizers so that the total sum of all values is maximized.
	 * This is equivalent to maximizing a 2d array where you can only have one value at most chosen from each row and column.
	 * WARNING THIS METHOD MODIFIES THE LIST THAT IS PASSED IN AND THE OBJECTS IN IT
	 * @param list of Organizers that is being maximized.
	 * @return maximum collective sum.
	 */
	private double maximizeScore(List<Organizer> list){
		int highestOppIndex = -1;
		double highestOppValue = -1;
		double sum = 0;
		int n = list.size();
		Set<Integer> usedIndices = new HashSet<Integer>();
		
		for (int i = 0; i < list.size(); i++){
			for (int j = 0; j < n; j++){
				Organizer temp = list.get(j);
				//skip to a value whose index hasn't already been claimed by another quote
				while (usedIndices.contains(temp.getCurrentMax().getRight())){
					temp.nextMax();
				}

				if (temp.getOpportunityCost() > highestOppValue){
					highestOppValue = temp.getOpportunityCost();
					highestOppIndex = j;
				}
			}
			sum += list.get(highestOppIndex).getCurrentMax().getLeft();
			//when a index gets used no other quote can match with that index anymore
			usedIndices.add(list.get(highestOppIndex).getCurrentMax().getRight());

			//swap chosen Pair with the last element in list so that it doesn't get checked in following iterations since n will get decremented
			Organizer temp = list.get(highestOppIndex);
			list.set(highestOppIndex, list.get(n - 1));
			list.set(n - 1, temp);
			highestOppIndex = -1;
			highestOppValue = -1;
			n--;
		}
		
		return sum;
	}
	
	/**
	 * Organizes a array of doubles in ascending order, but also keeps track of what the original arrangement of them was.
	 * @author Jamison Bradley
	 */
	private class Organizer{
		/**
		 * Double is the value and the Integer is the index location for when it gets passed in so after sort orginal index can still be known.
		 */
		private List<Pair<Double, Integer>> sortedValues;
		/**
		 * The current largest value that has been rejected as being a duplicate index of another Organizer.
		 */
		private int current;
		
		private Organizer(double[] values){
			sortedValues = new ArrayList<Pair<Double, Integer>>();
			//populate the list then sort it based on the double valuse into ascending order
			for (int i = 0; i < values.length; i++){
				sortedValues.add(new Pair<Double, Integer>(values[i], i));
			}
			Collections.sort(sortedValues, new OrganizerPairComparator());
			current = sortedValues.size() - 1;
		}
		
		/**
		 * Gets the current maximum value.
		 * @return The Pair that is the current maximum value.
		 */
		private Pair<Double, Integer> getCurrentMax(){
			return sortedValues.get(current);
		}
		
		/**
		 * The opportunity cost of moving to the next maximum, currentMax - nextMax.
		 * @return Opportunity cost incurred by making call to nextMax().
		 */
		private double getOpportunityCost(){
			if (current <= 0){
//				throw new IllegalStateException("There is no next state to move to the bottom of the list has been reached");
				return Double.MIN_VALUE;
			}
			double currentValue = sortedValues.get(current).getLeft();
			double nextValue = sortedValues.get(current - 1).getLeft();
			return currentValue - nextValue;
		}
		
		/**
		 * Moves to the maximum pointer to the next value in the list.
		 */
		private void nextMax(){
			if (current <= 0){
				throw new IllegalStateException("There is no next max the bottom of the list has been reached");
			}
			current--;
		}
		
		@Override
		public String toString(){
			StringBuilder builder = new StringBuilder(100);
			for (int i = 0; i < sortedValues.size(); i++){
				String temp = "[" + sortedValues.get(i).getLeft() + ", " + sortedValues.get(i).getRight() + "] ";
				builder.append(temp);
				if (current == i){
					builder.append("<-- ");
				}
			}
			return builder.toString();
		}
	}
	
	/**
	 * Comparator for a Pair<Double, Integer> object, sorts the objects using Double.compareTo the Integer has no effect on the compare.
	 * @author Jamison Bradley
	 */
	private class OrganizerPairComparator implements Comparator<Pair<Double, Integer>>{
		@Override
		public int compare(Pair<Double, Integer> arg0, Pair<Double, Integer> arg1){
			return arg0.getLeft().compareTo(arg1.getLeft());
		}
	}
}
