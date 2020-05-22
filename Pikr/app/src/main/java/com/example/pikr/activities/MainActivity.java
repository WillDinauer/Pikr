package com.example.pikr.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

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
        bottomNavigationView = findViewById(R.id.main_bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationListener);
        startMainFragmentView();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()){
                case R.id.start_activity_nav:
                    selectedFragment = new FeedFragment();
                    break;
                case R.id.entry_activity_nav:
                    selectedFragment = new EntryFragment();
                    break;
                case R.id.history_activity_nav:
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


    private void startMainFragmentView(){
//        viewPager = findViewById(R.id.main_view_pager);
//        mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), getLifecycle());
//        viewPager.setAdapter(mainViewPagerAdapter);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FeedFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

//    public void onClickFeed(MenuItem item){
//        viewPager.setCurrentItem(FEED_FRAGMENT_INDEX);
//    }

    public void onClickMyActivity(MenuItem item) {
//        viewPager.setCurrentItem(MY_ACTIVITY_FRAGMENT_INDEX);
    }
}
