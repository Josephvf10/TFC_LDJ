package com.dam.proyectotfc.ui.Juegos;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dam.proyectotfc.R;
import com.dam.proyectotfc.model.JuegosBusqueda;
import com.dam.proyectotfc.model.ResultsBusqueda;
import com.dam.proyectotfc.retrofit.APIRestService;
import com.dam.proyectotfc.retrofit.RetrofitClient;
import com.dam.proyectotfc.utils.JuegosAdapter;

import java.text.MessageFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class JuegosInfoFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "FALLO GRAVE";
    public static final String CLAVE_JUEGO = "JUEGO";
    RecyclerView rvJuegos;
    LinearLayoutManager llm;
    JuegosAdapter adapter;
    Button btnC;
    EditText etNom;

    public JuegosInfoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater
                .inflate(R.layout.fragment_juegos_info,container,false);

        rvJuegos = v.findViewById(R.id.rvJuegos);
        llm = new LinearLayoutManager(getContext());
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
            Call<ResultsBusqueda> call = ars.getJuegos(APIRestService.KEY, APIRestService.FORMAT,
                    APIRestService.RESOURCES, query);
            call.enqueue(new Callback<ResultsBusqueda>() {
                @Override
                public void onResponse(Call<ResultsBusqueda> call, Response<ResultsBusqueda> response) {
                    if (!response.isSuccessful()) {
                        Log.i(TAG, "error" + response.code());
                    } else {
                        ArrayList<JuegosBusqueda> juegosRes = (ArrayList<JuegosBusqueda>) response.body().getResults();
                        cargarRV(juegosRes);
                        quitarTeclado(view);
                    }
                }

                @Override
                public void onFailure(Call<ResultsBusqueda> call, Throwable t) {
                    Log.e(TAG ,"he fallao" + t.toString());
                }
            });
        } else {
            Toast.makeText(getContext(), R.string.msg_no_name, Toast.LENGTH_LONG).show();
        }

    }

    private void cargarRV(ArrayList<JuegosBusqueda> juegosRes) {
        adapter = new JuegosAdapter(juegosRes);
        adapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JuegosBusqueda juegoD = juegosRes.get(rvJuegos.getChildAdapterPosition(v));
                Bundle bundle = new Bundle();
                FragmentTransaction ft = getParentFragment().getChildFragmentManager().beginTransaction();
                bundle.putParcelable(CLAVE_JUEGO, juegoD);
                DatosJuegoFragment datos = new DatosJuegoFragment();
                datos.setArguments(bundle);
                ft.replace(getId(),datos);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        rvJuegos.setAdapter(adapter);
    }

    public static void quitarTeclado(View v){
        InputMethodManager im = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE) ;
        im.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
}