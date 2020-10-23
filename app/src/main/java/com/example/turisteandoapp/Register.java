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
    EditText firstNameR, lastNameR, emailR, celR, userR , passR, valPassR;
    Button btnRegis;
    TextView nameR, apellidoR, correoR, celularR, usuarioR, contraseñaR, valContraR;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firstNameR = findViewById(R.id.firstName);
        lastNameR = findViewById(R.id.lastName);
        emailR = findViewById(R.id.emailR);
        celR = findViewById(R.id.editTextPhone);
        userR = findViewById(R.id.userName);
        passR = findViewById(R.id.registryPass);
        valContraR = findViewById(R.id.registryPassVal);
        btnRegis = findViewById(R.id.btnRegistry);
        nameR = findViewById(R.id.nombre);
        apellidoR = findViewById(R.id.apellido);
        correoR = findViewById(R.id.correo);
        celularR = findViewById(R.id.celular);
        usuarioR = findViewById(R.id.usuario);
        contraseñaR = findViewById(R.id.contraseña);
        valContraR = findViewById(R.id.valContra);
        fAuth = FirebaseAuth.getInstance();


        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo =  emailR.getText().toString().trim();
                String pass = passR.getText().toString().trim();
                if (TextUtils.isEmpty(correo)){
                    emailR.setError("Email es necesario");
                    return;
                }
                if (TextUtils.isEmpty(pass)){
                    passR.setError("Contraseña necesaria");
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