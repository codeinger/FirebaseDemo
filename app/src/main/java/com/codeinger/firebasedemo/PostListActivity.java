package com.codeinger.firebasedemo;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class PostListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PostAdapter adapter;
    private FloatingActionButton add;
    private DatabaseReference post;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        post = FirebaseDatabase.getInstance().getReference().child("Post");


//        //Query query = post.orderByChild("title");
//
//
//        Query query = FirebaseDatabase.getInstance().getReference().child("Mobile");
//        query.orderByValue().addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                Log.i("dsjcbjhsbdch", "onChildAdded: "+dataSnapshot.toString());
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//
//        Query query1 = post.orderByKey();


        //select * from post
        //Query query = post;

        //select * from post limit 3
       // Query query = post.limitToFirst(3);

        //select * from post where author = 'Apple'
        //Query query = post.orderByChild("author").equalTo("Apple");

        //select * from post where like >= 300
       // Query query = post.orderByChild("like").startAt(300);

        //select * from post where like <= 700
       // Query query = post.orderByChild("like").endAt(700);

        //select * from post where like <= 700 limit 2
        //Query query = post.orderByChild("like").endAt(700).limitToFirst(2);

        //select * from post where like >= 300 AND like <= 700
       // Query query = post.orderByChild("like").startAt(300).endAt(700);

        //select * from post where like >= 300 AND like <= 700 limit 2
        //Query query = post.orderByChild("like").startAt(300).endAt(700).limitToFirst(2);

        //select * from post where title LIKE %A   //\uf8ff
        Query query = post.orderByChild("title").startAt("A").endAt("A\uf8ff");






        FirebaseRecyclerOptions<Post> options =
                new FirebaseRecyclerOptions.Builder<Post>()
                        .setQuery(query, Post.class)
                        .build();

        adapter = new PostAdapter(options,this);
        recyclerView.setAdapter(adapter);

        add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PostListActivity.this, AddPostActivity.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
