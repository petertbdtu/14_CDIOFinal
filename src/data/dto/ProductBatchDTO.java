package data.dto;

import java.io.Serializable;

public class ProductBatchDTO implements Serializable {

	private static final long serialVersionUID = -6440335065542506197L;
	private int pbId;//product batch Nr.
	private int recipeId;//product recept
	private int status;
	
	public ProductBatchDTO() {
		
	}

	public int getPbId() {
		return pbId;
	}

	public void setPbId(int pbId) {
		this.pbId = pbId;
	}

	public int getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
