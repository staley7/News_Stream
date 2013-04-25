package database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import manager.IDatabase;

public class FakeDatabase implements IDatabase {
	
	private static int STORY = 0;
	private static int ARTICLE = 1;
	
	private static FakeDatabase instance;

	private Map<Integer, Article> articleTable;
	private Map<Integer, Story> storyTable;
	
	public FakeDatabase(){
		articleTable = new HashMap<Integer, Article>();
		storyTable = new HashMap<Integer, Story>();
		InputStream articleDoc = getClass().getResourceAsStream("/database/localArticleTable.txt");
		InputStream storyDoc = getClass().getResourceAsStream("/database/localStoryTable.txt");
		BufferedReader articleReader = new BufferedReader(new InputStreamReader(articleDoc));
		BufferedReader storyReader = new BufferedReader(new InputStreamReader(storyDoc));
		
		try{
			String line = articleReader.readLine();
			while (line != null){
				//create the Article object
				Article article = new Article();
				article.setID(new ArticleID(Integer.parseInt(line)));
				String title = articleReader.readLine();
				article.setTitle(title);
				String category = articleReader.readLine();
				article.setCategory(category);
				String date = articleReader.readLine();
				article.setDate(new Date(Long.parseLong(date)));
				String location = articleReader.readLine();
				article.setLocation(location);
				String source = articleReader.readLine();
				article.setSource(source);
				String url = articleReader.readLine();
				article.setUrl(url);
				String keyString = articleReader.readLine();
				String keySplit[] = keyString.split(",");
				List<String> keywords = new ArrayList<String>();
				for (String keyword: keySplit){
					keywords.add(keyword);
				}
				article.setKeywords(keywords);
				String quoteString = articleReader.readLine();
				String quoteSplit[] = quoteString.split("\"");
				List<String> quotes = new ArrayList<String>();
				for (String quote: quoteSplit){
					quotes.add(quote);
				}
				article.setQuotes(quotes);
				String storyId = articleReader.readLine();
				article.setStoryID(new StoryID(Integer.parseInt(storyId)));
				//add entry to map
				articleTable.put(article.getID().getID(), article);
				line = articleReader.readLine();
			}
			articleReader.close();
			
			line = storyReader.readLine();
			while (line != null){
				//construct the story object
				Story story = new Story();
				story.setID(new StoryID(Integer.parseInt(line)));
				String category = storyReader.readLine();
				story.setCategory(category);
				String date = storyReader.readLine();
				story.setDate(new Date(Long.parseLong(date)));
				String title = storyReader.readLine();
				story.setTitle(title);
				//add entry to the map
				storyTable.put(story.getID().getID(), story);
				line = storyReader.readLine();
			}
			storyReader.close();
		} 
		catch (IOException e){
			e.printStackTrace();
		}

	}
	
	@Override
	public ArticleID storeArticle(Article article) {
		Set<Integer> keys = articleTable.keySet();
		int highest = -1;
		for (Integer key: keys){
			if (key > highest){
				highest = key;
			}
		}
		
		ArticleID id = new ArticleID(highest + 1);
		article.setID(id);
		articleTable.put(highest + 1, article);
		update(FakeDatabase.ARTICLE);
		return id;
	}

	@Override
	public Article getArticle(ArticleID articleID) {
		return articleTable.get(articleID.getID());
	}

	@Override
	public List<Story> getStoriesByDate(long start, long end) {
		List<Story> ret = new ArrayList<Story>();
		Set<Integer> keys = storyTable.keySet();
		//add all keys in the time range
		for (Integer key: keys){
			Story story = storyTable.get(key);
			long time = story.getDate().getTime();
			if (time >= start && time <= end){
				ret.add(story);
			}
		}
		return ret;
	}

	@Override
	public StoryID storeStory(Story story) {
		Set<Integer> keys = storyTable.keySet();
		int highest = -1;
		for (Integer key: keys){
			if (key > highest){
				highest = key;
			}
		}
		
		StoryID id = new StoryID(highest + 1);
		story.setID(id);
		storyTable.put(highest + 1, story);
		update(FakeDatabase.STORY);
		return id;
	}

