package data.dto;

import java.io.Serializable;

public class ProductBatchDTO implements Serializable {

	private static final long serialVersionUID = -6440335065542506197L;
	private int pbId;//product batch Nr.
	private int receptId;//product recept
	private int status;
	
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
