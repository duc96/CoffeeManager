package vn.com.duan1.coffeemanagement.Menu_item;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

//import vn.com.duan1.coffeemanagement.Adapter.ImageAdapter;
import vn.com.duan1.coffeemanagement.Adapter.ListViewCartAdapter;
import vn.com.duan1.coffeemanagement.DAO.HoaDonChiTietDAO;
import vn.com.duan1.coffeemanagement.DAO.HoaDonDAO;
import vn.com.duan1.coffeemanagement.DAO.SanPhamDAO;
import vn.com.duan1.coffeemanagement.DataModel.CartHDCT;
import vn.com.duan1.coffeemanagement.DataModel.HoaDon;
import vn.com.duan1.coffeemanagement.DataModel.HoaDonChiTiet;
import vn.com.duan1.coffeemanagement.DataModel.NonUI;
import vn.com.duan1.coffeemanagement.DataModel.SanPham;
import vn.com.duan1.coffeemanagement.R;

import static vn.com.duan1.coffeemanagement.MainActivity.hoaDons;
import static vn.com.duan1.coffeemanagement.MainActivity.idAfterLogin;
import static vn.com.duan1.coffeemanagement.MainActivity.nguoiDungs;
import static vn.com.duan1.coffeemanagement.MainActivity.sanPhamss;

public class MenuFragment extends Fragment {

    private ViewPager viewPagerMenu;
    private TabLayout tabLayoutMenu;
    private RecyclerView.LayoutManager layoutManager;
    NonUI nonUI;

    String loai, maSP;
    int size = 0;

    private RecyclerView rcImage;
    private FloatingActionButton flAdd;
    private FloatingActionButton flCart;
    private SanPhamDAO sanPhamDAO;
//    private ImageAdapter imageAdapter;

    public static ArrayList<SanPham> drinks = new ArrayList<>();
    public static ArrayList<SanPham> foods = new ArrayList<>();
    public static ArrayList<SanPham> khacs = new ArrayList<>();
    DatabaseReference mdata;
    public static ArrayList<SanPham> sanphamss = new ArrayList<>();
    public static ArrayList<CartHDCT> hdcts = new ArrayList<CartHDCT>();

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

        //an button Add khi dang nhap la nhan vien
        if (idAfterLogin.substring(0, 2).equals("nv")) {
            flAdd.setEnabled(false);
            flAdd.setVisibility(View.GONE);
        } else {

            // set su kien cho button dang nhap
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

//                    System.out.println("drinks: " + drinks.size());
//                    System.out.println("foods: " + foods.size());
//                    System.out.println("khacs: " + khacs.size());

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

//                    final ImageView ivPhoto = viewdialog.findViewById(R.id.ivPhoto);

                    // map cac thanh phan cua dialog add
                    final EditText edtItemLinkImage = viewdialog.findViewById(R.id.edtChonLinkAnh);
                    final ImageView imvAnh = viewdialog.findViewById(R.id.imvAnh);
                    final Button btnKiemTra = viewdialog.findViewById(R.id.btnCheck);
                    final EditText edtItemName = viewdialog.findViewById(R.id.edtItemName);
                    final EditText edtItemPrice = viewdialog.findViewById(R.id.edtItemPrice);

                    // set su kien cho nut kiem tra link anh san pham
                    btnKiemTra.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (edtItemLinkImage.getText().toString().equals("")) {
                                nonUI = new NonUI(getContext());
                                nonUI.toast("Bạn chưa nhập link hình ảnh");
                            } else {
                                Picasso.with(getContext()).load(edtItemLinkImage.getText().toString()).into(imvAnh);
                            }

                        }
                    });
                    // chon anh cho san pham
