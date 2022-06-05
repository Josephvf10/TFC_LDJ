package com.dam.proyectotfc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ModificarActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etModCuentaNom;
    EditText etModCuentaTelf;
    Button btnConfirmarMod;

    FirebaseDatabase fdb;
    DatabaseReference dbRef;
    FirebaseAuth fAuth;
    FirebaseUser fUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        etModCuentaNom = findViewById(R.id.etModCuentaNom);
        etModCuentaTelf = findViewById(R.id.etModCuentaTelf);
        btnConfirmarMod = findViewById(R.id.btnConfirmarMod);
        btnConfirmarMod.setOnClickListener(this);

        fdb = FirebaseDatabase.getInstance();
        dbRef = fdb.getReference("usuarios");
        fAuth = FirebaseAuth.getInstance();
        fUser = fAuth.getCurrentUser();
    }

    @Override
    public void onClick(View view) {
        String id = fAuth.getUid();
        String nom = etModCuentaNom.getText().toString().trim();
        String telf = etModCuentaTelf.getText().toString().trim();
        if (nom.isEmpty() & telf.isEmpty()){
            Toast.makeText(this,
                    getString(R.string.msj_campo_vacio),
                    Toast.LENGTH_LONG).show();
        } else if (telf.length() != 13 & telf.length() != 9) {
            Toast.makeText(this,
                    getString(R.string.msj_tel_comp),
                    Toast.LENGTH_LONG).show();
        } else {
            dbRef.child(id).child("nombreCompleto").setValue(nom);
            dbRef.child(id).child("telefono").setValue(telf);
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }
    }
}