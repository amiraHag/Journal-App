package com.example.android.journalapp;

public class Post {

   private String postId;
   private String postTitle;
   private String postDescription;


    public Post() {
        // Default constructor required to create the value
    }

    public Post(String postId, String postTitle, String postDescription) {
        this.postId = postId;
        this.postTitle = postTitle;
        this.postDescription = postDescription;
    }

    public String getPostId() {
        return postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public String getPostDescription() {
        return postDescription;
    }
}
