package data.dto;

import java.io.Serializable;

public class IngredientDTO implements Serializable{
	
	private int id;
	private String name;
	private double amount;
	
	public IngredientDTO() {
		//For Jackson
	}
	
	public IngredientDTO(int id, String name, double amount) {
		this.id = id;
		this.name = name;
		this.amount = amount;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	@Override 
	public boolean equals (Object other) 
	{
		if (other instanceof IngredientDTO) 
		{
			if (this.id == ((IngredientDTO)other).id);
			return true;
		}
			
		return false;
}
}