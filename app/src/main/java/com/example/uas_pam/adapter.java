package com.example.uas_pam;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class adapter extends RecyclerView.Adapter<adapter.MyViewHolder> {
    FirebaseFirestore db;

    Context context;

    public adapter(Context context, ArrayList<DataMenu> list) {
        this.context = context;
        this.list = list;
    }

    ArrayList<DataMenu>list;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent , false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataMenu menu = list.get(position);

        holder.nama.setText(menu.getNama());
        holder.harga.setText(menu.getHarga());
        Glide.with(holder.img.getContext()).load(menu.getmUrl()).into(holder.img);

        holder.tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = FirebaseFirestore.getInstance();
                Map<String, Object> cart = new HashMap<>();
                cart.put("Nama", menu.getNama());
                cart.put("Harga", menu.getHarga());
                cart.put("mUrl", menu.getmUrl());


                db.collection("Cartt").add(cart).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(context, menu.getNama()+" berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, menu.getNama()+" gagal ditambahkan", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }



    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nama, harga;
        Button tambah;
        ImageView img;


        ConstraintLayout MainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = (TextView) itemView.findViewById(R.id.tv_name);
            harga = (TextView) itemView.findViewById(R.id.tv_harga);
            img = (ImageView) itemView.findViewById(R.id.gmbar);
            MainLayout = itemView.findViewById(R.id.mainLayout);
            tambah = (Button) itemView.findViewById(R.id.btn_tambah);
        }
    }
}
