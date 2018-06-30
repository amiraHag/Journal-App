package com.example.android.journalapp.model;

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

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public void setPostDescription(String postDescription) {
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
