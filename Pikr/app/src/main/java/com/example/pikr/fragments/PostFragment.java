package com.example.pikr.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pikr.R;
import com.example.pikr.activities.MainActivity;

public class PostFragment extends Fragment {
    ImageButton imageButton;

    public PostFragment(){
        // Default constructor
    }

    public static PostFragment newInstance(String position) {
        Log.d("TEST", "this is a new feed fragment");
        return new PostFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed_post, container, false);
        Log.d("TEST", "onCreateView()");

        imageButton = view.findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VotingFragment.newInstance();
            }
        });
        return view;
    }
}
