package vn.com.duan1.coffeemanagement;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import vn.com.duan1.coffeemanagement.Account_item.AccountInforFragment;
import vn.com.duan1.coffeemanagement.Bill_item.BillFragment;
import vn.com.duan1.coffeemanagement.DAO.HoaDonChiTietDAO;
import vn.com.duan1.coffeemanagement.DAO.HoaDonDAO;
import vn.com.duan1.coffeemanagement.DAO.NguoiDungDAO;
import vn.com.duan1.coffeemanagement.DAO.SanPhamDAO;
import vn.com.duan1.coffeemanagement.DataModel.HoaDon;
import vn.com.duan1.coffeemanagement.DataModel.HoaDonChiTiet;
import vn.com.duan1.coffeemanagement.DataModel.NguoiDung;
import vn.com.duan1.coffeemanagement.DataModel.SanPham;
import vn.com.duan1.coffeemanagement.Menu_item.MenuFragment;
import vn.com.duan1.coffeemanagement.Staff_item.StaffManagementFragment;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bnv;
    public static String idAfterLogin;
    public static String passwordAfterLogin;
    public static ArrayList<HoaDonChiTiet> listHDCTs = new ArrayList<>();
    public static ArrayList<HoaDon> hoaDons = new ArrayList<>();
    public static ArrayList<NguoiDung> nguoiDungs = new ArrayList<>();

    public static ArrayList<SanPham> sanPhamss = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle b = getIntent().getBundleExtra("loginInfo");
        if(b!=null){
            idAfterLogin = b.getString("id");
            passwordAfterLogin = b.getString("password");
            Log.d("accountInfo", idAfterLogin+passwordAfterLogin);
        }

        bnv = findViewById(R.id.bnv);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fl,
                    new StaffManagementFragment()).commit();

            bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    if (item.getItemId() == R.id.staff)
                        getSupportFragmentManager().beginTransaction().replace(R.id.fl,
                                new StaffManagementFragment()).commit();
                    if (item.getItemId() == R.id.menu)
                        getSupportFragmentManager().beginTransaction().replace(R.id.fl,
                                new MenuFragment()).commit();
                    if (item.getItemId() == R.id.bill)
                        getSupportFragmentManager().beginTransaction().replace(R.id.fl,
                                new BillFragment()).commit();
                    if (item.getItemId() == R.id.account)
                        getSupportFragmentManager().beginTransaction().replace(R.id.fl,
                                new AccountInforFragment()).commit();
                    return true;
                }
            });
        }

//        HoaDonDAO hoaDonDAO = new HoaDonDAO(this);
//        HoaDon hoaDon = new HoaDon("hd03","08/04/2020","Chưa thanh toán","nv1");
//        HoaDon hoaDon1 = new HoaDon("hd04", "09/04/2020","Đã thanh toán","ql1");
//        hoaDonDAO.insert(hoaDon);
//        hoaDonDAO.insert(hoaDon1);
//        HoaDonChiTietDAO hoaDonChiTietDAO = new HoaDonChiTietDAO(this);
//        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet("hdct03", "hd03", "NU4", 7 );
//        HoaDonChiTiet hoaDonChiTiet1 = new HoaDonChiTiet("hdct02", "hd04", "NU3", 3 );
//        HoaDonChiTiet hoaDonChiTiet2 = new HoaDonChiTiet("hdct04", "hd03", "NU1", 6 );
//        HoaDonChiTiet hoaDonChiTiet3 = new HoaDonChiTiet("hdct01", "hd02", "NU2", 4 );
//        hoaDonChiTietDAO.insert(hoaDonChiTiet);
//        hoaDonChiTietDAO.insert(hoaDonChiTiet1);
//        hoaDonChiTietDAO.insert(hoaDonChiTiet2);
//        hoaDonChiTietDAO.insert(hoaDonChiTiet3);
        getHDCT();
        getHoaDon();
        getSanPham();

        getUser();


    }

    public void getUser(){
        NguoiDungDAO nguoiDungDAO = new NguoiDungDAO(this);
        nguoiDungs = nguoiDungDAO.getUsers();
    }

    public void getSanPham(){
        SanPhamDAO sanPhamDAO = new SanPhamDAO(this);
        sanPhamss = sanPhamDAO.getAll1();
    }

    public void getHDCT(){
        HoaDonChiTietDAO hoaDonChiTietDAO = new HoaDonChiTietDAO(this);
        listHDCTs = (ArrayList<HoaDonChiTiet>) hoaDonChiTietDAO.getAll();
    }

    public void getHoaDon(){
        HoaDonDAO hoaDonDAO = new HoaDonDAO(this);
        hoaDons = (ArrayList<HoaDon>) hoaDonDAO.getAll1();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.back_in, R.anim.back_out);
    }


}



