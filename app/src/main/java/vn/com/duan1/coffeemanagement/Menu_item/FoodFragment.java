package vn.com.duan1.coffeemanagement.Menu_item;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import vn.com.duan1.coffeemanagement.Adapter.SanPhamAdapter;
import vn.com.duan1.coffeemanagement.R;

//import static vn.com.duan1.coffeemanagement.MainActivity.foods;
import static vn.com.duan1.coffeemanagement.Menu_item.MenuFragment.foods;


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

    public FoodFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_food, container, false);
        rvFood = view.findViewById(R.id.rvFood);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvFood.setLayoutManager(linearLayoutManager);
        sanPhamAdapter = new SanPhamAdapter(foods, getContext(), this);
        rvFood.setAdapter(sanPhamAdapter);
        capnhatgiaodien();
        return view;
    }

    public void capnhatgiaodien() {
        sanPhamAdapter.notifyItemInserted(foods.size());
        sanPhamAdapter.notifyDataSetChanged();

    }

}
