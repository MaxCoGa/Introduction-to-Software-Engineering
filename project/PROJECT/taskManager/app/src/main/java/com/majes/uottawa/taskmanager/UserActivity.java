package com.majes.uottawa.taskmanager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

/**
 * Created by Maxime on 12-04-2017.
 */
public class UserActivity extends AppCompatActivity {

    private static final int GALLERY_INTENT = 2;

    private DatabaseReference mUserData;
    private StorageReference mStorage;
    private String mUserId;

    private TextView mNameTextView;
    private TextView mEmailTextView;
    private TextView mPoints;
    private TextView mFamily;
    private TextView mIsParent;

    private Button mSignout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        mSignout = (Button) findViewById(R.id.btn_sign_out);

        mUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mUserData = FirebaseDatabase.getInstance().getReference("users").child(mUserId);
        mStorage = FirebaseStorage.getInstance().getReference();

        mNameTextView = (TextView) findViewById(R.id.user_name);
        mEmailTextView = (TextView) findViewById(R.id.user_email);
        mPoints = (TextView) findViewById(R.id.user_points);
        mFamily = (TextView)findViewById(R.id.user_family);
        mIsParent = (TextView) findViewById(R.id.isParent);

        //TODO CHANGE WHERE IS THE DISCONNECTED FUNCTION  TO PLACE IT IN THE MENU
        mSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(UserActivity.this,LoginActivity.class);
                finishAffinity();
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "DISCONNECTED!", Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        mUserData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                String name = user.getFirstName() + " " + user.getLastName();
                String email = user.getEmail();
                int points = user.getPoints();
                String family = user.getFamily();//TODO
                Boolean isParent = user.getIsParent();

                mNameTextView.setText(name);//check the name of this user
                mEmailTextView.setText(email);//check the email of this user
                mPoints.setText(String.valueOf(points));//Check point of this user
                mFamily.setText(family);//TODO
                //check if the user is Parent or Child
                if( isParent == true){
                    mIsParent.setText("Parent");
                } else {
                    mIsParent.setText("Child");
                }

            }
            //TODO maybe catch some exception that can be happen if when we change the format of the databse
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }


}
