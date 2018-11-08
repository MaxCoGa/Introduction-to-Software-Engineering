package com.majes.uottawa.taskmanager;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

/**
 * Created by Jeremie on 05-Dec-17.
 */

public class NoteAdapter extends ArrayAdapter<Note>{
    private Activity context;
    private DatabaseReference mDatabaseNotes;

    List<Note> notes;

    public NoteAdapter(Activity context, List<Note> notes){
        super(context, R.layout.layout_note_adapter, notes);
        this.notes = notes;
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_note_adapter, null, true);

        TextView creator = (TextView) listViewItem.findViewById(R.id.poster);
        TextView comment = (TextView) listViewItem.findViewById(R.id.comment);

        Note note = notes.get(position);
        creator.setText(note.getCreator() + " a poste:");
        comment.setText(note.getComment());
        return listViewItem;


    }
}
