package analysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import database.Article;

import parsing.SourceParser;
import utility.Source;

/**
 * Finds keywords in a text document. Achieves this by removing needless punctuation and common words that can be found in the
 * CommonWords.txt file. Then any word that hasn't been removed and appears twice or more will be considered key.
 * @author Jamison Bradley
 */
public class KeywordFilter {
	
	/**
	 * The set of common words that should be removed from consideration as key due to how common they are in English.
	 */
	private Set<String> commonWords;
	/**
	 * Punctuation that could interfere with the text analysis that need to be removed.
	 */
	private String[] punctuation = {".", ",", "?", "!", "\"", "(", ")", ":", "#", "[", "]", "@", "/"};
	
	public KeywordFilter(){
		initCommonWords();
	}
	
	/**
	 * Initialize the commonWords Set with common words in the English language.
	 */
	private void initCommonWords(){
		commonWords = new HashSet<String>();
		InputStream iStream = getClass().getResourceAsStream("/analysis/CommonWords.txt");

		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(iStream));
			String line = null;
			while ((line = reader.readLine()) != null){
				commonWords.add(line);
			}
		}
		catch (FileNotFoundException e){
			e.printStackTrace();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates a map of keywords from a given String. Note in order for a word to be considered key it must appear at least twice in the text
	 * and not be in the list of common words found in CommonWords.txt.
	 * @param str is the String that is being checked for keywords.
	 * @return A map of keywords with the words being the keys and the keys mapping to an integer value of how many times the word appeared.
	 */
	public Map<String, Integer> getKeywordsMap(String str){
		Map<String, Integer> counter = new HashMap<String, Integer>();
		//split on white space or hyphens
		String[] words = str.split("\\s|\\-");
		for (String word: words){
			String toCompare = formatWord(word);
			if (!isCommon(toCompare) && toCompare.length() > 2){
				if (counter.containsKey(toCompare)){
					counter.put(toCompare, counter.get(toCompare) + 1);
				}
				else{
					counter.put(toCompare, 1);
				}
			}
		}
		
		//only select things that appear more than once as key words
		Map<String, Integer> ret = new HashMap<String, Integer>();
		Set<String> keys = counter.keySet();
		for (String key: keys){
			if (counter.get(key) > 1){
				ret.put(key, counter.get(key));
			}
		}
		return ret;
	}
	
	/**
	 * Creates a list of keywords from a given String. Note in order for a word to be considered key it must appear at least twice in the text
	 * and not be in the list of common words found in CommonWords.txt.
	 * @param str is the String that is being checked for keywords.
	 * @return A list of keywords.
	 */
	public List<String> getKeywordsList(String str){
		List<String> ret = new ArrayList<String>();
		Map<String, Integer> map = getKeywordsMap(str);
		//return the keys
		Set<String> keys = map.keySet();
		for (String key: keys){
			ret.add(key);
		}
		return ret;
	}
	
	/**
	 * Replaces upper case letters with lower case, and removes . , ? ! " etc. (anything from the punctuation array) from the word.
	 * @param word that is being modified to the specified format.
	 * @return new String with specified modifications made to word.
	 */
	private String formatWord(String word){
		String ret = new String(word);
		for (int i = 0; i < ret.length(); i++){
			//lower case the character
			if (Character.isUpperCase(ret.charAt(i))){
				ret = replaceChar(ret, i, "" + Character.toLowerCase(ret.charAt(i)));
			}
			//removal of punctuation from a String
			for (int j = 0; j < punctuation.length; j++){
				if (punctuation[j].equals("" + ret.charAt(i))){
					ret = replaceChar(ret, i, "");
					i--;
					break;
				}
			}
		}
		return ret;
	}
	
	/**
	 * Replaces the char at the given location with the replacement char.
	 * @param word is the String that the replacement is occurring in.
	 * @param location is the location in the String that the character being replaced is located at.
	 * @param replacement is the replacement String to be inserted at location, if replacement is null or empty String
	 *  it will just remove char at that location.
	 * @return A string the char at location changed to replacement.
	 */
	private String replaceChar(String word, int location, String replacement){
		String ret;
		String toInsert = replacement;
		if (toInsert == null){
			toInsert = "";
		}
		ret = word.substring(0, location) + toInsert;
		if (location != word.length() - 1){
			ret += word.substring(location + 1, word.length());
		}
		return ret;
	}
	
	/**
	 * Checks to see if a word is in the common word set.
	 * @param word to be checked against the common word set.
	 * @return True if the word is in the common word set false if not.
	 */
	private boolean isCommon(String word){
		String test = formatWord(word);
		return commonWords.contains(test);
	}
	
//	public static void main(String[] args){
//		SourceParser parser = new SourceParser();
//		Article article = parser.parse(new Source("http://www.guardian.co.uk/world/2013/apr/16/boston-marathon-explosions-fbi"));
//		System.out.println("-----------------------------------------------");
//		KeywordFilter filter = new KeywordFilter();
//		Map<String, Integer> map = filter.getKeyWords(article.getText());
//		Set<String> keys = map.keySet();
//		for (String key: keys){
//			System.out.println(key + " " + map.get(key));
//		}
//	}
	
//	public static void main(String[] args){
//		
//		
//		try {
//			File f = new File("C:\\Users\\Jamison\\Documents\\SeniorDesign\\GSLWords.txt");
//			BufferedReader reader = new BufferedReader(new FileReader(f));
//			Set<String> set = new HashSet<String>();
//			String line = reader.readLine();
//			while (line != null){
//				String[] split = line.split(" ");
//				set.add(split[2].toLowerCase());
//				line = reader.readLine();
//			}
//			reader.close();
//			
//			File f2 = new File("C:\\Users\\Jamison\\Documents\\CommonWords.txt");
//			BufferedReader reader2 = new BufferedReader(new FileReader(f2));
//			line = reader2.readLine();
//			while (line != null){
//				set.add(line.toLowerCase());
//				line = reader2.readLine();
//			}
//			reader.close();
//			System.out.println(set.size());
//			
//			for (String str: set){
//				System.out.println(str);
//			}
//		} 
//		catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} 
//		catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

}
