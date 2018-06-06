package data.dto;

import java.io.Serializable;

public class IngredientBatchDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9055060400210373872L;
	private int ibId;
	private double amount;
	private String supplier;

	public IngredientBatchDTO() {

	}

	public int getIbID() {
		return ibId;
	}

	public void setIbID(int ibID) {
		this.ibId = ibID;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
}