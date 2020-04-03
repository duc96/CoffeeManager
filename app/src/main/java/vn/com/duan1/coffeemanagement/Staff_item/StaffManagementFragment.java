package vn.com.duan1.coffeemanagement.Staff_item;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import vn.com.duan1.coffeemanagement.DataModel.NguoiDung;
import vn.com.duan1.coffeemanagement.R;

import static vn.com.duan1.coffeemanagement.LoginActivity.nguoiDungs;

public class StaffManagementFragment extends Fragment {
    ViewPager viewPagerStaff;
    TabLayout tabLayoutStaff;
    public static ArrayList<NguoiDung> quanLys = new ArrayList<>();
    public static ArrayList<NguoiDung> nhanViens = new ArrayList<>();

    public StaffManagementFragment() {
        // Required empty public constructor
    }
    public void phanNhom(){
        quanLys.clear();
        nhanViens.clear();
        for(NguoiDung nguoiDung: nguoiDungs){
            if(nguoiDung.getUserID().contains("ql")){
                quanLys.add(nguoiDung);
            }
            else if(nguoiDung.getUserID().contains("nv")){
                nhanViens.add(nguoiDung);
            }
        }
        Log.d("size", quanLys.size()+"");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_staff_management,null);
        viewPagerStaff=view.findViewById(R.id.viewpagerStaff);
        tabLayoutStaff=view.findViewById(R.id.tablayoutStaff);
        phanNhom();
        MyFragmentAdapter adapter=new MyFragmentAdapter(getChildFragmentManager());
        viewPagerStaff.setAdapter(adapter);
        tabLayoutStaff.addTab(tabLayoutStaff.newTab().setText("Quản lý"));
        tabLayoutStaff.addTab(tabLayoutStaff.newTab().setText("Nhân viên"));

        viewPagerStaff.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutStaff));
        tabLayoutStaff.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerStaff.setCurrentItem(tabLayoutStaff.getSelectedTabPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

    public class MyFragmentAdapter extends FragmentStatePagerAdapter
    {

        public MyFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            switch (position)
            {
                case 0:
                    fragment=new AccountLoginFragment();
                    break;
                case 1:
                    fragment=new StaffFragment();
                    break;
                default:
                    return null;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

}
