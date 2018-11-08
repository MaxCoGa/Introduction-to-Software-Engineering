package com.majes.uottawa.taskmanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**Classe pratiquement identique à EditTaskAcitivty, mais les items ne sont pas selectionnables
 *
 *
 */
public class ViewTaskActivity extends AppCompatActivity {

    private static final String EXTRA_TASK_ID = "com.majes.uottawa.taskmanager.task_id";
    private DatabaseReference mDatabaseUsers;
    private DatabaseReference mDatabaseTasks;
    private DatabaseReference mDatabaseNotes;
    private DatabaseReference mDatabaseRes;
    private FirebaseAuth mAuth;

    private ListView listViewInst;
    private ListView listViewFeed;
    private List<Note> inst;
    private List<Note> feed;


    private Task mTask;
    private User mUser;

    private TextView mTaskName;
    private TextView mTaskDescription;
    private TextView mTaskInstruction;
    private TextView mTaskDueDate;

    private RadioGroup mRadioGroup;
    private RadioGroup mTaskRes;


    private String extraTaskId;

    public static Intent newIntent(Context packageContext, String taskId) {
        Intent intent = new Intent(packageContext, ViewTaskActivity.class);
        intent.putExtra(EXTRA_TASK_ID, taskId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        extraTaskId = getIntent().getStringExtra(EXTRA_TASK_ID);

        mDatabaseTasks = FirebaseDatabase.getInstance().getReference("tasks");
        mDatabaseUsers = FirebaseDatabase.getInstance().getReference("users");
        mDatabaseNotes = FirebaseDatabase.getInstance().getReference("Notes");

        listViewFeed = (ListView) findViewById(R.id.feedback);
        listViewInst = (ListView) findViewById(R.id.instruction);

        inst = new ArrayList<>();
        feed = new ArrayList<>();



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

        mAuth = FirebaseAuth.getInstance();


        mTaskName = (TextView) findViewById(R.id.edit_task_name);
        mTaskDescription = (TextView) findViewById(R.id.edit_task_description);
        mTaskInstruction = (TextView) findViewById(R.id.edit_task_instruction);
        mTaskDueDate = (TextView) findViewById(R.id.edit_due_date);
        mRadioGroup = (RadioGroup) findViewById(R.id.edit_status_radio_group);
        mTaskRes = (RadioGroup) findViewById(R.id.edit_res_radio_group);




        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        mTaskName.addTextChangedListener(textWatcher);
        mTaskDescription.addTextChangedListener(textWatcher);
        mTaskInstruction.addTextChangedListener(textWatcher);
        mTaskDueDate.addTextChangedListener(textWatcher);
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }


    @Override
    protected void onStart() {
        super.onStart();

        mDatabaseTasks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Task task = snapshot.getValue(Task.class);
                    if (task.getId().equals(extraTaskId)) {
                        mTask = task;
                    }
                }
                taskStatus();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mDatabaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    if (user.getId().equals(mAuth.getCurrentUser().getUid())) {
                        mUser = user;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mDatabaseNotes.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                inst.clear();
                feed.clear();

                for(DataSnapshot snap : dataSnapshot.getChildren()){
                    Note note = snap.getValue(Note.class);
                    //inst.add(note);

                    if(note.getTacheId().equals(mTask.getId())) {               // if note.tachedID != tacheID, on n'ajoute pas ces taches a feed ou inst
                        if (note.getCreator().equals(mUser.getId())) {             // note a ete ecrit par le createur de la tache
                            inst.add(note);                                        // add to inst
                        } else {
                            feed.add(note);                                 // note n'a pas ete ecrit par le createur de la tache
                        }
                    }
                }

                NoteAdapter noteAdapter = new NoteAdapter(ViewTaskActivity.this, inst);         //Create adapter pour lse listviews
                listViewInst.setAdapter(noteAdapter);
                NoteAdapter feedAdapter = new NoteAdapter(ViewTaskActivity.this, feed);// Display les listview
                listViewFeed.setAdapter(feedAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



    private void taskStatus() {
        mTaskName.setText(mTask.getTitle());
        mTaskDescription.setText(mTask.getDescription());
        mTaskInstruction.setText(mTask.getInstruction());
        mTaskDueDate.setText(mTask.getDate());

        switch (mTask.getStatus()) {
            case "incomplete":
                mRadioGroup.check(R.id.edit_radio_incomplete);
                break;
            case "in progress":
                mRadioGroup.check(R.id.edit_radio_in_progress);
                break;
            case "complete":
                mRadioGroup.check(R.id.edit_radio_complete);
                break;
            default:
                break;
        }
        switch (mTask.getRes()){
            case "Money":
                mTaskRes.check(R.id.edit_radio_money);
                break;
            case "Cleaning Kit":
                mTaskRes.check(R.id.edit_radio_clean);
                break;
            default:
                break;
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_note, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.note_settings) {
            addNote();

        }
        return true;
    }












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
        if (mTask.getCreatorId() == mUser.getId()){              // check if user has permission to commit action
            DatabaseReference temp = FirebaseDatabase.getInstance().getReference("Notes").child(id);
            temp.removeValue();
            Toast.makeText(getApplicationContext(), "Note Deleted", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), "You do not have permission to delete this note", Toast.LENGTH_LONG).show();
        }
        return true;
    }

    protected void updateNote(Note note, String comment){
        if (note.getCreator() == mUser.getId()){             // check if user has permission to commit action
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
        String id = mDatabaseNotes.push().getKey();
        Note note = new Note(id, mTask.getId(), mUser.getId(), comment);
        mDatabaseNotes.child(id).setValue(note);
    }


}
