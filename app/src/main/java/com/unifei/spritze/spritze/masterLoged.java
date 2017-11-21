package com.unifei.spritze.spritze;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.google.firebase.database.DatabaseReference;

import Adapters.Comunicator;
import entities.Master;

public class masterLoged extends Activity {

    Master masterAux;
    DatabaseReference rootDB;
    DatabaseReference auxRef;
    ImageButton viewAux;
    ImageButton allDosages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_loged);

        this.masterAux = (Master)Comunicator.getItem("master");
        this.viewAux = (ImageButton) findViewById(R.id.registerFunc);
        this.allDosages = (ImageButton) findViewById(R.id.medicalHistoric);
        this.viewAux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comunicator.getInstance();
                Comunicator.clear();
                Comunicator.addObject("master", masterAux);

                Intent intent = new Intent(masterLoged.this, masterView.class);
                startActivity(intent);
            }
        });

        this.allDosages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comunicator.getInstance();
                Comunicator.clear();
                Comunicator.addObject("master", masterAux);

                Intent intent = new Intent(masterLoged.this, masterViewDosages.class);
                startActivity(intent);
            }
        });

    }
}
