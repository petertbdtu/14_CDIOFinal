package data.dao;

import java.io.IOException;
import java.util.*;
import data.DALException;
import data.dto.RecipeCompDTO;
import data.idao.IRecipeCompDAO;

public class RecipeCompDAO extends StorageDAO implements IRecipeCompDAO {

	static private RecipeCompDAO instance = new RecipeCompDAO();

	/*
	 * Constructor which ensures that a file exists.
	 */
	private RecipeCompDAO() {
		try {
			Map<String, RecipeCompDTO> component = (Map<String, RecipeCompDTO>) super.load();
		} catch (ClassNotFoundException | IOException e) {
			super.save(new HashMap<String, RecipeCompDTO>());
		}
	}

    /*
     * Used for static reference to instance.
     */
	public static RecipeCompDAO getInstance() {
		return instance;
	}

	/*
	 * Generates a string from 2 keys
	 */
	public String generateKey(RecipeCompDTO comp) {
		return comp.getRecipeId() + "," + comp.getIngredientId();
	}

	/*
	 * Generates a string from 2 keys
	 */
	public String generateKey(int recipeId, int ingredientId) {
		return recipeId + "," + ingredientId;
	}

	/*
	 * Returns a specific Recipe component with
	 * the specific IDs
	 */
	@Override
	public synchronized RecipeCompDTO getRecipeComp(int recipeId, int ingredientId) throws DALException {
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

	/*
	 * Returns a list of Recipe components which
	 * have the given recipeID
	 */
	@Override
	public synchronized List<RecipeCompDTO> getRecipeCompList(int recipeId) {
		try {
			Map<String, RecipeCompDTO> component = (Map<String, RecipeCompDTO>) super.load();
			List<RecipeCompDTO> recipeComps = new ArrayList<>();
			for (RecipeCompDTO recipeComp : component.values())
				if (recipeComp.getRecipeId() == recipeId)
					recipeComps.add(recipeComp);
			return recipeComps;

		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Returns a list with all Recipe components
	 */
	@Override
	public synchronized List<RecipeCompDTO> getRecipeCompList() {
		try {
			Map<String, RecipeCompDTO> component = (Map<String, RecipeCompDTO>) super.load();
			return new ArrayList<>(component.values());
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	/*
	 * Puts a Recipe component to the map and saves
	 * to file.
	 * 
	 * ERROR if it already exists
	 */
	@Override
	public synchronized void createRecipeComp(RecipeCompDTO recipeComp) throws DALException {
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

	/*
	 * Replaces an already created Recipe component with
	 * the inputed one
	 */
	@Override
	public synchronized void updateRecipeComp(RecipeCompDTO recipeComp) throws DALException {
		try {
			String keyString = generateKey(recipeComp);
			Map<String, RecipeCompDTO> component = (Map<String, RecipeCompDTO>) super.load();
			if (!component.containsKey(keyString)) {
				throw new DALException("Component does not exist");
			}
			component.replace(keyString, recipeComp);
			super.save(component);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
}