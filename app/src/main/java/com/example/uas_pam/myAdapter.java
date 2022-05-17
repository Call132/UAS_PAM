package com.example.uas_pam;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        holder.checkbox.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            DatabaseReference db;
            db = FirebaseDatabase.getInstance().getReference().child("Cart");

            Map<String, Object> menu = new HashMap<>();
            menu.put("Nama", model.getNama());
            menu.put("Harga", model.getHarga());
            menu.put("mUrl", model.getmUrl());
            if(isChecked){

                db.push().setValue(menu).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(compoundButton.getContext(), model.getNama()  + " berhasil Ditambahkan" , Toast.LENGTH_SHORT).show();
                    }
                });

            }else {

                db.child(getRef(position).getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(compoundButton.getContext(), model.getNama() + " berhasil dihapus", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);

        return new MyViewHolder(v);

    }


    static class MyViewHolder extends RecyclerView.ViewHolder{
       TextView nama, harga;
       CheckBox checkbox;
       ImageView img;


       ConstraintLayout MainLayout;

       public MyViewHolder(@NonNull View itemView) {
           super(itemView);

           nama = (TextView) itemView.findViewById(R.id.tv_name);
           harga = (TextView) itemView.findViewById(R.id.tv_harga);
           img = (ImageView) itemView.findViewById(R.id.gmbar);
           MainLayout = itemView.findViewById(R.id.mainLayout);
           checkbox = (CheckBox) itemView.findViewById(R.id.checkBox2);








       }

   }

}
