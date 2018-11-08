package com.uottawa.advancedui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by cedric on 11/7/2017.
 */

public class Profile extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.object_profile);

        /**
         *  TODO: (1) Obtain the resources from the MainActivity
         *  TODO: (2) Obtain reference to UI elements
         *  TODO: (3) Set UI element values to resource values
         */

        String dataIMg=getIntent().getExtras().getString("pic");
        String data=getIntent().getExtras().getString("data");


        TextView name = (TextView) findViewById(R.id.textName);
//...
        ImageView img = (ImageView) findViewById(R.id.imageRecipe);

        name.setText(data);
        int imageId = getResources().getIdentifier(dataIMg, "drawable", getPackageName());
        img.setImageResource(imageId);

    }
}
