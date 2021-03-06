package com.majes.uottawa.taskmanager;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Jeremie on 05-Dec-17.
 */


public class NoteActivity extends AppCompatActivity {
    private Button addNote;
    private Button cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        addNote = (Button) findViewById(R.id.addNote);
        cancel = (Button) findViewById(R.id.noteCancel);

        addNote.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                addNote();
            }
        });


    }

    protected void cancelNote(){
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();
    }

    protected void addNote(){
        TextView comment = (TextView) findViewById(R.id.noteComment);
        String comm = comment.getText().toString();
        Intent returnIntent = new Intent();
        returnIntent.putExtra("NOTE", comm);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
