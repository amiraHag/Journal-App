package com.example.android.journalapp.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.journalapp.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {


            case R.id.menu_item_profile:
                Intent launchProfileActivityIntent = new Intent(this, AccountActivity.class);
                startActivityForResult(launchProfileActivityIntent, 0);

                return true;

            case R.id.menu_item_allposts:
                Intent launchAllPostsActivityIntent = new Intent(this, AllPostsActivity.class);
                startActivityForResult(launchAllPostsActivityIntent, 0);

                return true;

            case R.id.menu_item_add_post:
                Intent launchAddPostActivityIntent = new Intent(this, PostActivity.class);
                startActivityForResult(launchAddPostActivityIntent, 0);

                return true;

            default:
                return true;
        }

    }
}
