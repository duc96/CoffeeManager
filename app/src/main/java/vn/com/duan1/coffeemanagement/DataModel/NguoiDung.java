package vn.com.duan1.coffeemanagement.DataModel;

public class NguoiDung {
    private String userID;
    private String password;
    private String ten;
    private String CMND;
    private String sdt;
    public NguoiDung(){

    }

    public NguoiDung(String userID, String password) {
        this.userID = userID;
        this.password = password;
    }

    public NguoiDung(String userID, String password, String ten, String CMND, String sdt) {
        this.userID = userID;
        this.password = password;
        this.ten = ten;
        this.CMND = CMND;
        this.sdt = sdt;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
