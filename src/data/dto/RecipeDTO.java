package data.dto;

import java.io.Serializable;

public class RecipeDTO implements Serializable {
    
	private static final long serialVersionUID = -6440335065542506197L;
	int recipeId;
    String recipeName;

    public RecipeDTO() {

    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }
}
