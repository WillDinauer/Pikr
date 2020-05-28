package com.example.pikr.loaders;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.example.pikr.models.Post;

import java.util.ArrayList;

public class PastPostsLoader extends AsyncTaskLoader<ArrayList<Post>> {

    public PastPostsLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public ArrayList<Post> loadInBackground() {
        return null;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
