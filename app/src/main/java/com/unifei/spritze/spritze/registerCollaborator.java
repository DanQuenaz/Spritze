package com.unifei.spritze.spritze;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;

import Adapters.Comunicator;
import Utils.StringFormat;
import entities.Master;
import entities.Medic;
import entities.Nurse;
import entities.User;
import firebase.ConfigFireBase;

public class registerCollaborator extends Activity {
    private EditText name;
    private EditText email;
    private EditText password;
    private EditText address;
    private EditText tel;
    private EditText cpf;
    private ImageButton btnConfirm;
    private RadioButton radioMedic;
    private RadioButton radioMaster;
    private RadioButton radioNurse;
    private FirebaseAuth mAuth;
    private DatabaseReference rootDB;
    private DatabaseReference auxDB;
    private Master master;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_collaborator);

        master = (Master)Comunicator.getItem("master");

        name = (EditText) findViewById(R.id.RGnameText);
        email = (EditText) findViewById(R.id.RGemailText);
        password = (EditText) findViewById(R.id.RGpasswordText);
        address = (EditText) findViewById(R.id.RGaddressText);
        tel = (EditText) findViewById(R.id.RGtellText);
        cpf = (EditText) findViewById(R.id.RGcpfText);

        radioMaster = (RadioButton) findViewById(R.id.radioMaster);
        radioMedic = (RadioButton) findViewById(R.id.radioMedic);
        radioNurse = (RadioButton) findViewById(R.id.radioNurse);

        mAuth = ConfigFireBase.getAuth();
        rootDB = ConfigFireBase.getDataReference();
        auxDB = rootDB.child("Colaborador");

        btnConfirm = (ImageButton) findViewById(R.id.btnRegisterClbr);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String email, String name, Long cpf, String address, Long tel, String type, String hospitals
                HashMap<String, Object> data = new HashMap<String, Object>();
                if(radioMaster.isChecked()){
                    Master auxM = new Master( email.getText().toString(), name.getText().toString(), Long.valueOf(cpf.getText().toString()),
                            address.getText().toString(), Long.valueOf(tel.getText().toString()), "master", master.getHospitals(), "Mula" );

                    data.put(StringFormat.formatEmail(email.getText().toString()), auxM);
                    auxDB.updateChildren(data);

                    mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(registerCollaborator.this, "Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    });
                }
                else if(radioMedic.isChecked()){
                    Medic auxM = new Medic( email.getText().toString(), name.getText().toString(), Long.valueOf(cpf.getText().toString()),
                            address.getText().toString(), Long.valueOf(tel.getText().toString()), "medic", master.getHospitals(), Long.valueOf(345345363), "Cardiologista" );

                    data.put(StringFormat.formatEmail(email.getText().toString()), auxM);
                    auxDB.updateChildren(data);

                    mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(registerCollaborator.this, "Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    });
                }
                else if (radioNurse.isChecked()){
                    Nurse auxM = new Nurse( email.getText().toString(), name.getText().toString(), Long.valueOf(cpf.getText().toString()),
                            address.getText().toString(), Long.valueOf(tel.getText().toString()), "nurse", master.getHospitals(), Long.valueOf(345345363));

                    data.put(StringFormat.formatEmail(email.getText().toString()), auxM);
                    auxDB.updateChildren(data);

                    mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(registerCollaborator.this, "Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    });
                }
            }
        });

    }
}
