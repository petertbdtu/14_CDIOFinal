package data.dto;

import java.util.ArrayList;

public class ProductBatchDTO {
	private int pbNr;//product batch Nr.
	private int recept;//product recept
	private String date;
	private String status;
	private ArrayList<Integer> ingID = new ArrayList<Integer>();
	private ArrayList<Integer> ingbID = new ArrayList<Integer>();
	private ArrayList<Double> amount = new ArrayList<Double>();
	
	public ProductBatchDTO() {
		
	}
	

	public ArrayList<Integer> getIngbID() {
		return ingbID;
	}


	public void setIngbID(ArrayList<Integer> ingbID) {
		this.ingbID = ingbID;
	}


	public void setIngID(ArrayList<Integer> ingID) {
		this.ingID = ingID;
	}


	public void setAmount(ArrayList<Double> amount) {
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
