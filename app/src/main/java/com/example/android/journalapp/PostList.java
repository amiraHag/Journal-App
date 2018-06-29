package com.example.android.journalapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class PostList extends ArrayAdapter<Post> {


    private Activity context;
    private List<Post> posts;



    public PostList(Activity context, List<Post> posts) {
        super(context, R.layout.post_item, posts);
        this.context = context;
        this.posts = posts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.post_item, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.post_title);
        TextView textViewGenre = (TextView) listViewItem.findViewById(R.id.post_body);

        Post post = posts.get(position);
        textViewName.setText(post.getPostTitle());
        textViewGenre.setText(post.getPostDescription());

        return listViewItem;
    }

}
