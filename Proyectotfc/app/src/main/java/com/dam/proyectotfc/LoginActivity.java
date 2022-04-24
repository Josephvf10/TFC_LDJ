package com.dam.proyectotfc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth fAuth;
    FirebaseUser fUser;

    EditText etEmailL;
    EditText etContraL;
    Button btnAccederL;
    TextView tvLogRegisterL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fAuth = FirebaseAuth.getInstance();

        etEmailL = findViewById(R.id.etLogUser);
        etContraL = findViewById(R.id.etLogPass);
        tvLogRegisterL = findViewById(R.id.tvLogRegister);
        btnAccederL = findViewById(R.id.btnLogAcceder);

        btnAccederL.setOnClickListener(this);
        tvLogRegisterL.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.equals(tvLogRegisterL)) {
            Intent intent = new Intent(this, RegistroActivity.class);
            startActivity(intent);
        } else if (v.equals(btnAccederL)) {
            login();
        }
    }

    private void login() {
        String email = etEmailL.getText().toString().trim();
        String contrasena = etContraL.getText().toString().trim();

        if (email.isEmpty()|| contrasena.isEmpty()) {
            Toast.makeText(this, R.string.msg_Error, Toast.LENGTH_SHORT).show();
        } else {
            fAuth.signInWithEmailAndPassword(email, contrasena)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                fUser = fAuth.getCurrentUser();
                                loginCompleto();
                            } else {
                                Toast.makeText(LoginActivity.this,getString(R.string.msj_no_accede),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private void loginCompleto() {
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        i.putExtra("EMAIL", fUser.getEmail());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(fAuth.getCurrentUser()!=null){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
        }
    }
}