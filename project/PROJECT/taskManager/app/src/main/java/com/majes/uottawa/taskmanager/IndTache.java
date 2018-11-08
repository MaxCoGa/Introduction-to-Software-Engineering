package com.majes.uottawa.taskmanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeremie on 05-Dec-17.
 */


public class IndTache extends AppCompatActivity {
    private static final String EXTRA_TASK_ID = "com.majes.uottawa.taskmanager.task_id";
    String userName;
    String tacheId;
    String tacheName;
    String tacheDate;
    String tacheCreator;
    DatabaseReference databaseNotes;
    // DatabaseReference databaseFeed;

    ListView listViewInst;
    ListView listViewFeed;

    List<Note> inst;
    List<Note> feed;

    public static Intent newIntent(Context packageContext, String taskId) {
        Intent intent = new Intent(packageContext, EditTaskActivity.class);
        intent.putExtra(EXTRA_TASK_ID, taskId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        userName = getIntent().getStringExtra("");
        tacheId = getIntent().getStringExtra("");
        tacheName = getIntent().getStringExtra("");
        tacheDate = getIntent().getStringExtra("");
        tacheCreator = getIntent().getStringExtra("");
        setContentView(R.layout.activity_ind_tache);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(tacheName);

        TextView creator = (TextView) findViewById(R.id.tacheCreator);
        TextView date = (TextView) findViewById(R.id.date);

        creator.setText(tacheCreator);
        date.setText(tacheDate);

        inst = new ArrayList<>();
        feed = new ArrayList<>();

        //databaseFeed = FirebaseDatabase.getInstance().getReference("Feed");
        databaseNotes = FirebaseDatabase.getInstance().getReference("Notes");

        listViewInst.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Note note = inst.get(position);
                update(note);
                return true;
            }
        });

        listViewFeed.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Note note = feed.get(position);
                update(note);
                return true;
            }
        });
    }


    protected void onStart(){
        super.onStart();

        databaseNotes.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                inst.clear();

                for(DataSnapshot snap : dataSnapshot.getChildren()){
                    Note note = snap.getValue(Note.class);
                    if(note.getTacheId() == tacheId){               // if note.tachedID != tacheID, on n'ajoute pas ces taches a feed ou inst
                        if(note.getCreator() == tacheCreator) {             // note a ete ecrit par le createur de la tache
                            inst.add(note);                                        // add to inst
                        }else{
                            feed.add(note);                                 // note n'a pas ete ecrit par le createur de la tache
                        }
                    }
                }

                NoteAdapter noteAdapter = new NoteAdapter(IndTache.this, inst);         //Create adapter pour lse listviews
                NoteAdapter feedAdapter = new NoteAdapter(IndTache.this, feed);
                listViewInst.setAdapter(noteAdapter);                                   // Display les listview
                listViewFeed.setAdapter(feedAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        /* databaseFeed.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                feed.clear();

                for(DataSnapshot snap : dataSnapshot.getChildren()){
                    Note note = snap.getValue(Note.class);
                    if(note.getTacheId() == tacheId){
                        feed.add(note);
                    }
                }

                NoteAdapter noteAdapter = new NoteAdapter(IndTache.this, feed);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        }); */


    }

    // Handle editing/ deleting note

    // onItemLongClick -> update(note) -> updateNote() or  deleteNote()
    private void update(final Note note) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.layout_note_update, null);
        dialogBuilder.setView(dialogView);

        EditText noteEvent = (EditText) findViewById(R.id.noteComment);
        noteEvent.setText("Edit Note");
        final EditText editNote = (EditText) dialogView.findViewById(R.id.noteUse);;
        final Button update = (Button) dialogView.findViewById(R.id.updateNoteButton);
        final Button delete = (Button) dialogView.findViewById(R.id.deleteNoteButton);

        final AlertDialog b = dialogBuilder.create();
        b.show();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String comment = editNote.getText().toString().trim();
                if (!TextUtils.isEmpty(comment)) {
                    updateNote(note, comment);
                    b.dismiss();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteNote(note.getId());
                b.dismiss();
            }
        });
    }


    protected boolean deleteNote(String id){
        if (tacheCreator == userName){              // check if user has permission to commit action
            DatabaseReference temp = FirebaseDatabase.getInstance().getReference("Notes").child(id);
            temp.removeValue();
            Toast.makeText(getApplicationContext(), "Note Deleted", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), "You do not have permission to delete this note", Toast.LENGTH_LONG).show();
        }
        return true;
    }

    protected void updateNote(Note note, String comment){
        if (note.getCreator() == userName){             // check if user has permission to commit action
            DatabaseReference temp = FirebaseDatabase.getInstance().getReference("Notes").child(note.getId());
            Note newNote = new Note(note.getId(),note.getTacheId(),note.getCreator(), comment);
            temp.setValue(newNote);
            Toast.makeText(getApplicationContext(), "Note Updated", Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(getApplicationContext(), "You do not have permission to edit this note", Toast.LENGTH_LONG).show();
        }
    }


    // Handle adding notes

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String comm = data.getStringExtra("NOTE");
                addNote(comm);
            }
        }
    }


    protected void addNote(){
        Intent intent = new Intent(this, NoteActivity.class);
        startActivityForResult(intent, 1);
    }

    // Ajout note avec valeur comment comme commentaire
    protected void addNote(String comment){
        String id = databaseNotes.push().getKey();
        Note note = new Note(id, tacheId, tacheCreator, comment);
        databaseNotes.child(id).setValue(note);
    }

}
