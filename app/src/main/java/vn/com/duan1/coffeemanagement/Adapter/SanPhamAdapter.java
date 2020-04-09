package vn.com.duan1.coffeemanagement.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.com.duan1.coffeemanagement.DAO.SanPhamDAO;
import vn.com.duan1.coffeemanagement.DataModel.SanPham;
import vn.com.duan1.coffeemanagement.Menu_item.DrinkFragment;
import vn.com.duan1.coffeemanagement.R;

import static vn.com.duan1.coffeemanagement.MainActivity.idAfterLogin;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.SanPhamViewHolder> {

    SanPhamDAO sanPhamDAO;
    private ArrayList<SanPham> sanPhams;
    private Context context;
    DrinkFragment fr;

    public SanPhamAdapter(ArrayList<SanPham> sanPhams, Context context, DrinkFragment fr) {
        this.sanPhams = sanPhams;
        this.context = context;
        this.fr = fr;
    }

    @Override
    public SanPhamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.one_item_menu,parent,false);

        return new SanPhamViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamViewHolder holder, final int position) {

        sanPhamDAO = new SanPhamDAO(context);
        final SanPham sp = sanPhams.get(position);

        if(idAfterLogin.substring(0,2).equals("nv")){
            holder.iv_xoa.setEnabled(false);
            holder.iv_xoa.setVisibility(View.GONE);
        }else{
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Sửa thông tin sản phẩm")
                            .setPositiveButton("Cập nhật", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

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

        holder.ten.setText(sp.getTenSP());
        holder.gia.setText(sp.getGiaSP()+"");
        holder.hinh.setImageResource(sp.getHinhSP());

        holder.iv_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xóa sản phẩm")
                        .setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sanPhamDAO.xoaSanPham(sp);
                                sanPhams.remove(position);
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
    }

    @Override
    public int getItemCount() {
        return sanPhams.size();
    }

    class SanPhamViewHolder extends RecyclerView.ViewHolder{
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
