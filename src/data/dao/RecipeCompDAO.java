package data.dao;

import java.io.IOException;
import java.util.*;
import data.DALException;
import data.dto.RecipeCompDTO;
import data.idao.IRecipeCompDAO;

public class RecipeCompDAO extends StorageDAO implements IRecipeCompDAO {

	static private RecipeCompDAO instance = new RecipeCompDAO();

	private RecipeCompDAO() {
		try {
			Map<String, RecipeCompDTO> component = (Map<String, RecipeCompDTO>) super.load();
		} catch (ClassNotFoundException | IOException e) {
			super.save(new HashMap<String, RecipeCompDTO>());
		}
	}

	public static RecipeCompDAO getInstance() {
		return instance;
	}

	public String generateKey(RecipeCompDTO comp) {
		return comp.getRecipeId() + "," + comp.getIngredientId();
	}

	public String generateKey(int recipeId, int ingredientId) {
		return recipeId + "," + ingredientId;
	}

	@Override
	public RecipeCompDTO getRecipeComp(int recipeId, int ingredientId) throws DALException {
		try {
			String keyString = generateKey(recipeId, ingredientId);
			Map<String, RecipeCompDTO> component = (Map<String, RecipeCompDTO>) super.load();
			if (!component.containsKey(keyString)) {
				throw new DALException("Component does not exist");
			}
			return component.get(keyString);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<RecipeCompDTO> getRecipeCompList(int recipeId) {
		try {
			Map<String, RecipeCompDTO> component = (Map<String, RecipeCompDTO>) super.load();
			return (List<RecipeCompDTO>) component.values();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<RecipeCompDTO> getRecipeCompList() {
		try {
			Map<String, RecipeCompDTO> component = (Map<String, RecipeCompDTO>) super.load();
			return (List<RecipeCompDTO>) component.values();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public void createRecipeComp(RecipeCompDTO recipeComp) throws DALException {
		try {
			String keyString = generateKey(recipeComp);
			Map<String, RecipeCompDTO> component = (Map<String, RecipeCompDTO>) super.load();
			if (component.containsKey(keyString)) {
				throw new DALException("Component already exists");
			}
			component.put(keyString, recipeComp);
			super.save(component);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateRecipeComp(RecipeCompDTO recipeComp) throws DALException {
		try {
			String keyString = generateKey(recipeComp);
			Map<String, RecipeCompDTO> component = (Map<String, RecipeCompDTO>) super.load();
			if (!component.containsKey(recipeComp)) {
				throw new DALException("Component does not exist");
			}
			component.replace(keyString, recipeComp);
			super.save(component);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
}