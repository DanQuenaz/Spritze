package com.unifei.spritze.spritze;

import android.app.Activity;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;

import Adapters.Comunicator;
import entities.Recipe;
import firebase.ConfigFireBase;

import static Adapters.Comunicator.*;

public class medicRecipeView extends Activity {
    private TextView pctName;
    private TextView pctAge;
    private String hospitalKey;
    private String pacientKey;
    private EditText textRecipe;
    private EditText textObs;
    private ImageButton btnConfirm;
    private HashMap<String, Object> info;
    private Recipe auxRec;
    private DatabaseReference rootDB;
    private DatabaseReference auxRef;

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

        this.pctName.setText("" + info.get("pacientName"));
        this.pctAge.setText("" + info.get("pacientAge") + " anos");
        this.auxRec = (Recipe)info.get("recipe");
        this.textRecipe.setText(auxRec.getText());
        this.textObs.setText(auxRec.getObservations());
        this.hospitalKey = (String)info.get("hospitalKey");
        this.pacientKey = (String)info.get("pacientKey");

        this.rootDB = ConfigFireBase.getDataReference();

        this.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auxRec.setObservations(textObs.getText().toString());
                auxRec.setText(textRecipe.getText().toString());
                String auxKey = auxRec.getDate().replace(".", "");
                auxKey = auxRec.getRemedy() + auxKey;
                auxKey = auxKey.replace(" ", "");
                auxKey = auxKey.replace("-", "");
                auxKey = auxKey.replace("/", "");
                auxKey = auxKey.replace(":", "");
                auxKey = auxKey.replace("+", "");

                auxKey = Long.toString(auxRec.getCrm()) + auxKey;

                auxRef = rootDB.child("Hospital").child(hospitalKey).child("Pacientes").child(pacientKey).child("Recipes");
                HashMap<String, Object> auxHash = new HashMap<String, Object>();
                auxHash.put(auxKey, auxRec);
                auxRef.updateChildren(auxHash);



                Toast.makeText(medicRecipeView.this, "Receita Confirmada!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });



    }
}
