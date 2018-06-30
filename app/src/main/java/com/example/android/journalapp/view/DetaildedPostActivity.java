package com.example.android.journalapp.view;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetaildedPostActivity extends BaseActivity {

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
        mTitleDetailPost = findViewById(R.id.text_details_post_title);
        mBodyDetailPost = findViewById(R.id.text_details_post_body);
        mEditButton = findViewById(R.id.button_edit_details_post);
        // [END initialize_views]


        // [START set_value_views]
        Intent intent = getIntent();


        final String id = intent.getStringExtra("POST_ID");
        final String title = intent.getStringExtra("POST_NAME");
        final String body = intent.getStringExtra("POST_BODY");

        mTitleDetailPost.setText(title);
        mBodyDetailPost.setText(body);
        // [END set_value_views]

        mEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showUpdatePostDialog(id, title, body);
            }
        });

    }


    private void showUpdatePostDialog(final String postId, final String postTitle, final String postBody) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_post_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextName = (EditText) dialogView.findViewById(R.id.et_modified_field_title);
        final EditText editTextdescription = (EditText) dialogView.findViewById(R.id.et_modified_field_body);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.button_update);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.button_delete);
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
                    if (updatePost(postId, title, body)) {
                        startAllPostsActivity();
                    }
                }
            }
        });


        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (deletePost(postId)) {
                    startAllPostsActivity();
                }

            }
        });
    }

    // [START all post  activity]
    public void startAllPostsActivity() {
        finish();
        startActivity(new Intent(this, AllPostsActivity.class));
    }
    // [END all post activity]


    private boolean updatePost(String id, String title, String body) {
        //getting the specified post reference
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("posts").child(id);

        //updating post
        Post post = new Post(id, title, body);
        databaseReference.setValue(post);
        Toast.makeText(getApplicationContext(), "Post Updated", Toast.LENGTH_LONG).show();
        return true;
    }

    private boolean deletePost(String id) {
        //getting the specified post reference
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("posts").child(id);

        //removing post
        databaseReference.removeValue();


        Toast.makeText(getApplicationContext(), "post Deleted", Toast.LENGTH_LONG).show();

        return true;
    }
}
