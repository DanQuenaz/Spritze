package com.unifei.spritze.spritze;

import android.app.Activity;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class masterLoged extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_loged);

        //capture the size of the devices screen
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);

        //define the Layout height
        RelativeLayout layout = (RelativeLayout) this.findViewById(R.id.bottonColor);
        layout.setMinimumHeight(size.y/2);
    }
}
