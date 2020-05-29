package com.example.pikr.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.pikr.R;
import com.example.pikr.fragments.CreateFragment;
import com.example.pikr.models.Login;
import com.example.pikr.models.Post;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PastPostActivity extends AppCompatActivity {

    private Login loginInfo;
    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBar();
        setContentView(R.layout.activity_past_post);
        loginInfo = new Login(this.getApplicationContext());

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String emailKey = loginInfo.getEmail().replace(".", CreateFragment.PERIOD_REPLACEMENT_KEY);
        mRef = database.getReference(emailKey);
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Post Details");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_delete, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_delete:
                //SET THE POST DELETE VAR TO TRUE
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
