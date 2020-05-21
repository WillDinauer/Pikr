package com.example.pikr.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pikr.R;

public class FeedFragment extends Fragment {
    private static final String PAGE_TYPE = "feed_page";

    public FeedFragment(){
        // Default constructor
    }

    public static FeedFragment newInstance(String position) {
        FeedFragment fragment = new FeedFragment();
        Bundle values = new Bundle();
        values.putString(PAGE_TYPE, position);
        fragment.setArguments(values);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);

        return view;
    }
}
