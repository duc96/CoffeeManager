package vn.com.duan1.coffeemanagement.DataModel;

public class SanPham {
    private String maSP;
    private String maLoai;
//    private int hinhSP;
    private String tenSP;
    private int giaSP;
    private String hinhSP;

    public SanPham(){
    }

    public SanPham(String hinhSP, String tenSP) {
        this.hinhSP = hinhSP;
        this.tenSP = tenSP;
    }

    public SanPham(String maSP, String maLoai, String tenSP, int giaSP) {
        this.maSP = maSP;
        this.maLoai = maLoai;
        this.tenSP = tenSP;
        this.giaSP = giaSP;
    }

    public SanPham(String maSP, String maLoai, String hinhSP, String tenSP, int giaSP) {
        this.maSP = maSP;
        this.maLoai = maLoai;
        this.hinhSP = hinhSP;
        this.tenSP = tenSP;
        this.giaSP = giaSP;
    }

    public String getHinhSP() {
        return hinhSP;
    }

    public void setHinhSP(String hinhSP) {
        this.hinhSP = hinhSP;
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


    @Override
    public String toString() {
        return "SanPham{" +
                "maSP='" + maSP + '\'' +
                ", maLoai='" + maLoai + '\'' +
                ", tenSP='" + tenSP + '\'' +
                ", giaSP=" + giaSP +
                '}';
    }
}
