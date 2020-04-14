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

import static vn.com.duan1.coffeemanagement.Menu_item.MenuFragment.khacs;


public class OthersFragment extends Fragment {

    RecyclerView rvOthers;
    public SanPhamAdapter sanPhamAdapter;

    public static int[] images = {R.mipmap.k_banhngot,
            R.mipmap.k_banhpia,
            R.mipmap.k_banhplan,
            R.mipmap.k_banhtrang_cay,
            R.mipmap.k_cavien_chien,
            R.mipmap.k_duiga_chien,
            R.mipmap.k_hamburger,
            R.mipmap.k_kemcuon,
            R.mipmap.k_khoaitay_chien,
            R.mipmap.k_snack,
            R.mipmap.k_traicay_to
    };

    DatabaseReference databaseReference;
    ArrayList<SanPham> khac;
    public OthersFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_others, container, false);
        rvOthers = view.findViewById(R.id.rvOthers);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvOthers.setLayoutManager(linearLayoutManager);

        khac = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("sanpham");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                khac.clear();

                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    SanPham item = data.getValue(SanPham.class);
                    if (item.getMaLoai().equals("khac")) {
                        khac.add(item);
                    }
                }
                capnhatgiaodien();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        sanPhamAdapter = new SanPhamAdapter(khac, getContext(), this);
        rvOthers.setAdapter(sanPhamAdapter);
        return view;
    }

    public void capnhatgiaodien() {
        sanPhamAdapter.notifyItemInserted(khacs.size());
        sanPhamAdapter.notifyDataSetChanged();
    }

}
