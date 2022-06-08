package com.dam.proyectotfc.ui.BuscarPersonas;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dam.proyectotfc.R;
import com.dam.proyectotfc.model.Usuario;
import com.dam.proyectotfc.ui.Perfil.EstadoJuegoFragment;
import com.dam.proyectotfc.utils.UsuariosAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BuscarPersonasFragment extends Fragment implements View.OnClickListener {

    public static final String CLAVE_USUARIO = "USUARIO";

    private List<Usuario> listaUsuarios;
    private UsuariosAdapter adapter;
    private RecyclerView rv;
    private Button btnBuscar;
    private EditText etUsuraio;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser usuarioActual;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buscar_personas,container,false);

        btnBuscar = view.findViewById(R.id.btnBuscarPersonas);
        etUsuraio = view.findViewById(R.id.etBusquedaPersonas);

        btnBuscar.setOnClickListener(this);

        rv = view.findViewById(R.id.rvBuscarPersonas);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        listaUsuarios = new ArrayList<>();


        leerUsuarios();
        cargarRV();

        return view;
    }

    private void cargarRV() {
        adapter = new UsuariosAdapter(listaUsuarios, getContext(), true);
        adapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario = listaUsuarios.get(rv.getChildAdapterPosition(v));

                Bundle bundle = new Bundle();
                FragmentTransaction ft = getParentFragment().getChildFragmentManager().beginTransaction();
                bundle.putString(CLAVE_USUARIO, usuario.getEmail());
                DatosPersonaFragment datos = new DatosPersonaFragment();
                datos.setArguments(bundle);
                ft.replace(getId(),datos);
                ft.addToBackStack(null);
                ft.setReorderingAllowed(true);
                ft.commit();
            }
        });
        rv.setAdapter(adapter);
    }

    private void leerUsuarios() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("usuarios");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (TextUtils.isEmpty(etUsuraio.getText().toString())) {
                    listaUsuarios.clear();
                    for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                        Usuario usuario = snapshot.getValue(Usuario.class);
                        firebaseAuth = FirebaseAuth.getInstance();
                        usuarioActual = firebaseAuth.getCurrentUser();
                        if(!usuario.getEmail().equals(usuarioActual.getEmail())) {
                            listaUsuarios.add(usuario);
                        }

                    }

                    adapter.notifyDataSetChanged();
                } else if(!TextUtils.isEmpty(etUsuraio.getText().toString())) {
                    listaUsuarios.clear();
                    for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                        Usuario usuario = snapshot.getValue(Usuario.class);
                        if (usuario.getNombreCompleto().toLowerCase(Locale.ROOT).contains(etUsuraio.getText().toString().toLowerCase(Locale.ROOT))) {
                            listaUsuarios.add(usuario);
                        }
                    }
                    if (listaUsuarios.isEmpty()){
                        Toast.makeText(getContext().getApplicationContext(),
                                getString(R.string.no_personas),
                                Toast.LENGTH_LONG).show();
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext().getApplicationContext(),
                        getString(R.string.msg_Error_db),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.equals(btnBuscar)) {
            leerUsuarios();
        }
    }
}