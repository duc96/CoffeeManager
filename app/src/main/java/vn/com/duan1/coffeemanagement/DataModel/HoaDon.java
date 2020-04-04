package vn.com.duan1.coffeemanagement.DataModel;

public class HoaDon {
    private String maHD;
    private String ngayXuat;
    private String trangThai;

    public HoaDon(){}

    public HoaDon(String maHD, String ngayXuat, String trangThai) {
        this.maHD = maHD;
        this.ngayXuat = ngayXuat;
        this.trangThai = trangThai;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getNgayXuat() {
        return ngayXuat;
    }

    public void setNgayXuat(String ngayXuat) {
        this.ngayXuat = ngayXuat;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "HoaDon{" +
                "maHD='" + maHD + '\'' +
                ", ngayXuat='" + ngayXuat + '\'' +
                ", trangThai='" + trangThai + '\'' +
                '}';
    }
}
