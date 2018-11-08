package com.majes.uottawa.taskmanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxime with the Task.java code from Jeremie.
 */

public class TaskListActivity extends AppCompatActivity {

    private DatabaseReference mDatabaseTasks;
    private DatabaseReference mDatabaseUsers;
    private DatabaseReference mDatabaseNotes;
    private FirebaseAuth mAuth;
    private List<Task> mTasks;
    private List<User> mUsers;
    private List<Task> mUserTasks;
    private User mCurrentUser;

    private FloatingActionButton mFAB;
    private LinearLayout mAddTaskLayout;
    private LinearLayout mNewGroupLayout;
    private Switch mTaskSwitch;

    private ListView mTasksListView;
    private TaskAdapter mTaskAdapter;
    private boolean subMenuExpanded = false;
    private boolean userTasksOnly = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        mDatabaseTasks = FirebaseDatabase.getInstance().getReference("tasks");
        mDatabaseUsers = FirebaseDatabase.getInstance().getReference("users");
        mDatabaseNotes = FirebaseDatabase.getInstance().getReference("Notes");

        mAuth = FirebaseAuth.getInstance();



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);







        mTasksListView = (ListView) findViewById(R.id.task_list_view);
        mAddTaskLayout = (LinearLayout) findViewById(R.id.layout_add_task);
        mNewGroupLayout = (LinearLayout) findViewById(R.id.layout_new_group);
        mTaskSwitch = (Switch) findViewById(R.id.task_switch);

        mTasks = new ArrayList<>();
        mUsers = new ArrayList<>();
        mUserTasks = new ArrayList<>();



        mTaskSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    userTasksOnly = true;
                    mTaskAdapter = new TaskAdapter(TaskListActivity.this, mUserTasks);
                    mTasksListView.setAdapter(mTaskAdapter);
                    mTaskAdapter.notifyDataSetChanged();
                } else {
                    userTasksOnly = false;
                    mTaskAdapter = new TaskAdapter(TaskListActivity.this, mTasks);
                    mTasksListView.setAdapter(mTaskAdapter);
                    mTaskAdapter.notifyDataSetChanged();
                }
            }
        });

        mFAB = (FloatingActionButton) findViewById(R.id.main_fab);
        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (subMenuExpanded) {
                    closeSubMenu();
                } else {
                    openSubMenu();
                }
            }
        });

        FloatingActionButton addTaskFab = (FloatingActionButton) findViewById(R.id.fab_add_task);
        addTaskFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskListActivity.this, NewTaskActivity.class);
                startActivity(intent);
            }
        });

        FloatingActionButton newGroupFab = (FloatingActionButton) findViewById(R.id.fab_new_group);
        newGroupFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskListActivity.this, NewFamilyActivity.class);
                startActivity(intent);
            }
        });

        mTasksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Task selected = mTasks.get(position);
                if (userTasksOnly) {
                    selected = mUserTasks.get(position);
                }
                String taskId = selected.getId();
                Intent intent = ViewTaskActivity.newIntent(TaskListActivity.this, taskId);
                startActivity(intent);
            }
        });


        mTasksListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {

                final int position = pos;

                AlertDialog.Builder builder;
                builder = new AlertDialog.Builder(TaskListActivity.this);
                builder.setTitle("Edit Task")
                        .setMessage("Are you sure you want to edit this task?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //deletes entry from database
                                Task selected = mTasks.get(position);
                                if (userTasksOnly) {
                                    String taskId = selected.getId();
                                    Intent intent = EditTaskActivity.newIntent(TaskListActivity.this, taskId);
                                    startActivity(intent);
                                }
                                if (!userTasksOnly && mUserTasks.contains(selected)) {
                                    selected = mUserTasks.get(position);
                                }
                                String taskId = selected.getId();
                                Intent intent = EditTaskActivity.newIntent(TaskListActivity.this, taskId);
                                startActivity(intent);


                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .show();

                return true;
            }

        });

        closeSubMenu();
    }

    @Override
    protected void onStart() {
        super.onStart();

        mDatabaseTasks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mTasks.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Task task = postSnapshot.getValue(Task.class);
                    mTasks.add(task);
                }
                if (!userTasksOnly) {
                    mTaskAdapter = new TaskAdapter(TaskListActivity.this, mTasks);
                    mTasksListView.setAdapter(mTaskAdapter);
                }
                mTaskAdapter.notifyDataSetChanged();
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
                    mUsers.add(user);
                    if (user.getId().equals(mAuth.getCurrentUser().getUid())) {
                        mCurrentUser = user;
                    }
                }

                for (Task task : mTasks) {
                    if (task.getAssignedUsers().contains(mCurrentUser.getId())) {
                        mUserTasks.add(task);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onPostResume() {
        closeSubMenu();
        super.onPostResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.user_settings) {
            Intent sendToSettings = new Intent(TaskListActivity.this, UserActivity.class);
            startActivity(sendToSettings);

        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_task_list, menu);
        return true;
    }

    private void deleteTask(Task task) {
        for (String deleteId : task.getAssignedUsers()) {
            for (User user : mUsers) {
                if (user.getId().equals(deleteId) && user.getAssignedTasks() != null) {
                    List<String> assignedTasks = user.getAssignedTasks();
                    assignedTasks.remove(task.getId());
                    user.setAssignedTasks(assignedTasks);
                    mDatabaseUsers.child(user.getId()).setValue(user);
                }
            }
        }
        mDatabaseTasks.child(task.getId()).removeValue();
    }



    private void openSubMenu() {
        mAddTaskLayout.setVisibility(View.VISIBLE);
        mNewGroupLayout.setVisibility(View.VISIBLE);
        mFAB.setImageResource(R.drawable.cancel);
        subMenuExpanded = true;
    }
    private void closeSubMenu() {
        mAddTaskLayout.setVisibility(View.INVISIBLE);
        mNewGroupLayout.setVisibility(View.INVISIBLE);
        mFAB.setImageResource(R.drawable.add);
        subMenuExpanded = false;
    }



}
