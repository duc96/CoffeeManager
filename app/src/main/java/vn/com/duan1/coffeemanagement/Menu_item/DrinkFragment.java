package vn.com.duan1.coffeemanagement.Menu_item;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import vn.com.duan1.coffeemanagement.Adapter.SanPhamAdapter;
import vn.com.duan1.coffeemanagement.DataModel.SanPham;
import vn.com.duan1.coffeemanagement.R;

public class DrinkFragment extends Fragment {
    private RecyclerView rvDrink;
    private SanPhamAdapter sanPhamAdapter;

    public static int[] images = {R.mipmap.seven_up,
            R.mipmap.ca_phe_den,
            R.mipmap.ca_phe_den_nong,
            R.mipmap.ca_phe_sua,
            R.mipmap.ca_phe_sua_nong,
            R.mipmap.coca_cola,
            R.mipmap.da_chanh,
            R.mipmap.bac_xiu_da,
            R.mipmap.tra_gung,
            R.mipmap.tra_sua_chan_chau_duong_den,
            R.mipmap.cam_ep,
            R.mipmap.mirinda,
            R.mipmap.monter,
            R.mipmap.moutanin_dew,
            R.mipmap.spire,
            R.mipmap.sting
    };

    DatabaseReference databaseReference;
    public ArrayList<SanPham> drink;

    public DrinkFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_drink, container, false);

        rvDrink = view.findViewById(R.id.rvDrink);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvDrink.setLayoutManager(linearLayoutManager);

        drink = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("sanpham");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                drink.clear();

                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    SanPham item = data.getValue(SanPham.class);
                    if (item.getMaLoai().equals("drink")) {
                        drink.add(item);
                    }
                }
                capnhatgiaodien();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        sanPhamAdapter = new SanPhamAdapter(drink, getContext(), this);

        rvDrink.setAdapter(sanPhamAdapter);

        return view;
    }

    public void capnhatgiaodien() {
        sanPhamAdapter.notifyItemInserted(drink.size());
        sanPhamAdapter.notifyDataSetChanged();
    }

}