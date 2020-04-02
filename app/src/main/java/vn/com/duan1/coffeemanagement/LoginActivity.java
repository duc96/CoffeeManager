package vn.com.duan1.coffeemanagement;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText edtUsername, edtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUsername=findViewById(R.id.edtUsername);
        edtPassword=findViewById(R.id.edtPassword);
        btnLogin=findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edtUsername.getText().toString().equals("") || edtUsername.getText().toString().equals(""))
                {
                    Toast.makeText(LoginActivity.this,"Bạn phải điền tên đăng nhập và mật khẩu", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(edtUsername.getText().toString().equals("admin") && edtPassword.getText().toString().equals("123"))
                    {
                        Intent i=new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(i);
                        Toast.makeText(LoginActivity.this,"Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }
}
