package data.dto;

public class IngredientBatchDTO {
	private int ibID;
	private int ingbatchID;
	private double amount;
	private String supplier;

	public IngredientBatchDTO() {

	}

	public int getIbID() {
		return ibID;
	}

	public void setIbID(int ibID) {
		this.ibID = ibID;
	}

	public int getIngbatchID() {
		return ingbatchID;
	}

	public void setIngbatchID(int ingbatchID) {
		this.ingbatchID = ingbatchID;
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