package com.dam.proyectotfc.ui.Perfil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.dam.proyectotfc.LoginActivity;
import com.dam.proyectotfc.R;
import com.dam.proyectotfc.model.JuegosBusqueda;
import com.dam.proyectotfc.model.Usuario;
import com.dam.proyectotfc.ui.BuscarPersonas.BuscarPersonasFragment;
import com.dam.proyectotfc.ui.Juegos.DatosJuegoFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class PerfilFragment extends Fragment
implements View.OnClickListener{

    public static final String CLAVE_LISTA = "ESTADO";
    public static final String CLAVE_USUARIO = "USUARIO";
    Button btnJuegosJugados;
    Button btnJuegosCompletos;
    Button btnJuegosMedias;
    Button btnJuegosOlvidados;
    TextView tvNom;
    FirebaseAuth mAuth;
    DatabaseReference mDataBase;
    DatabaseReference dbRef;
    ValueEventListener vel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_perfil,container,false);


        btnJuegosJugados  = v.findViewById(R.id.btnJuegosJugados);
        btnJuegosCompletos  = v.findViewById(R.id.btnJuegosCompletados);
        btnJuegosMedias  = v.findViewById(R.id.btnJuegosMedias);
        btnJuegosOlvidados  = v.findViewById(R.id.btnJuegosOlvidados);
        tvNom = v.findViewById(R.id.tvNombre);
        mAuth = FirebaseAuth.getInstance();
        mDataBase = FirebaseDatabase.getInstance().getReference();

        btnJuegosJugados.setOnClickListener(this);
        btnJuegosCompletos.setOnClickListener(this);
        btnJuegosMedias.setOnClickListener(this);
        btnJuegosOlvidados.setOnClickListener(this);

        userInfo();

        return v;
    }


    private void userInfo(){
        String id= mAuth.getCurrentUser().getUid();
        mDataBase.child("usuarios").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String nombre = snapshot.child("nombreCompleto").getValue().toString();

                    tvNom.setText("Bienvenido "+nombre);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    @Override
    public void onClick(View view) {
        if (view.equals(btnJuegosJugados)) {
            String jugado = "j";
            Bundle bundle = new Bundle();
            FragmentTransaction ft = getParentFragment().getChildFragmentManager().beginTransaction();
            bundle.putString(CLAVE_LISTA, jugado);
            bundle.putString(CLAVE_USUARIO, mAuth.getUid());
            EstadoJuegoFragment datos = new EstadoJuegoFragment();
            datos.setArguments(bundle);
            ft.replace(getId(),datos);
            ft.addToBackStack(null);
            ft.commit();
        } else if (view.equals(btnJuegosCompletos)) {
            String completado = "c";
            Bundle bundle = new Bundle();
            FragmentTransaction ft = getParentFragment().getChildFragmentManager().beginTransaction();
            bundle.putString(CLAVE_LISTA, completado);
            bundle.putString(CLAVE_USUARIO, mAuth.getUid());
            EstadoJuegoFragment datos = new EstadoJuegoFragment();
            datos.setArguments(bundle);
            ft.replace(getId(),datos);
            ft.addToBackStack(null);
            ft.commit();
        } else if (view.equals(btnJuegosMedias)) {
            String medias = "m";
            Bundle bundle = new Bundle();
            FragmentTransaction ft = getParentFragment().getChildFragmentManager().beginTransaction();
            bundle.putString(CLAVE_LISTA, medias);
            bundle.putString(CLAVE_USUARIO, mAuth.getUid());
            EstadoJuegoFragment datos = new EstadoJuegoFragment();
            datos.setArguments(bundle);
            ft.replace(getId(),datos);
            ft.addToBackStack(null);
            ft.commit();
        } else if (view.equals(btnJuegosOlvidados)) {
            String olvidado = "o";
            Bundle bundle = new Bundle();
            FragmentTransaction ft = getParentFragment().getChildFragmentManager().beginTransaction();
            bundle.putString(CLAVE_LISTA, olvidado);
            bundle.putString(CLAVE_USUARIO, mAuth.getUid());
            EstadoJuegoFragment datos = new EstadoJuegoFragment();
            datos.setArguments(bundle);
            ft.replace(getId(),datos);
            ft.addToBackStack(null);
            ft.commit();
        }
    }

}