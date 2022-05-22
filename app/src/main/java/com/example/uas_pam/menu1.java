package com.example.uas_pam;



import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class menu1 extends Fragment {

    RecyclerView recyclerView;
    adapter adapter;
    myAdapter myAdapter;
    DatabaseReference db;
    ArrayList<DataMenu> list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment


        View v =  inflater.inflate(R.layout.fragment_menu, container, false);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        recyclerView = (RecyclerView) v.findViewById(R.id.rv);
//        db = FirebaseDatabase.getInstance().getReference().child("DaftarMenu");
        list = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new adapter(getContext(),list);
        recyclerView.setAdapter(adapter);

        db.collection("DaftarMenu").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                list.clear();
                for (DocumentSnapshot snapshot : task.getResult()){
                    DataMenu menu = new DataMenu(snapshot.getString("Nama"),snapshot.getString("Harga"),snapshot.getString("mUrl"));
                    list.add(menu);
                }
                adapter.notifyDataSetChanged();
            }
        });





        return v;
    }

}