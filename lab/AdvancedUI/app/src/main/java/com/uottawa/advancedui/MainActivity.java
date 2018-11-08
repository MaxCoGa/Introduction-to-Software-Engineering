package com.uottawa.advancedui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get ListView object from xml layout
        ListView listView = (ListView) findViewById(R.id.list);

        // Defining Array values to show in ListView
        String[] values = new String[] {
                "Item 01","Item 02","Item 03","Item 04","Item 05","Item 06","Item 07","Item 08"
        };

        // Converting Array to ArrayList
        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }

        // Create an ArrayAdapter and Set it on the ListView
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);//dans le tableau

                /**
                 *  TODO: (1) Pass information to the next activity.
                 */
                Intent i = new Intent(getApplicationContext(),Profile.class);
                i.putExtra("pic","banana");
                i.putExtra("data",item);
                startActivityForResult(i,0);

                // ....

            }
        });
    }
}
