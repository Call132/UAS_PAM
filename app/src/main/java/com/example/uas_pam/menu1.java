package com.example.uas_pam;

import static android.content.ContentValues.TAG;

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
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class menu1 extends Fragment {

    RecyclerView recyclerView;
    myAdapter myAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment


        View v =  inflater.inflate(R.layout.fragment_menu, container, false);
//        FirebaseFirestore db = FirebaseFirestore.getInstance();

        recyclerView = (RecyclerView) v.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



//        Map<String, Object> menu = new HashMap<>();
//        menu.put("Nama", "Sate");
//        menu.put("Harga", "Rp. 2.000");
//        menu.put("mUrl", "https://upload.wikimedia.org/wikipedia/commons/a/ad/Sate_Ponorogo.jpg");
//
//        db.collection("DaftarMenu").add(menu)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w(TAG, "Error adding document", e);
//                    }
//                });




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