package vn.com.duan1.coffeemanagement.Menu_item;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import vn.com.duan1.coffeemanagement.R;

public class FoodFragment extends Fragment {
    RecyclerView rvFood;
    FloatingActionButton flAddFood, flCart;


    public FoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_food, container, false);
        flAddFood=view.findViewById(R.id.addFood);
        flCart=view.findViewById(R.id.cart);
        rvFood=view.findViewById(R.id.rvFood);




        flAddFood.setOnClickListener(new View.OnClickListener() {
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
                        //image input here
                        String Name=edtItemName.getText().toString();
                        String Price=edtItemPrice.getText().toString();
                        String Describe=edtItemDescribe.getText().toString();
                        capnhatgiaodien();
                        Toast.makeText(getActivity(), "Đã thêm", Toast.LENGTH_SHORT).show();
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

    public void capnhatgiaodien(){}

}
