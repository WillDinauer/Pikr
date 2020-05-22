package com.example.pikr.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.pikr.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EntryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EntryFragment extends Fragment {
    private TextView mTitle, mDescription;
    private Button mPostButton, mPhotoButton;
    public EntryFragment() {
        // Required empty public constructor
    }

    public static EntryFragment newInstance() {
        return new EntryFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_entry, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTitle = view.findViewById(R.id.title_text);
        mDescription = view.findViewById(R.id.description_text);
        mPhotoButton = view.findViewById(R.id.add_photo_button);
        mPostButton = view.findViewById(R.id.save_post_button);

    }
}
