package com.dam.proyectotfc;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dam.proyectotfc.model.Platform;
import com.dam.proyectotfc.model.Result;
import com.dam.proyectotfc.model.Results;
import com.dam.proyectotfc.retrofit.APIRestService;
import com.dam.proyectotfc.retrofit.RetrofitClient;
import com.dam.proyectotfc.ui.Juegos.JuegosFragment;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DatosJuegoActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "FALLO GRAVE";

    TextView tvNomDet, tvDescDet, tvGenDet, tvPlatDet;
    ImageView ivImagenDet;
    String juegoguid;
    Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_juego);

        tvNomDet = findViewById(R.id.tvNombreJuegoDetalle);
        tvDescDet = findViewById(R.id.tvDescripcionJuegoDetalle);
        tvGenDet = findViewById(R.id.tvGeneroJuegoDetalle);
        tvPlatDet = findViewById(R.id.tvPlataformasJuegoDetalle);
        ivImagenDet = findViewById(R.id.ivPortadaJuegoDetalle);
        btnVolver = findViewById(R.id.btnVolverDet);

        btnVolver.setOnClickListener(this);

        juegoguid = getIntent().getStringExtra(JuegosFragment.CLAVE_JUEGO);


    }

    @Override
    protected void onResume() {
        addValueEventListener();
        super.onResume();
    }

    private void addValueEventListener() {
        /*
        String query =  MessageFormat.format("query=\"{0}\"", juegoguid);
        Retrofit r = RetrofitClient.getClient(APIRestService.BASE_URL);
        APIRestService ars = r.create(APIRestService.class);
        Call<Results> call = ars.getJuegos(APIRestService.KEY, APIRestService.FORMAT,
                APIRestService.RESOURCES, query);
        call.enqueue(new Callback<Results>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {
                if (!response.isSuccessful()) {
                    Log.i(TAG, "error" + response.code());
                } else {
                    Result juego = (Result) response.body().getResults();
                    ArrayList<Platform> plataformas = (ArrayList<Platform>) juego.getPlatforms();
                    String plataformasCadena = "";
                    for (Platform plataforma: plataformas) {
                        if(plataformas.) {

                        } else {
                            plataformasCadena = plataformasCadena + plataforma.getName() + ", ";
                        }
                    }
                    tvNomDet.setText(juego.getName());
                    tvDescDet.setText(juego.getDescription());
                    tvGenDet.setText(String.format(tvGenDet.getText().toString(),juego.getResourceType()));
                    tvPlatDet.setText(String.format(tvPlatDet.getText().toString(),plataformasCadena));
                    Glide.with(ivImagenDet.getContext()).load(juego.getImage().getMediumUrl()).into(ivImagenDet);
                }
            }

            @Override
            public void onFailure(Call<Results> call, Throwable t) {
                Log.e(TAG,"he fallao" + t.toString());
            }
        });
        */
    }

    @Override
    public void onClick(View v) {
        if (v.equals(btnVolver)) {
            finish();
        }
    }
}