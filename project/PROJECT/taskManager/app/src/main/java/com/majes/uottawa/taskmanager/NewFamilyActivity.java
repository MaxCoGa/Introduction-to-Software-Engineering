package com.majes.uottawa.taskmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

//Labo 7 des lists
//TODO MODIFICATION!!!!
public class NewFamilyActivity extends AppCompatActivity {

    private DatabaseReference mDatabaseUsers;
    private DatabaseReference mDatabaseGroups;

    private EditText mFamilyName;
    private Button mNewFamilyBtn;
    private ListView mUserList;
    private ArrayAdapter<String> mAdapter;

    private List<String> mUserNames;
    private List<String> mUserIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_family);

        mDatabaseUsers = FirebaseDatabase.getInstance().getReference("users");
        mDatabaseGroups = FirebaseDatabase.getInstance().getReference("family");

        mFamilyName = (EditText) findViewById(R.id.family_name);
        mUserList = (ListView) findViewById(R.id.user_list);

        mUserNames = new ArrayList<>();
        mUserIds = new ArrayList<>();
        mNewFamilyBtn = (Button) findViewById(R.id.btn_new_family);
        mNewFamilyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createFamily();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        mDatabaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUserNames.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    User user = postSnapshot.getValue(User.class);
                    String name = user.getFirstName() + " " + user.getLastName();
                    mUserIds.add(user.getId());
                    mUserNames.add(name);
                }

                //reference du lab 7 permettant de creer un adapter pour une liste qui peut nous faire chosir un item dans une liste
                mAdapter = new ArrayAdapter<>(NewFamilyActivity.this, android.R.layout.simple_list_item_multiple_choice, mUserNames);
                mUserList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                mUserList.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void createFamily() {

        String name = mFamilyName.getText().toString();

        SparseBooleanArray checked = mUserList.getCheckedItemPositions();
        ArrayList<String> selectedUsers = new ArrayList<>();
        for (int i = 0; i < checked.size(); i++) {
            if (checked.get(i)) {
                String userId = mUserIds.get(i);
                selectedUsers.add(userId);
            }
        }
        String familyID = mDatabaseGroups.push().getKey();

        Family newFamily = new Family(name);
        newFamily.setFamilyName(name);
        newFamily.setId(familyID);
        newFamily.setUsers(selectedUsers);




        mDatabaseGroups.child(familyID).setValue(newFamily);
        for (String id: selectedUsers) {
            mDatabaseUsers.child(id).child("family").setValue(familyID);
        }
    }
}
