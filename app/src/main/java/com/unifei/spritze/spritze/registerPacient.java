package com.unifei.spritze.spritze;

import android.app.Activity;
import android.graphics.Point;
import android.media.Image;
import android.media.MediaMetadata;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;

import Adapters.Comunicator;
import entities.Pacient;
import firebase.ConfigFireBase;

public class registerPacient extends Activity {

    private EditText pctName;
    private  EditText pctAge;
    private EditText pctAddress;
    private EditText pctTell;
    private EditText pctCpf;

    private String name;
    private Long cpf;
    private Long tell;
    private String address;
    private  Long age;
    private String hospitalKey;

    private ImageButton btnRegister;

    private DatabaseReference rootDB;
    private DatabaseReference auxRef;

    private Pacient auxP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_pacient);

        btnRegister = (ImageButton) findViewById(R.id.btnRegisterPct);

        rootDB = ConfigFireBase.getDataReference();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pctName = (EditText) findViewById(R.id.RGnameText);
                pctAge = (EditText) findViewById(R.id.RGageText);
                pctCpf = (EditText) findViewById(R.id.RGcpfText);
                pctAddress = (EditText) findViewById(R.id.RGaddressText);
                pctTell = (EditText) findViewById(R.id.RGtellText);

                name = pctName.getText().toString();
                address = pctAddress.getText().toString();
                cpf = Long.valueOf( pctCpf.getText().toString() );
                age = Long.valueOf( pctAge.getText().toString() );
                tell = Long.valueOf( pctTell.getText().toString() );

                Comunicator.getInstance();
                hospitalKey = (String)Comunicator.getItem("hospitalKey");

                auxP = new Pacient(name, age, cpf, address, tell);
                auxRef = rootDB.child("Hospital").child(hospitalKey).child("Pacientes");

                HashMap<String, Object> aux = new HashMap<String, Object>();
                aux.put(String.valueOf(cpf), auxP);

                auxRef.updateChildren(aux);

                Toast.makeText(registerPacient.this, "Paciente cadastrado com sucesso!", Toast.LENGTH_SHORT).show();

                finish();
            }
        });
    }


}
