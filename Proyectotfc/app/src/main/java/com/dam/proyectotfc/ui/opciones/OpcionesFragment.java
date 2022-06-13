package com.dam.proyectotfc.ui.opciones;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dam.proyectotfc.LoginActivity;
import com.dam.proyectotfc.ModificarActivity;
import com.dam.proyectotfc.R;
import com.dam.proyectotfc.ui.Perfil.PerfilFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class OpcionesFragment extends Fragment implements View.OnClickListener {

    TextView tvElimCuenta;
    TextView tvModCuenta;
    TextView tvNom;
    FirebaseDatabase fdb;
    DatabaseReference dbRef;
    FirebaseAuth fAuth;
    FirebaseUser fUser;
    Button btnCerrar;
    DatabaseReference mDataBase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_opciones, container, false);

        tvElimCuenta = v.findViewById(R.id.tvEliminarCuenta);
        tvElimCuenta.setOnClickListener(this);
        tvModCuenta = v.findViewById(R.id.tvModificarCuenta);
        tvModCuenta.setOnClickListener(this);
        btnCerrar  = v.findViewById(R.id.btnCerrarSesion);
        btnCerrar.setOnClickListener(this);
        tvNom=v.findViewById(R.id.tvNomOpciones);

        fdb = FirebaseDatabase.getInstance();
        dbRef = fdb.getReference("usuarios");
        fAuth = FirebaseAuth.getInstance();
        fUser = fAuth.getCurrentUser();
        mDataBase = FirebaseDatabase.getInstance().getReference();

        userInfo();

        return v;
    }

    private void userInfo() {
        String id= fAuth.getCurrentUser().getUid();
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

    @Override
    public void onClick(View view) {
        if (view.equals(tvElimCuenta)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            View viewD = getActivity().getLayoutInflater().inflate(R.layout.dialog_eliminar_cuenta, null);
            builder.setView(viewD);
            builder.setTitle(R.string.title_eliminar_cuenta);
            builder.setPositiveButton(R.string.dialog_si_elim,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            eliminar();
                            dialog.dismiss();
                        }
                    });

            builder.setNegativeButton(R.string.dialog_no_elim,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            dialog.dismiss();
                        }
                    });

            builder.create().show();
        } else if (view.equals(tvModCuenta)) {
            Intent i = new Intent(getContext(), ModificarActivity.class);
            startActivity(i);
        } else if(view.equals(btnCerrar)) {
            fAuth.signOut();
            Intent i = new Intent(getContext(), LoginActivity.class);
            startActivity(i);
        }

    }

    private void eliminar() {
        String id = fUser.getUid();
        dbRef.child(id).removeValue();
        fAuth.signOut();
        fUser.delete();
        Intent i = new Intent(getContext(), LoginActivity.class);
        startActivity(i);
    }
}