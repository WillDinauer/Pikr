package com.example.pikr.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pikr.R;
import com.example.pikr.models.Login;

public class MyActivityFragment extends Fragment {
    private Login loginInfo;

    public MyActivityFragment(){
        // Default constructor
    }

    public static MyActivityFragment newInstance() {
        return new MyActivityFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginInfo = new Login(getActivity().getApplicationContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_activity, container, false);
        String nameText = "Hello, " + loginInfo.getName() + "!";
        ((TextView)view.findViewById(R.id.hello_text)).setText(nameText);

//        createEditProfileListener(view);

        return view;
    }

//    private void createEditProfileListener(View view) {
//        Button editProfileButton = view.findViewById(R.id.edit_profile_button);
//
//        editProfileButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // Open registration page and allow user to edit information
//            }
//        });
//
//    }
}
