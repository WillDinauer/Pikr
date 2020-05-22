package com.example.pikr.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pikr.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EntryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EntryFragment extends Fragment {
    public EntryFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static EntryFragment newInstance() {
        return new EntryFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_entry, container, false);
    }
}
