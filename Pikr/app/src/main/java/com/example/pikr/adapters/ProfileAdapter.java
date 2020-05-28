package com.example.pikr.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.pikr.Post;
import com.example.pikr.R;

import java.util.ArrayList;
import java.util.List;

public class ProfileAdapter extends ArrayAdapter<Post> {

    private Context context;

    public ProfileAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Post> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.post_entry, parent, false);
        TextView title = convertView.findViewById(R.id.entry_title);
        TextView time = convertView.findViewById(R.id.entry_time);
        TextView votes = convertView.findViewById(R.id.entry_votes);
        title.setText("Title");
        time.setText("12:12");
        votes.setText("Votes: 0");
        return convertView;
    }
}
