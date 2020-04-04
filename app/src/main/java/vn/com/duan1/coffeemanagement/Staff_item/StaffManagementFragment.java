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
    public static int sttQuanLy;
    public static int sttNhanVien;

    public StaffManagementFragment() {
        // Required empty public constructor
    }

    public void phanNhom() {
        quanLys.clear();
        nhanViens.clear();
        for (NguoiDung nguoiDung : nguoiDungs) {
            if (nguoiDung.getUserID().contains("ql")) {
                quanLys.add(nguoiDung);
            } else if (nguoiDung.getUserID().contains("nv")) {
                nhanViens.add(nguoiDung);
            }
        }
        if (quanLys.size() > 0) {
            String sttQL = quanLys.get(quanLys.size() - 1).getUserID();
            String middle1 = sttQL.substring(sttQL.indexOf("l") + 1, sttQL.length());
            System.out.println(middle1);
            sttQuanLy = Integer.parseInt(middle1);
        } else {
            sttQuanLy = 0;
        }
        if (nhanViens.size() > 0) {
            String sttNV = nhanViens.get(nhanViens.size() - 1).getUserID();
            String middle2 = sttNV.substring(sttNV.indexOf("v") + 1, sttNV.length());
            System.out.println(middle2);
            sttNhanVien = Integer.parseInt(middle2);
        } else {
            sttNhanVien = 0;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_staff_management, null);
        viewPagerStaff = view.findViewById(R.id.viewpagerStaff);
        tabLayoutStaff = view.findViewById(R.id.tablayoutStaff);
        phanNhom();
        MyFragmentAdapter adapter = new MyFragmentAdapter(getChildFragmentManager());
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

    public class MyFragmentAdapter extends FragmentStatePagerAdapter {

        public MyFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            switch (position) {
                case 0:
                    fragment = new AccountLoginFragment();
                    break;
                case 1:
                    fragment = new StaffFragment();
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
