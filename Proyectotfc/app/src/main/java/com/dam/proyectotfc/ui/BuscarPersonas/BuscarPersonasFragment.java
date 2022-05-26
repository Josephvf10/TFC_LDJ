package com.dam.proyectotfc.ui.BuscarPersonas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.dam.proyectotfc.R;
import com.dam.proyectotfc.model.Usuario;
import com.dam.proyectotfc.utils.UsuariosAdapter;

import java.util.List;

public class BuscarPersonasFragment extends Fragment {

    private List<Usuario> listaUsuarios;
    private UsuariosAdapter adapter;
    private RecyclerView rv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buscar_personas,container,false);

        return view;
    }

}