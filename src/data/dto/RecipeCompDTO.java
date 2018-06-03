package data.dto;

public class RecipeCompDTO {
	String ingredient;
	int amount;
	int tolerance;
	

	public RecipeCompDTO(String ingredient, int amount, int tolerance) {
		this.ingredient = ingredient;
		this.amount = amount;
		this.tolerance = tolerance;
	}

	public String getIngredient() {
		return ingredient;
	}

	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getTolerance() {
		return tolerance;
	}

	public void setTolerance(int tolerance) {
		this.tolerance = tolerance;
	}
	
	
}
