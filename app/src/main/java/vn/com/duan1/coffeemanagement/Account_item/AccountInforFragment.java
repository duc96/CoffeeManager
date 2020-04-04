package vn.com.duan1.coffeemanagement.Account_item;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

import vn.com.duan1.coffeemanagement.DAO.NguoiDungDAO;
import vn.com.duan1.coffeemanagement.DataModel.NguoiDung;
import vn.com.duan1.coffeemanagement.LoginActivity;
import vn.com.duan1.coffeemanagement.R;

import static vn.com.duan1.coffeemanagement.LoginActivity.nguoiDungs;
import static vn.com.duan1.coffeemanagement.MainActivity.idAfterLogin;

public class AccountInforFragment extends Fragment {
    Button btnUpdateInfor, btnChangePass, btnLogout;
    NguoiDung nguoiDungHienTai;
    EditText etTen, etCMND, etsdt;
    NguoiDungDAO nguoiDungDAO;
    public AccountInforFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_account_infor, container, false);
        getNguoiDung();
        nguoiDungDAO = new NguoiDungDAO(getContext());
        btnUpdateInfor=view.findViewById(R.id.btnUpdateInfor);
        btnChangePass=view.findViewById(R.id.btnChangePassword);
        btnLogout=view.findViewById(R.id.btnLogout);
        etTen = view.findViewById(R.id.et1);
        etCMND = view.findViewById(R.id.et2);
        etsdt = view.findViewById(R.id.et3);
        if(nguoiDungHienTai!=null){
           hienThiThongTin();
        }


        btnUpdateInfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
                LayoutInflater inf=getLayoutInflater();
                View viewdialog=inf.inflate(R.layout.dialog_update_infor,null);
                builder.setView(viewdialog);



                final EditText edtNameUpdate=viewdialog.findViewById(R.id.edtNameUpdate);
                final EditText edtCMNDUpdate=viewdialog.findViewById(R.id.edCMNDUpdate);
                final EditText edtPhoneNumberUpdate=viewdialog.findViewById(R.id.edtPhoneNumberUpdate);



                builder.setPositiveButton("Cập nhật", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String Name=edtNameUpdate.getText().toString();
                        String CMND=edtCMNDUpdate.getText().toString();
                        String PhoneNumber=edtPhoneNumberUpdate.getText().toString();
                        nguoiDungHienTai.setTen(Name);
                        nguoiDungHienTai.setCMND(CMND);
                        nguoiDungHienTai.setSdt(PhoneNumber);
                        hienThiThongTin();
                        nguoiDungDAO.updateNguoiDung(nguoiDungHienTai);
                        Toast.makeText(getActivity(), "Đã cập nhật.", Toast.LENGTH_SHORT).show();
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



        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
                LayoutInflater inf=getLayoutInflater();
                View viewdialog=inf.inflate(R.layout.dialog_change_password,null);
                builder.setView(viewdialog);



                final EditText edtCurrentPass=viewdialog.findViewById(R.id.edtCurrentPass);
                final EditText edtNewPass=viewdialog.findViewById(R.id.edtNewPass);
                final EditText edtNewPassConfirm = viewdialog.findViewById(R.id.edtNewPassConfirm);

                builder.setPositiveButton("Thay đổi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String current=edtCurrentPass.getText().toString();
                        String newPass=edtNewPass.getText().toString();
                        String newPassConfirm=edtNewPassConfirm.getText().toString();
                        if(nguoiDungHienTai.getPassword().equals(current)){
                            if(newPass.equals(newPassConfirm)){
                                nguoiDungHienTai.setPassword(newPass);
                                hienThiThongTin();
                                nguoiDungDAO.updateNguoiDung(nguoiDungHienTai);
                                Toast.makeText(getActivity(), "Đã thay đổi.", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(getActivity(), "Mật khẩu xác nhận không trùng khớp.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(getActivity(), "Sai mật khẩu, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                        }

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

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
                LayoutInflater inf=getLayoutInflater();
                View viewdialog=inf.inflate(R.layout.dialog_logout,null);
                builder.setView(viewdialog);


                builder.setPositiveButton("Đăng xuất", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intenLogout = new Intent(getContext(), LoginActivity.class);
                        startActivity(intenLogout);
                        Toast.makeText(getActivity(), "Đã đăng xuất", Toast.LENGTH_SHORT).show();
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

        return view;
    }


    public void getNguoiDung(){
        for(NguoiDung nguoiDung : nguoiDungs){
            if(nguoiDung.getUserID().equals(idAfterLogin)){
                nguoiDungHienTai = nguoiDung;
            }
        }
    }
    public void hienThiThongTin(){
        etTen.setText(nguoiDungHienTai.getTen());
        etCMND.setText(nguoiDungHienTai.getCMND());
        etsdt.setText(nguoiDungHienTai.getSdt());
    }
}
