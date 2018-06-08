package data.dto;

import data.dao.*;
import data.idao.*;

import java.io.Serializable;

public class ProductBatchCompDTO implements Serializable{
	private int pbID;
	private int ibID;
	private double tara;
	private double netto;
	private int usrID;
	public ProductBatchCompDTO() 
	{

	}
	// getters and setters below
	public int getpbID() {
		return pbID;
	}
	public void setpbID(int pbID) {
		this.pbID = pbID;
	}
	public int getibID() {
		return ibID;
	}
	public void setibID(int ibID) {
		this.ibID = ibID;
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
