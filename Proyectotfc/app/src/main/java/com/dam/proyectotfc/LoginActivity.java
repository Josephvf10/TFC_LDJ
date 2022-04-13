package com.dam.proyectotfc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputLayout tipUsuario;
    TextInputLayout tipContraseña;
    TextView tvUsuario;
    TextView tvRegistrarse;
    Button btnAceptar;
    String usuario = "";
    String contraseña = "";
    //FirebaseAuth lAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tipUsuario = findViewById(R.id.etUsuario);
        tipContraseña = findViewById(R.id.etContrasena);
        tvUsuario = findViewById(R.id.tvUsuario);
        tvRegistrarse = findViewById(R.id.tvRegistro);
        btnAceptar = findViewById(R.id.btnAceptar);
        //lAuth = FirebaseAuth.getInstance();


        btnAceptar.setOnClickListener(this);
        tvRegistrarse.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        usuario = tipUsuario.getEditText().getText().toString();
        contraseña = tipContraseña.getEditText().getText().toString();

        if (v.equals(tvRegistrarse)) {
            Intent intent = new Intent(this, RegistroActivity.class);
            startActivity(intent);
        } else if (v.equals(btnAceptar)) {
            if (usuario.isEmpty() || contraseña.isEmpty()) {
                Toast.makeText(this, R.string.msg_Error, Toast.LENGTH_SHORT).show();
            } else {
                //loginUser();
            }
        }
    }

    /*public void loginUser() {
        lAuth.signInWithEmailAndPassword(usuario, contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent i = new Intent(PaginaLogin.this, MainActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(PaginaLogin.this, R.string.msg_ErrorLogin, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }*/
}