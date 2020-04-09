package vn.com.duan1.coffeemanagement.Menu_item;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import vn.com.duan1.coffeemanagement.Adapter.ImageAdapter;
import vn.com.duan1.coffeemanagement.Adapter.SanPhamAdapter;
import vn.com.duan1.coffeemanagement.DAO.SanPhamDAO;
import vn.com.duan1.coffeemanagement.DataModel.SanPham;
import vn.com.duan1.coffeemanagement.R;

import static vn.com.duan1.coffeemanagement.MainActivity.idAfterLogin;

import static vn.com.duan1.coffeemanagement.MainActivity.sanPhamss;

public class DrinkFragment extends Fragment {
    private RecyclerView rvDrink;
    private RecyclerView rcImage;
    private FloatingActionButton flAdd, flCart;
    private final String loai = "drink";
    private SanPhamDAO sanPhamDAO;
    private SanPhamAdapter sanPhamAdapter;
    private ImageAdapter imageAdapter;

    private int[] images = {R.mipmap.seven_up,
            R.mipmap.ca_phe_den,
            R.mipmap.ca_phe_den_nong,
            R.mipmap.ca_phe_sua,
            R.mipmap.ca_phe_sua_nong,
            R.mipmap.coca_cola,
            R.mipmap.da_chanh,
            R.mipmap.bac_xiu_da,
            R.mipmap.tra_gung,
            R.mipmap.tra_sua_chan_chau_duong_den,
            R.mipmap.cam_ep,
            R.mipmap.mirinda,
            R.mipmap.monter,
            R.mipmap.moutanin_dew,
            R.mipmap.spire,
            R.mipmap.sting
    };

    private RecyclerView.LayoutManager layoutManager;
    public DrinkFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_drink, container, false);
        flAdd = view.findViewById(R.id.addDrink);
        flCart = view.findViewById(R.id.cart);
        rvDrink = view.findViewById(R.id.rvDrink);

        sanPhamDAO = new SanPhamDAO(getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvDrink.setLayoutManager(linearLayoutManager);

        sanPhamAdapter = new SanPhamAdapter(sanPhamss, getContext(), this);
        rvDrink.setAdapter(sanPhamAdapter);

        if (idAfterLogin.substring(0, 2).equals("nv")) {
            flAdd.setEnabled(false);
            flAdd.setVisibility(View.GONE);
        }


        flAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                final LayoutInflater inf = getLayoutInflater();
                final View viewdialog = inf.inflate(R.layout.dialog_add_menu, null);
                builder.setView(viewdialog);

                final ImageView ivPhoto = viewdialog.findViewById(R.id.ivPhoto);
                final EditText edtItemName = viewdialog.findViewById(R.id.edtItemName);
                final EditText edtItemPrice = viewdialog.findViewById(R.id.edtItemPrice);

                ivPhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                        LayoutInflater inf1 = getLayoutInflater().from(viewdialog.getContext());
                        View view2 = inf1.inflate(R.layout.rv_image, null);
                        rcImage = view2.findViewById(R.id.rcImage);
                        layoutManager = new GridLayoutManager(viewdialog.getContext(),2);
                        rcImage.setHasFixedSize(true);
                        rcImage.setLayoutManager(layoutManager);
                        imageAdapter = new ImageAdapter(images,getContext());
                        rcImage.setAdapter(imageAdapter);

                        if(ImageAdapter.id_image != 0){
                            ivPhoto.setImageResource(ImageAdapter.id_image);
                        }else{
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
                        int size = sanPhamss.size();
                        String maSP = "NU" + size;
                        for (int i = 0; i < sanPhamss.size(); i++) {
                            if (sanPhamss.get(i).getMaSP().equals(maSP)) {
                                size++;
                                maSP = "NU" + size;
                            }
                        }

                        String Name = edtItemName.getText().toString();
                        String Price = edtItemPrice.getText().toString();

                        sanPhamDAO.themSanPham(new SanPham(maSP, loai,ImageAdapter.id_image, Name, Integer.parseInt(Price)));
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
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                LayoutInflater inf = getLayoutInflater();
                View viewdialog = inf.inflate(R.layout.dialog_cart, null);
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

    public void capnhatgiaodien() {
        sanPhamAdapter.notifyItemInserted(sanPhamss.size());
        sanPhamAdapter.notifyDataSetChanged();
    }

}