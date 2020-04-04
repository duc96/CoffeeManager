package vn.com.duan1.coffeemanagement.Menu_item;

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

public class MenuFragment extends Fragment {

    ViewPager viewPagerMenu;
    TabLayout tabLayoutMenu;


    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_menu,null);
        viewPagerMenu=view.findViewById(R.id.viewpagerMenu);
        tabLayoutMenu=view.findViewById(R.id.tablayoutMenu);

        MyFragmentAdapter adapter1 = new MyFragmentAdapter(getChildFragmentManager());
        viewPagerMenu.setAdapter(adapter1);
        tabLayoutMenu.addTab(tabLayoutMenu.newTab().setText("Nước uống"));
        tabLayoutMenu.addTab(tabLayoutMenu.newTab().setText("Thức Ăn"));
        tabLayoutMenu.addTab(tabLayoutMenu.newTab().setText("Khác"));

        viewPagerMenu.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutMenu));

        tabLayoutMenu.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerMenu.setCurrentItem(tabLayoutMenu.getSelectedTabPosition());

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
                    fragment=new DrinkFragment();
                    break;
                case 1:
                    fragment=new FoodFragment();
                    break;
                case 2:
                    fragment=new OthersFragment();
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
