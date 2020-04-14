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

public class FoodFragment extends Fragment {
    RecyclerView rvFood;
    private SanPhamAdapter sanPhamAdapter;

    public static int[] images = {R.mipmap.ta_banhcanh,
            R.mipmap.ta_banhmi_bth,
            R.mipmap.ta_banhmi_chaca,
            R.mipmap.ta_bunrieu,
            R.mipmap.ta_comchien_trungmuoi,
            R.mipmap.ta_goicuon,
            R.mipmap.ta_hutiu_nuoc,
            R.mipmap.ta_hutiu_xao,
            R.mipmap.ta_mi_xao_bo,
            R.mipmap.ta_mi_xao_thapcam,
            R.mipmap.ta_mi_xao_tom,
            R.mipmap.ta_mixao_trung,
            R.mipmap.ta_phobo,
            R.mipmap.ta_phocuon,
            R.mipmap.ta_phoga,
            R.mipmap.ta_soup_cua,
            R.mipmap.ta_soup_raucu
    };

    DatabaseReference databaseReference;
    public ArrayList<SanPham> food;

    public FoodFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_food, container, false);
        rvFood = view.findViewById(R.id.rvFood);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvFood.setLayoutManager(linearLayoutManager);
        food = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("sanpham");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                food.clear();

                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    SanPham item = data.getValue(SanPham.class);
                    if (item.getMaLoai().equals("food")) {
                        food.add(item);
                    }
                }
                capnhatgiaodien();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        sanPhamAdapter = new SanPhamAdapter(food, getContext(), this);
        rvFood.setAdapter(sanPhamAdapter);
        return view;
    }

    public void capnhatgiaodien() {
        sanPhamAdapter.notifyItemInserted(food.size());
        sanPhamAdapter.notifyDataSetChanged();

    }

}
