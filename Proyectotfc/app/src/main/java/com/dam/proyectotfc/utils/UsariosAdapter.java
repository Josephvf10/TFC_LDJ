package com.dam.proyectotfc.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dam.proyectotfc.R;
import com.google.firebase.auth.FirebaseAuth;


import java.util.ArrayList;

public class UsariosAdapter extends RecyclerView.Adapter<UsariosAdapter.UsuariosVH> implements View.OnClickListener {

    private ArrayList<FirebaseAuth> listaUsuarios;
    private View.OnClickListener listener;

    public UsariosAdapter(ArrayList<FirebaseAuth> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public UsuariosVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.usuarios_layout, parent, false);
        v.setOnClickListener(listener);
        UsuariosVH vh = new UsuariosVH(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull UsuariosVH holder, int position) {
        holder.bindUsuarios(listaUsuarios.get(position));
    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    @Override
    public void onClick(View view) {
        if (listener != null){
            listener.onClick(view);
        }
    }

    public class UsuariosVH extends RecyclerView.ViewHolder {
        TextView tvNombreCompleto;
        TextView tvEmail;

        public UsuariosVH(@NonNull View itemView) {
            super(itemView);
            tvNombreCompleto = itemView.findViewById(R.id.tvNombreUsuarioBloque);
            tvEmail = itemView.findViewById(R.id.tvEmailUsuarioBloque);
        }

        public void bindUsuarios(FirebaseAuth resultado) {
            tvNombreCompleto.setText(resultado.getUid());
            tvEmail.setText(resultado.getTenantId());
        }
    }
}
