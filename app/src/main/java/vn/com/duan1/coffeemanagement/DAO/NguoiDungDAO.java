package vn.com.duan1.coffeemanagement.DAO;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import vn.com.duan1.coffeemanagement.DataModel.NguoiDung;
import vn.com.duan1.coffeemanagement.DataModel.NonUI;

public class NguoiDungDAO {
    private DatabaseReference mDatabase;
    NonUI nonUI;
    String userID;
    Context context;

    public NguoiDungDAO(Context context){
        this.context=context;
        this.mDatabase = FirebaseDatabase.getInstance().getReference("nguoidung");
        this.nonUI = new NonUI(context);
    }

    ArrayList<NguoiDung> nguoiDungs;
    public ArrayList<NguoiDung> getUsers(){
        nguoiDungs = new ArrayList<>();
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nguoiDungs.clear();
                for(DataSnapshot data:dataSnapshot.getChildren()){
                    NguoiDung nguoiDung = data.getValue(NguoiDung.class);
                    nguoiDungs.add(nguoiDung);
                }
                Log.d("Người dùng: ", nguoiDungs.size()+"");
//                fr.updateRecyclerView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        mDatabase.addValueEventListener(listener);
        return nguoiDungs;
    }
    public void inserNguoiDung(NguoiDung nguoiDung){
        userID = nguoiDung.getUserID();
        mDatabase.child(userID).setValue(nguoiDung).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                nonUI.toast("New user's added");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                nonUI.toast("Cannot add new user");
            }
        });
    }
    public void updateNguoiDung(final NguoiDung nguoiDung){
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data: dataSnapshot.getChildren()){
                    if(data.child("userID").getValue(String.class).equalsIgnoreCase(nguoiDung.getUserID())){
                        userID = data.getKey();
                        Log.d("getKey", "onCreate: key:"+userID);
                        mDatabase.child(userID).setValue(nguoiDung)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        nonUI.toast("Update "+userID+" succeeded");
                                        Log.d("update","update "+userID+" succeeded!");
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                nonUI.toast("Update "+userID+" failed");
                                Log.d("update","update "+userID+" failed!");
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
    public void deleteNguoiDung(final NguoiDung nguoiDung){
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data: dataSnapshot.getChildren()){
                    if(data.child("userID").getValue(String.class).equalsIgnoreCase(nguoiDung.getUserID())){
                        userID = data.getKey();
                        Log.d("getKey", "onCreate: key:"+userID);
                        mDatabase.child(userID).removeValue()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        nonUI.toast("Delete "+userID+" succeeded");
                                        Log.d("Delete","delete "+userID+" succeeded!");
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                nonUI.toast("Delete "+userID+" failed");
                                Log.d("Delete","Delete "+userID+" failed!");
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
