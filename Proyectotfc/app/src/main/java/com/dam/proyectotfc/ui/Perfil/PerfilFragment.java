package com.dam.proyectotfc.ui.Perfil;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.dam.proyectotfc.LoginActivity;
import com.dam.proyectotfc.MainActivity;
import com.dam.proyectotfc.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class PerfilFragment extends Fragment
implements View.OnClickListener{

    Button btnCerrar;
    TextView tvNom;
    FirebaseAuth mAuth;
    DatabaseReference mDataBase;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_perfil,container,false);

        btnCerrar  = v.findViewById(R.id.btnCerrarSesion);
        tvNom = v.findViewById(R.id.tvNombre);
        mAuth = FirebaseAuth.getInstance();
        mDataBase = FirebaseDatabase.getInstance().getReference();

        btnCerrar.setOnClickListener(this);

        //userInfo();

        return v;
    }
/*
    private void userInfo(){
        String id= mAuth.getCurrentUser().getUid();
        mDataBase.child("usuarios").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String nombre = snapshot.child("nombreCompleto").getValue().toString();

                    tvNom.setText(nombre);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


 */
    @Override
    public void onClick(View view) {
        if(view.equals(btnCerrar)){
            mAuth.signOut();
            Intent i = new Intent(getContext(), LoginActivity.class);
            startActivity(i);
        }
    }

}