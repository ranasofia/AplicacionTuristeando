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

public class Login extends AppCompatActivity {
    EditText nEmail,nPass;
    Button btIngresar;
    FirebaseAuth fAuth;
    TextView tittle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        nEmail = findViewById(R.id.usuarioLogin);
        nPass = findViewById(R.id.passLogin);
        btIngresar = findViewById(R.id.btnSend);

        fAuth = FirebaseAuth.getInstance();

        btIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = nEmail.getText().toString().trim();
                String contra = nPass.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    nEmail.setError("MAL");
                    return;
                }
                if(TextUtils.isEmpty(contra)){
                    nPass.setError("MAL");
                    return;
                }

                if(nPass.length() < 6){
                    nPass.setError("Password Must be >= 6 Characters");
                    return;
                }
                //autenticar usuario
                fAuth.signInWithEmailAndPassword(email,contra).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Login.this,"Inicio de sesion correcto" , Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else {
                            Toast.makeText(Login.this,"Error !"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }

        });


    }
}