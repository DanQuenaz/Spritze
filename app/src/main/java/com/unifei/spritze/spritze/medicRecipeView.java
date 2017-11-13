package com.unifei.spritze.spritze;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.HashMap;

import Adapters.Comunicator;
import entities.Recipe;

import static Adapters.Comunicator.*;

public class medicRecipeView extends Activity {
    private TextView pctName;
    private TextView pctAge;
    private EditText textRecipe;
    private EditText textObs;
    private ImageButton btnConfirm;
    private HashMap<String, Object> info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medic_recipe_view);

        this.pctName = (TextView) findViewById(R.id.pctName);
        this.pctAge = (TextView) findViewById(R.id.pctAge);
        this.textRecipe = (EditText) findViewById(R.id.textRecipe);
        this.textObs = (EditText) findViewById(R.id.textObs);
        this.btnConfirm = (ImageButton) findViewById(R.id.btnConfirmRecipe);

        this.info = Comunicator.getInstance();

        this.pctName.setText("Nome: " + info.get("pacientName"));
        this.pctAge.setText("Idade: " + info.get("pacientAge"));
        Recipe auxRec = (Recipe)info.get("recipe");
        this.textRecipe.setText(auxRec.getText());
        this.textObs.setText(auxRec.getObservations());




    }
}
