package com.example.uas_pam;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class menu1 extends Fragment {

    RecyclerView recyclerView;
    myAdapter myAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment


        View v =  inflater.inflate(R.layout.fragment_menu, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        FirebaseRecyclerOptions<DataMenu> options =
                new FirebaseRecyclerOptions.Builder<DataMenu>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("DaftarMenu"),DataMenu.class)
                        .build();

        myAdapter = new myAdapter(options);
        recyclerView.setAdapter(myAdapter);


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