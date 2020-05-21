package com.example.pikr.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.pikr.R;
import com.example.pikr.adapters.MainViewPagerAdapter;

public class MainActivity extends AppCompatActivity {
    public static final int FEED_FRAGMENT_INDEX = 0;
    public static final int MY_ACTIVITY_FRAGMENT_INDEX = 1;

    private MainViewPagerAdapter mainViewPagerAdapter;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startMainFragmentView();
    }

    private void startMainFragmentView(){
        viewPager = findViewById(R.id.main_view_pager);
        mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager.setAdapter(mainViewPagerAdapter);
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

    public void onClickFeed(MenuItem item){
        viewPager.setCurrentItem(FEED_FRAGMENT_INDEX);
    }

    public void onClickMyActivity(MenuItem item) {
        viewPager.setCurrentItem(MY_ACTIVITY_FRAGMENT_INDEX);
    }
}
