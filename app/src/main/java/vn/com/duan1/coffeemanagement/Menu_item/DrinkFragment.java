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

//import static vn.com.duan1.coffeemanagement.MainActivity.drinks;
import static vn.com.duan1.coffeemanagement.MainActivity.sanPhamss;
import static vn.com.duan1.coffeemanagement.Menu_item.MenuFragment.drinks;


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

    private RecyclerView.LayoutManager layoutManager;

    public DrinkFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_drink, container, false);

        rvDrink = view.findViewById(R.id.rvDrink);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvDrink.setLayoutManager(linearLayoutManager);

        sanPhamAdapter = new SanPhamAdapter(drinks, getContext(), this);
        rvDrink.setAdapter(sanPhamAdapter);

        capnhatgiaodien();

        return view;
    }

    public void capnhatgiaodien() {
        sanPhamAdapter.notifyItemInserted(drinks.size());
        sanPhamAdapter.notifyDataSetChanged();
    }

}