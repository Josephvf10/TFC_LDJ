package com.dam.proyectotfc.ui.Perfil;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dam.proyectotfc.LoginActivity;
import com.dam.proyectotfc.R;
import com.dam.proyectotfc.model.JuegosEstado;
import com.dam.proyectotfc.model.ResultEstado;
import com.dam.proyectotfc.retrofit.APIRestService;
import com.dam.proyectotfc.retrofit.RetrofitClient;
import com.dam.proyectotfc.utils.EstadoJuegosAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class EstadoJuegoFragment extends Fragment{

    public static final String CLAVE_JUEGO_E = "ELIMINAR";
    public static final String CLAVE_ESTADO = "ESTADO_JUEGO";
    TextView tvNomE;
    FirebaseDatabase fdb;
    DatabaseReference rDBJuegos;
    ValueEventListener vel;
    String idUser;
    FirebaseAuth mAuth;
    ArrayList<String> juegoP;
    int contJuegoP;
    ImageView ivPortadaE;
    RecyclerView rvJuegosE;
    LinearLayoutManager llm;
    EstadoJuegosAdapter adapter;
    ArrayList<ResultEstado> juegoRes;
    String estado;

    public EstadoJuegoFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater
                .inflate(R.layout.fragment_estado_juego, container, false);

        Bundle bundle = this.getArguments();

        if (bundle.containsKey(PerfilFragment.CLAVE_USUARIO)) {
            estado = bundle.getString(PerfilFragment.CLAVE_LISTA);
            idUser = bundle.getString(PerfilFragment.CLAVE_USUARIO);
        }

        contJuegoP = 0;
        juegoRes = new ArrayList<ResultEstado>();

        fdb = FirebaseDatabase.getInstance();
        tvNomE = v.findViewById(R.id.tvNombreJuegoEstado);
        ivPortadaE = v.findViewById(R.id.ivPortadaJuegoEstado);

        rvJuegosE = v.findViewById(R.id.rvJuegosE);
        llm = new LinearLayoutManager(getContext());
        rvJuegosE.setLayoutManager(llm);

        if (estado == "j") {
            rDBJuegos = fdb.getReference("/usuarios/"+idUser+"/listaJugados");
            addValueEventListener();
        } else if (estado == "c") {
            rDBJuegos = fdb.getReference("/usuarios/"+idUser+"/listaCompletados");
            addValueEventListener();
        }else if (estado == "m") {
            rDBJuegos = fdb.getReference("/usuarios/"+idUser+"/listaMedias");
            addValueEventListener();
        }else if (estado == "o") {
            rDBJuegos = fdb.getReference("/usuarios/"+idUser+"/listaOlvidados");
            addValueEventListener();
        }

        return v;
    }

     private void  addValueEventListener() {
        if (vel == null) {
            vel = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.getValue() == null) {

                    }  else {
                        HashMap<String, String> valoresJuegos = (HashMap<String, String>) snapshot.getValue();
                        Set<String> keySet = valoresJuegos.keySet();
                        juegoP = new ArrayList<String>(keySet);

                        Retrofit r = RetrofitClient.getClient(APIRestService.BASE_URL);
                        APIRestService ars = r.create(APIRestService.class);

                        for (int i = 0; i < juegoP.size(); i++) {
                            Call<JuegosEstado> call = ars.getJuegosEstado(APIRestService.KEY, APIRestService.FORMAT,
                                    APIRestService.FILTER + juegoP.get(i));
                            call.enqueue(new Callback<JuegosEstado>() {
                                @Override
                                public void onResponse(Call<JuegosEstado> call, Response<JuegosEstado> response) {
                                    if (!response.isSuccessful()) {
                                        Log.i("fallon", "error" + response.code());
                                    } else {
                                        contJuegoP++;
                                        juegoRes.add(((ArrayList<ResultEstado>) response.body().getResults()).get(0));

                                        if (contJuegoP == juegoP.size()){
                                            cargarJuegos(juegoRes);
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<JuegosEstado> call, Throwable t) {
                                    Log.w("FALLO API", "API falla, porque"  + t.toString());
                                }
                            });
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.w("LECTURA_FIREBASE", "lectura cancelada",
                            error.toException());
                }
            };
        }
        rDBJuegos.addValueEventListener(vel);
    }

    private void cargarJuegos(ArrayList<ResultEstado> juegoRes) {
        if (juegoRes.size() > 0) {
            cargarRV(juegoRes);
        }  else {
            Toast.makeText(getContext(), getString(R.string.msj_juego_est_val), Toast.LENGTH_LONG).show();
        }
    }
    private void cargarRV(ArrayList<ResultEstado> juegoRes) {
        adapter = new EstadoJuegosAdapter(juegoRes);
        adapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultEstado juegoDE = juegoRes.get(rvJuegosE.getChildAdapterPosition(v));
                Bundle bundle = new Bundle();
                FragmentTransaction ft = getParentFragment().getChildFragmentManager().beginTransaction();
                bundle.putParcelable(CLAVE_JUEGO_E, juegoDE);
                bundle.putString(CLAVE_ESTADO, estado);
                EliminarJuegoFragment datos = new EliminarJuegoFragment();
                datos.setArguments(bundle);
                ft.replace(getId(),datos);
                ft.addToBackStack(null);
                ft.setReorderingAllowed(true);
                ft.commit();
            }
        });
        rvJuegosE.setAdapter(adapter);
    }
}