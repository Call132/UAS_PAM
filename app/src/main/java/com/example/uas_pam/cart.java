package com.example.uas_pam;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class cart extends Fragment {
    RecyclerView recyclerView;
    myAdapter myAdapter;
    DatabaseReference db;
    Button hapus;
    String username;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_cart, container, false);



        db = FirebaseDatabase.getInstance().getReference().child("Cart");

        recyclerView = (RecyclerView) v.findViewById(R.id.rv2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        FirebaseRecyclerOptions<DataMenu> options =
                new FirebaseRecyclerOptions.Builder<DataMenu>()
                        .setQuery(db,DataMenu.class)
                        .build();

        myAdapter = new myAdapter(options);
        recyclerView.setAdapter(myAdapter);

        hapus = v.findViewById(R.id.btn_hapus);
        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.removeValue();
            }
        });

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        myAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        myAdapter.stopListening();
    }
}