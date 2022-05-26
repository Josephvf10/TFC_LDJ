package com.dam.proyectotfc.ui.BuscarPersonas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.dam.proyectotfc.R;
import com.dam.proyectotfc.model.Usuario;

import java.util.List;

public class BuscarPersonasFragment extends Fragment {

    private List<Usuario> listaUsuarios;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buscar_personas,container,false);

        return view;
    }

}