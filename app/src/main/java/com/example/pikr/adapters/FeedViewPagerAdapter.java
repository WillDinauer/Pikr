package com.example.pikr.adapters;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.pikr.fragments.PostFragment;

import java.util.ArrayList;

public class FeedViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<PostFragment> postFragments = new ArrayList<>();

    public FeedViewPagerAdapter(@NonNull FragmentManager fragmentManager, Lifecycle lifecycle) {
        super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        Log.d("TEST", "feedViewPagerAdapter()");
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Log.d("TEST", "getItem()");
        return postFragments.get(position);
    }

    public void addFragment(PostFragment postFragment){
        postFragments.add(postFragment);
    }

    @Override
    public int getCount() {
        return postFragments.size();
    }
}
