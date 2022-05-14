package com.example.uas_pam;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.zip.Inflater;


public class myAdapter extends FirebaseRecyclerAdapter<DataMenu, myAdapter.MyViewHolder> {

    public myAdapter(@NonNull FirebaseRecyclerOptions<DataMenu> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull DataMenu model) {
        holder.nama.setText(model.getNama());
        holder.harga.setText(model.getHarga());

        Glide.with(holder.img.getContext())
                .load(model.getmUrl()).into(holder.img);
        holder.MainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
       TextView nama, harga;
       ImageView img;
       ConstraintLayout MainLayout;

       public MyViewHolder(@NonNull View itemView) {
           super(itemView);

           nama = (TextView) itemView.findViewById(R.id.tv_name);
           harga = (TextView) itemView.findViewById(R.id.tv_harga);
           img = (ImageView) itemView.findViewById(R.id.gmbar);
           MainLayout = itemView.findViewById(R.id.mainLayout);

       }
   }

}
