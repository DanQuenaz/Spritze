package com.unifei.spritze.spritze;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.HashMap;

import Adapters.Comunicator;
import entities.Nurse;
import entities.Pacient;
import firebase.ConfigFireBase;

public class nurseLoged extends Activity implements SearchView.OnQueryTextListener{
    private ListView listPacientes;
    private SearchView filter;
    private ArrayList<Pacient> allPacients;
    private String hospitalKey;
    private DatabaseReference rootBD;
    private DatabaseReference auxRef;
    private Nurse nurseLoged;
    private HashMap<String, Object> auxCom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_loged);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        this.auxCom = Comunicator.getInstance();
        this.listPacientes = (ListView) findViewById(R.id.Nurse_listPacients);
        this.filter = (SearchView) findViewById(R.id.Nurse_searchPacient);
        this.allPacients = new ArrayList<Pacient>();
        this.hospitalKey = (String)getIntent().getSerializableExtra("hospitalKey");
        this.nurseLoged = (Nurse)auxCom.get("nurse");
        this.rootBD = ConfigFireBase.getDataReference();

        Log.e("Nome", "Value: " + nurseLoged.getEmail());

        //capture the size of the devices screen
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);

        //define the Layout height
        RelativeLayout layout = (RelativeLayout) this.findViewById(R.id.bottonColor);
        layout.setMinimumHeight(size.y/2);

        getPacients();
        setupSearchView();

        this.listPacientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Pacient pAux = (Pacient) adapterView.getAdapter().getItem(i);

                Comunicator.getInstance();
                Comunicator.clear();
                Comunicator.addObject("pacient", pAux);
                Comunicator.addObject("nurse", nurseLoged);
                Comunicator.addObject("hospitalKey", hospitalKey);

                Intent intent = new Intent(nurseLoged.this, listRecipes.class);
                startActivity(intent);

            }
        });

    }

    private void setupSearchView(){
        filter.setIconifiedByDefault(false);
        filter.setOnQueryTextListener(this);
        filter.setSubmitButtonEnabled(true);
        filter.setQueryHint("Procure Paciente Aqui");
    }

    private void getPacients(){
        this.auxRef = this.rootBD.child("Hospital").child(this.hospitalKey).child("Pacientes");
        this.auxRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Pacient auxPacient = postSnapshot.getValue(Pacient.class);
                    allPacients.add(new Pacient(auxPacient.getName(), auxPacient.getAge(), auxPacient.getCpf(), auxPacient.getAdress(), auxPacient.getTell()));
                }
                listPacientes.setAdapter(new ArrayAdapter<Pacient>(nurseLoged.this, R.layout.pacient_item_list, allPacients));
                listPacientes.setTextFilterEnabled(true);

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
