package vn.com.duan1.coffeemanagement;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import vn.com.duan1.coffeemanagement.DAO.NguoiDungDAO;
import vn.com.duan1.coffeemanagement.DataModel.NguoiDung;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText edtUsername, edtPassword;
    public static ArrayList<NguoiDung> nguoiDungs = new ArrayList<>();

    NguoiDungDAO nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        nguoiDungDAO = new NguoiDungDAO(getApplicationContext());
        addFirstInfo();
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle loginBundle = new Bundle();
                int flag = 0;
                if (edtUsername.getText().toString().equals("") || edtPassword.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "Bạn phải điền tên đăng nhập và mật khẩu", Toast.LENGTH_SHORT).show();
                } else {
//                    if(edtUsername.getText().toString().equals("admin") && edtPassword.getText().toString().equals("123"))
//                    {
//                        Intent i=new Intent(LoginActivity.this, MainActivity.class);
//                        startActivity(i);
//                        Toast.makeText(LoginActivity.this,"Đăng nhập thành công", Toast.LENGTH_SHORT).show();
//                    }
                    String id = edtUsername.getText().toString();
                    String password = edtPassword.getText().toString();
                    for (NguoiDung nguoiDung : nguoiDungs) {
                        if (id.equals(nguoiDung.getUserID()) && password.equals(nguoiDung.getPassword())) {
                            flag = 1;
                            loginBundle.putString("id", id);
                            loginBundle.putString("password", password);
                        }
                    }
                }
                if (flag == 1) {
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    i.putExtra("loginInfo", loginBundle);
                    Toast.makeText(LoginActivity.this,"Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    startActivity(i);
                }
                else {
                    Toast.makeText(LoginActivity.this,"Tài khoản/Mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
                    edtUsername.setText("");
                    edtPassword.setText("");
                }
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    public void addFirstInfo() {
//        NguoiDung nguoiDung1 = new NguoiDung("ql1", "ql1");
//        nguoiDungDAO.inserNguoiDung(nguoiDung1);
        nguoiDungs = nguoiDungDAO.getUsers();
//        NguoiDung nguoiDung2 = new NguoiDung("ql02", "ql02");
//        NguoiDung nguoiDung3 = new NguoiDung("nv01", "nv01");
//        NguoiDung nguoiDung4 = new NguoiDung("nv02", "nv02");
//        nguoiDungs.add(nguoiDung1);
//        nguoiDungs.add(nguoiDung2);
//        nguoiDungs.add(nguoiDung3);
//        nguoiDungs.add(nguoiDung4);
    }


}
