package vn.com.duan1.coffeemanagement.Menu_item;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import vn.com.duan1.coffeemanagement.Adapter.ImageAdapter;
import vn.com.duan1.coffeemanagement.DAO.SanPhamDAO;
import vn.com.duan1.coffeemanagement.DataModel.HoaDonChiTiet;
import vn.com.duan1.coffeemanagement.DataModel.SanPham;
import vn.com.duan1.coffeemanagement.R;

import static vn.com.duan1.coffeemanagement.MainActivity.idAfterLogin;
import static vn.com.duan1.coffeemanagement.MainActivity.sanPhamss;

public class MenuFragment extends Fragment {

    private ViewPager viewPagerMenu;
    private TabLayout tabLayoutMenu;
    private RecyclerView.LayoutManager layoutManager;

    String loai, maSP;
    int size = 0;

    private RecyclerView rcImage;
    private FloatingActionButton flAdd;
    private FloatingActionButton flCart;
    private SanPhamDAO sanPhamDAO;
    private ImageAdapter imageAdapter;

    public static ArrayList<SanPham> drinks = new ArrayList<>();
    public static ArrayList<SanPham> foods = new ArrayList<>();
    public static ArrayList<SanPham> khacs = new ArrayList<>();
    DatabaseReference mdata;
    public static ArrayList<SanPham> sanphamss = new ArrayList<>();
    public static ArrayList<HoaDonChiTiet> hdcts = new ArrayList<HoaDonChiTiet>();

    public MenuFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_menu, null);

        // map cac thanh phan cua layout
        viewPagerMenu = view.findViewById(R.id.viewpagerMenu);
        tabLayoutMenu = view.findViewById(R.id.tablayoutMenu);
        sanPhamDAO = new SanPhamDAO(getActivity());
        flCart = view.findViewById(R.id.cart);
        flAdd = view.findViewById(R.id.add);
        MyFragmentAdapter adapter1 = new MyFragmentAdapter(getChildFragmentManager());
        viewPagerMenu.setAdapter(adapter1);

        // set tieu de cho cac tablayout
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

        // doc danh sach san pham da co tren database
        mdata = FirebaseDatabase.getInstance().getReference("sanpham");
        mdata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                sanphamss.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    SanPham sanPham = data.getValue(SanPham.class);
                    sanphamss.add(sanPham);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


        if (idAfterLogin.substring(0, 2).equals("nv")) {
            flAdd.setEnabled(false);
            flAdd.setVisibility(View.GONE);
        } else {

            flAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // chia san pham theo loai
                    drinks.clear();
                    foods.clear();
                    khacs.clear();

                    for (int i = 0; i < sanphamss.size(); i++) {
                        if (sanphamss.get(i).getMaLoai().equals("drink")) {
                            drinks.add(sanphamss.get(i));
                        }
                        if (sanphamss.get(i).getMaLoai().equals("food")) {
                            foods.add(sanphamss.get(i));
                        }
                        if (sanphamss.get(i).getMaLoai().equals("khac")) {
                            khacs.add(sanphamss.get(i));
                        }
                    }

                    System.out.println("drinks: " + drinks.size());
                    System.out.println("foods: " + foods.size());
                    System.out.println("khacs: " + khacs.size());

                    // lay loai cho san pham
                    if (tabLayoutMenu.getSelectedTabPosition() == 0) {
                        loai = "drink";
                    } else if (tabLayoutMenu.getSelectedTabPosition() == 1) {
                        loai = "food";
                    } else {
                        loai = "khac";
                    }

                    // lay masp
                    if (tabLayoutMenu.getSelectedTabPosition() == 0) {
                        size = drinks.size();
                        maSP = "NU" + size;
                    }
                    if (tabLayoutMenu.getSelectedTabPosition() == 1) {
                        size = foods.size();
                        maSP = "TA" + size;

                    }
                    if (tabLayoutMenu.getSelectedTabPosition() == 2) {
                        size = khacs.size();
                        maSP = "K" + size;

                    }
                    for (int i = 0; i < drinks.size(); i++) {
                        if (drinks.get(i).getMaSP().equals(maSP)) {
                            size++;
                            maSP = "NU" + size;
                        }
                    }
                    for (int i = 0; i < foods.size(); i++) {
                        if (foods.get(i).getMaSP().equals(maSP)) {
                            size++;
                            maSP = "TA" + size;
                        }
                    }
                    for (int i = 0; i < khacs.size(); i++) {
                        if (khacs.get(i).getMaSP().equals(maSP)) {
                            size++;
                            maSP = "K" + size;
                        }
                    }

                    // tao  dialog khi an nut add
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    final LayoutInflater inf = getLayoutInflater();
                    final View viewdialog = inf.inflate(R.layout.dialog_add_menu, null);
                    builder.setView(viewdialog);

                    final ImageView ivPhoto = viewdialog.findViewById(R.id.ivPhoto);
                    final EditText edtItemName = viewdialog.findViewById(R.id.edtItemName);
                    final EditText edtItemPrice = viewdialog.findViewById(R.id.edtItemPrice);

                    // chon anh cho san pham
                    ivPhoto.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            //gan list anh len layout
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                            View view2 = LayoutInflater.from(viewdialog.getContext()).inflate(R.layout.rv_image, null);
                            rcImage = view2.findViewById(R.id.rcImage);
                            layoutManager = new GridLayoutManager(viewdialog.getContext(), 2);
                            rcImage.setHasFixedSize(true);
                            rcImage.setLayoutManager(layoutManager);

                            //chon list anh cho tung loai san pham
                            if (tabLayoutMenu.getSelectedTabPosition() == 0) {
                                imageAdapter = new ImageAdapter(DrinkFragment.images, getContext());
                            } else if (tabLayoutMenu.getSelectedTabPosition() == 1) {
                                imageAdapter = new ImageAdapter(FoodFragment.images, getContext());
                            } else {
                                imageAdapter = new ImageAdapter(OthersFragment.images, getContext());
                            }

                            rcImage.setAdapter(imageAdapter);

                            // gan anh cho san pham
                            if (ImageAdapter.id_image != 0) {
                                ivPhoto.setImageResource(ImageAdapter.id_image);
                            } else {
                                ivPhoto.setImageResource(R.drawable.ic_camera);
                            }

                            builder1.setView(view2)
                                    .setTitle("Chọn ảnh")
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            ivPhoto.setImageResource(ImageAdapter.id_image);
                                        }
                                    })
                                    .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    });
                            builder1.show();
                        }
                    });

                    builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            String Name = edtItemName.getText().toString();
                            String Price = edtItemPrice.getText().toString();

                            sanPhamDAO.themSanPham(new SanPham(maSP, loai, ImageAdapter.id_image, Name, Integer.parseInt(Price)));

                        }
                    });

                    builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    builder.show();
                }
            });

        }

        flCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View view1 = LayoutInflater.from(getContext()).inflate(R.layout.dialog_cart,null);
                builder.setView(view1)
                        .setTitle("Các hóa đơn chưa thanh toán")
                        .setNegativeButton("Quay lại", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.show();
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
                    fragment = new DrinkFragment();
                    break;
                case 1:
                    fragment = new FoodFragment();
                    break;
                case 2:
                    fragment = new OthersFragment();
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
