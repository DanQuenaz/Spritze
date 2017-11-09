package com.unifei.spritze.spritze;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import firebase.ConfigFireBase;

public class MainActivity extends Activity {
    private ImageButton loginBtn;
    private EditText loginEmail;
    private EditText loginPass;
    private FirebaseAuth auth;
    private DatabaseReference rootBD;
    private DatabaseReference auxRef;
    private String auxEmail;
    private TextInputLayout usernameWrapper;
    private TextInputLayout passwordWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //capture the size of the devices screen
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);

        //define the Layout height
        RelativeLayout layout = (RelativeLayout) this.findViewById(R.id.bottonColor);
        layout.setMinimumHeight(size.y/2);

        loginBtn = (ImageButton) findViewById(R.id.loginButton);
        loginEmail = (EditText) findViewById(R.id.loginEmail);
        loginPass = (EditText) findViewById(R.id.loginPass);
        usernameWrapper = (TextInputLayout) findViewById(R.id.usernameWrapper);
        passwordWrapper = (TextInputLayout) findViewById(R.id.passwordWrapper);

        rootBD = ConfigFireBase.getDataReference();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean login = loginEmail.getText().toString().isEmpty();
                boolean password = loginPass.getText().toString().isEmpty();

                if(login) usernameWrapper.setError("Campo não pode ser vazio");
                else usernameWrapper.setErrorEnabled(false);

                if(password) passwordWrapper.setError("Campo não pode ser vazio");
                else passwordWrapper.setErrorEnabled(false);

                if(!login && !password)
                    validLogin(loginEmail.getText().toString(), loginPass.getText().toString());
            }
        });

    }

    @Override
    protected  void onStart(){
        super.onStart();

    }

    private void validLogin(final String email, String password){

        auth = ConfigFireBase.getAuth();
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    auxEmail = email.replace(".com", "");
                    auxEmail = auxEmail.replace(".br", "");
                    auxEmail = auxEmail.replace("@", "_");
                    auxRef = rootBD.child("Colaborador").child(auxEmail).child("type");
                    auxRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String text = dataSnapshot.getValue(String.class);
                            if(text.equals("master")){
                                Intent intent = new Intent(MainActivity.this, masterLoged.class);
                                startActivity(intent);
                                Toast.makeText(MainActivity.this, "Logado com sucesso!", Toast.LENGTH_SHORT).show();
                            }
                            else if(text.equals("medic")){
                                Intent intent = new Intent(MainActivity.this, hospital_select.class);
                                intent.putExtra("medicEmail", auxEmail);
                                startActivity(intent);
                                Toast.makeText(MainActivity.this, "Logado com sucesso!", Toast.LENGTH_SHORT).show();
                            }
                            else if(text.equals("nurse")){
                                Intent intent = new Intent(MainActivity.this, nurseLoged.class);
                                startActivity(intent);
                                Toast.makeText(MainActivity.this, "Logado com sucesso!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    //Intent intent = new Intent(masterLogin.this, masterLoged.class);
                    //startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "Erro de autenticação", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
