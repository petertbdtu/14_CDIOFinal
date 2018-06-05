package data.dto;

public class UserDTO {
    private int usrID;
    private String usrName;
    private String ini;
    private String cpr;

    byte roles; //(byte)0b00000000 - 0b00001111;

    public UserDTO() {

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

    public byte getRoles() {
        return roles;
    }

    public void setRoles(byte roles) {
        this.roles = roles;
    }
}