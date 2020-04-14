package vn.com.duan1.coffeemanagement.DataModel;

public class CartHDCT {

    private String tenSP;
    private int soLuongMua;
    private int tongtien;

    public CartHDCT() {
    }

    public CartHDCT(String tenSP, int soLuongMua, int tongtien) {
        this.tenSP = tenSP;
        this.soLuongMua = soLuongMua;
        this.tongtien = tongtien;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    @Override
    public String toString() {
        return "CartHDCT{" +
                "tenSP='" + tenSP + '\'' +
                ", soLuongMua=" + soLuongMua +
                ", tongtien=" + tongtien +
                '}';
    }
}
