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

public class medicLoged extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    private List<String> hospitals;
    private String selectedHospital;
    private DatabaseReference rootBD;
    private DatabaseReference auxRef;
    private String medicEmail;
    private ImageButton btnDosar;
    private User paciente;
    private ListView listPacientes;
    private SearchView filter;
    private static ArrayList<Pacient> allPacients;

    private String[] _namesP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medic_loged);
        this.listPacientes = (ListView) findViewById(R.id.listPacients);
        this.filter = (SearchView) findViewById(R.id.searchPacient);
        this.btnDosar = (ImageButton) findViewById(R.id.btnDosar);
        this.btnDosar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(medicLoged.this, dosageActivity.class);
                intent.putExtra("Paciente", new String());
                startActivity(intent);
            }
        });
        this.rootBD = ConfigFireBase.getDataReference();
        this.medicEmail = (String)getIntent().getSerializableExtra("medicEmail");
        this.expListView = (ExpandableListView) findViewById(R.id.listHosp);
        prepareListData();
        this.listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        this.expListView.setAdapter(this.listAdapter);

        this.expListView.setOnChildClickListener(new OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                selectedHospital = hospitals.get(childPosition);
                Toast.makeText(medicLoged.this, selectedHospital + " selecionado!", Toast.LENGTH_LONG).show();
                getPacients();

                for (Pacient _aux:medicLoged.allPacients) {
                    Log.e("Teste: ", _aux.getName());
                }
                String[] auxS = new String[] { "Google", "Apple", "Samsung", "Sony", "LG", "HTC", "Google", "Apple", "Samsung", "Sony", "LG", "HTC" };
                listPacientes.setAdapter(new ArrayAdapter<String>(medicLoged.this, R.layout.pacient_item_list, auxS));
                listPacientes.setTextFilterEnabled(true);
                setupSearchView();
                return false;
            }
        });


    }

    private void setupSearchView(){
        filter.setIconifiedByDefault(false);
        filter.setOnQueryTextListener(this);
        filter.setSubmitButtonEnabled(true);
        filter.setQueryHint("Search Here");
    }

    private void prepareListData(){
        this.listDataHeader = new ArrayList<String>();
        this.listDataChild = new HashMap<String, List<String>>();

        this.listDataHeader.add("Hospitais");
        getMedicData();
        this.listDataChild.put(this.listDataHeader.get(0), hospitals);

    }

    private void getMedicData(){
        this.hospitals = new ArrayList<String>();
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

    private void getPacients(){
        medicLoged.allPacients = new ArrayList<Pacient>();
        this.auxRef = this.rootBD.child("Hospital").child(this.selectedHospital).child("Pacientes");
        this.auxRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("Count " ,""+dataSnapshot.getChildrenCount());
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Pacient auxPacient = postSnapshot.getValue(Pacient.class);
                    medicLoged.allPacients.add(new Pacient(auxPacient.getName(), auxPacient.getAge(), auxPacient.getCpf(), auxPacient.getAdress(), auxPacient.getTell()));
                    Log.e("Get Data", auxPacient.getName());
                }
                Log.e("Count:", ""+medicLoged.allPacients.size());
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
