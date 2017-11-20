package com.unifei.spritze.spritze;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.HashMap;

import Adapters.Comunicator;
import entities.Nurse;
import entities.Pacient;
import entities.Recipe;
import firebase.ConfigFireBase;

public class listRecipes extends Activity implements SearchView.OnQueryTextListener{

    private SearchView filter;
    private ListView listRecipes;
    private DatabaseReference rootBD;
    private DatabaseReference auxRef;
    private ArrayList<Recipe> allRecipes;
    private Pacient pacient;
    private Nurse nurse;
    private String hospitalKey;
    private HashMap<String, Object> auxCom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_recipes);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        this.auxCom = Comunicator.getInstance();
        this.listRecipes = (ListView) findViewById(R.id.Nurse_listRecipes);
        this.filter = (SearchView) findViewById(R.id.Nurse_searchRecipe);
        this.allRecipes = new ArrayList<Recipe>();
        this.hospitalKey = (String)auxCom.get("hospitalKey");
        this.nurse = (Nurse)auxCom.get("nurse");
        this.pacient = (Pacient)auxCom.get("pacient");
        this.rootBD = ConfigFireBase.getDataReference();

        getRecipes();
        setupSearchView();

        this.listRecipes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Recipe auxReci = (Recipe)adapterView.getAdapter().getItem(i);
                Comunicator.getInstance();
                Comunicator.clear();
                Comunicator.addObject("recipe", auxReci);
                Comunicator.addObject("pacient", pacient);
                Comunicator.addObject("hospitalKey", hospitalKey);

                Intent intent = new Intent(listRecipes.this, nurseRecipeView.class);
                startActivity(intent);
            }
        });
    }


    private void setupSearchView(){
        filter.setIconifiedByDefault(false);
        filter.setOnQueryTextListener(this);
        filter.setSubmitButtonEnabled(true);
        filter.setQueryHint("Procure Receitas Aqui");
    }

    private void getRecipes(){
        this.auxRef = this.rootBD.child("Hospital").child(this.hospitalKey).child("Pacientes").child(Long.toString(pacient.getCpf())).child("Recipes");
        this.auxRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Recipe auxRecipe = postSnapshot.getValue(Recipe.class);
                    allRecipes.add(auxRecipe);
                }
                listRecipes.setAdapter(new ArrayAdapter<Recipe>(listRecipes.this, R.layout.pacient_item_list, allRecipes));
                listRecipes.setTextFilterEnabled(true);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        if (TextUtils.isEmpty(s)) {
            listRecipes.clearTextFilter();
        } else {
            listRecipes.setFilterText(s.toString());
        }
        return true;
    }

}
