package com.unifei.spritze.spritze;

import android.app.ActionBar;
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
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

import entities.Pacient;
import firebase.ConfigFireBase;

public class hospital_select extends Activity implements SearchView.OnQueryTextListener{
    private DatabaseReference rootBD;
    private ListView hospList;
    private String medicEmail;
    private SearchView filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_select);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        this.rootBD = ConfigFireBase.getDataReference();
        this.medicEmail = (String)getIntent().getSerializableExtra("medicEmail");
        this.hospList = (ListView) findViewById(R.id.listHosp);
        this.filter = (SearchView) findViewById(R.id.searchHospital);

        //capture the size of the devices screen
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);

        //define the Layout height
        RelativeLayout layout = (RelativeLayout) this.findViewById(R.id.bottonColor);
        layout.setMinimumHeight(size.y/2);

        getHospitals();
        setupSearchView();

        this.hospList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(hospital_select.this, medicLoged.class);
                intent.putExtra("Hospital", (String)adapterView.getAdapter().getItem(i));
                startActivity(intent);
            }
        });
    }

    private void setupSearchView(){
        filter.setIconifiedByDefault(false);
        filter.setOnQueryTextListener(this);
        filter.setSubmitButtonEnabled(true);
        filter.setQueryHint("Procure Hospital Aqui");
    }

    private void getHospitals(){
        final DatabaseReference auxRef  = this.rootBD.child("Colaborador").child(this.medicEmail).child("hospitals");
        auxRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String _auxS = dataSnapshot.getValue(String.class);
                String[] _hosps = _auxS.split(" ");
                hospList.setAdapter(new ArrayAdapter<String>(hospital_select.this, R.layout.pacient_item_list, _hosps));
                hospList.setTextFilterEnabled(true);
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
            hospList.clearTextFilter();
        } else {
            hospList.setFilterText(s.toString());
        }
        return true;
    }
}
