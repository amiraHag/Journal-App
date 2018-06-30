package com.example.android.journalapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class PostList extends RecyclerView.Adapter<PostList.ViewHolder> {
    //Constants to pass the post id, title, description to another activity

    public static final String POST_ID = "postid";
    public static final String POST_NAME = "postname";
    public static final String POST_BODY = "postbody";



    private Context context;
    private List<Post> posts;


    public PostList(Activity context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater
                        .from(context)
                        .inflate(R.layout.post_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Post post = posts.get(position);

        holder.textViewTitle.setText(post.getPostTitle());
        holder.textViewBody.setText(post.getPostDescription());


        holder.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getting the selected artist
                Post post = posts.get(position);

                //creating an intent
                Intent intent = new Intent(context, DetaildedPostActivity.class);

                //putting artist name and id to intent
                intent.putExtra(POST_ID, post.getPostId());
                intent.putExtra(POST_NAME, post.getPostTitle());
                intent.putExtra(POST_BODY, post.getPostDescription());

                //starting the activity with intent
                context.startActivity(intent);
            }
        });


    }



    @Override
    public int getItemCount() {
        return this.posts.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewBody;
        private View parentView;


        public ViewHolder(View view) {
            super(view);
            this.parentView = view;
            this.textViewTitle = (TextView) view
                    .findViewById(R.id.post_title);
            this.textViewBody = (TextView) view
                    .findViewById(R.id.post_body);

        }
    }
}