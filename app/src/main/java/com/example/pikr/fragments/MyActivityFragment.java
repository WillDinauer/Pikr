package com.example.pikr.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import com.example.pikr.activities.RegisterActivity;
import com.example.pikr.models.Post;
import com.example.pikr.R;
import com.example.pikr.adapters.ProfileAdapter;
import com.example.pikr.loaders.PastPostsLoader;
import com.example.pikr.models.Login;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MyActivityFragment extends Fragment {
    private static final int LOAD_POSTS_ID = 1;
    private Login loginInfo;
    private ListView mListView;
    private ProfileAdapter mAdapter;
    private DatabaseReference mRef;
    private ArrayList<Post> userPosts;

    public MyActivityFragment() {
        // Default constructor
    }

    public static MyActivityFragment newInstance() {
        return new MyActivityFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginInfo = new Login(getActivity().getApplicationContext());
        userPosts = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String emailKey = loginInfo.getEmail().replace(".", CreateFragment.PERIOD_REPLACEMENT_KEY);
        mRef = database.getReference(emailKey);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_activity, container, false);
        String nameText = "Hello, " + loginInfo.getName() + "!";
        ((TextView) view.findViewById(R.id.hello_text)).setText(nameText);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mListView = view.findViewById(R.id.profile_list_view);
        mAdapter = new ProfileAdapter(requireActivity(), 0, userPosts);
        mListView.setAdapter(mAdapter);
        setupDatabaseListener();
    }

    private void setupDatabaseListener() {
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("TEST", "onDataChange");
                mAdapter.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Post post = postSnapshot.getValue(Post.class);
                    if (post != null && !post.getDeleted()) {
                        mAdapter.add(post);
                    }
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("TEST", "error loading profile");
                Toast.makeText(getActivity(), "Error loading profile",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}
