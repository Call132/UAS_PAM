package com.example.uas_pam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    EditText email, password, conpass;
    Button regis;
    FirebaseAuth mAuth;
    String Email, pass1, pass2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        conpass = findViewById(R.id.etPass);

        regis = findViewById(R.id.btnregis);
        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pass1 != pass2){
                    Toast.makeText(Register.this, "password tidak sama", Toast.LENGTH_SHORT).show();
                }
                registrasi();
                Intent i = new Intent(Register.this, Loginuser.class);
                startActivity(i);
            }
        });
    }

    private void registrasi() {
        Email = email.getText().toString();
        pass1 = password.getText().toString();
      pass2 =   conpass.getText().toString();

        mAuth.createUserWithEmailAndPassword(Email,pass1)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Register.this, "Success", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(Register.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}