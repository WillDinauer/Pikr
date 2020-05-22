package com.example.pikr.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.pikr.R;
import com.example.pikr.adapters.FeedViewPagerAdapter;

public class FeedFragment extends Fragment {

    public FeedFragment(){
        // Default constructor
    }

    public static FeedFragment newInstance() {
        return new FeedFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        setFeedButtonListeners(view);
        return view;
    }

    private void setFeedButtonListeners(View view){
        final Button publicFeed = view.findViewById(R.id.public_button);
        final Button privateFeed = view.findViewById(R.id.private_button);
        final String selectedButtonColor = "#98FB98";
        publicFeed.setBackgroundColor(Color.parseColor(selectedButtonColor));

        publicFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                publicFeed.setBackgroundColor(Color.parseColor(selectedButtonColor));
                privateFeed.setBackgroundColor(Color.WHITE);
            }
        });
        privateFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                privateFeed.setBackgroundColor(Color.parseColor(selectedButtonColor));
                publicFeed.setBackgroundColor(Color.WHITE);
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        FeedViewPagerAdapter feedViewPagerAdapter = new FeedViewPagerAdapter(getChildFragmentManager(), getLifecycle());
        Log.d("TEST", "onViewCreated");
        feedViewPagerAdapter.addFragment(new PostFragment());
        feedViewPagerAdapter.addFragment(new PostFragment());
        ViewPager mViewPager = view.findViewById(R.id.feed_view_pager);
        mViewPager.setAdapter(feedViewPagerAdapter);
        super.onViewCreated(view, savedInstanceState);
    }
}
