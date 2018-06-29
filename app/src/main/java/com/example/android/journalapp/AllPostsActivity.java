package com.example.android.journalapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AllPostsActivity extends AppCompatActivity {

ListView listViewPosts;

    //a list to store all the artist from firebase database
    List<Post> posts;


    // [START declare_database_ref]
    private DatabaseReference mDatabase;
    // [END declare_database_ref]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_posts);


        // [START initialize_database_ref]
        mDatabase = FirebaseDatabase.getInstance().getReference("posts");
        // [END initialize_database_ref]

        //list to store posts
        posts = new ArrayList<>();

        listViewPosts = (ListView) findViewById(R.id.list_View_Posts);
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
                    //getting artist
                    Post post = postSnapshot.getValue(Post.class);
                    //adding artist to the list
                    posts.add(post);
                }

                //creating adapter
                PostList postAdapter = new PostList(AllPostsActivity.this, posts);
                //attaching adapter to the listview
                listViewPosts.setAdapter(postAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
