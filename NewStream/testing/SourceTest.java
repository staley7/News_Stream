package testing;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;

import utility.Source;


/**
 * Unit test of the source class
 * REQUIRES INTERNET CONNECTION TO RUN CORRECTLY
 * @author Jamison Bradley
 */
public class SourceTest {


	@Test
	//makes a connection to a website and makes sure the InputStream can be retrieved. Can't really test the text of the InputStream
	//due to the fact that the website could change at any time.
	public void webSourceTest(){
		Source source = new Source("http://www.google.com");
		assertEquals(source.getUrl(), "http://www.google.com");
		InputStream stream = source.getSource();
		assertEquals(stream != null, true);
	}
	
	@Test
	//makes sure that a String can be converted correctly and read back from the source object and that the url is stored correctly.
	public void stringSourceTest(){
		String text = "this is a test\n" +
					  "another line\n" +
					  "last line\n";
		Source source = new Source(text, "www.aurl.com");
		StringBuilder builder = new StringBuilder(1000);
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(source.getSource()));
			String line = reader.readLine();
			while (line != null){
				builder.append(line);
				builder.append("\n");
				line = reader.readLine();
			}
		} 
		catch (IOException e){
			e.printStackTrace();
			//make sure test fails if exception occurs
			assertEquals(false, true);
		}
		assertEquals(text, builder.toString());
		assertEquals(source.getUrl(), "www.aurl.com");
	}
}
