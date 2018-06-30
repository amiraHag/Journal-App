package com.example.android.journalapp.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.journalapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class AccountActivity extends AppCompatActivity {


    //Views
    ImageView userAccountPhoto;
    TextView  userNameTextView,  userEmailTextView;
    Button signoutButton;

    //Firebase
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);


        //Initialize the views
        userAccountPhoto = findViewById(R.id.userAccountPhoto);

        userNameTextView = (TextView) findViewById(R.id.userAccountName);
        userEmailTextView = (TextView) findViewById(R.id.userAccountEmail);
        signoutButton = (Button) findViewById(R.id.signoutButton);


        //Get instance for firebase auth
        mAuth = FirebaseAuth.getInstance();


        //Get firebase user
        final FirebaseUser currentUser = mAuth.getCurrentUser();

        //set the values of views
        Glide.with(this)
                .load(currentUser.getPhotoUrl())
                .into(userAccountPhoto);

        userNameTextView.setText("Name:  " + currentUser.getDisplayName());
        userEmailTextView.setText("Email:  " + currentUser.getEmail());
        signoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userSignout(currentUser);
            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message2");

        myRef.setValue("Hello, World!2");

// Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("message", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("message", "Failed to read value.", error.toException());
            }
        });

    }



    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(!checkUser(currentUser) ){
            startLoginActivity();

        }


    }
    // [START login activity]
    public void startLoginActivity() {
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }
    // [END login activity]


    // [START check login user]
    public boolean checkUser(FirebaseUser user){

        if (user != null){
            return true;
        }
        return false;

    }
    // [END check login user]

    // [START user sign out]
    public void userSignout(FirebaseUser user){

        if (user != null){
            FirebaseAuth.getInstance().signOut();
            startLoginActivity();


        }

    }


}
