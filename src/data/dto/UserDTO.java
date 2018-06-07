package data.dto;

public class UserDTO {
    private int usrID;
    private String usrName;
    private String ini;
    private String cpr;
    private boolean isAdmin;
    private boolean isProdLead;
    private boolean isPharma;
    private boolean isLabTech;
    private boolean active;

    
   

    public UserDTO() {

    }

    public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isProdLead() {
		return isProdLead;
	}

	public void setProdLead(boolean isProdLead) {
		this.isProdLead = isProdLead;
	}

	public boolean isPharma() {
		return isPharma;
	}

	public void setPharma(boolean isPharma) {
		this.isPharma = isPharma;
	}

	public boolean isLabTech() {
		return isLabTech;
	}

	public void setLabTech(boolean isLabTech) {
		this.isLabTech = isLabTech;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
    public int getUsrID() {
        return usrID;
    }

    public void setUsrID(int usrID) {
        this.usrID = usrID;
    }

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public String getIni() {
        return ini;
    }

    public void setIni(String ini) {
        this.ini = ini;
    }

    public String getCpr() {
        return cpr;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

}