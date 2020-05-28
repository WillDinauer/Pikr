package com.example.pikr.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.pikr.Post;

import java.util.ArrayList;
import java.util.List;

public class ProfileAdapter extends ArrayAdapter<Post> {

    public ProfileAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Post> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
