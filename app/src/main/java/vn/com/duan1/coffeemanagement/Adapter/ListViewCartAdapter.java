package vn.com.duan1.coffeemanagement.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import vn.com.duan1.coffeemanagement.DataModel.CartHDCT;
import vn.com.duan1.coffeemanagement.R;

public class ListViewCartAdapter extends ArrayAdapter<CartHDCT> {

    ArrayList<CartHDCT> list;
    Context context;

    public ListViewCartAdapter(@NonNull Context context, @NonNull ArrayList<CartHDCT> hoaDonChiTiets) {
        super(context, 0, hoaDonChiTiets);
        this.list = hoaDonChiTiets;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        CartHDCT cartHDCT = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }
        TextView tvTenSP = convertView.findViewById(R.id.tv_ten_sp);
        TextView tvSoLuongMua = convertView.findViewById(R.id.tv_soluong_sp);
        TextView tvThanhTien = convertView.findViewById(R.id.tv_thanhtien_sp);

        tvTenSP.setText(cartHDCT.getTenSP());
        tvSoLuongMua.setText(cartHDCT.getSoLuongMua()+"");
        tvThanhTien.setText(cartHDCT.getTongtien()+"");

        return convertView;
    }


}
