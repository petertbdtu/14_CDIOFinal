package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import data.DALException;
import data.dao.IngredientBatchDAO;
import data.dao.StorageDAO;
import data.dto.IngredientBatchDTO;

public class IngredientBatchTest {

	IngredientBatchDAO instance;
	IngredientBatchDTO ibd1;
	IngredientBatchDTO ibd2;
	IngredientBatchDTO ibd3;
	IngredientBatchDTO ibd4;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		StorageDAO sd = new StorageDAO();
		sd.deleteFile(IngredientBatchDAO.class.getSimpleName());	
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		StorageDAO sd = new StorageDAO();
		sd.deleteFile(IngredientBatchDAO.class.getSimpleName());
	}
	
	@Before
	public void setUp() throws Exception {
		instance = IngredientBatchDAO.getInstance();
		
		ibd1 = new IngredientBatchDTO();
		ibd1.setIbID(1);
		ibd1.setAmount(3.75);
		ibd1.setIngredientId(5);
		
		ibd2 = new IngredientBatchDTO();
		ibd2.setIbID(104);
		ibd2.setAmount(4.222);
		ibd2.setIngredientId(5);
		
		ibd3 = new IngredientBatchDTO();
		ibd3.setIbID(99);
		ibd3.setAmount(5.4);
		ibd3.setIngredientId(3);
		
		ibd4 = new IngredientBatchDTO();
		ibd4.setIbID(66);
		ibd4.setAmount(7.77);
		ibd4.setIngredientId(32);
	
		try {
			instance.createIngredientBatch(ibd1);
			instance.createIngredientBatch(ibd2);
			instance.createIngredientBatch(ibd3);
		} catch(DALException e) {
		}
	}

	@Test
	public void testGetIngredientBatch() {
		try {
			IngredientBatchDTO actual = instance.getIngredientBatch(1);
			IngredientBatchDTO expected = ibd1;
			assertEquals(expected, actual);
		} catch(DALException e) {
			fail("Ingredient with this ID does not exist.");
		}
		try {
			IngredientBatchDTO object = instance.getIngredientBatch(2);
			assertNull(object);
		} catch(DALException e) {
			assertTrue(true);
		}
		
	}

	@Test
	public void testGetIngredientBatchList() {
		List<IngredientBatchDTO> actual = instance.getIngredientBatchList();
		List<IngredientBatchDTO> expected = new ArrayList<>();
		expected.add(ibd1);
		expected.add(ibd2);
		expected.add(ibd3);
		assertEquals(expected, actual);
	}

	@Test
	public void testGetIngredientBatchListInt() {
		try {
			List<IngredientBatchDTO> actual = instance.getIngredientBatchList(5);
			List<IngredientBatchDTO> expected = new ArrayList<>();
			expected.add(ibd1);
			expected.add(ibd2);
			assertEquals(expected, actual);
		} catch(DALException e) {
			fail("No Batches with this ID exists");
		}
	}

	@Test
	public void testCreateIngredientBatch() {
		try {
			instance.createIngredientBatch(ibd4);
			assertNotNull(ibd4);
		} catch(DALException e) {
			fail("Ingredient with this ID already exists.");
		}
	}

	@Test
	public void testUpdateIngredientBatch() {
		try {
			ibd1.setAmount(9.1);
			instance.updateIngredientBatch(ibd1);
			assertTrue(ibd1.getAmount() == 9.1);
		} catch(DALException e) {
		fail("Ingredient batch not found");
		}
	}
}
