package test;

import static org.junit.Assert.*;

import data.dao.IngredientDAO;
import data.dao.StorageDAO;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StorageTest {

	StorageDAO sd;

	@Before
	public void setUp() throws Exception {
		sd = new StorageDAO();
		Map<?,?> testMap = new HashMap<Integer, Integer>();
		sd.save(testMap);
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		StorageDAO sd = new StorageDAO();
		sd.deleteFile(StorageDAO.class.getSimpleName());
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		StorageDAO sd = new StorageDAO();
		sd.deleteFile(StorageDAO.class.getSimpleName());
	}

	@Test
	public void testSave() {
		String filePath = sd.getPath(StorageDAO.class.getSimpleName());
		File file = new File(filePath);
		if(!file.exists()){
			fail("File not found");
		}

		try {
			assertTrue(sd.load().getClass().getSimpleName().equals("HashMap"));
		} catch (ClassNotFoundException | IOException e) {
			fail("Save test failed");
		}


	}

	@Test
	public void testLoad() {
		Map<Integer, Integer> testMap2 = new HashMap<>();
		testMap2.put(5,15);
		testMap2.put(15,5);
		sd.save(testMap2);

		try {
			assertTrue(sd.load().containsKey(5));
			assertTrue(sd.load().containsValue(15));
			assertTrue(sd.load().values().size() == 2);
		} catch (ClassNotFoundException | IOException e) {
			fail("Load test failed");
		}
	}

	@Test
	public void testGetPath(){
		String tmp = System.getProperty("user.home");
		tmp = tmp + "/.weightData/Storage.data";
		assertTrue(sd.getPath(StorageDAO.class.getSimpleName()).equals(tmp));
	}

}