	@Override
	public void updateStory(Story story) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean articleExists(String url) {
		if (url == null){
			throw new IllegalArgumentException("The url can not be null.");
		}
		
		Set<Integer> keys = articleTable.keySet();
		for (Integer key: keys){
			if (url.equals(articleTable.get(key).getUrl())){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public List<Article> getArticlesInStory(Story story){
		List<Article> ret = new ArrayList<Article>();
		Set<Integer> keys = articleTable.keySet();
		for (Integer key: keys){
			Article article = articleTable.get(key);
			if (article.getStoryID().equals(story.getID())){
				ret.add(article);
			}
		}
		
		return ret;
	}
	
	@Override
	public void printArticleTable(){
		Set<Integer> keys = articleTable.keySet();
		for (Integer key: keys){
			Article article = articleTable.get(key);
			System.out.println("-----------Entry-----------");
			System.out.println("Article ID = " + article.getID().getID());
			System.out.println("Title = " + article.getTitle());
			System.out.println("Category = " + article.getCategory());
			System.out.println("Date = " + article.getDate());
			System.out.println("Location = " + article.getLocation());
			System.out.println("Source = " + article.getSource());
			System.out.println("Url = " + article.getUrl());
			System.out.println("Keywords = " + article.getKeywords());
			System.out.println("Quotes = " + article.getQuotes());
			System.out.println("Story ID = " + article.getStoryID().getID());
		}
	}
	
	@Override
	public void printStoryTable(){
		Set<Integer> keys = storyTable.keySet();
		for (Integer key: keys){
			Story story = storyTable.get(key);
			System.out.println("-----------Entry-----------");
			System.out.println("Story ID = " + story.getID().getID());
			System.out.println("Category = " + story.getCategory());
			System.out.println("Date = " + story.getDate());
			System.out.println("Title = " + story.getTitle());
		}
	}
	
	private void update(int table){
		if (table == STORY){
			try{
				String path = getClass().getResource("/database/localStoryTable.txt").getPath();
				path = path.replaceAll("bin/database/localStoryTable.txt", "src/database/localStoryTable.txt");
				BufferedWriter writer = new BufferedWriter(new FileWriter(path));
				//get keys and make entries
				Set<Integer> keys = storyTable.keySet();
				for (Integer key: keys){
					Story s = storyTable.get(key);
					writer.write(s.getID().getID() + "\n");
					writer.write(s.getCategory() + "\n");
					writer.write(s.getDate().getTime() + "\n");
					writer.write(s.getTitle() + "\n");
				}
				writer.close();
			} 
			catch (IOException e){
				e.printStackTrace();
			}
			
		}
		else if (table == ARTICLE){
			try{
				String path = getClass().getResource("/database/localArticleTable.txt").getPath();
				path = path.replaceAll("bin/database/localArticleTable.txt", "src/database/localArticleTable.txt");
				BufferedWriter writer = new BufferedWriter(new FileWriter(path));
				//get keys and make entries
				Set<Integer> keys = articleTable.keySet();
				for (Integer key: keys){
					Article a = articleTable.get(key);
					writer.write(a.getID().getID() + "\n");
					writer.write(a.getTitle() + "\n");
					writer.write(a.getCategory() + "\n");
					writer.write(a.getDate().getTime() + "\n");
					writer.write(a.getLocation() + "\n");
					writer.write(a.getSource() + "\n");
					writer.write(a.getUrl() + "\n");
					List<String> keywords = a.getKeywords();
					for (String word: keywords){
						writer.write(word + ",");
					}
					writer.write("\n");
					List<String> quotes = a.getQuotes();
					for (String quote: quotes){
						writer.write(quote + "\"");
					}
					writer.write("\n");
					writer.write(a.getStoryID().getID() + "\n");
				}
				writer.close();
			}
			catch (IOException e){
				e.printStackTrace();
			}
		}
	}
	
	public static FakeDatabase getInstance(){
		if (instance == null){
			instance = new FakeDatabase();
		}
		return instance;
	}
	
	public static void main(String[] args){
		FakeDatabase fb = new FakeDatabase();
		fb.printArticleTable();
		fb.printStoryTable();
	}

}
