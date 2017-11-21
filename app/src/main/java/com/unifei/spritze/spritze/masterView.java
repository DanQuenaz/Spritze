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

import Adapters.Comunicator;
import entities.Master;
import entities.Medic;
import entities.Pacient;
import entities.User;
import firebase.ConfigFireBase;

public class masterView extends Activity implements SearchView.OnQueryTextListener{

    private DatabaseReference rootBD;
    private DatabaseReference auxRef;
    private Master master;
    private ImageButton btnNwCollaborator;
    private ListView listCollaborators;
    private SearchView filter;
    private ArrayList<User> allCollaborators;
    private ArrayList<Object> auxList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_view);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        this.btnNwCollaborator = (ImageButton) findViewById(R.id.btnNewCollaborator);
        this.master = (Master) Comunicator.getItem("master");
        this.rootBD = ConfigFireBase.getDataReference();
        this.allCollaborators = new ArrayList<User>();
        this.listCollaborators = (ListView) findViewById(R.id.listCollaborators);
        this.filter = (SearchView) findViewById(R.id.searchCollaborator);

        getCollaborators();
        setupSearchView();


        btnNwCollaborator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void setupSearchView(){
        filter.setIconifiedByDefault(false);
        filter.setOnQueryTextListener(this);
        filter.setSubmitButtonEnabled(true);
        filter.setQueryHint("Procure Colaborador Aqui");
    }

    private void getCollaborators(){
        this.auxRef = this.rootBD.child("Colaborador");
        this.auxRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                allCollaborators.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    User auxUser = postSnapshot.getValue(User.class);
                    if (auxUser.getHospitals().contains(master.getHospitals())) {
                        allCollaborators.add(auxUser);

                    }
                }
                listCollaborators.setAdapter(new ArrayAdapter<User>(masterView.this, R.layout.pacient_item_list, allCollaborators));
                listCollaborators.setTextFilterEnabled(true);

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
            listCollaborators.clearTextFilter();
        } else {
            listCollaborators.setFilterText(s.toString());
        }
        return true;
    }
}
