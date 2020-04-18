package vn.com.duan1.coffeemanagement.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import vn.com.duan1.coffeemanagement.DataModel.HoaDonChiTiet;
import vn.com.duan1.coffeemanagement.DataModel.SanPham;
import vn.com.duan1.coffeemanagement.R;

import static vn.com.duan1.coffeemanagement.MainActivity.sanPhamss;

public class HoaDonChiTietAdapter extends ArrayAdapter<HoaDonChiTiet> {


    public HoaDonChiTietAdapter(@NonNull Context context, @NonNull List<HoaDonChiTiet> hoaDonChiTiets) {
        super(context, 0, hoaDonChiTiets);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        HoaDonChiTiet hoaDonChiTiet = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.one_item_hdct, parent, false);
        }
        TextView tvMaHDCT = convertView.findViewById(R.id.tv_maHDCT);
        TextView tvTenSP = convertView.findViewById(R.id.tv_TenSP);
        TextView tvSoLuong = convertView.findViewById(R.id.tv_SoLuong);
        tvMaHDCT.setText("Số hóa đơn ct: " + hoaDonChiTiet.getMaHDCT());
        tvSoLuong.setText("Số lượng : " + hoaDonChiTiet.getSoLuongMua());
        tvTenSP.setText("Tên sản phẩm: " + getTenSP(hoaDonChiTiet.getMaSP()));
        return convertView;
    }

    public String getTenSP(String maSP){
        for(SanPham  sanPham : sanPhamss){
            if(maSP.equalsIgnoreCase(sanPham.getMaSP())){
                return sanPham.getTenSP();
            }
        }
        return null;
    }
}
