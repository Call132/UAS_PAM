package com.example.uas_pam;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;


public class home extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        TextView  tv2;
        WebView tv1;
        ImageView img1;



        View V = inflater.inflate(R.layout.fragment_home, container, false);
        tv1 =(WebView) V.findViewById(R.id.tv_1);
        tv2 = V.findViewById(R.id.tv_2);
        img1 = V.findViewById(R.id.imageView);

        String text = "Kantin Engineering adalah kantin yang berada di fakultas teknik universitas negeri Gorontalo, Kantin ini menyediakan berbagai macam Makanan dan Minuman untuk mahasiswa."
                ;

        img1.setImageResource(R.drawable.canteen);
        tv2.setText("Kantin Engineering");
        tv1.loadData("<p style=\"text-align: justify\">"+ text + "</p>", "text/html", "UTF-8");
//        tv1.setText("Kantin Engineering adalah kantin yang berada di fakultas teknik universitas negeri Gorontalo, Kantin ini menyediakan berbagai macam Makanan dan Minuman untuk mahasiswa");
        return V;
    }
}