package data.dao;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.DALException;
import data.dto.IngredientDTO;
import data.idao.IIngredientDAO;

public class IngredientDAO extends StorageDAO implements IIngredientDAO {

	private static IngredientDAO instance = new IngredientDAO();

	private IngredientDAO() {

		try {
			Map<Integer,IngredientDTO> ingredients = (HashMap<Integer, IngredientDTO>) super.load();
		} catch (ClassNotFoundException | IOException e) {
			super.save(new HashMap<Integer, IngredientDTO>());
		}

	}
  
	public static IngredientDAO getInstance()
	{
		return instance;
	} 

	@Override
	public synchronized IngredientDTO getIngredient(int ingredientId)  {
		try {
			Map<Integer, IngredientDTO> ingredients = (HashMap<Integer, IngredientDTO>) super.load();
			return ingredients.get(ingredientId);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public synchronized List<IngredientDTO> getIngredientList() {
		try {
			Map<Integer, IngredientDTO> ingredients = (HashMap<Integer, IngredientDTO>) super.load();
			return new ArrayList<>(ingredients.values()); //values indholder alle elementer i hashmapen
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public synchronized void createIngredient(IngredientDTO ingredient) throws DALException { 
		try {
			Map<Integer, IngredientDTO> ingredients = (HashMap<Integer, IngredientDTO>) super.load(); //hentet en ny ingredients liste fra filen
			if (!ingredients.containsKey(ingredient.getId())) {  //tjekker om den har en nøgle der er magen til den man er igang med at oprette.
				ingredients.put(ingredient.getId(),ingredient); //hvis ikke , put i listen. 
				super.save(ingredients); //gemmer til filen 
			} else
				throw new DALException("Ingredient with this ID already exists.");
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();   //ber om at vise fejl (rød i console)
		}
	}

	@Override
	public synchronized void updateIngredient(IngredientDTO ingredient) throws DALException {
		try {
			Map<Integer, IngredientDTO> ingredients = (HashMap<Integer, IngredientDTO>) super.load();
			ingredients.replace(ingredient.getId(), ingredient); //ersattter hele rækken, hvis den findes. 
			super.save(ingredients);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
}
