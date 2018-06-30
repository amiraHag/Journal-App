package com.example.android.journalapp.view;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.journalapp.R;
import com.example.android.journalapp.model.Post;
import com.example.android.journalapp.view.AllPostsActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostActivity extends AppCompatActivity {

    // [START declare_static_variables]
    private static final String TAG = "NewPostActivity";
    private static final String REQUIRED = "Required";
    // [END declare_static_variables]

    // [START declare_database_ref]
    private DatabaseReference mDatabase;
    // [END declare_database_ref]

    // [START declare_views]
    private EditText mTitleField;
    private EditText mBodyField;
    private FloatingActionButton mSubmitButton;
    // [END declare_views]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        // [START initialize_database_ref]
        mDatabase = FirebaseDatabase.getInstance().getReference("posts");
        // [END initialize_database_ref]

        mTitleField = findViewById(R.id.field_title);
        mBodyField = findViewById(R.id.field_body);
        mSubmitButton = findViewById(R.id.submit_new_post);

        mSubmitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               submitPost();
            }
        });
    }



    // [START Submit function]
    private void submitPost() {
        final String title = mTitleField.getText().toString();
        final String body = mBodyField.getText().toString();

        // Title is required
        if (TextUtils.isEmpty(title)) {
            mTitleField.setError(REQUIRED);
            Toast.makeText(this, "You should enter title", Toast.LENGTH_SHORT).show();
            return;
        }

        // Body is required
        if (TextUtils.isEmpty(body)) {
            mBodyField.setError(REQUIRED);
            Toast.makeText(this, "You should enter description", Toast.LENGTH_SHORT).show();
            return;
        }

        // Disable button so there are no multi-posts
        setEditingEnabled(false);
        Toast.makeText(this, "Posting...", Toast.LENGTH_SHORT).show();


        //getting a unique id using push().getKey() method to use it as the Primary Key for post
        String id = mDatabase.push().getKey();

        //creating the post Object
        Post post = new Post(id, title, body);

        //Saving the post in firebase database
        mDatabase.child(id).setValue(post);

        //setting text fields to blank again
        resetEditingFields();

        //displaying a success toast
        Toast.makeText(this, "Post added", Toast.LENGTH_LONG).show();

        startAllPostsActivity();


    }
    // [END Submit function]


    // [START login activity]
    public void startAllPostsActivity() {
        finish();
        startActivity(new Intent(this, AllPostsActivity.class));
    }
    // [END login activity]


    // [START Enable Editing Function prevent user edit while posting]
    private void setEditingEnabled(boolean enabled) {
        mTitleField.setEnabled(enabled);
        mBodyField.setEnabled(enabled);
        if (enabled) {
            mSubmitButton.setVisibility(View.VISIBLE);
        } else {
            mSubmitButton.setVisibility(View.GONE);
        }
    }
    // [END Enable Editing Function]


    // [START reset fields]
    private void resetEditingFields() {
        mTitleField.setText("");
        mBodyField.setText("");

    }
    // [END reset fields]
}
