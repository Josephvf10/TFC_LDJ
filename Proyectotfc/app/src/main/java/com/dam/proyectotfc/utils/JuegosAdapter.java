package com.dam.proyectotfc.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dam.proyectotfc.R;
import com.dam.proyectotfc.model.JuegosBusqueda;

import java.util.ArrayList;

public class JuegosAdapter extends RecyclerView.Adapter<JuegosAdapter.JuegosVH>
        implements View.OnClickListener{

    private ArrayList<JuegosBusqueda> datos;
    private View.OnClickListener listener;

    public JuegosAdapter(ArrayList<JuegosBusqueda> datos) {
        this.datos = datos;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public JuegosVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.juegos_layout, parent, false);
        v.setOnClickListener(listener);
        JuegosVH vh = new JuegosVH(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull JuegosVH holder, int position) {
        holder.bindJuegos(datos.get(position));
    }


    @Override
    public int getItemCount() {
        return datos.size();
    }

    @Override
    public void onClick(View view) {
        if (listener != null){
            listener.onClick(view);
        }
    }

    public class JuegosVH extends RecyclerView.ViewHolder {
        ImageView ivPort;
        TextView tvNom;
        TextView tvDeck;
        public JuegosVH(@NonNull View itemView) {
            super(itemView);
            ivPort = itemView.findViewById(R.id.ivPortadaJuego);
            tvNom = itemView.findViewById(R.id.tvNombreJuego);
            tvDeck = itemView.findViewById(R.id.tvResumenJuego);
        }

        public void bindJuegos(JuegosBusqueda resultado) {
            Glide.with(ivPort.getContext()).load(resultado.getImage().getMediumUrl()).into(ivPort);
            tvNom.setText(resultado.getName());
            tvDeck.setText(resultado.getDeck());
        }
    }
}
