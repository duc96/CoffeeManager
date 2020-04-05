package vn.com.duan1.coffeemanagement.DataModel;

public class SanPham {
    private String maSP;
    private String maLoai;
    private String tenSP;
    private int giaSP;
    private int soLuongBan;

    public SanPham() {
    }

    public SanPham(String maSP, String maLoai, String tenSP, int giaSP, int soLuongBan) {
        this.maSP = maSP;
        this.maLoai = maLoai;
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.soLuongBan = soLuongBan;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(int giaSP) {
        this.giaSP = giaSP;
    }

    public int getSoLuongBan() {
        return soLuongBan;
    }

    public void setSoLuongBan(int soLuongBan) {
        this.soLuongBan = soLuongBan;
    }

    @Override
    public String toString() {
        return "SanPham{" +
                "maSP='" + maSP + '\'' +
                ", maLoai='" + maLoai + '\'' +
                ", tenSP='" + tenSP + '\'' +
                ", giaSP=" + giaSP +
                ", soLuongBan=" + soLuongBan +
                '}';
    }
}
