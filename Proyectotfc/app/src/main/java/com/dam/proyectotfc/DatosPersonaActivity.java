package com.dam.proyectotfc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dam.proyectotfc.R;
import com.dam.proyectotfc.model.JuegosBusqueda;
import com.dam.proyectotfc.model.Usuario;
import com.dam.proyectotfc.ui.BuscarPersonas.BuscarPersonasFragment;
import com.dam.proyectotfc.ui.Juegos.DatosJuegoFragment;
import com.dam.proyectotfc.utils.JuegosAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class DatosPersonaActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "FALLO GRAVE";
    public static final String CLAVE_JUEGO = "JUEGO";

    TextView tvNombreDU, tvEmailDU, tvTelfDU;
    String emailUsuario;
    LinearLayoutManager llm;
    JuegosAdapter adapterCom, adapterJug, adapterMed, adapterOlv;
    ArrayList<String> juegosCom;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    Button btnJuegosJugados, btnJuegosCompletos, btnJuegosMedias, btnJuegosOlvidados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_persona_fragment);

        tvNombreDU = findViewById(R.id.tvNombreDU);
        tvEmailDU = findViewById(R.id.tvEmailDU);
        tvTelfDU = findViewById(R.id.tvTelefDU);
        btnJuegosJugados  = findViewById(R.id.btnJuegosJugados2);
        btnJuegosCompletos  = findViewById(R.id.btnJuegosCompletados2);
        btnJuegosMedias  = findViewById(R.id.btnJuegosMedias2);
        btnJuegosOlvidados  = findViewById(R.id.btnJuegosOlvidados2);

        Intent i = getIntent();
        Bundle bundle = i.getExtras();

        emailUsuario = bundle.getString(BuscarPersonasFragment.CLAVE_USUARIO);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("usuarios");
        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("StringFormatMatches")
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    Usuario usuario = snapshot.getValue(Usuario.class);
                    if(usuario.getEmail().equals(emailUsuario)) {
                        tvNombreDU.setText(usuario.getNombreCompleto());
                        tvEmailDU.setText(String.format(getString(R.string.email_du), usuario.getEmail()));
                        tvTelfDU.setText(String.format(getString(R.string.telef_du), usuario.getTelefono()));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DatosPersonaActivity.this, R.string.msg_Error_db, Toast.LENGTH_LONG).show();
            }
        });

        llm = new LinearLayoutManager(this);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        //DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Seguir").child(firebaseUser.getUid()).child("siguiendo");


    }



    @Override
    public void onClick(View view) {
        if (view.equals("")) {

        } else {

        }
    }
}