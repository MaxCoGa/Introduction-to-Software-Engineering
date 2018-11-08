package com.majes.uottawa.taskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Switch;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.majes.uottawa.taskmanager.Task;

import java.util.ArrayList;
import java.util.List;

public class TacheActivity extends AppCompatActivity {

    DatabaseReference databaseTaches;
    ListView listViewTaches;
    Switch tacheFilter;

    List<Task> taches;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_tache_holder);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //database reference pour les taches
        databaseTaches = FirebaseDatabase.getInstance().getReference("Taches");     // ?c'est quoi la reference dans la db
       // listViewTaches = (ListView) findViewById(R.id.listViewTaches);
      //  tacheFilter = (Switch) findViewById(R.id.tacheFilter);

        taches = new ArrayList<>();

        listViewTaches.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l){
              //  Tache tache = taches.get(position);
                Intent intent = new Intent(TacheActivity.this, IndTache.class);
               // intent.putExtra("TACHE_ID", tache.getId());
             //   intent.putExtra("USER_NAME", getIntent().getStringExtra("USER_NAME"));
             //   intent.putExtra("TACHE_NAME", tache.getName());
               // intent.putExtra("TACHE_CREATOR", tache.getCreator());
                //intent.putExtra("TACHE_DATE", tache.getDate());
                startActivity(intent);
                return;
            }
        });
    }

    protected void onStart(){
        super.onStart();

        //databaseTaches.addValueEventListener(new ValueEventListener (){
          //  public void onDataChange(DataSnapshot dataSnapshot){
            //    taches.clear();

        //        for(DataSnapshot snap: dataSnapshot.getChildren()){
      //              Tache tache = snap.getValue(Tache.class);
    //                taches.add(tache);
                }

//                TacheList tacheAdapter = new TacheList(TacheActivity.this, taches);
  //              listViewTaches.setAdapter(tacheAdapter);
            //}

            //@Override
           // public void onCancelled(DatabaseError databaseError) {

            //}
     //   });
    //}






    protected void addTache(){

    }

}
