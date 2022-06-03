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
import com.dam.proyectotfc.model.JuegoDetalles;
import com.dam.proyectotfc.model.JuegosBusqueda;
import com.dam.proyectotfc.model.ResultEstado;
import com.dam.proyectotfc.model.ResultsDetalles;

import java.util.ArrayList;

public class EstadoJuegosAdapter extends RecyclerView.Adapter<EstadoJuegosAdapter.estadoJuegosVH>
implements View.OnClickListener{
    private ArrayList<ResultEstado> datos;
    private View.OnClickListener listener;

    public EstadoJuegosAdapter(ArrayList<ResultEstado> datos) {
        this.datos = datos;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public estadoJuegosVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.estado_juegos_layout, parent, false);
        v.setOnClickListener(listener);
        EstadoJuegosAdapter.estadoJuegosVH vh = new EstadoJuegosAdapter.estadoJuegosVH(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull estadoJuegosVH holder, int position) {
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

    public class estadoJuegosVH extends RecyclerView.ViewHolder {
        ImageView ivPort;
        TextView tvNom;
        public estadoJuegosVH(@NonNull View itemView) {
            super(itemView);
            ivPort = itemView.findViewById(R.id.ivPortadaJuegoEstado);
            tvNom = itemView.findViewById(R.id.tvNombreJuegoEstado);
        }

        public void bindJuegos(ResultEstado resultado) {
            Glide.with(ivPort.getContext()).load(resultado.getImage().getMediumUrl()).into(ivPort);
            tvNom.setText(resultado.getName());
        }
    }
}
