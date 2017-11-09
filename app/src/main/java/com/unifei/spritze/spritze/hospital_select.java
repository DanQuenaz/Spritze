package com.unifei.spritze.spritze;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import firebase.ConfigFireBase;

public class hospital_select extends AppCompatActivity {
    private DatabaseReference rootDB;
    private ListView hospList;
    private String medicEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_select);
        this.medicEmail = (String)getIntent().getSerializableExtra("medicEmail");
        this.hospList = (ListView) findViewById(R.id.listHosp);
        this.rootDB = ConfigFireBase.getDataReference();
        final DatabaseReference auxRef = rootDB.child("Colaborador").child(this.medicEmail).child("hospitals");
        auxRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String _auxS = dataSnapshot.getValue(String.class);
                String[] _hosps = _auxS.split(" ");
                hospList.setAdapter(new ArrayAdapter<String>(hospital_select.this, R.layout.pacient_item_list, _hosps));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        this.hospList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(hospital_select.this, medicLoged.class);
                intent.putExtra("Hospital", (String)adapterView.getAdapter().getItem(i));
                startActivity(intent);
            }
        });
    }


}
