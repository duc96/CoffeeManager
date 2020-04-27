package vn.com.duan1.coffeemanagement.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import vn.com.duan1.coffeemanagement.DAO.SanPhamDAO;
import vn.com.duan1.coffeemanagement.DataModel.CartHDCT;
import vn.com.duan1.coffeemanagement.DataModel.HoaDonChiTiet;
import vn.com.duan1.coffeemanagement.DataModel.NonUI;
import vn.com.duan1.coffeemanagement.DataModel.SanPham;
import vn.com.duan1.coffeemanagement.Menu_item.DrinkFragment;
import vn.com.duan1.coffeemanagement.Menu_item.FoodFragment;
import vn.com.duan1.coffeemanagement.Menu_item.OthersFragment;
import vn.com.duan1.coffeemanagement.R;

import static vn.com.duan1.coffeemanagement.MainActivity.idAfterLogin;
import static vn.com.duan1.coffeemanagement.Menu_item.MenuFragment.hdcts;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.SanPhamViewHolder> {

    SanPhamDAO sanPhamDAO;
    private ArrayList<SanPham> sanPhams;
    private Context context;
    Fragment fr;

    private RecyclerView rcImage;
    private RecyclerView.LayoutManager layoutManager;
    private ImageAdapter imageAdapter;

    public static int stt = 0;
    NonUI nonUI;

    public SanPhamAdapter(ArrayList<SanPham> sanPhams, Context context, Fragment fr) {
        this.sanPhams = sanPhams;
        this.context = context;
        this.fr = fr;
    }

    @Override
    public SanPhamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.one_item_menu, parent, false);

        return new SanPhamViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final SanPhamViewHolder holder, final int position) {

        sanPhamDAO = new SanPhamDAO(context);
        final SanPham sp = sanPhams.get(position);

        holder.ten.setText(sp.getTenSP());
        holder.gia.setText(sp.getGiaSP() + "");
//        holder.hinh.setImageResource(sp.getHinhSP());
        Picasso.with(context).load(sp.getHinhSP()).into(holder.hinh);

        // an imageview Delete tren giao dien khi dang nhap la nhan vien
        if (idAfterLogin.substring(0, 2).equals("nv")) {
            holder.iv_xoa.setEnabled(false);
            holder.iv_xoa.setVisibility(View.GONE);

        } else {

            // set su kien Click cho imageview Delete
            holder.iv_xoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Xóa sản phẩm")
                            .setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Delete san pham va cap nhat lai giao dien
                                    sanPhamDAO.xoaSanPham(sp);
                                    sanPhams.remove(sp);
                                    notifyDataSetChanged();
                                }
                            })
                            .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    builder.show();
                }
            });

            // set su kien longClick cho 1 cardView tren giao dien (cap nhat san pham)
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    final View view = LayoutInflater.from(context).inflate(R.layout.dialog_update_menu, null);

                    //map cac thanh phan co trong giao dien cua cardview
                    final EditText edtUpdateAnh = view.findViewById(R.id.edtUpdateLinkAnh);
                    final ImageView ivPhoto = view.findViewById(R.id.imvUpdateAnh);
                    final EditText edtItemName = view.findViewById(R.id.edtUpdateItemName);
                    final EditText edtItemPrice = view.findViewById(R.id.edtUpdateItemPrice);
                    final Button btnUpdateCheck = view.findViewById(R.id.btnUpdateCheck);

                    //gan du lieu len cardview
                    edtUpdateAnh.setHint(sp.getHinhSP());
                    Picasso.with(context).load(sp.getHinhSP()).into(ivPhoto);
                    edtItemName.setHint(sp.getTenSP());
                    edtItemPrice.setHint(sp.getGiaSP() + "");

                    //set su kien cho button Update
                    btnUpdateCheck.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // kiem tra da nhap link san pham chua
                            if (edtUpdateAnh.getText().toString().equals("")){
                                nonUI = new NonUI(context);
                                nonUI.toast("Bạn chưa nhập link hình ảnh");
                            }else{
                                Picasso.with(context).load(edtUpdateAnh.getText().toString()).into(ivPhoto);
                            }

                        }
                    });
