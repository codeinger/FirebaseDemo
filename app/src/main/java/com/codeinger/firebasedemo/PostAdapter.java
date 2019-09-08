package com.codeinger.firebasedemo;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseError;

public class PostAdapter extends FirebaseRecyclerAdapter<Post, PostAdapter.PastViewHolder> {


    public PostAdapter(@NonNull FirebaseRecyclerOptions<Post> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull PastViewHolder holder, int i, @NonNull Post post) {
        holder.title.setText(post.getTitle());
        holder.description.setText(post.getDescription());
        holder.author.setText(post.getAuthor());
    }

    @NonNull
    @Override
    public PastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post, parent, false);
        return new PastViewHolder(view);
    }

    class PastViewHolder extends RecyclerView.ViewHolder{

        TextView title,description,author;

        public PastViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            author = itemView.findViewById(R.id.author);
        }
    }
}
