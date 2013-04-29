package testing;

import org.junit.Test;

import database.Article;

import parsing.BBCParser;
import parsing.IPageParser;
import utility.Source;

public class BBCParserTest {

	@Test
	public void inputParseOne(){
		IPageParser parser = new BBCParser();
		String pageSource = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML+RDFa 1.0//EN\" \"http://www.w3.org/MarkUp/DTD/xhtml-rdfa-1.dtd\">\n" +
							"<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:og=\"http://opengraphprotocol.org/schema/\" xml:lang=\"en-GB\">\n" +
							"<meta property=\"og:title\" content=\"title here\"/>\n" +
							"<meta property=\"article:section\" content=\"section here\"/>\n" +
							"<meta name=\"OriginalPublicationDate\" content=\"pub date here\"/>\n" +
							"<p class=\"story-body\">article text here</p>\n" +
							"</html>\n";
		Source source = new Source(pageSource, "http://www.google.com");
		Article article = parser.parse(source);
		System.out.println(article.getTitle());
		System.out.println(article.getCategory());
		System.out.println(article.getDate());
	}
}
