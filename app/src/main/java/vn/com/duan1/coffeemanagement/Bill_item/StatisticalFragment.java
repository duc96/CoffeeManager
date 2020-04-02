package vn.com.duan1.coffeemanagement.Bill_item;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

import vn.com.duan1.coffeemanagement.R;

public class StatisticalFragment extends Fragment {

    public StatisticalFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_statistical,null);
        return view;
    }
}
