package test;

import static org.junit.Assert.*;

import data.DALException;
import data.dao.RecipeCompDAO;
import data.dto.RecipeCompDTO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class RecipeCompTest {

	RecipeCompDAO rcd;
	RecipeCompDTO rc1;
	RecipeCompDTO rc2;
	RecipeCompDTO rc3;


	@Before
	public void setUp() throws Exception {
		rcd = RecipeCompDAO.getInstance();
		rc1 = new RecipeCompDTO();
		rc1.setRecipeId(1);
		rc1.setTolerance(2);
		rc1.setIngredient(3);
		rc1.setAmount(4);

		rc2 = new RecipeCompDTO();
		rc2.setRecipeId(1);
		rc2.setTolerance(3);
		rc2.setIngredient(5);
		rc2.setAmount(5);

        rc3 = new RecipeCompDTO();
        rc3.setAmount(5);
        rc3.setIngredient(8);
        rc3.setTolerance(12);
        rc3.setRecipeId(10);

        try {
            rcd.createRecipeComp(rc1);
            rcd.createRecipeComp(rc2);
        } catch (DALException e) {
            //nothing wrong here
        }
	}

	@Test
	public void testGenerateKeyRecipeCompDTO() {
		String tmpKey = rcd.generateKey(rc1);
		assertTrue(tmpKey.equals("1,3"));
	}

	@Test
	public void testGenerateKeyIntInt() {
		String tmpKey = rcd.generateKey(1,3);
		assertTrue(tmpKey.equals("1,3"));
	}

	@Test
	public void testCreateRecipeComp() {
		try {
			rcd.createRecipeComp(rc3);
		} catch (DALException e) {
			fail("ID already in use");
		}

		try {
			assertTrue(rcd.getRecipeComp(10,8).getTolerance() == 12);
		} catch (DALException e) {
			fail("Create unsuccesful");
		}

		try {
			rcd.createRecipeComp(rc3);
		} catch (DALException e) {
			try {
				assertTrue(rcd.getRecipeComp(10,8).getTolerance() == 12);
			} catch (DALException e1) {}
		}
	}

	@Test
	public void testGetRecipeComp() {
		try {
			rcd.getRecipeComp(1,3);
		} catch (DALException e) {
			fail("Recipe not found");
		}
		assertTrue(true);
		try {
			rcd.getRecipeComp(1,4);
		} catch (DALException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testGetRecipeCompListInt() {
		List<?> recCompList = rcd.getRecipeCompList(1);

		assertTrue(recCompList.size() == 2);
	}

	@Test
	public void testGetRecipeCompList() {
		List<?> recCompList = rcd.getRecipeCompList();

		assertTrue(recCompList.size() == 3);
	}

	@Test
	public void testUpdateRecipeComp() {
		rc2.setAmount(600);
		try {
			rcd.updateRecipeComp(rc2);
		} catch (DALException e) {
			fail("Failed to update");
		}

		assertTrue(rc2.getAmount() == 600);
	}

	@AfterClass
	public static void tearDownClass(){
		RecipeCompDAO rcd = RecipeCompDAO.getInstance();
		File file = new File(rcd.getPath());
		if(!file.delete()) {
			System.out.println("Failed to delete the file");
		}
	}

}