//                    ivPhoto.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//
//                            //gan list anh len layout
//                            AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
//                            View view2 = LayoutInflater.from(viewdialog.getContext()).inflate(R.layout.rv_image, null);
//                            rcImage = view2.findViewById(R.id.rcImage);
//                            layoutManager = new GridLayoutManager(viewdialog.getContext(), 2);
//                            rcImage.setHasFixedSize(true);
//                            rcImage.setLayoutManager(layoutManager);
//
//                            //chon list anh cho tung loai san pham
//                            if (tabLayoutMenu.getSelectedTabPosition() == 0) {
//                                imageAdapter = new ImageAdapter(DrinkFragment.images, getContext());
//                            } else if (tabLayoutMenu.getSelectedTabPosition() == 1) {
//                                imageAdapter = new ImageAdapter(FoodFragment.images, getContext());
//                            } else {
//                                imageAdapter = new ImageAdapter(OthersFragment.images, getContext());
//                            }
//
//                            rcImage.setAdapter(imageAdapter);
//
//                            // gan anh cho san pham
//                            if (ImageAdapter.id_image != 0) {
//                                ivPhoto.setImageResource(ImageAdapter.id_image);
//                            } else {
//                                ivPhoto.setImageResource(R.drawable.ic_camera);
//                            }
//
//                            builder1.setView(view2)
//                                    .setTitle("Chọn ảnh")
//                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialog, int which) {
//                                            ivPhoto.setImageResource(ImageAdapter.id_image);
//                                        }
//                                    })
//                                    .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialog, int which) {
//
//                                        }
//                                    });
//                            builder1.show();
//                        }
//                    });

                    builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            String Name = edtItemName.getText().toString();
                            String Price = edtItemPrice.getText().toString();
                            String linkAnh = edtItemLinkImage.getText().toString();

//                            sanPhamDAO.themSanPham(new SanPham(maSP, loai, ImageAdapter.id_image, Name, Integer.parseInt(Price)));

                            // them san pham
                            sanPhamDAO.themSanPham(new SanPham(maSP, loai, linkAnh, Name, Integer.parseInt(Price)));
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

        // set su kien cho button gio hang
        flCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View view1 = LayoutInflater.from(getContext()).inflate(R.layout.dialog_cart, null);

                // map cac thanh phan cua dialog gio hang
                final TextView maHD = view1.findViewById(R.id.tv_ma_hd);
                TextView total = view1.findViewById(R.id.tv_total);
                ListView lv = view1.findViewById(R.id.lv_cart_hdct);
                final TextView ngaylaphd = view1.findViewById(R.id.tv_ngay_lap_hd);
                final TextView nguoilaphd = view1.findViewById(R.id.tv_nguoi_lap_hd);

                ListViewCartAdapter listViewCartAdapter = new ListViewCartAdapter(getContext(), hdcts);
                lv.setAdapter(listViewCartAdapter);
                listViewCartAdapter.notifyDataSetChanged();

                //lay tong tien cua hoa don
                int tong = 0;
                for (int i = 0; i < hdcts.size(); i++) {
                    tong += hdcts.get(i).getTongtien();
                }

                total.setText(tong + "");

                //lay ngay hien tai
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Calendar cal = Calendar.getInstance();
                String s = dateFormat.format(cal.getTime());

                ngaylaphd.setText(s);
                String name = "null";
                for (int i = 0; i < nguoiDungs.size(); i++) {
                    if (idAfterLogin.equals(nguoiDungs.get(i).getUserID())) {
                        name = nguoiDungs.get(i).getTen();
                    }
                }

//                System.out.println(nguoiDungs);
                nguoilaphd.setText(name);

                // lay ma hoa don
                int stt = 0;
                String ma = "HD0";
                for (int i = 0; i < hoaDons.size(); i++) {
                    stt = hoaDons.size();
                    ma = "HD" + stt;
                }

                maHD.setText(ma);

                builder.setView(view1)
                        .setPositiveButton("Xuất hóa đơn", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                HoaDonDAO hoaDonDAO = new HoaDonDAO(getContext());
                                hoaDonDAO.insert(new HoaDon(maHD.getText().toString(), ngaylaphd.getText().toString(), nguoilaphd.getText().toString()));

                                HoaDonChiTietDAO hoaDonChiTietDAO = new HoaDonChiTietDAO(getContext());
                                String mahdct = "null";
                                String mahd = "null";
                                String masp = "null";
                                int soluongmua;
                                for (int i = 0; i < hdcts.size(); i++) {
                                    // lay ma hdct
                                    mahdct = "hdct" + i;
                                    //lay ma hoa don
                                    mahd = maHD.getText().toString();
                                    // lay ma san pham
                                    for (int j = 0; j < sanPhamss.size(); j++) {
                                        if (hdcts.get(i).getTenSP().equals(sanPhamss.get(j).getTenSP())) {
                                            masp = sanPhamss.get(j).getMaSP();
                                        }
                                    }
                                    //lay so luong mua
                                    soluongmua = hdcts.get(i).getSoLuongMua();

                                    // them moi hoa don chi tiet
                                    hoaDonChiTietDAO.insert(new HoaDonChiTiet(mahdct, mahd, masp, soluongmua));
                                }

                                //sau khi xuat hoa don thi xoa danh sach hdct tam
                                hdcts.clear();


                            }
                        })
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
