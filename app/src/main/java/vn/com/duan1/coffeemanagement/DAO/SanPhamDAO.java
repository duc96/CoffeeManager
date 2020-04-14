package vn.com.duan1.coffeemanagement.DAO;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import vn.com.duan1.coffeemanagement.DataModel.NonUI;
import vn.com.duan1.coffeemanagement.DataModel.SanPham;
import vn.com.duan1.coffeemanagement.Menu_item.DrinkFragment;

public class SanPhamDAO {

    private DatabaseReference mdata;
    String key;
    Context context;
    NonUI nonUI;
    ArrayList<SanPham> sanPhams;
    DrinkFragment drinkFragment;

    public SanPhamDAO(Context context) {
        this.mdata = FirebaseDatabase.getInstance().getReference("sanpham");
        this.context = context;
        this.nonUI = new NonUI(context);
    }

    public ArrayList<SanPham> getAll1(){
        sanPhams = new ArrayList<>();
        mdata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                sanPhams.clear();
                for(DataSnapshot data:dataSnapshot.getChildren()){
                    SanPham sanPham = data.getValue(SanPham.class);
                    sanPhams.add(sanPham);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                nonUI.toast("Lỗi kết nối Database rồi !");
            }
        });
        return sanPhams;
    }

    public ArrayList<SanPham> getAll(){
        sanPhams = new ArrayList<>();
        mdata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                sanPhams.clear();
                for(DataSnapshot data:dataSnapshot.getChildren()){
                    SanPham sanPham = data.getValue(SanPham.class);
                    sanPhams.add(sanPham);
                }
                drinkFragment.capnhatgiaodien();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                nonUI.toast("Lỗi kết nối Database rồi !");
            }
        });
        return sanPhams;
    }

    public void themSanPham(SanPham sanPham){

        key = mdata.push().getKey();
        mdata.child(key).setValue(sanPham).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                nonUI.toast("Đã thêm !");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                nonUI.toast("Lỗi rồi !");
            }
        });
    }

    public void xoaSanPham(final SanPham sanPham){
        mdata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data: dataSnapshot.getChildren()){
                    if (data.child("maSP").getValue(String.class).equalsIgnoreCase(sanPham.getMaSP())){
                        key = data.getKey();
                        mdata.child(key).removeValue()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        nonUI.toast("xóa rồi nè !");
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        nonUI.toast("xóa không được !");
                                    }
                                });
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void capnhatSanPham(final SanPham sanPham){

        mdata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot data: dataSnapshot.getChildren()){
                    if (data.child("maSP").getValue(String.class).equals(sanPham.getMaSP())) {

                        key = data.getKey();

                        mdata.child(key).setValue(sanPham)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        nonUI.toast("update được nè !");

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        nonUI.toast("update không được !");

                                    }
                                });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
