package com.unifei.spritze.spritze;

import android.app.Activity;
import android.graphics.Point;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;

import Adapters.Comunicator;
import entities.Pacient;
import entities.Recipe;

public class nurseRecipeView extends Activity {
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
    private String hospitalKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_recipe_view);

        this.pctName = (TextView) findViewById(R.id.Nurse_pctName);
        this.pctAge = (TextView) findViewById(R.id.Nurse_pctAge);
        this.medicName = (TextView) findViewById(R.id.Nurse_medic);
        this.medicCrm = (TextView) findViewById(R.id.Nurse_mediccrm);
        this.status = (TextView) findViewById(R.id.Nurse_status);

        this.textRecipe = (EditText)findViewById(R.id.Nurse_textRecipe);
        this.textObs = (EditText)findViewById(R.id.Nurse_textObs);

        this.btnConfirm = (ImageButton)findViewById(R.id.Nurse_btnConfirmRecipe);

        this.auxCom = Comunicator.getInstance();
        this.pacient = (Pacient) this.auxCom.get("pacient");
        this.recipe = (Recipe) this.auxCom.get("recipe");
        this.hospitalKey = (String) this.auxCom.get("hospitalKey");

        this.pctName.setText(this.pacient.getName());
        this.pctAge.setText(Long.toString(this.pacient.getAge()) + " anos");

        this.medicName.setText(this.recipe.getMedic());
        this.medicCrm.setText(Long.toString(this.recipe.getCrm()));

        this.status.setText(this.recipe.getStatus());

        this.textRecipe.setText(this.recipe.getText());
        this.textRecipe.setActivated(false);

        this.textObs.setText(this.recipe.getObservations());
        this.textObs.setActivated(false);



    }
}
