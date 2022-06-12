package com.dam.proyectotfc.ui.BuscarPersonas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.dam.proyectotfc.R;
import com.dam.proyectotfc.model.Genre;
import com.dam.proyectotfc.model.JuegoDetalles;
import com.dam.proyectotfc.model.Platform;
import com.dam.proyectotfc.model.ResultEstado;
import com.dam.proyectotfc.model.ResultsDetalles;
import com.dam.proyectotfc.retrofit.APIRestService;
import com.dam.proyectotfc.retrofit.RetrofitClient;
import com.dam.proyectotfc.ui.Perfil.EstadoJuegoFragment;
import com.dam.proyectotfc.ui.Perfil.PerfilFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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


public class InfoJuegoPersonasFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "FALLO";
    TextView tvNombre;
    ImageView ivPortada;
    TextView tvDes;
    TextView tvGen;
    TextView tvPlat;
    Integer juegoId;
    FloatingActionButton btnEliminarJuego;
    String genero = "";
    String plataforma = "";

    FirebaseDatabase fdb;
    DatabaseReference dbRef;
    FirebaseAuth fAuth;
    ResultsDetalles juegoE;
    String juegoGuid;
    String estado;
    String idUser;
    ValueEventListener vel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_eliminar_juego, container, false);

        Bundle bundle = this.getArguments();

        ResultEstado info = bundle.getParcelable(EstadoJuegoFragment.CLAVE_JUEGO_E);
        estado = bundle.getString(EstadoJuegoFragment.CLAVE_ESTADO);

        juegoGuid = info.getGuid();
        juegoId = info.getId() ;

        tvNombre = v.findViewById(R.id.tvNombreJuegoEliminar);
        tvDes = v.findViewById(R.id.tvDescripcionJuegoEliminar);
        tvGen = v.findViewById(R.id.tvGeneroJuegoEliminar);
        tvPlat = v.findViewById(R.id.tvPlataformasJuegoEliminar);
        ivPortada = v.findViewById(R.id.ivPortadaJuegoEliminar);
        btnEliminarJuego = v.findViewById(R.id.fabEliminarJuego);
        btnEliminarJuego.setOnClickListener(this);

        fdb = FirebaseDatabase.getInstance();
        dbRef = fdb.getReference("usuarios");
        fAuth = FirebaseAuth.getInstance();
        idUser = fAuth.getCurrentUser().getUid();

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
                juegoE = response.body().getResults();
                tvNombre.setText(juegoE.getName());
                Glide.with(ivPortada.getContext()).load(juegoE.getImage().getMediumUrl()).into(ivPortada);
                if (juegoE.getDescription() == null) {
                    tvDes.setText(R.string.no_descripcion);
                } else {
                    tvDes.setText(Html.fromHtml(juegoE.getDescription()));
                }
                if (juegoE.getGenres() == null){
                    tvGen.setText(R.string.no_genero);
                } else {
                    for (Genre gen: juegoE.getGenres()) {
                        genero += gen.getName() + "\t";
                    }
                    tvGen.setText(String.format(getResources().getString(R.string.tv_genero_juego_detalle),genero));
                }
                for (Platform plat: juegoE.getPlatforms()) {
                    plataforma += plat.getName() + ",\t";
                }
                tvPlat.setText(String.format(getResources().getString(R.string.tv_plataformas_juego_detalle),plataforma));
            }
        }
            @Override
            public void onFailure(Call<JuegoDetalles> call, Throwable t) {
                Log.i(TAG, "error" + t.toString());
            }
        });
        return v;
    }

    @Override
    public void onClick(View view) {
        FragmentTransaction ft = getParentFragment().getChildFragmentManager().beginTransaction();
        BuscarPersonasFragment datos = new BuscarPersonasFragment();
        ft.replace(getId(),datos);
        ft.addToBackStack(null);
        ft.setReorderingAllowed(true);
        ft.commit();
    }
}