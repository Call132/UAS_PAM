package com.example.uas_pam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;


import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        BottomNavigationView btnNav = findViewById(R.id.bottomNavView);
        btnNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_layout, new home()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragments = null;

                    switch (item.getItemId()) {
                        case R.id.home:
                            selectedFragments = new home();
                            break;

                        case R.id.Menu:
                            selectedFragments = new menu1();
                            break;

                        case R.id.setting:
                            selectedFragments = new settings();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().
                            replace(R.id.fragment_layout,
                                    selectedFragments).commit();
                    return true;
                }
            };

}