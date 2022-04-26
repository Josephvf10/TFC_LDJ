package com.dam.proyectotfc.ui.Juegos;

import android.os.Bundle;
import android.util.Log;
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
import com.dam.proyectotfc.model.Results;
import com.dam.proyectotfc.retrofit.APIRestService;
import com.dam.proyectotfc.retrofit.RetrofitClient;
import com.dam.proyectotfc.utils.JuegosAdapter;

import java.text.MessageFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class JuegosFragment extends Fragment
implements View.OnClickListener {

    private static final String TAG = "FALLO GRAVE";
    RecyclerView rvJuegos;
    LinearLayoutManager llm;
    JuegosAdapter adapter;
    Button btnC;
    EditText etNom;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater
                .inflate(R.layout.fragment_juegos,container,false);

        rvJuegos = v.findViewById(R.id.rvJuegos);
        llm = new LinearLayoutManager(v.getContext());
        rvJuegos.setLayoutManager(llm);
        btnC = v.findViewById(R.id.btnConsultarJuego);
        btnC.setOnClickListener(this);
        etNom = v.findViewById(R.id.etNombreJuego);

        return v;

    }

    @Override
    public void onClick(View view) {
        String nombre = etNom.getText().toString();
        String query =  MessageFormat.format("query=\"{0}\"", nombre);
        Retrofit r = RetrofitClient.getClient(APIRestService.BASE_URL);
        APIRestService ars = r.create(APIRestService.class);
        if (!etNom.getText().toString().isEmpty()){
            Call<ArrayList<Results>> call = ars.getJuegos(APIRestService.KEY, APIRestService.FORMAT,
                    APIRestService.RESOURCES, query);
            call.enqueue(new Callback<ArrayList<Results>>() {
                @Override
                public void onResponse(Call<ArrayList<Results>> call, Response<ArrayList<Results>> response) {
                    if (!response.isSuccessful()) {
                        Log.i(TAG, "error" + response.code());
                    } else {
                        ArrayList<Results> juegosRes = response.body();
                        cargarRV(juegosRes);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Results>> call, Throwable t) {
                    Log.e(TAG ,"he fallao" + t.toString());
                }
            });
        } else {
            Toast.makeText(getContext(), R.string.msg_no_name, Toast.LENGTH_LONG).show();
        }

    }

    private void cargarRV(ArrayList<Results> juegosRes) {
        adapter = new JuegosAdapter(juegosRes);
        /*
        adapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(JuegosFragment.this, DatosSkinActivity.class);
                Skins skinsD = skinRes.get(rvSkins.getChildAdapterPosition(v));
                i.putExtra(CLAVE_SKIN, skinsD.getIdentifier());
                startActivity(i);
            }
        });
         */
        rvJuegos.setAdapter(adapter);
    }
}