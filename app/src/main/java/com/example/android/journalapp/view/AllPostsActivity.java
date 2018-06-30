package com.example.android.journalapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.android.journalapp.R;
import com.example.android.journalapp.model.Post;
import com.example.android.journalapp.viewholder.PostList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AllPostsActivity extends BaseActivity {


    RecyclerView recyclerViewPosts;


    //a list to store all the posts from firebase database
    List<Post> posts;


    // [START declare_database_ref]
    private DatabaseReference mDatabase;
    // [END declare_database_ref]

    private FloatingActionButton mAddPostButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_posts);


        // [START initialize_database_ref]
        mDatabase = FirebaseDatabase.getInstance().getReference("posts");
        // [END initialize_database_ref]

        //list to store posts
        posts = new ArrayList<>();

        recyclerViewPosts = (RecyclerView) findViewById(R.id.list_view_posts);
        recyclerViewPosts.setLayoutManager(new LinearLayoutManager(this));

        mAddPostButton = findViewById(R.id.button_add_new_post);

        mAddPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAddPostActivity();
            }
        });


    }

    public void startAddPostActivity() {
        finish();

        startActivity(new Intent(this, PostActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous posts list
                posts.clear();


                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting post
                    Post post = postSnapshot.getValue(Post.class);
                    //adding post to the list
                    posts.add(post);
                }

                //creating adapter
                PostList postAdapter = new PostList(AllPostsActivity.this, posts);
                //attaching adapter to the listview
                recyclerViewPosts.setAdapter(postAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
