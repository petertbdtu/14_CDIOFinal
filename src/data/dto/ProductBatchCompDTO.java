package dto;


public class ProductBatchCompDTO 
{
	private int prodBatchID;
	private int ingbatchID;
	private double tara;
	private double netto;
	private int usrID;
	public ProductBatchCompDTO() 
	{

	}
	public int getProdBatchID() {
		return prodBatchID;
	}
	public void setProdBatchID(int prodBatchID) {
		this.prodBatchID = prodBatchID;
	}
	public int getIngbatchID() {
		return ingbatchID;
	}
	public void setIngbatchID(int ingbatchID) {
		this.ingbatchID = ingbatchID;
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
	public int getUsrID() {
		return usrID;
	}
	public void setUsrID(int usrID) {
		this.usrID = usrID;
	}


}
