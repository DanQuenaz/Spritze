package com.unifei.spritze.spritze;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import Adapters.ExpandableListAdapter;
import firebase.ConfigFireBase;

public class medicLoged extends AppCompatActivity {
    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    private List<String> hospitals = new ArrayList<String>();

    private DatabaseReference rootBD;
    private DatabaseReference auxRef;
    private String medicEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medic_loged);

        this.rootBD = ConfigFireBase.getDataReference();
        this.medicEmail = (String)getIntent().getSerializableExtra("medicEmail");
        this.expListView = (ExpandableListView) findViewById(R.id.listHosp);
        prepareListData();
        this.listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        this.expListView.setAdapter(this.listAdapter);
    }

    private void prepareListData(){
        this.listDataHeader = new ArrayList<String>();
        this.listDataChild = new HashMap<String, List<String>>();

        this.listDataHeader.add("Hospitais");
        getMedicData();
        this.listDataChild.put(this.listDataHeader.get(0), hospitals);

    }

    private void getMedicData(){

        this.auxRef = this.rootBD.child("Colaborador").child(this.medicEmail).child("hospitals");
        this.auxRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String auxS = dataSnapshot.getValue(String.class);
                String auxL[] = auxS.split(" ");
                for (String index:auxL) {
                    hospitals.add(index);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
