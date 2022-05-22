package com.example.uas_pam;

import android.annotation.SuppressLint;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class helper extends RecyclerView.Adapter<helper.MyViewHolder> {
    FirebaseFirestore db;
    Context context;
    ArrayList<DataMenu>list2;

    public helper(Context context, ArrayList<DataMenu> list2) {
        this.context = context;
        this.list2 = list2;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item2,parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        DataMenu menu = list2.get(position);

        holder.nama.setText(menu.getNama());
        holder.harga.setText(menu.getHarga());
        Glide.with(holder.img.getContext()).load(menu.getmUrl()).into(holder.img);


        holder.hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               db =  FirebaseFirestore.getInstance();
               db.collection("Cartt").document(menu.getId()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                   @Override
                   public void onSuccess(Void unused) {
                       Toast.makeText(context, menu.getNama()+" berhasil dihapus", Toast.LENGTH_SHORT).show();
                   }
               });


            }
        });


    }


    @Override
    public int getItemCount() {
        return list2.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nama, harga;
        Button hapus;
        ImageView img;


        ConstraintLayout cart;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nama = (TextView) itemView.findViewById(R.id.cart_name);
            harga = (TextView) itemView.findViewById(R.id.cart_harga);
            img = (ImageView) itemView.findViewById(R.id.cart_gmbar);
            cart = itemView.findViewById(R.id.cartLayout);
            hapus = (Button) itemView.findViewById(R.id.btnHapus);
        }

    }
}
