package test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import data.DALException;
import data.dao.IngredientDAO;
import data.dao.StorageDAO;
import data.dto.IngredientDTO;
import data.idao.IIngredientDAO;

public class IngredientTest {
	
	IIngredientDAO iid = IngredientDAO.getInstance();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		StorageDAO sd = new StorageDAO();
		sd.deleteFile(IngredientDAO.class.getSimpleName());
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		StorageDAO sd = new StorageDAO();
		sd.deleteFile(IngredientDAO.class.getSimpleName());
	}

	@Test
	public void testCreateIngredient() throws DALException {
		//Create test ingredient
		IngredientDTO ingredient1 = new IngredientDTO();
		ingredient1.setId(1);
		
		//Add to datalayer
		iid.createIngredient(ingredient1);
		
		//Check if .getIngredient returns ingredient1
		IngredientDTO testIngredient = iid.getIngredient(ingredient1.getId());
		assertEquals(ingredient1.getId(), testIngredient.getId());
	}
	
	@Test
	public void testGetIngredient() throws DALException {
		//Create test ingredient
		IngredientDTO ingredient2 = new IngredientDTO();
		ingredient2.setId(2);
		
		//Add to datalayer
		iid.createIngredient(ingredient2);
		
		//Check if .getIngredient returns ingredient1
		IngredientDTO testIngredient = iid.getIngredient(ingredient2.getId());
		assertEquals(ingredient2.getId(), testIngredient.getId());
	}

	@Test
	public void testGetIngredientList() throws DALException {
		//Create test ingredients
		IngredientDTO ingredient10 = new IngredientDTO();
		IngredientDTO ingredient20 = new IngredientDTO();
		ingredient10.setId(10);
		ingredient20.setId(20);
		iid.createIngredient(ingredient10);
		iid.createIngredient(ingredient20);
		
		//Check if .testGetIngredientList returns ingredient10/20
		boolean temp10 = false;
		boolean temp20 = false;
		for (IngredientDTO tempIngredient : iid.getIngredientList()) {
			if (tempIngredient.getId() == 10)
				temp10 = true;
			else if(tempIngredient.getId() == 20)
				temp20 = true;
		}
		
		assertTrue(temp10 && temp20);
	}

	@Test
	public void testUpdateIngredient() throws DALException {
		//Create test ingredient
		IngredientDTO ingredient100 = new IngredientDTO();
		ingredient100.setId(100);
		ingredient100.setName("NotUpdated");
		iid.createIngredient(ingredient100);
		
		//Update ingredient
		ingredient100.setName("Updated");
		iid.updateIngredient(ingredient100);
		
		//Check if .updateIngredient updated name
		IngredientDTO testIngredient = iid.getIngredient(ingredient100.getId());
		assertTrue(testIngredient.getName().equals("Updated"));
	}

}
