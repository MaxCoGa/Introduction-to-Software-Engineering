package com.uottawa.advancedui2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ObjectProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_profile);
        
        /**
         * TODO (1): Get reference to received data.
         * TODO (2): Get reference to UI elements.
         * TODO (3): Set UI element values to those of received data. 
         */

        String dataName=getIntent().getExtras().getString("data-name");
        //autre getters pour TODO(1)
        //....

        //TODO(2)
        TextView name = (TextView) findViewById(R.id.textName);
        //autre instance des UI elems
        //...

        //TODO(3)
        name.setText((CharSequence) name);


    }
}
