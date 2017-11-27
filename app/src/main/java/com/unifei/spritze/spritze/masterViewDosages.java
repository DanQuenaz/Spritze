package com.unifei.spritze.spritze;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentMap;

import Adapters.Comunicator;
import entities.Master;
import entities.Medic;
import entities.Pacient;
import entities.Recipe;
import entities.User;
import firebase.ConfigFireBase;

public class masterViewDosages extends Activity implements SearchView.OnQueryTextListener{

    private DatabaseReference rootBD;
    private DatabaseReference auxRef;
    private Master master;
    private ListView listDosages;
    private SearchView filter;
    private ArrayList<Recipe> allDosages;
    private ArrayList<Object> auxList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_view_dosages);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        this.master = (Master) Comunicator.getItem("master");
        this.rootBD = ConfigFireBase.getDataReference();
        this.allDosages = new ArrayList<Recipe>();
        this.listDosages = (ListView) findViewById(R.id.listDosages);
        this.filter = (SearchView) findViewById(R.id.searchDosage);

        getDosages();
        setupSearchView();

        listDosages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Recipe recipe = (Recipe)parent.getAdapter().getItem(position);

                Comunicator.getInstance();
                Comunicator.clear();
                Comunicator.addObject("recipe", recipe);
                Comunicator.addObject("master", master);

                Intent intent = new Intent(masterViewDosages.this, masterRecipeView.class);
                startActivity(intent);
            }
        });
    }

    private void setupSearchView(){
        filter.setIconifiedByDefault(false);
        filter.setOnQueryTextListener(this);
        filter.setSubmitButtonEnabled(true);
        filter.setQueryHint("Procure Receita Aqui");
    }

    private void getDosages(){
        this.auxRef = this.rootBD.child("Hospital").child(master.getHospitals()).child("Pacientes");
        this.auxRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                allDosages.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    for (DataSnapshot index : postSnapshot.child("Recipes").getChildren()) {
                        Recipe auxRec = index.getValue(Recipe.class);
                        allDosages.add(auxRec);
                    }
                }
                listDosages.setAdapter(new ArrayAdapter<Recipe>(masterViewDosages.this, R.layout.pacient_item_list, allDosages));
                listDosages.setTextFilterEnabled(true);
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
            listDosages.clearTextFilter();
        } else {
            listDosages.setFilterText(s.toString());
        }
        return true;
    }
}
