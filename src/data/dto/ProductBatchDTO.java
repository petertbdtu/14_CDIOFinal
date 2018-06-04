package dto;

public class ProductBatchDTO {
	private int pbNr;//product batch Nr.
	private int recept;//product 
	private String date;
	private String status;
	private double amount;
	private String ingID;
	
	public productBatchDTO(int pbNr, int recept, String date, String status) {
		this.pbNr = pbNr;
		this.recept = recept;
		this.dato = dato;
		this.status = status;
	}
	
	public ingredients(String ingID, double amount) {
		this.ingID = ingID;
		this.amount = amount;
	}

	public int getPbNr() {
		return pbNr;
	}

	public void setPbNr(int pbNr) {
		this.pbNr = pbNr;
	}

	public int getRecept() {
		return recept;
	}

	public void setRecept(int recept) {
		this.recept = recept;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getIngID() {
		return ingID;
	}

	public void setIngID(String ingID) {
		this.ingID = ingID;
	}
	
}
