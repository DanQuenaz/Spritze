package com.unifei.spritze.spritze;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import android.text.TextUtils;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import Adapters.ExpandableListAdapter;
import entities.Pacient;
import entities.User;
import firebase.ConfigFireBase;

public class medicLoged extends Activity implements SearchView.OnQueryTextListener {

    private DatabaseReference rootBD;
    private DatabaseReference auxRef;
    private String medicEmail;
    private String selectedHospital;
    private ImageButton btnDosar;
    private ListView listPacientes;
    private SearchView filter;
    private ArrayList<Pacient> allPacients;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medic_loged);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        this.rootBD = ConfigFireBase.getDataReference();
        this.allPacients = new ArrayList<Pacient>();
        this.selectedHospital = (String)getIntent().getSerializableExtra("Hospital");
        this.listPacientes = (ListView) findViewById(R.id.listPacients);
        this.filter = (SearchView) findViewById(R.id.searchPacient);

        getPacients();
        listPacientes.setTextFilterEnabled(true);
        setupSearchView();


        this.listPacientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Pacient auxP = (Pacient) adapterView.getAdapter().getItem(i);
                Intent intent = new Intent(medicLoged.this, dosageActivity.class);
                intent.putExtra("Nome", auxP.getName());
                intent.putExtra("Idade", auxP.getAge());
                startActivity(intent);
            }
        });


    }

    private void setupSearchView(){
        filter.setIconifiedByDefault(false);
        filter.setOnQueryTextListener(this);
        filter.setSubmitButtonEnabled(true);
        filter.setQueryHint("Search Pacient Here");
    }

    private void getPacients(){

        this.auxRef = this.rootBD.child("Hospital").child(this.selectedHospital).child("Pacientes");
        this.auxRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Pacient auxPacient = postSnapshot.getValue(Pacient.class);
                    allPacients.add(new Pacient(auxPacient.getName(), auxPacient.getAge(), auxPacient.getCpf(), auxPacient.getAdress(), auxPacient.getTell()));
                }
                listPacientes.setAdapter(new ArrayAdapter<Pacient>(medicLoged.this, R.layout.pacient_item_list, allPacients));
                listPacientes.setTextFilterEnabled(true);
                Log.e("Count1", ""+allPacients.size());

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
            listPacientes.clearTextFilter();
        } else {
            listPacientes.setFilterText(s.toString());
        }
        return true;
    }
}