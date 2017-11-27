package com.unifei.spritze.spritze;

import android.app.Activity;
import android.graphics.Point;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.HashMap;

import Adapters.Comunicator;
import Utils.StringFormat;
import entities.Master;
import entities.Master;
import entities.Pacient;
import entities.Recipe;
import firebase.ConfigFireBase;

public class masterRecipeView extends Activity {
    private TextView pctName;
    private TextView pctAge;
    private EditText textRecipe;
    private EditText textObs;
    private TextView medicName;
    private TextView medicCrm;
    private TextView status;
    private ImageButton btnConfirm;
    private HashMap<String, Object> auxCom;
    private Recipe recipe;
    private Pacient pacient;
    private Master masterLoged;
    private String hospitalKey;
    private DatabaseReference rootDB;
    private DatabaseReference auxRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_recipe_view);

        this.pctName = (TextView) findViewById(R.id.master_pctName);
        this.pctAge = (TextView) findViewById(R.id.master_pctAge);
        this.medicName = (TextView) findViewById(R.id.master_medic);
        this.medicCrm = (TextView) findViewById(R.id.master_mediccrm);
        this.status = (TextView) findViewById(R.id.master_status);

        this.textRecipe = (EditText)findViewById(R.id.master_textRecipe);
        this.textObs = (EditText)findViewById(R.id.master_textObs);

        this.btnConfirm = (ImageButton)findViewById(R.id.master_btnConfirmRecipe);

        this.auxCom = Comunicator.getInstance();
        this.pacient = (Pacient) this.auxCom.get("pacient");
        this.recipe = (Recipe) this.auxCom.get("recipe");
        this.hospitalKey = (String) this.auxCom.get("hospitalKey");
        this.masterLoged = (Master)Comunicator.getItem("master");

        this.pctName.setText(this.recipe.getPctName());
        this.pctAge.setText(Long.toString(this.recipe.getPctAge()) + " anos");

        this.medicName.setText(this.recipe.getMedic());
        this.medicCrm.setText(Long.toString(this.recipe.getCrm()));

        this.status.setText(this.recipe.getStatus());

        this.textRecipe.setText(this.recipe.getText());
        this.textRecipe.setActivated(false);

        this.textObs.setText(this.recipe.getObservations());
        this.textObs.setActivated(false);

        this.rootDB = ConfigFireBase.getDataReference();
        this.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

    }
}
