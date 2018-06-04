package data.dao;

import data.dto.RecipeDTO;
import data.idao.IRecipeDAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RecipeDAO implements IRecipeDAO{
    List<RecipeDTO> list;

    public RecipeDAO() {
        list = new ArrayList<>();
    }

    @Override
    public RecipeDTO getRecipe(int recipeId) {
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            RecipeDTO tempRecipe = (RecipeDTO) iterator.next();
            if (tempRecipe.getRecipeId() == recipeId) {
                return tempRecipe;
            }
        }

        return null;
        //Klar til at throwe exception
        //throw new DALException("Fejl: Recept ikke fundet!");
    }

    @Override
    public List<RecipeDTO> getRecipeList() {
        return list;
    }

    @Override
    public void createRecipe(RecipeDTO recipe) {
        if(getRecipe(recipe.getRecipeId()) == null ) {
            RecipeDTO tempRecipe = new RecipeDTO();

            tempRecipe.setRecipeId(recipe.getRecipeId());
            tempRecipe.setRecipeName(recipe.getRecipeName());

            list.add(tempRecipe);
        }
    }

    @Override
    public void updateRecipe(RecipeDTO recipe) {
        RecipeDTO curRecipe = getRecipe(recipe.getRecipeId());

        if(curRecipe != null){
            curRecipe.setRecipeName(recipe.getRecipeName());
        }
    }
}