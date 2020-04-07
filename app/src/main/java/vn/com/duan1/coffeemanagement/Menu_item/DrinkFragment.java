package vn.com.duan1.coffeemanagement.Menu_item;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import vn.com.duan1.coffeemanagement.Adapter.SanPhamAdapter;
import vn.com.duan1.coffeemanagement.DAO.SanPhamDAO;
import vn.com.duan1.coffeemanagement.DataModel.SanPham;
import vn.com.duan1.coffeemanagement.R;

import static vn.com.duan1.coffeemanagement.MainActivity.idAfterLogin;

import static vn.com.duan1.coffeemanagement.MainActivity.sanPhamss;

public class DrinkFragment extends Fragment {
    RecyclerView rvDrink;
    FloatingActionButton flAdd, flCart;
    final String loai = "drink";
    SanPhamDAO sanPhamDAO;
    SanPhamAdapter sanPhamAdapter;

    public DrinkFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_drink, container, false);
        flAdd=view.findViewById(R.id.addDrink);
        flCart=view.findViewById(R.id.cart);
        rvDrink=view.findViewById(R.id.rvDrink);

           sanPhamDAO = new SanPhamDAO(getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvDrink.setLayoutManager(linearLayoutManager);

        sanPhamAdapter = new SanPhamAdapter(sanPhamss,getContext(), this);
        rvDrink.setAdapter(sanPhamAdapter);
        
        if(idAfterLogin.substring(0,2).equals("nv")){
            flAdd.setEnabled(false);
            flAdd.setVisibility(View.GONE);
        }

        flAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
                LayoutInflater inf=getLayoutInflater();
                View viewdialog=inf.inflate(R.layout.dialog_add_menu,null);
                builder.setView(viewdialog);

                final ImageView ivPhoto=viewdialog.findViewById(R.id.ivPhoto);
                final EditText edtItemName=viewdialog.findViewById(R.id.edtItemName);
                final EditText edtItemPrice=viewdialog.findViewById(R.id.edtItemPrice);
                final EditText edtItemDescribe=viewdialog.findViewById(R.id.edtItemDescribe);

                builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int size = sanPhamss.size();
                        String maSP = "NU" + size;
                        for(int i = 0; i < sanPhamss.size(); i++){
                            if(sanPhamss.get(i).getMaSP().equals(maSP)){
                                size ++;
                                maSP = "NU" + size;
                            }
                        }

                        String Name=edtItemName.getText().toString();
                        String Price=edtItemPrice.getText().toString();
                        String Describe=edtItemDescribe.getText().toString();

                        sanPhamDAO.themSanPham(new SanPham(maSP,loai,Name,Integer.parseInt(Price),Describe));
                        capnhatgiaodien();

                    }
                });

                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                capnhatgiaodien();
            }
        });

        flCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
                LayoutInflater inf=getLayoutInflater();
                View viewdialog=inf.inflate(R.layout.dialog_cart,null);
                builder.setView(viewdialog);

                builder.setPositiveButton("Cập nhật", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //do something here

                        Toast.makeText(getActivity(), "Đã cập nhật", Toast.LENGTH_SHORT).show();
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

    public void capnhatgiaodien(){
        sanPhamAdapter.notifyItemInserted(sanPhamss.size());
        sanPhamAdapter.notifyDataSetChanged();
    }

}