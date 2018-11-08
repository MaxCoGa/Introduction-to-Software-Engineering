package com.uottawa.advancedui2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Get ListView object from xml layout
        ListView listView = (ListView) findViewById(R.id.list);

        // Defining Array values to show in ListView
        Recette r1 = new Recette("Banana","North Pole",555,R.drawable.banana);
        
        List<Recette> cookbook = new ArrayList<Recette>();
        
        /**
         * TODO (1): You may want to have more recipes
         */

        Recette r2 = new Recette("banana Split", "UK", 555, R.drawable.bananasplit);
        //Recette r2,r3,r4...

        cookbook.add(r1);
        cookbook.add(r2);
        // cookbook.add(r3);
        // cookbook.add(r4);
        // ...



        // create an instance of the adapter
        AdaptateurRecette ad = new AdaptateurRecette(this, cookbook);
                
        // associate view to adapter
        listView.setAdapter(ad);

        // Create an ArrayAdapter and Set it on the ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final Recette item = (Recette) parent.getItemAtPosition(position);
                
                //Do something with the string that you just got!
                Intent i = new Intent(getApplicationContext(),ObjectProfile.class);

            /**
             * TODO (2): Pass data to the next activity.
             */

            i.putExtra("data-image",item.getImageName());
            i.putExtra("data-name", item.getName());
            //..
                //..
            
            }
        });
    }
}
