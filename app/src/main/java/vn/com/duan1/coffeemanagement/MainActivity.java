package vn.com.duan1.coffeemanagement;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import vn.com.duan1.coffeemanagement.Account_item.AccountInforFragment;
import vn.com.duan1.coffeemanagement.Bill_item.BillFragment;
import vn.com.duan1.coffeemanagement.Menu_item.MenuFragment;
import vn.com.duan1.coffeemanagement.Staff_item.StaffManagementFragment;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bnv;
    public static String idAfterLogin;
    public static String passwordAfterLogin;
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
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.back_in, R.anim.back_out);
    }


}



