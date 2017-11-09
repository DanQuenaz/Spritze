package com.unifei.spritze.spritze;

<<<<<<< HEAD
import android.app.Activity;
=======
import android.content.Intent;
>>>>>>> 3c92980feb2e44cef5520d9890b992ec96bd17e5
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

<<<<<<< HEAD
public class dosageActivity extends Activity {
=======
public class dosageActivity extends AppCompatActivity {
    private String pacientName;
    private Long pacientAge;
    private double pacientWeight;
    private TextView pctName;
    private TextView pctAge;
>>>>>>> 3c92980feb2e44cef5520d9890b992ec96bd17e5

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosage);

        this.pctName = (TextView) findViewById(R.id.pacientName);
        this.pctAge = (TextView) findViewById(R.id.pacientAge);

        this.pacientName = (String)getIntent().getSerializableExtra("Nome");
        this.pacientAge = (Long)getIntent().getSerializableExtra("Idade");

        this.pctName.setText("Nome: " + this.pacientName);
        this.pctAge.setText("Idade: " + Long.toString( this.pacientAge));
    }
}
