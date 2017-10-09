package com.unifei.spritze.spritze;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private ImageButton medicButton;
    private ImageButton masterButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        medicButton = (ImageButton) findViewById(R.id.buttonMedicLogin);
        masterButton = (ImageButton) findViewById(R.id.buttonMasterLogin);

        medicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intet = new Intent(MainActivity.this, medicLogin.class);
                startActivity(intet);
            }
        });

        masterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, masterLogin.class);
                startActivity(intent);
            }
        });
    }


}
