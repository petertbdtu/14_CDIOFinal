package data.dto;

public class ProductBatchDTO {
	private int pbNr;//product batch Nr.
	private int receptId;//product recept
	private String status;
	
	public ProductBatchDTO() {
		
	}

	public int getPbNr() {
		return pbNr;
	}

	public void setPbNr(int pbNr) {
		this.pbNr = pbNr;
	}

	public int getRecept() {
		return receptId;
	}

	public void setRecept(int recept) {
		this.receptId = recept;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
