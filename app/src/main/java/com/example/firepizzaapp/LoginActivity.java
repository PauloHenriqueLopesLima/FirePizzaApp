package com.example.firepizzaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity.class";
    private FirebaseAuth firebaseAuth;
    private TextInputEditText emailUsuario;
    private TextInputEditText senhaUsuario;
    private Button loginButton;
    private Button registro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();




        // Initialize Firebase Auth

        emailUsuario = findViewById(R.id.email);
        senhaUsuario = findViewById(R.id.senha);

        loginButton = findViewById(R.id.ok);
        registro = findViewById(R.id.registro);
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),CadastroActivity.class);
                startActivity(intent);
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailPassado = emailUsuario.getEditableText().toString();
                String passwordPassado = senhaUsuario.getEditableText().toString();

                logar(emailPassado,passwordPassado);
            }
        });





    }

    private void logar( String email,String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            irParaMain();
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            // If sign in fails, display a message to the user.


                        }

                        // ...
                    }
                });
    }

    private void irParaMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }






}
