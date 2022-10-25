package com.example.pikr.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.pikr.activities.PastPostActivity;
import com.example.pikr.models.Post;
import com.example.pikr.R;

import java.util.ArrayList;

public class ProfileAdapter extends ArrayAdapter<Post> {
    public static final String POST_ID_KEY = "id";
    private Context context;
    private ArrayList<Post> posts;

    public ProfileAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Post> objects) {
        super(context, resource, objects);
        this.context = context;
        posts = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Post post = posts.get(position);
        convertView = LayoutInflater.from(context).inflate(R.layout.post_entry, parent, false);
        TextView title = convertView.findViewById(R.id.entry_title);
        TextView time = convertView.findViewById(R.id.entry_time);
        TextView votes = convertView.findViewById(R.id.entry_votes);
        title.setText(post.getTitle());
        time.setText(post.getDatetime());
        votes.setText("Votes: 0");
        final View finalConvertView = convertView;
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PastPostActivity.class);
                intent.putExtra(POST_ID_KEY, post.getId());
                finalConvertView.getContext().startActivity(intent);
            }
        });
        return convertView;
    }
}
