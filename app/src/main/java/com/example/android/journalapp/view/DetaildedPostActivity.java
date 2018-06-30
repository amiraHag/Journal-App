package com.example.android.journalapp.view;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.journalapp.R;
import com.example.android.journalapp.model.Post;
import com.example.android.journalapp.view.AllPostsActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetaildedPostActivity extends AppCompatActivity {

    // [START declare_views]
    private TextView mTitleDetailPost;
    private TextView mBodyDetailPost;
    private Button mEditButton;
    // [END declare_views]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailded_post);


        // [START initialize_views]
        mTitleDetailPost = findViewById(R.id.details_post_title);
        mBodyDetailPost = findViewById(R.id.details_post_body);
        mEditButton = findViewById(R.id.edit_details_post);
        // [END initialize_views]


        // [START set_value_views]
        Intent intent = getIntent();


        final String id = intent.getStringExtra("postid");
        final String title = intent.getStringExtra("postname");
        final String body = intent.getStringExtra("postbody");

        mTitleDetailPost.setText(title);
        mBodyDetailPost.setText(body);
        // [END set_value_views]

        mEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showUpdatePostDialog(id, title,body);
            }
        });

    }



    private void showUpdatePostDialog(final String postId, final String postTitle, final String postBody) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_post_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextName = (EditText) dialogView.findViewById(R.id.modified_field_title);
        final EditText editTextdescription = (EditText) dialogView.findViewById(R.id.modified_field_body);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.update_button);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.delete_button);
        editTextName.setText(postTitle);
        editTextdescription.setText(postBody);

        dialogBuilder.setTitle(postTitle);
        final AlertDialog dialog = dialogBuilder.create();
        dialog.show();


        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = editTextName.getText().toString().trim();
                String body = editTextdescription.getText().toString().trim();

                if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(body)) {
                   if(updatePost(postId, title, body)) {
                       dialog.dismiss();
                   }
                }
            }
        });




        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(deletePost(postId)) {
                    startAllPostsActivity();
                }

            }
        });
    }
    // [START login activity]
    public void startAllPostsActivity() {
        finish();
        startActivity(new Intent(this, AllPostsActivity.class));
    }
    // [END login activity]


    private boolean updatePost(String id, String title, String body) {
        //getting the specified artist reference
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("posts").child(id);

        //updating artist
        Post post = new Post(id, title, body);
        databaseReference.setValue(post);
        Toast.makeText(getApplicationContext(), "Post Updated", Toast.LENGTH_LONG).show();
        return true;
    }

    private boolean deletePost(String id) {
        //getting the specified artist reference
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("posts").child(id);

        //removing artist
        databaseReference.removeValue();


        Toast.makeText(getApplicationContext(), "post Deleted", Toast.LENGTH_LONG).show();

        return true;
    }
}
