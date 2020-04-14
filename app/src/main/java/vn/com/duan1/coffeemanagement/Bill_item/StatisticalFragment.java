package vn.com.duan1.coffeemanagement.Bill_item;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import vn.com.duan1.coffeemanagement.DataModel.HoaDon;
import vn.com.duan1.coffeemanagement.DataModel.HoaDonChiTiet;
import vn.com.duan1.coffeemanagement.DataModel.SanPham;
import vn.com.duan1.coffeemanagement.R;

import static vn.com.duan1.coffeemanagement.MainActivity.hoaDons;
import static vn.com.duan1.coffeemanagement.MainActivity.listHDCTs;
import static vn.com.duan1.coffeemanagement.MainActivity.sanPhamss;

public class StatisticalFragment extends Fragment {
    TextView tvTuNgay;
    TextView tvDenNgay;
    TextView tvGMV;
    TextView tvLike;
    Button btnThongKe;
    String tuNgay;
    String denNgay;
    PieChart pieChart;

    public StatisticalFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_statistical,null);

        tvTuNgay = view.findViewById(R.id.tvStart);
        tvDenNgay = view.findViewById(R.id.tvEnd);
        tvGMV = view.findViewById(R.id.tvGMV);
        btnThongKe = view.findViewById(R.id.btnUpdateInfor);
        pieChart = view.findViewById(R.id.pieChart);

        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtnThongKe();

            }
        });

        tvTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTuNgay();
            }
        });

        tvDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDenNgay();
            }
        });
        return view;
    }
    public void setTuNgay() {
        final Calendar calendar = Calendar.getInstance();
        final int ngay = calendar.get(Calendar.DATE);
        final int thang = calendar.get(Calendar.MONTH);
        final int nam = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                tuNgay = simpleDateFormat.format(calendar.getTime());
                tvTuNgay.setText(tuNgay);
            }
        }, nam, thang, ngay);
        datePickerDialog.show();

    }


    public void setDenNgay() {
        final Calendar calendar = Calendar.getInstance();
        final int ngay = calendar.get(Calendar.DATE);
        final int thang = calendar.get(Calendar.MONTH);
        final int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                denNgay = simpleDateFormat.format(calendar.getTime());
                tvDenNgay.setText(denNgay);
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }
    public void setBtnThongKe(){
        Date date1 = null;
        Date date2 = null;
        String strDate1 = tvTuNgay.getText().toString();
        String strDate2 = tvDenNgay.getText().toString();
        try {
            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(strDate1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            date2 = new SimpleDateFormat("dd/MM/yyyy").parse(strDate2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        double tong = 0;
        double tien = 0;
        int soLuong = 0;


        for (int i = 0; i < hoaDons.size(); i++){
            HoaDon hoaDon = hoaDons.get(i);
            try {
                if ((date1.before(new SimpleDateFormat("dd/MM/yyyy").parse(hoaDon.getNgayXuat())) || date1.equals(new SimpleDateFormat("dd/MM/yyyy").parse(hoaDon.getNgayXuat()))) && (date2.after(new SimpleDateFormat("dd/MM/yyyy").parse(hoaDon.getNgayXuat())) || date2.equals(new SimpleDateFormat("dd/MM/yyyy").parse(hoaDon.getNgayXuat())))){
                    for (int j = 0; j < listHDCTs.size(); j++){
                        HoaDonChiTiet hoaDonChiTiet = listHDCTs.get(j);
                        if (hoaDonChiTiet.getMaHD().equals(hoaDon.getMaHD())){
                            soLuong =  hoaDonChiTiet.getSoLuongMua();

                            for (int k = 0; k < sanPhamss.size(); k++){

                                if(sanPhamss.get(k).getMaSP().equals(hoaDonChiTiet.getMaSP())){
                                    tien = sanPhamss.get(k).getGiaSP();
                                    tong += (tien*soLuong);

                                }
                            }
                        }

                    }
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        List<HoaDon> hoaDonList = new ArrayList<>();
        List<HoaDonChiTiet> hoaDonChiTietList = new ArrayList<>();
        ArrayList<HoaDonChiTiet> arrayList = new ArrayList<>();

        for (int i = 0; i < hoaDons.size(); i++){
            HoaDon hoaDon = hoaDons.get(i);
            try {
                if ((date1.before(new SimpleDateFormat("dd/MM/yyyy").parse(hoaDon.getNgayXuat())) || date1.equals(new SimpleDateFormat("dd/MM/yyyy").parse(hoaDon.getNgayXuat()))) && (date2.after(new SimpleDateFormat("dd/MM/yyyy").parse(hoaDon.getNgayXuat())) || date2.equals(new SimpleDateFormat("dd/MM/yyyy").parse(hoaDon.getNgayXuat())))){
                    hoaDonList.add(hoaDon);
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        for (int j = 0; j < hoaDonList.size(); j++){
            for (HoaDonChiTiet h: listHDCTs){
                if (h.getMaHD().equals(hoaDonList.get(j).getMaHD())){
                    hoaDonChiTietList.add(h);
                }
            }
        }
        String tenSP = "";

        for (SanPham sanPham: sanPhamss){
            int sl = 0;

            for (HoaDonChiTiet hoaDonChiTiet : hoaDonChiTietList){
                if (sanPham.getMaSP().equals(hoaDonChiTiet.getMaSP())){
                    sl += hoaDonChiTiet.getSoLuongMua();

                }
            }

            arrayList.add(new HoaDonChiTiet(sanPham.getMaSP(), sl));

        }

        int max = 0;
        String maSP = "";
        int tongSL = 0;
        for (int i = 0; i <arrayList.size(); i++){
            for (int j = i +1; j < arrayList.size(); j++){
                if (arrayList.get(i).getSoLuongMua()>arrayList.get(j).getSoLuongMua()){
                    max = arrayList.get(i).getSoLuongMua();
                    maSP = arrayList.get(i).getMaSP();
                }else if (arrayList.get(i).getSoLuongMua() < arrayList.get(j).getSoLuongMua()){
                    max = arrayList.get(j).getSoLuongMua();
                    maSP = arrayList.get(j).getMaSP();
                }
            }
        }

        for (SanPham sanPham: sanPhamss){
            if (maSP.equals(sanPham.getMaSP())){
                tenSP = sanPham.getTenSP();
            }
        }
        tvGMV.setText("Doanh thu: " + tong);

        Collections.sort(arrayList, new Comparator<HoaDonChiTiet>() {
            @Override
            public int compare(HoaDonChiTiet t1, HoaDonChiTiet t2) {
                if(t1.getSoLuongMua() < t2.getSoLuongMua()){
                    return 1;
                } else {
                    if(t1.getSoLuongMua() == t2.getSoLuongMua()){
                        return 0;
                    } else {
                        return -1;
                    }
                }
            }
        });
        String ten1 = "";
        String ten2 = "";
        String ten0 = "";
        System.out.println(arrayList);
        for (int i = 0; i < sanPhamss.size(); i++){
            if (arrayList.get(1).getMaSP().equals(sanPhamss.get(i).getMaSP())){
                ten1 = sanPhamss.get(i).getTenSP();
            }
            if (arrayList.get(2).getMaSP().equals(sanPhamss.get(i).getMaSP())){
                ten2 = sanPhamss.get(i).getTenSP();
            }
            if (arrayList.get(0).getMaSP().equals(sanPhamss.get(i).getMaSP())){
                ten0 = sanPhamss.get(i).getTenSP();
            }
        }


        for (int i = 0; i < arrayList.size(); i++){
            tongSL += arrayList.get(i).getSoLuongMua();
        }

        float other = tongSL - (arrayList.get(1).getSoLuongMua() + arrayList.get(2).getSoLuongMua() + arrayList.get(0).getSoLuongMua());
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(arrayList.get(0).getSoLuongMua(), ten0));
        pieEntries.add(new PieEntry(arrayList.get(1).getSoLuongMua(), ten1));
        pieEntries.add(new PieEntry(arrayList.get(2).getSoLuongMua(), ten2));
        pieEntries.add(new PieEntry(other, "Loại khác"));

        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Biểu đồ thống kê");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("TOP LIKE !!");
        pieChart.animate();

    }
}
