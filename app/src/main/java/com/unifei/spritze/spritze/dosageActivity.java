package com.unifei.spritze.spritze;


import android.app.Activity;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class dosageActivity extends AppCompatActivity {
    private String pacientName;
    private Long pacientAge;
    private double pacientWeight;
    private TextView pctName;
    private TextView pctAge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosage);

        this.pctName = (TextView) findViewById(R.id.pacientName);
        this.pctAge = (TextView) findViewById(R.id.pacientAge);

        this.pacientName = (String)getIntent().getSerializableExtra("Nome");
        this.pacientAge = (Long)getIntent().getSerializableExtra("Idade");

        this.pctName.setText("Nome: " + this.pacientName);
        this.pctAge.setText("Idade: " + Long.toString( this.pacientAge));
    }
}
