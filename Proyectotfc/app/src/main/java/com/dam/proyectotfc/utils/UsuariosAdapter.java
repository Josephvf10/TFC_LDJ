package com.dam.proyectotfc.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dam.proyectotfc.R;
import com.dam.proyectotfc.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;


import java.util.List;

public class UsuariosAdapter extends RecyclerView.Adapter<UsuariosAdapter.UsuariosVH> implements View.OnClickListener {

    private List<Usuario> listaUsuarios;
    private View.OnClickListener listener;
    private boolean isFragment;
    private Context context;

    private FirebaseUser firebaseUser;

    public UsuariosAdapter(List<Usuario> listaUsuarios, Context context, boolean isFragment) {
        this.listaUsuarios = listaUsuarios;
        this.context = context;
        this.isFragment = isFragment;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public UsuariosVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.usuarios_layout, parent, false);
        view.setOnClickListener(listener);
        return new UsuariosAdapter.UsuariosVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuariosVH holder, int position) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        Usuario usuario = listaUsuarios.get(position);
        holder.tvNombreCompleto.setText(usuario.getNombreCompleto());
        holder.tvEmail.setText(usuario.getEmail());
        holder.tvTelefono.setText(usuario.getTelefono());

        //TODO: Método seguir isFollowed(usuario.getEmail(), holder.btnSeguir)

        //TODO: if(usuario.getEmail().equals((firebaseUser.getEmail()))) {}


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
        TextView tvTelefono;

        public UsuariosVH(@NonNull View itemView) {
            super(itemView);
            tvNombreCompleto = itemView.findViewById(R.id.tvNombreUsuarioBloque);
            tvEmail = itemView.findViewById(R.id.tvEmailUsuarioBloque);
            tvTelefono = itemView.findViewById(R.id.tvTelefonoUsuarioBloque);
        }
    }
}
