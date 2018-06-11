package data.dto;

public class ProductBatchPrintCompDTO {
	
	private String name; // Name of the ingredient
	private int ingredientId;
	private int amount; // Amount in the batch
	private double tolerance;
	private int ibId; // The ingredient batch it is from
	private double tara;
	private double netto;
    private String ini; // Initials of the operator that weighed it
    
    private int part; // Completely unknown, presumptuously translated from 'Del'
    
    public ProductBatchPrintCompDTO() {}
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIngredientId() {
		return ingredientId;
	}
	public void setIngredientId(int ingredientId) {
		this.ingredientId = ingredientId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public double getTolerance() {
		return tolerance;
	}
	public void setTolerance(double tolerance) {
		this.tolerance = tolerance;
	}
	public int getIbID() {
		return ibId;
	}
	public void setIbID(int ibID) {
		this.ibId = ibID;
	}
	public double getTara() {
		return tara;
	}
	public void setTara(double tara) {
		this.tara = tara;
	}
	public double getNetto() {
		return netto;
	}
	public void setNetto(double netto) {
		this.netto = netto;
	}
	public String getIni() {
		return ini;
	}
	public void setIni(String ini) {
		this.ini = ini;
	}

	public int getPart() {
		return part;
	}

	public void setPart(int part) {
		this.part = part;
	}
	
}
