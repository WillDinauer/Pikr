package com.example.pikr.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.pikr.activities.MainActivity;
import com.example.pikr.fragments.FeedFragment;
import com.example.pikr.fragments.MyActivityFragment;

public class MainViewPagerAdapter extends FragmentStateAdapter {
    private static final int NUM_MAIN_FRAGMENTS = 2;

    public MainViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == MainActivity.MY_ACTIVITY_FRAGMENT_INDEX){
            return MyActivityFragment.newInstance(String.valueOf(position));
        }
        else{
            return FeedFragment.newInstance(String.valueOf(position));
        }
    }

    @Override
    public int getItemCount() {
        return NUM_MAIN_FRAGMENTS;
    }
}
