package com.dam.proyectotfc.ui.Juegos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dam.proyectotfc.R;
import com.dam.proyectotfc.model.Genre;
import com.dam.proyectotfc.model.JuegoDetalles;
import com.dam.proyectotfc.model.JuegosBusqueda;
import com.dam.proyectotfc.model.Platform;
import com.dam.proyectotfc.model.ResultsDetalles;
import com.dam.proyectotfc.retrofit.APIRestService;
import com.dam.proyectotfc.retrofit.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DatosJuegoFragment extends Fragment {

    private static final String TAG = "FALLO";
    TextView tvNombre;
    ImageView ivPortada;
    TextView tvDes;
    TextView tvGen;
    TextView tvPlat;
    String juegoGuid;
    Button btnVolver;
    String genero;
    String plataforma;

    public DatosJuegoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater
                .inflate(R.layout.fragment_datos_juego, container, false);

        Bundle bundle = this.getArguments();

        JuegosBusqueda info = bundle.getParcelable(JuegosInfoFragment.CLAVE_JUEGO);

        juegoGuid = info.getGuid();

        tvNombre = v.findViewById(R.id.tvNombreJuegoDetalle);
        tvDes = v.findViewById(R.id.tvDescripcionJuegoDetalle);
        tvGen = v.findViewById(R.id.tvGeneroJuegoDetalle);
        tvPlat = v.findViewById(R.id.tvPlataformasJuegoDetalle);
        ivPortada = v.findViewById(R.id.ivPortadaJuegoDetalle);

        Retrofit r = RetrofitClient.getClient(APIRestService.BASE_URL);
        APIRestService ars = r.create(APIRestService.class);

        Call<JuegoDetalles> call = ars.getInfo(juegoGuid,APIRestService.KEY, APIRestService.FIELD_LIST,
                APIRestService.FORMAT);

        call.enqueue(new Callback<JuegoDetalles>() {
            @Override
            public void onResponse(Call<JuegoDetalles> call, Response<JuegoDetalles> response) {
                if (!response.isSuccessful()) {
                    Log.i(TAG, "error" + response.code());
                } else {

                   ResultsDetalles juegoD = response.body().getResults();
                   tvNombre.setText(juegoD.getName());
                   Glide.with(ivPortada.getContext()).load(juegoD.getImage().getMediumUrl()).into(ivPortada);
                   if (juegoD.getDescription() == null) {
                       tvDes.setText(R.string.no_descripcion);
                   }else {
                       tvDes.setText(juegoD.getDescription());
                   }
                    for (Genre gen: juegoD.getGenres()) {
                        genero += gen.getName();
                    }
                    tvGen.setText(String.format(getResources().getString(R.string.tv_genero_juego_detalle),genero));
                    for (Platform plat: juegoD.getPlatforms()) {
                        plataforma += plat.getName();
                    }
                    tvPlat.setText(String.format(getResources().getString(R.string.tv_plataformas_juego_detalle),plataforma));
                }
            }

            @Override
            public void onFailure(Call<JuegoDetalles> call, Throwable t) {
                Log.e(TAG ,"he fallao" + t.toString());
            }
        });

        return v;
    }
}