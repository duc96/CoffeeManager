package vn.com.duan1.coffeemanagement.DataModel;

public class HoaDonChiTiet {
    private String maHDCT;
    private String maHD;
    private String maSP;
    private int soLuongMua;

    public HoaDonChiTiet(){}

    public HoaDonChiTiet(String maHDCT, String maHD, String maSP, int soLuongMua) {
        this.maHDCT = maHDCT;
        this.maHD = maHD;
        this.maSP = maSP;
        this.soLuongMua = soLuongMua;
    }

    public HoaDonChiTiet(String maSP, int soLuongMua){
        this.maSP = maSP;
        this.soLuongMua = soLuongMua;
    }

    public HoaDonChiTiet(String maHDCT, String maSP, int soLuongMua){
        this.maHDCT = maHDCT;
        this.maSP = maSP;
        this.soLuongMua = soLuongMua;
    }

    public String getMaHDCT() {
        return maHDCT;
    }

    public void setMaHDCT(String maHDCT) {
        this.maHDCT = maHDCT;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }

    @Override
    public String toString() {
        return "HoaDonChiTiet{" +
                "maHDCT='" + maHDCT + '\'' +
                ", maHD='" + maHD + '\'' +
                ", maSP='" + maSP + '\'' +
                ", soLuongMua=" + soLuongMua +
                '}';
    }
}
