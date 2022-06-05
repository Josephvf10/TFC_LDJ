package com.dam.proyectotfc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dam.proyectotfc.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnGuardarR;
    Button btnCancelR;
    EditText etNomR;
    EditText etEmailR;
    EditText etTelefonoR;
    EditText etContrasenaR;
    EditText etContrasenaRepR;

    FirebaseAuth fAuth;
    FirebaseUser fUser;
    FirebaseDatabase fdb;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        fAuth = FirebaseAuth.getInstance();
        fUser = fAuth.getCurrentUser();
        fdb = FirebaseDatabase.getInstance();
        dbRef = fdb.getReference("usuarios");

        etNomR = findViewById(R.id.etNombre);
        etEmailR = findViewById(R.id.etCorreo);
        etTelefonoR = findViewById(R.id.etTelefono);
        etContrasenaR = findViewById(R.id.etContrasenaR);
        etContrasenaRepR = findViewById(R.id.etContrasenaRepR);

        btnCancelR = findViewById(R.id.btnCancelarR);
        btnGuardarR = findViewById(R.id.btnGuardarR);

        btnCancelR.setOnClickListener(this);
        btnGuardarR.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v.equals(btnCancelR)) {
            finish();
        } else if (v.equals(btnGuardarR))
            datosRegistro();

    }

    private void datosRegistro() {
        String nombre = etNomR.getText().toString().trim();
        String email = etEmailR.getText().toString().trim();
        String telefono = etTelefonoR.getText().toString().trim();
        String contrasena = etContrasenaR.getText().toString().trim();
        String contrasenaRep = etContrasenaRepR.getText().toString().trim();

        if (!nombre.isEmpty() & !email.isEmpty() & !telefono.isEmpty() & !contrasena.isEmpty()
                & !contrasena.isEmpty()) {
            if (!nombre.trim().contains("")) {
                nombre = "";
                etNomR.setText("");

            } else if (!contrasena.equals(contrasenaRep) | contrasena.length() < 6) {
                contrasena = "";
                etContrasenaR.setText("");
                etContrasenaRepR.setText("");
                Toast.makeText(RegistroActivity.this,
                        getString(R.string.msj_contrsena_error),
                        Toast.LENGTH_LONG).show();

            }
            if (!email.contains("@") || !email.contains(".")) {
                email = "";
                etEmailR.setText("");
                Toast.makeText(RegistroActivity.this,
                        getString(R.string.msj_email_error),
                        Toast.LENGTH_LONG).show();

            } else if (telefono.length() != 13 & telefono.length() != 9) {
                telefono = "";
                etTelefonoR.setText("");
                Toast.makeText(RegistroActivity.this,
                        getString(R.string.msj_tel_comp),
                        Toast.LENGTH_LONG).show();

            } else {
                Usuario user = new Usuario(nombre, telefono, email);
                registrarUser(email, contrasena, user);
            }

        } else {
            Toast.makeText(RegistroActivity.this,
                    getString(R.string.msj_campo_vacio),
                    Toast.LENGTH_LONG).show();
        }

    }

    private void registrarUser(String email, String contrasena, Usuario user) {
        fAuth.createUserWithEmailAndPassword(email, contrasena)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            fUser = fAuth.getCurrentUser();
                            dbRef.child(fUser.getUid()).setValue(user);
                            Toast.makeText(RegistroActivity.this,
                                    getString(R.string.msj_registrado),
                                    Toast.LENGTH_LONG).show();
                            Intent i = new Intent(RegistroActivity.this, MainActivity.class);
                            //adFlags para que al dar atras no retroceda por todos los activity
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                            finish();
                        } else {
                            Toast.makeText(RegistroActivity.this,
                                    getString(R.string.msj_ya_registrado),
                                    Toast.LENGTH_LONG).show();

                            etNomR.setText("");
                            etEmailR.setText("");
                            etTelefonoR.setText("");
                            etContrasenaR.setText("");
                            etContrasenaRepR.setText("");
                        }
                    }
                });
    }
}