package com.dam.proyectotfc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvNombre, tvApellido, tvCorreo, tvTelefono,
            tvContrasenaR, tvCuentaR;
    Button btnGuardarR;
    Button btnCancelR;
    EditText etNombre, etApellido, etCorreo, etTelefono, etContasena;
    //DatabaseReference mReference;

    //FirebaseAuth mAuth;
    //FirebaseUser fUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        tvNombre = findViewById(R.id.tvNombre);
        tvApellido = findViewById(R.id.tvApellido);
        tvCuentaR = findViewById(R.id.tvCuentaR);
        tvCorreo = findViewById(R.id.tvCorreo);
        tvTelefono = findViewById(R.id.tvTelefono);
        tvContrasenaR = findViewById(R.id.tvContraseñaR);

        //mReference = FirebaseDatabase.getInstance().getReference();
        //mAuth = FirebaseAuth.getInstance();

        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etCorreo = findViewById(R.id.etCorreo);
        etTelefono = findViewById(R.id.etTelefono);
        etContasena = findViewById(R.id.etContrasenaR);

        btnCancelR = findViewById(R.id.btnCancelarR);
        btnGuardarR = findViewById(R.id.btnGuardarR);

        btnCancelR.setOnClickListener(this);
        btnGuardarR.setOnClickListener(this);
        tvCuentaR.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        String nombreU = etNombre.getText().toString();
        String apellidoU = etApellido.getText().toString();
        String correoU = etCorreo.getText().toString();
        String telefonoU = etTelefono.getText().toString();
        String contrasenaU = etContasena.getText().toString();

        if (v.equals(tvCuentaR)) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        } else if (nombreU.isEmpty() || apellidoU.isEmpty() || contrasenaU.isEmpty() || correoU.isEmpty()) {
            Toast.makeText(this, R.string.msg_Error, Toast.LENGTH_SHORT).show();
        } else if (v.equals(btnGuardarR)) {
            /*mAuth.createUserWithEmailAndPassword(correoU, contrasenaU)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful() && !(contrasenaU.length() < 6)) {
                                Map<String, Object> subirDatos = new HashMap<>();

                                subirDatos.put("nombre", nombreU);
                                subirDatos.put("apellido", apellidoU);
                                subirDatos.put("correo", correoU);
                                subirDatos.put("telefono", telefonoU);
                                subirDatos.put("contraseña", contrasenaU);

                                mReference.child("usuarios").push().setValue(subirDatos);
                                fUser = mAuth.getCurrentUser();

                                Toast.makeText(PaginaRegistro.this, "REgistrado con exito", Toast.LENGTH_SHORT).show();
                                finish();

                            } else {
                                Toast.makeText(PaginaRegistro.this, "Fracaso absoluto", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });*/
        }
    }
}