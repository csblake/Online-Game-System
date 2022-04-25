package tests;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import graphics.CreateAccountGUI;

public class CreateAccountGUITest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSortUsersByEmailGoodSort() {
		// make the hash map
		HashMap<String, String> testHash = new HashMap<>();
		
		// put values into the hash map
		testHash.put("clay", "a");
		testHash.put("michelle", "b");
		testHash.put("colin", "c");
		
		CreateAccountGUI.setUserAccounts(testHash);
				
		// test the sort and put into a string
		String result = "clay\ta\ncolin\tc\nmichelle\tb\n";
		String actual = CreateAccountGUI.sortUsersByEmail(testHash);
				
		assertEquals(result, actual);
	}
	
	@Test
	public void testSortUsersByEmailBadSort() {
		// make the hash map
		HashMap<String, String> testHash = new HashMap<>();
		
		// put values into the hash map
		testHash.put("clay", "a");
		testHash.put("michelle", "b");
		testHash.put("colin", "c");
		
		CreateAccountGUI.setUserAccounts(testHash);
				
		// test the sort and put into a string
		String result = "clay\ta\nmichelle\tb\ncolin\tc\n";
		String actual = CreateAccountGUI.sortUsersByEmail(testHash);
				
		assertNotEquals(result, actual);
	}
	
	@Test
	public void testSortUsersByEmailBadInputWithNumber() {
		// make the hash map
		HashMap<String, String> testHash = new HashMap<>();
		
		// put values into the hash map
		testHash.put("clay", "a");
		testHash.put("michelle", "b");
		testHash.put("colin", "c");
		testHash.put("c1onner", "co");
		
		CreateAccountGUI.setUserAccounts(testHash);
				
		// test the sort and put into a string
		String result = "c1onner\tco\nclay\ta\ncolin\tc\nmichelle\tb\n";
		String actual = CreateAccountGUI.sortUsersByEmail(testHash);
				
		assertEquals(result, actual);
	}
}
