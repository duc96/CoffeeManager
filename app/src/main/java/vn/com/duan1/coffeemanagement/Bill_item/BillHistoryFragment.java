package vn.com.duan1.coffeemanagement.Bill_item;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import vn.com.duan1.coffeemanagement.Adapter.HoaDonAdapter;
import vn.com.duan1.coffeemanagement.DAO.HoaDonDAO;
import vn.com.duan1.coffeemanagement.DataModel.HoaDon;
import vn.com.duan1.coffeemanagement.R;

import static vn.com.duan1.coffeemanagement.MainActivity.hoaDons;

public class BillHistoryFragment extends Fragment {

    RecyclerView rcvHoaDon;
    HoaDonAdapter hoaDonAdapter;
    HoaDonDAO hoaDonDAO;
    HoaDon hoaDon;
    LinearLayoutManager layoutManager;

    public BillHistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_bill_history,null);

        rcvHoaDon = view.findViewById(R.id.rvBill);

        hoaDonDAO = new HoaDonDAO(getActivity(), this);
        layoutManager = new LinearLayoutManager(getActivity());
        rcvHoaDon.setLayoutManager(layoutManager);

        hoaDonAdapter =  new HoaDonAdapter(getActivity(), hoaDons, this);
        rcvHoaDon.setAdapter(hoaDonAdapter);


        return view;
    }
    public void capnhatLV(){

        hoaDonAdapter.notifyItemInserted(hoaDons.size());

        hoaDonAdapter.notifyDataSetChanged();
    }
}
