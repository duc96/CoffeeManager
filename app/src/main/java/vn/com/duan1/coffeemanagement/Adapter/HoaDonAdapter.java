package vn.com.duan1.coffeemanagement.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.com.duan1.coffeemanagement.Bill_item.BillHistoryFragment;
import vn.com.duan1.coffeemanagement.DAO.HoaDonChiTietDAO;
import vn.com.duan1.coffeemanagement.DataModel.HoaDon;
import vn.com.duan1.coffeemanagement.DataModel.HoaDonChiTiet;
import vn.com.duan1.coffeemanagement.R;

import static vn.com.duan1.coffeemanagement.MainActivity.hoaDons;
import static vn.com.duan1.coffeemanagement.MainActivity.listHDCTs;
import static vn.com.duan1.coffeemanagement.MainActivity.sanPhamss;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.ViewHolder> {
    static Context context;
    HoaDonChiTietDAO chiTietDAO;
    LinearLayoutManager mLayoutManager;
    HoaDonChiTiet hoaDonChiTiet;
    List<HoaDon> ds;
    BillHistoryFragment fr;

    public HoaDonAdapter(Context context, List<HoaDon> ds, BillHistoryFragment fr){
        this.context = context;
        this.ds = ds;
        this.fr = fr;
    }

    @NonNull
    @Override
    public HoaDonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context)
                .inflate(R.layout.one_item_bill, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final HoaDonAdapter.ViewHolder holder, final int position) {

        final HoaDon hoaDon = ds.get(position);
        holder.tvMaHD.setText("Số hóa đơn: "+hoaDon.getMaHD());
        holder.tvNgay.setText("Ngày lập: "+hoaDon.getNgayXuat());

        holder.tvNguoiLap.setText("Nhân viên xử lý: " + hoaDon.getNguoiLap());

        double tong = 0;
        for (int i = 0;i < listHDCTs.size(); i++){
            for (int j = 0; j < sanPhamss.size(); j++){
                if (hoaDon.getMaHD().equals(listHDCTs.get(i).getMaHD()) && listHDCTs.get(i).getMaSP().equals(sanPhamss.get(j).getMaSP())){
                    tong += sanPhamss.get(j).getGiaSP()*listHDCTs.get(i).getSoLuongMua();
                }
            }
        }

        holder.tvTongTien.setText("Tổng tiền: " + tong + " VNĐ");
        holder.btnXemCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view =LayoutInflater.from(context).inflate(R.layout.dialog_bill, null, false);
                TextView tvTongTien = view.findViewById(R.id.tv_BillTotal);

                double tong1 = 0;
                for (int i = 0;i < listHDCTs.size(); i++){
                    for (int j = 0; j < sanPhamss.size(); j++){
                        if (hoaDon.getMaHD().equals(listHDCTs.get(i).getMaHD()) && listHDCTs.get(i).getMaSP().equals(sanPhamss.get(j).getMaSP())){
                            tong1 += sanPhamss.get(j).getGiaSP()*listHDCTs.get(i).getSoLuongMua();
                        }
                    }
                }

                tvTongTien.setText(tong1 +" VNĐ");
                ListView lv = view.findViewById(R.id.lvHDCT);

                List list = new ArrayList();
                for (int i = 0; i < listHDCTs.size(); i++){
                    hoaDonChiTiet = listHDCTs.get(i);
                    if (hoaDonChiTiet.getMaHD().equals(hoaDon.getMaHD())){

                        list.add(hoaDonChiTiet);
                    }

                }
                HoaDonChiTietAdapter adapter = new HoaDonChiTietAdapter(context, list);
                lv.setAdapter(adapter);
                builder.setView(view);
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return ds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvMaHD, tvNgay, tvTongTien, tvNguoiLap;
        public Button btnXemCT;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMaHD = itemView.findViewById(R.id.tv_Id);
            tvNgay = itemView.findViewById(R.id.tv_Date);
            tvTongTien = itemView.findViewById(R.id.tv_Price);
            tvNguoiLap = itemView.findViewById(R.id.tv_SubmitBy);
            btnXemCT = itemView.findViewById(R.id.btnDetail);
        }
    }
}