package vn.com.duan1.coffeemanagement.Adapter;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.com.duan1.coffeemanagement.DAO.NguoiDungDAO;
import vn.com.duan1.coffeemanagement.DataModel.NguoiDung;
import vn.com.duan1.coffeemanagement.R;

import static vn.com.duan1.coffeemanagement.MainActivity.idAfterLogin;

public class NguoiDungAdapter extends RecyclerView.Adapter<NguoiDungAdapter.ViewHolder> {
    static Context context;
    ArrayList<NguoiDung> nguoiDungArrayList;
    NguoiDungDAO nguoiDungDAO;

    public NguoiDungAdapter(Context context, ArrayList<NguoiDung> nguoiDungArrayList) {
        this.context = context;
        this.nguoiDungArrayList = nguoiDungArrayList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvID;
        TextView tvTen;
        TextView tvCMND;
        TextView tvsdt;
        ImageView ivDel;
        public ViewHolder(final View itemView) {
            super(itemView);
            tvID = itemView.findViewById(R.id.tv_idAccount);
            tvTen = itemView.findViewById(R.id.tv_StaffName);
            tvCMND = itemView.findViewById(R.id.tv_CMND);
            tvsdt = itemView.findViewById(R.id.tv_StaffPhone);
            ivDel = itemView.findViewById(R.id.iv_DeleteStaff);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.one_item_staff,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        nguoiDungDAO = new NguoiDungDAO(context);
        final NguoiDung nguoiDung = nguoiDungArrayList.get(position);
        holder.tvID.setText(nguoiDung.getUserID());
        holder.tvTen.setText(nguoiDung.getTen());
        holder.tvCMND.setText(nguoiDung.getCMND());
        holder.tvsdt.setText(nguoiDung.getSdt());
        if(idAfterLogin.contains("ql")){
            holder.ivDel.setVisibility(View.VISIBLE);
            holder.ivDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("XÓA NGƯỜI DÙNG " + nguoiDung.getUserID() + "?");
                    builder.setPositiveButton("CÓ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            nguoiDungDAO.deleteNguoiDung(nguoiDung);
                            nguoiDungArrayList.remove(nguoiDung);
                            notifyDataSetChanged();
                        }
                    }).setNegativeButton("KHÔNG", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    builder.show();
                }
            });
        }
        else {
            holder.ivDel.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return nguoiDungArrayList.size();
    }


}
