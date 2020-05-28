package com.example.pikr.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import com.example.pikr.Picture;
import com.example.pikr.Post;
import com.example.pikr.R;
import com.example.pikr.adapters.ProfileAdapter;
import com.example.pikr.loaders.PastPostsLoader;
import com.example.pikr.models.Login;

import java.util.ArrayList;
import java.util.Objects;

public class MyActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<Post>>{
    private static final int LOAD_POSTS_ID = 1;
    private Login loginInfo;
    private ListView mListView;
    private ProfileAdapter mAdapter;

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListView = view.findViewById(R.id.profile_list_view);
        LoaderManager.getInstance(this).initLoader(LOAD_POSTS_ID, null, this);
    }

    @NonNull
    @Override
    public Loader<ArrayList<Post>> onCreateLoader(int id, @Nullable Bundle args) {
        if (id == LOAD_POSTS_ID)
            return new PastPostsLoader(requireActivity());
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Post>> loader, ArrayList<Post> data) {
        if (loader.getId() == LOAD_POSTS_ID && data.size()>0){
            mAdapter = new ProfileAdapter(requireActivity(), 0, data);
            mListView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Post>> loader) {
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