//                    ivPhoto.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//
//                            //gan list anh len layout
//                            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
//                            View view2 = LayoutInflater.from(view.getContext()).inflate(R.layout.rv_image, null);
//                            rcImage = view2.findViewById(R.id.rcImage);
//                            layoutManager = new GridLayoutManager(view.getContext(), 2);
//                            rcImage.setHasFixedSize(true);
//                            rcImage.setLayoutManager(layoutManager);
//
//                            //chon list anh cho tung fragment
//                            if (sp.getMaLoai().equals("drink")) {
//                                imageAdapter = new ImageAdapter(DrinkFragment.images, context);
//                            } else if (sp.getMaLoai().equals("food")) {
//                                imageAdapter = new ImageAdapter(FoodFragment.images, context);
//                            } else {
//                                imageAdapter = new ImageAdapter(OthersFragment.images, context);
//                            }
//
//                            //gan list anh len recycleview
//                            rcImage.setAdapter(imageAdapter);
//
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

                    builder.setView(view)
                            //set su kien khi nhan nut cap nhat
                            .setPositiveButton("Cập nhật", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    String maSP = sp.getMaSP();
                                    String loai = sp.getMaLoai();
                                    String Name = edtItemName.getText().toString();
                                    String hinh = edtUpdateAnh.getText().toString();
                                    String Price = edtItemPrice.getText().toString();

                                    if (Name.equals("")) {
                                        Name = edtItemName.getHint().toString();
                                    }

                                    if (hinh.equals("")){
                                        hinh = edtUpdateAnh.getHint().toString();
                                    }

                                    if (Price.equals("")) {
                                        Price = edtItemPrice.getHint().toString();
                                    }

                                    //cap nhat san pham
                                    sanPhamDAO.capnhatSanPham(new SanPham(maSP, loai, hinh, Name, Integer.parseInt(Price)));
//                                    sanPhamDAO.capnhatSanPham(new SanPham(maSP, loai, ImageAdapter.id_image, Name, Integer.parseInt(Price)));
                                }
                            })
                            .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    builder.show();


                    return false;
                }
            });
        }

        // set su kien onClick cho cardview
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View mv = LayoutInflater.from(context).inflate(R.layout.one_item_cart, null);

                ImageView ig = mv.findViewById(R.id.iv_ItemPhoto);
                final TextView ten = mv.findViewById(R.id.tv_ItemName);
                final TextView soluong = mv.findViewById(R.id.tv_ItemNumber);
                ImageView minus = mv.findViewById(R.id.minus);
                ImageView plus = mv.findViewById(R.id.plus);

//                ig.setImageResource(sp.getHinhSP());
                Picasso.with(context).load(sp.getHinhSP()).into(ig);
                ten.setText(sp.getTenSP());
                soluong.setText("1");

                //giam so luong
                minus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int num = Integer.parseInt(soluong.getText().toString());
                        if (num - 1 >= 1 ) {
                            soluong.setText(num - 1 + "");
                        }
                    }
                });

                //tang so luong
                plus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int num = Integer.parseInt(soluong.getText().toString());
                        soluong.setText(num + 1 + "");
                    }
                });

                //tao dialog chon mon
                builder.setTitle("Chọn món này nhé !")
                        .setView(mv)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                stt ++;
                                String tensp = ten.getText().toString();
                                int soluongmua = Integer.parseInt(soluong.getText().toString());
                                int tongtien = sp.getGiaSP() * soluongmua;

                                CartHDCT cartHDCT = new CartHDCT(tensp,soluongmua,tongtien);
                                //add san pham vao gio hang
                                hdcts.add(cartHDCT);

//                                System.out.println("stt"+ hdcts);

                            }
                        })
                        .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return sanPhams.size();
    }

    class SanPhamViewHolder extends RecyclerView.ViewHolder {
        private TextView ten;
        private TextView gia;
        private ImageView iv_xoa;
        private ImageView hinh;

        public SanPhamViewHolder(View itemView) {
            super(itemView);
            this.ten = itemView.findViewById(R.id.tv_ItemName);
            this.gia = itemView.findViewById(R.id.tv_ItemPrice);
            this.hinh = itemView.findViewById(R.id.iv_ItemPhoto);
            this.iv_xoa = itemView.findViewById(R.id.iv_DeleteItem);
        }
    }
}
