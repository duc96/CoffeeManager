package vn.com.duan1.coffeemanagement.DataModel;

public class HoaDon {
    private String maHD;
    private String ngayXuat;
    private String nguoiLap;

    public HoaDon(){}

    public HoaDon(String maHD, String ngayXuat,String nguoiLap) {
        this.maHD = maHD;
        this.ngayXuat = ngayXuat;
        this.nguoiLap = nguoiLap;
    }

    public String getNguoiLap() {
        return nguoiLap;
    }

    public void setNguoiLap(String nguoiLap) {
        this.nguoiLap = nguoiLap;
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


    @Override
    public String toString() {
        return "HoaDon{" +
                "maHD='" + maHD + '\'' +
                ", ngayXuat='" + ngayXuat + '\'' +
                ", nguoiLap='" + nguoiLap + '\'' +
                '}';
    }
}
