package data.dto;

public class ProductBatchDTO {
	private int pbId;//product batch Nr.
	private int receptId;//product recept
	private String status;
	
	public ProductBatchDTO() {
		
	}

	public int getPbId() {
		return pbId;
	}

	public void setPbId(int pbId) {
		this.pbId = pbId;
	}

	public int getReceptId() {
		return receptId;
	}

	public void setReceptId(int receptId) {
		this.receptId = receptId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
