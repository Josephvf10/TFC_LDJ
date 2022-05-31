package com.dam.proyectotfc.ui.BuscarPersonas;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dam.proyectotfc.R;
import com.dam.proyectotfc.RegistroActivity;
import com.dam.proyectotfc.model.Usuario;
import com.dam.proyectotfc.utils.UsuariosAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BuscarPersonasFragment extends Fragment implements View.OnClickListener {

    private List<Usuario> listaUsuarios;
    private UsuariosAdapter adapter;
    private RecyclerView rv;
    private Button btnBuscar;
    private EditText etUsuraio;

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
        adapter = new UsuariosAdapter(listaUsuarios, getContext(), true);
        rv.setOnClickListener(this);
        rv.setAdapter(adapter);

        leerUsuarios();

        return view;
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
                        listaUsuarios.add(usuario);
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