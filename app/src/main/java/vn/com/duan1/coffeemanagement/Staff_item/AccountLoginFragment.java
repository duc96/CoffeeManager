package vn.com.duan1.coffeemanagement.Staff_item;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import vn.com.duan1.coffeemanagement.Adapter.NguoiDungAdapter;
import vn.com.duan1.coffeemanagement.DAO.NguoiDungDAO;
import vn.com.duan1.coffeemanagement.DataModel.NguoiDung;
import vn.com.duan1.coffeemanagement.R;

import static vn.com.duan1.coffeemanagement.MainActivity.idAfterLogin;
import static vn.com.duan1.coffeemanagement.Staff_item.StaffManagementFragment.nhanViens;
import static vn.com.duan1.coffeemanagement.Staff_item.StaffManagementFragment.quanLys;
import static vn.com.duan1.coffeemanagement.Staff_item.StaffManagementFragment.sttNhanVien;
import static vn.com.duan1.coffeemanagement.Staff_item.StaffManagementFragment.sttQuanLy;

public class AccountLoginFragment extends Fragment {
    RecyclerView rvAccountManager;
    FloatingActionButton fl;
    NguoiDungDAO nguoiDungDAO;
    NguoiDungAdapter nguoiDungAdapter;
    public AccountLoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_account_login, null);
        rvAccountManager = view.findViewById(R.id.rvAccountManager);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getRootView().getContext());
        rvAccountManager.setLayoutManager(linearLayoutManager);
        nguoiDungAdapter = new NguoiDungAdapter(view.getRootView().getContext(),quanLys);
        rvAccountManager.setAdapter(nguoiDungAdapter);
        fl = view.findViewById(R.id.addStaffAcount);
        nguoiDungDAO = new NguoiDungDAO(getContext());

        if (idAfterLogin.contains("ql")) {
            fl.setEnabled(true);
            fl.setVisibility(View.VISIBLE);
        } else {
            fl.setEnabled(false);
            fl.setVisibility(View.GONE);
        }

        capnhatgiaodien();
        if (fl.isEnabled()) {
            fl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    themTaiKhoanB1();
                }
            });
        }
        return view;

    }

    public void capnhatgiaodien() {
    }
    String begin;
    int flagQL = 0;
    int flagNV = 0;

    public void themTaiKhoanB1() {
        sttQuanLy++;
        sttNhanVien++;
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
        LayoutInflater inf = getLayoutInflater();
        View viewdialog1 = inf.inflate(R.layout.dialog_add_account_login, null);
        builder1.setView(viewdialog1);
        final Spinner spinner = viewdialog1.findViewById(R.id.spinnerChucVu);
        final EditText edtSTT = viewdialog1.findViewById(R.id.edtSTT);
        final EditText edtAddPassword = viewdialog1.findViewById(R.id.edtAddPassword);
        ArrayList<String> chucVu = new ArrayList<>();
        chucVu.add("Quản lý - ql");
        chucVu.add("Nhân viên - nv");
        ArrayAdapter arrayAdapter = new ArrayAdapter(viewdialog1.getContext(), android.R.layout.simple_spinner_item, chucVu);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String chucVuNguoiDung = adapterView.getItemAtPosition(i).toString();
                if (chucVuNguoiDung.contains("ql")) {
                    flagQL = 1;
                    flagNV = 0;
                    begin = "ql";
                    edtSTT.setText(sttQuanLy + "");

                } else if (chucVuNguoiDung.contains("nv")) {
                    flagQL = 0;
                    flagNV = 1;
                    begin = "nv";
                    edtSTT.setText(sttNhanVien + "");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        builder1.setPositiveButton("Bước 2", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String userID = "";
                String password = edtAddPassword.getText().toString();

                userID = begin + edtSTT.getText().toString();

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(getContext(), "Mật khẩu không được trống!", Toast.LENGTH_SHORT).show();
                    sttQuanLy--;
                    sttNhanVien--;
                }else {
                    NguoiDung nguoiDungMoi = new NguoiDung(userID, password);
                    themTaiKhoanB2(nguoiDungMoi);
                    if (flagQL == 1 && flagNV == 0) {
                        sttNhanVien--;
                    } else if (flagQL == 0 && flagNV == 1) {
                        sttQuanLy--;
                    }
                }
            }
        });

        builder1.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sttQuanLy--;
                sttNhanVien--;
                dialog.cancel();
            }
        });
        builder1.show();
    }

    public void themTaiKhoanB2(final NguoiDung nguoiDung) {
        AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
        LayoutInflater inf = getLayoutInflater();
        View viewdialog2 = inf.inflate(R.layout.dialog_add_staff, null);
        builder2.setView(viewdialog2);
        final EditText edtTen = viewdialog2.findViewById(R.id.edtTen);
        final EditText edtCMND = viewdialog2.findViewById(R.id.edtCMND);
        final EditText edtsdt = viewdialog2.findViewById(R.id.edtsdt);
        builder2.setPositiveButton("Hoàn tất", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                nguoiDung.setTen(edtTen.getText().toString());
                nguoiDung.setCMND(edtCMND.getText().toString());
                nguoiDung.setSdt(edtsdt.getText().toString());
                nguoiDungDAO.inserNguoiDung(nguoiDung);
                nguoiDungAdapter.notifyDataSetChanged();
            }
        }).setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder2.show();
    }
}
