package com.example.uas_pam;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Tag;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class cart extends Fragment {
    RecyclerView recyclerView;
    myAdapter myAdapter;
    adapter adapter;
    FirebaseFirestore db;
    Button hapus;
    helper helper;
    ArrayList<DataMenu>listt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_cart, container, false);
            db = FirebaseFirestore.getInstance();
        listt = new ArrayList<>();

        recyclerView = (RecyclerView) v.findViewById(R.id.rv2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        helper= new helper(getContext(),listt);
        recyclerView.setAdapter(helper);



      db.collection("Cartt").addSnapshotListener(new EventListener<QuerySnapshot>() {
          @SuppressLint("NotifyDataSetChanged")
          @Override
          public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
              listt.clear();
              for (DocumentSnapshot snapshot : value){
                  DataMenu dataMenu = new DataMenu(snapshot.getString("Nama"),snapshot.getString("Harga"),snapshot.getString("mUrl"));
                    dataMenu.setId(snapshot.getId());
                   listt.add(dataMenu);
              }
              helper.notifyDataSetChanged();
          }
      });



        hapus = v.findViewById(R.id.btn_hapus);
        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        return v;
    }




}