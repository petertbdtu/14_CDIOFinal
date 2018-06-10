package test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import data.DALException;
import data.dao.RecipeDAO;
import data.dao.StorageDAO;
import data.dto.RecipeDTO;
import data.idao.IRecipeDAO;

public class RecipeTest {

	IRecipeDAO iid = RecipeDAO.getInstance();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		StorageDAO sd = new StorageDAO();
		sd.deleteFile(RecipeDAO.class.getSimpleName());
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		StorageDAO sd = new StorageDAO();
		sd.deleteFile(RecipeDAO.class.getSimpleName());
	}

	@Test
	public void testCreateRecipe() throws DALException {
		//Create test Recipe
		RecipeDTO Recipe1 = new RecipeDTO();
		Recipe1.setRecipeId(1);
		
		//Add to datalayer
		iid.createRecipe(Recipe1);
		
		//Check if .getRecipe returns Recipe1
		RecipeDTO testRecipe = iid.getRecipe(Recipe1.getRecipeId());
		assertEquals(Recipe1.getRecipeId(), testRecipe.getRecipeId());
	}
	
	@Test
	public void testGetRecipe() throws DALException {
		//Create test Recipe
		RecipeDTO Recipe2 = new RecipeDTO();
		Recipe2.setRecipeId(2);
		
		//Add to datalayer
		iid.createRecipe(Recipe2);
		
		//Check if .getRecipe returns Recipe1
		RecipeDTO testRecipe = iid.getRecipe(Recipe2.getRecipeId());
		assertEquals(Recipe2.getRecipeId(), testRecipe.getRecipeId());
	}

	@Test
	public void testGetRecipeList() throws DALException {
		//Create test Recipes
		RecipeDTO Recipe10 = new RecipeDTO();
		RecipeDTO Recipe20 = new RecipeDTO();
		Recipe10.setRecipeId(10);
		Recipe20.setRecipeId(20);
		iid.createRecipe(Recipe10);
		iid.createRecipe(Recipe20);
		
		//Check if .testGetRecipeList returns Recipe10/20
		boolean temp10 = false;
		boolean temp20 = false;
		for (RecipeDTO tempRecipe : iid.getRecipeList()) {
			if (tempRecipe.getRecipeId() == 10)
				temp10 = true;
			else if(tempRecipe.getRecipeId() == 20)
				temp20 = true;
		}
		
		assertTrue(temp10 && temp20);
	}

	@Test
	public void testUpdateRecipe() throws DALException {
		//Create test Recipe
		RecipeDTO Recipe100 = new RecipeDTO();
		Recipe100.setRecipeId(100);
		Recipe100.setRecipeName("NotUpdated");
		iid.createRecipe(Recipe100);
		
		//Update Recipe
		Recipe100.setRecipeName("Updated");
		iid.updateRecipe(Recipe100);
		
		//Check if .updateRecipe updated name
		RecipeDTO testRecipe = iid.getRecipe(Recipe100.getRecipeId());
		assertTrue(testRecipe.getRecipeName().equals("Updated"));
	}

}
