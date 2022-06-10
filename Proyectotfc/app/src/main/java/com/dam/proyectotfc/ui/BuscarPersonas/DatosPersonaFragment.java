package com.dam.proyectotfc.ui.BuscarPersonas;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.dam.proyectotfc.R;
import com.dam.proyectotfc.model.Usuario;
import com.dam.proyectotfc.ui.Perfil.EstadoJuegoFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DatosPersonaFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "FALLO GRAVE";
    public static final String CLAVE_JUEGO = "JUEGO";
    public static final String CLAVE_LISTA2 = "ESTADO2";
    public static final String CLAVE_USUARIO2 = "USUARIO2";

    TextView tvNombreDU, tvEmailDU, tvTelfDU;
    String emailUsuario;
    Button btnJuegosJugados2, btnJuegosCompletos2, btnJuegosMedias2, btnJuegosOlvidados2;
    Usuario usuario, usuarioEscrito;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_datos_persona, container, false);

        tvNombreDU = view.findViewById(R.id.tvNombreDU);
        tvEmailDU = view.findViewById(R.id.tvEmailDU);
        tvTelfDU = view.findViewById(R.id.tvTelefDU);
        btnJuegosJugados2  = view.findViewById(R.id.btnJuegosJugados2);
        btnJuegosCompletos2  = view.findViewById(R.id.btnJuegosCompletados2);
        btnJuegosMedias2  = view.findViewById(R.id.btnJuegosMedias2);
        btnJuegosOlvidados2  = view.findViewById(R.id.btnJuegosOlvidados2);

        btnJuegosJugados2.setOnClickListener(this);
        btnJuegosCompletos2.setOnClickListener(this);
        btnJuegosMedias2.setOnClickListener(this);
        btnJuegosOlvidados2.setOnClickListener(this);

        Bundle bundle = this.getArguments();

        emailUsuario = bundle.getString(BuscarPersonasFragment.CLAVE_USUARIO);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("usuarios");
        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("StringFormatMatches")
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    usuario = snapshot.getValue(Usuario.class);
                    if(usuario.getEmail().equals(emailUsuario)) {
                        usuarioEscrito = usuario;
                        tvNombreDU.setText(usuario.getNombreCompleto());
                        tvEmailDU.setText(String.format(getContext().getString(R.string.email_du), usuario.getEmail()));
                        tvTelfDU.setText(String.format(getContext().getString(R.string.telef_du), usuario.getTelefono()));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), R.string.msg_Error_db, Toast.LENGTH_LONG).show();
            }
        });

        return view;

    }



    @Override
    public void onClick(View view) {
        if (view.equals(btnJuegosJugados2)) {
            String jugado = "j";
            Bundle bundle = new Bundle();
            FragmentTransaction ft = getParentFragment().getChildFragmentManager().beginTransaction();
            bundle.putString(CLAVE_LISTA2, jugado);
            bundle.putString(CLAVE_USUARIO2, usuarioEscrito.getId());
            EstadoJuegoFragment datos = new EstadoJuegoFragment();
            datos.setArguments(bundle);
            ft.replace(getId(),datos);
            ft.addToBackStack(null);
            ft.setReorderingAllowed(true);
            ft.commit();
        } else if (view.equals(btnJuegosCompletos2)) {
            String completado = "c";
            Bundle bundle = new Bundle();
            FragmentTransaction ft = getParentFragment().getChildFragmentManager().beginTransaction();
            bundle.putString(CLAVE_LISTA2, completado);
            bundle.putString(CLAVE_USUARIO2, usuarioEscrito.getId());
            EstadoJuegoFragment datos = new EstadoJuegoFragment();
            datos.setArguments(bundle);
            ft.replace(getId(),datos);
            ft.addToBackStack(null);
            ft.setReorderingAllowed(true);
            ft.commit();
        } else if (view.equals(btnJuegosMedias2)) {
            String medias = "m";
            Bundle bundle = new Bundle();
            FragmentTransaction ft = getParentFragment().getChildFragmentManager().beginTransaction();
            bundle.putString(CLAVE_LISTA2, medias);
            bundle.putString(CLAVE_USUARIO2, usuarioEscrito.getId());
            EstadoJuegoFragment datos = new EstadoJuegoFragment();
            datos.setArguments(bundle);
            ft.replace(getId(),datos);
            ft.addToBackStack(null);
            ft.setReorderingAllowed(true);
            ft.commit();
        } else if (view.equals(btnJuegosOlvidados2)) {
            String olvidado = "o";
            Bundle bundle = new Bundle();
            FragmentTransaction ft = getParentFragment().getChildFragmentManager().beginTransaction();
            bundle.putString(CLAVE_LISTA2, olvidado);
            bundle.putString(CLAVE_USUARIO2, usuarioEscrito.getId());
            EstadoJuegoFragment datos = new EstadoJuegoFragment();
            datos.setArguments(bundle);
            ft.replace(getId(),datos);
            ft.addToBackStack(null);
            ft.setReorderingAllowed(true);
            ft.commit();
        }
    }
}