package com.example.uas_pam;



import android.annotation.SuppressLint;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import java.util.Map;


public class myAdapter extends FirebaseRecyclerAdapter<dataCart, myAdapter.MyViewHolder> {
    DatabaseReference db;


    public myAdapter(@NonNull FirebaseRecyclerOptions<dataCart> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull dataCart model) {

         holder.nama.setText(model.getNama());
         holder.harga.setText(model.getHarga());

        Glide.with(holder.img.getContext())
                .load(model.getmUrl()).into(holder.img);




    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item2,parent,false);

        return new MyViewHolder(v);

    }


    static class MyViewHolder extends RecyclerView.ViewHolder{
       TextView nama, harga;
       Button hapus;
       ImageView img;


       ConstraintLayout cart;

       public MyViewHolder(@NonNull View itemView) {
           super(itemView);

           nama = (TextView) itemView.findViewById(R.id.tv_name);
           harga = (TextView) itemView.findViewById(R.id.tv_harga);
           img = (ImageView) itemView.findViewById(R.id.gmbar);
           cart = itemView.findViewById(R.id.cartLayout);
           hapus = (Button) itemView.findViewById(R.id.btnHapus);


       }

   }


}
