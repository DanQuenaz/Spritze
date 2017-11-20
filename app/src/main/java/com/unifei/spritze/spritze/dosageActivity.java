package com.unifei.spritze.spritze;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.os.Bundle;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Calendar;
import Adapters.Comunicator;
import Calc.DosageCalculation;
import entities.Recipe;
import entities.Remedy;
import firebase.ConfigFireBase;


public class dosageActivity extends Activity implements SearchView.OnQueryTextListener {

    private String medic;
    private Long crm;
    private String pacientName;
    private Long pacientAge;
    private String pacientKey;
    private String hospitalKey;
    private double pacientWeight;
    private TextView pctName;
    private TextView pctAge;
    private ListView listDrugs;
    private SearchView filter;
    private ArrayList<Remedy> allDrugs;
    private DatabaseReference rootDB;
    private DatabaseReference auxRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosage);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        this.pctName = (TextView) findViewById(R.id.pacientName);
        this.pctAge = (TextView) findViewById(R.id.pacientAge);

        this.pacientName = (String)getIntent().getSerializableExtra("pacientName");
        this.pacientAge = (Long)getIntent().getSerializableExtra("pacientAge");
        this.medic = (String)getIntent().getSerializableExtra("medicName");
        this.crm = (Long)getIntent().getSerializableExtra("medicCrm");
        this.pacientKey = (String)getIntent().getSerializableExtra("pacientKey");
        this.hospitalKey = (String)getIntent().getSerializableExtra("hospitalKey");
        Log.e("View", "Value: " + this.pacientKey);

        this.pctName.setText(this.pacientName);
        this.pctAge.setText(Long.toString( this.pacientAge) + " anos");
        this.allDrugs = new ArrayList<Remedy>();
        this.rootDB = ConfigFireBase.getDataReference();
        this.listDrugs = (ListView) findViewById(R.id.remediosList);
        this.filter = (SearchView) findViewById(R.id.searchRemedio);

        this.listDrugs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Remedy auxRem = (Remedy)adapterView.getAdapter().getItem(i);
                String text = DosageCalculation.Calc(auxRem.getName(), pacientWeight, pacientAge);
                String date = Calendar.getInstance().getTime().toString();
                Recipe auxRec = new Recipe(auxRem.getName(), text, date, "", medic, crm);
                Intent intent = new Intent(dosageActivity.this, medicRecipeView.class);
                Comunicator.getInstance();
                Comunicator.clear();
                Comunicator.addObject("pacientKey", pacientKey);
                Comunicator.addObject("pacientAge", pacientAge);
                Comunicator.addObject("pacientName", pacientName);
                Comunicator.addObject("medicName", medic);
                Comunicator.addObject("medicCrm", crm);
                Comunicator.addObject("hospitalKey", hospitalKey);
                Comunicator.addObject("recipe", auxRec);
                startActivity(intent);
            }
        });

        getDrugs();
        setupSearchView();
    }

    private void setupSearchView(){
        filter.setIconifiedByDefault(false);
        filter.setOnQueryTextListener(this);
        filter.setSubmitButtonEnabled(true);
        filter.setQueryHint("Procure Remedio Aqui");
    }

    private void getDrugs(){
        this.auxRef = this.rootDB.child("Remedios");
        this.auxRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Remedy auxPacient = postSnapshot.getValue(Remedy.class);
                    Log.e("Count", auxPacient.toString());

                    allDrugs.add(new Remedy(auxPacient.getName(), auxPacient.getDescription(), auxPacient.getManufacturer()));
                }
                listDrugs.setAdapter(new ArrayAdapter<Remedy>(dosageActivity.this, R.layout.pacient_item_list, allDrugs));
                listDrugs.setTextFilterEnabled(true);

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
            listDrugs.clearTextFilter();
        } else {
            listDrugs.setFilterText(s.toString());
        }
        return true;
    }

}