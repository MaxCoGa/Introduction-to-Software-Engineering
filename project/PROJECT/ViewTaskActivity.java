package com.majes.uottawa.taskmanager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
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

    private static final String TASK_ID = "com.majes.uottawa.taskmanager.task_id";
    private DatabaseReference mDatabaseUsers;
    private DatabaseReference mDatabaseTasks;
    private FirebaseAuth mAuth;

    private List<Task> mTasks = new ArrayList<>();
    private List<User> mUsers = new ArrayList<>();
    private List<String> mAssignedUserIds = new ArrayList<>();
    private Task mTask = new Task();
    private User mUser;

    private EditText mTaskName;
    private EditText mTaskDescription;
    private EditText mTaskInstruction;
    private EditText mTaskDueDate;
    private TextInputLayout mTaskNameLayout;
    private TextInputLayout mTaskDescLayout;
    private TextInputLayout mTaskInstructionLayout;
    private TextInputLayout mTaskDateLayout;
    private RadioGroup mRadioGroup;


    private String extraTaskId;

    public static Intent newIntent(Context packageContext, String taskId) {
        Intent intent = new Intent(packageContext, ViewTaskActivity.class);
        intent.putExtra(TASK_ID, taskId);
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

        extraTaskId = getIntent().getStringExtra(TASK_ID);

        mDatabaseTasks = FirebaseDatabase.getInstance().getReference("tasks");
        mDatabaseUsers = FirebaseDatabase.getInstance().getReference("users");
        mAuth = FirebaseAuth.getInstance();

        mTaskName = (EditText) findViewById(R.id.edit_task_name);
        mTaskDescription = (EditText) findViewById(R.id.edit_task_description);
        mTaskInstruction = (EditText) findViewById(R.id.edit_task_instruction);
        mTaskDueDate = (EditText) findViewById(R.id.edit_due_date);
        mTaskNameLayout = (TextInputLayout) findViewById(R.id.edit_task_name_layout);
        mTaskDescLayout = (TextInputLayout) findViewById(R.id.edit_task_description_layout);
        mTaskInstructionLayout = (TextInputLayout) findViewById(R.id.task_instruction_layout);
        mTaskDateLayout = (TextInputLayout) findViewById(R.id.edit_due_date_layout);
        mRadioGroup = (RadioGroup) findViewById(R.id.edit_status_radio_group);




        if (!mAuth.getCurrentUser().getUid().equals(mTask.getCreatorId())) {
            mTaskName.setFocusable(false);
            mTaskDescription.setFocusable(false);
            mTaskInstruction.setFocusable(false);
            mTaskDueDate.setFocusable(false);
        }

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
                mTasks.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Task task = snapshot.getValue(Task.class);
                    mTasks.add(task);
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
                mUsers.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    mUsers.add(user);
                    if (user.getId().equals(mAuth.getCurrentUser().getUid())) {
                        mUser = user;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    /**
     *
     * Status
     */
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
            Intent sendToSettings = new Intent(ViewTaskActivity.this, NoteActivity.class);
            startActivity(sendToSettings);

        }
        return true;
    }


}
