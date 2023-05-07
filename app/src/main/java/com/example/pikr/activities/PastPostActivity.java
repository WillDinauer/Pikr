package com.example.pikr.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.pikr.R;
import com.example.pikr.adapters.ProfileAdapter;
import com.example.pikr.fragments.CreateFragment;
import com.example.pikr.models.Login;
import com.example.pikr.models.Post;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class PastPostActivity extends AppCompatActivity {

    private TextView title, description;
    private Post post;
    private Query query;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBar();
        setContentView(R.layout.activity_past_post);
        Login loginInfo = new Login(this.getApplicationContext());
        title = findViewById(R.id.history_title_text);
        description = findViewById(R.id.history_description_text);

        Log.d("CHECK", "id passed: " + getIntent().getExtras().getInt(ProfileAdapter.POST_ID_KEY));
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String emailKey = loginInfo.getEmail().replace(".", CreateFragment.PERIOD_REPLACEMENT_KEY);
        ref = database.getReference(emailKey);
        query = ref.orderByChild("id")
                .equalTo(Objects.requireNonNull(getIntent().getExtras()).getInt(ProfileAdapter.POST_ID_KEY));
        query.addListenerForSingleValueEvent(valueEventListener);
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()){
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    post = snapshot.getValue(Post.class);
                    updateFields();
                }
            }
        }
        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    ValueEventListener deleteEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()){
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    snapshot.getRef().removeValue();
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    private void updateFields(){
        if (post!=null) {
            title.setText(post.getTitle());
            description.setText(post.getDescription());
        }
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
                deletePost();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void deletePost(){
        ref.child(String.valueOf(post.getId())).child("deleted").setValue(true);
    }
}
