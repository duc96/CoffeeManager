package vn.com.duan1.coffeemanagement.Bill_item;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

import vn.com.duan1.coffeemanagement.R;

public class BillFragment extends Fragment {
    ViewPager viewPagerBill;
    TabLayout tabLayoutBill;


    public BillFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_bill,null);
        viewPagerBill=view.findViewById(R.id.viewpagerBill);
        tabLayoutBill=view.findViewById(R.id.tablayoutBill);

        MyFragmentAdapter adapter2 =new MyFragmentAdapter(getChildFragmentManager());
        viewPagerBill.setAdapter(adapter2);
        tabLayoutBill.addTab(tabLayoutBill.newTab().setText("Lịch sử"));
        tabLayoutBill.addTab(tabLayoutBill.newTab().setText("Chi phí"));
        tabLayoutBill.addTab(tabLayoutBill.newTab().setText("Thống kê"));

        viewPagerBill.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutBill));
        tabLayoutBill.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerBill.setCurrentItem(tabLayoutBill.getSelectedTabPosition());

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
                    fragment=new BillHistoryFragment();
                    break;
                case 1:
                    fragment=new CostFragment();
                    break;
                case 2:
                    fragment=new StatisticalFragment();
                    break;
                default:
                    return null;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

}
