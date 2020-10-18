package com.example.turisteandoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    EditText nameU, nEmail, nPass;
    Button btnRegis;
    TextView tittle;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nameU = findViewById(R.id.completeName);
        nEmail = findViewById(R.id.correoR);
        nPass = findViewById(R.id.passRe);
        btnRegis = findViewById(R.id.btnRegistry);
        tittle = findViewById(R.id.textView);
        fAuth = FirebaseAuth.getInstance();


        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo =  nEmail.getText().toString().trim();
                String pass = nPass.getText().toString().trim();
                if (TextUtils.isEmpty(correo)){
                    nEmail.setError("Email es necesario");
                    return;
                }
                if (TextUtils.isEmpty(pass)){
                    nPass.setError("Contraseña necesaria");
                    return;
                }
                fAuth.createUserWithEmailAndPassword(correo,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this,"Usuario creado ", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        }else{
                            Toast.makeText(Register.this,"Usuario no creado "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();


                        }
                    }
                });
            }
        });

    }
}