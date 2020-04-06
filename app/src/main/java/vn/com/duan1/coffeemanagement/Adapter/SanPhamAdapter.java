package vn.com.duan1.coffeemanagement.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.com.duan1.coffeemanagement.DataModel.SanPham;
import vn.com.duan1.coffeemanagement.Menu_item.DrinkFragment;
import vn.com.duan1.coffeemanagement.R;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.SanPhamViewHolder> {

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
    public void onBindViewHolder(@NonNull SanPhamViewHolder holder, int position) {

        SanPham sp = sanPhams.get(position);

        holder.ten.setText(sp.getTenSP());
        holder.mota.setText(sp.getMota());
        holder.gia.setText(sp.getGiaSP()+"");
    }

    @Override
    public int getItemCount() {
        return sanPhams.size();
    }

    class SanPhamViewHolder extends RecyclerView.ViewHolder{
        private TextView ten;
        private TextView mota;
        private TextView gia;

        public SanPhamViewHolder(View itemView) {
            super(itemView);
            this.ten = itemView.findViewById(R.id.tv_ItemName);
            this.mota = itemView.findViewById(R.id.tv_ItemDescribe);
            this.gia = itemView.findViewById(R.id.tv_ItemPrice);
        }
    }
}
