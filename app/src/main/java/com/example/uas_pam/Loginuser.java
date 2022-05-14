package com.example.uas_pam;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class Loginuser extends AppCompatActivity {
    EditText Etemail, Etusername, Etpassword;
    Button Lgn;
    TextView back2;
    String email, username, password;
    private FirebaseAuth mAuth;
    String Admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginuser);
        Admin = "admin";

        mAuth = FirebaseAuth.getInstance();

        Etemail = findViewById(R.id.etEmail);
        Etusername = findViewById(R.id.etUsername);
        Etpassword = findViewById(R.id.etPassword);

        Lgn = findViewById(R.id.buttonLgn);
        Lgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cekLogin();
            }
        });


        back2 = findViewById(R.id.txtRegister2);
        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Loginuser.this, Register.class);
                startActivity(i);
            }
        });

    }

    private void cekLogin() {
        email =  Etemail.getText().toString();
        password = Etpassword.getText().toString();

        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent i = new Intent(Loginuser.this, MainActivity.class);
                    startActivity(i);
                    Toast.makeText(Loginuser.this, "Login Success", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(Loginuser.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}