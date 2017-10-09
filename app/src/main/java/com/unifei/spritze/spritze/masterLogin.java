package com.unifei.spritze.spritze;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import entities.User;
import firebase.ConfigFireBase;

public class masterLogin extends AppCompatActivity {

    private EditText edtPassword;
    private EditText edtEmail;
    private ImageButton btnLogin;
    private FirebaseAuth auth;
    private User userAux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_login);

        edtPassword = (EditText) findViewById(R.id.edtMasterPassword);
        edtEmail = (EditText) findViewById(R.id.edtMasterEmail);
        btnLogin = (ImageButton) findViewById(R.id.btnMasterLogin2);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edtEmail.getText().toString().equals("") && !edtPassword.getText().toString().equals("")){
                    validLogin(edtEmail.getText().toString(), edtPassword.getText().toString());
                }else{
                    Toast.makeText(masterLogin.this, "Insira Email e Senha", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(auth != null){
            auth.signOut();
        }
    }

    private void validLogin(String email, String password){
        auth = ConfigFireBase.getAuth();
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(masterLogin.this, "Logado com sucesso!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(masterLogin.this, masterLoged.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(masterLogin.this, "Erro de autenticação", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
