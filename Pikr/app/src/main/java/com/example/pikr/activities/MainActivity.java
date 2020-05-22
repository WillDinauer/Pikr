package com.example.pikr.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.pikr.R;
import com.example.pikr.adapters.MainViewPagerAdapter;
import com.example.pikr.fragments.EntryFragment;
import com.example.pikr.fragments.FeedFragment;
import com.example.pikr.fragments.MyActivityFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    public static final int FEED_FRAGMENT_INDEX = 0;
    public static final int MY_ACTIVITY_FRAGMENT_INDEX = 1;

    private MainViewPagerAdapter mainViewPagerAdapter;
    private BottomNavigationView bottomNavigationView;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FeedFragment()).commit();
//        bottomNavigationView = findViewById(R.id.main_bottom_navigation);
        //bottomNavigationView.setOnNavigationItemSelectedListener(navigationListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()){
                case R.id.feed_activity_nav:
                    selectedFragment = new FeedFragment();
                    break;
                case R.id.new_post_nav:
                    selectedFragment = new EntryFragment();
                    break;
                case R.id.my_activity_nav:
                    selectedFragment = new MyActivityFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.edit_profile:
                return true;
            case R.id.add_friends:
                startActivity(new Intent(this, AddFriendsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Callback when feed pressed in bottom navigation
     */
    public void onClickFeed(MenuItem item){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FeedFragment()).commit();
    }

    public void onClickNewPost(MenuItem item){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new EntryFragment()).commit();
    }

    /**
     * Callback when My Activity pressed in bottom navigation
     */
    public void onClickMyActivity(MenuItem item) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MyActivityFragment()).commit();
    }
}
