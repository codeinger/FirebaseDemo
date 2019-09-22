package com.codeinger.firebasedemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ReadMiltiplePostActivity extends AppCompatActivity {

    private TextView textView;
    private List<Post> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_miltiple_post);

        textView = findViewById(R.id.text);

//        FirebaseDatabase.getInstance().getReference()
//                .child("Post")
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        String s = "";
//                        list = new ArrayList<>();
//                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
//                            s = s + "\n"+ "Title : "+ snapshot.child("title").getValue().toString()+"\n" +
//                                     "Description : "+  snapshot.child("description").getValue().toString()+"\n" +
//                                     "Author : "+  snapshot.child("author").getValue().toString()+"\n";
//
//                            Post post = snapshot.getValue(Post.class);
//                            list.add(post);
//                        }
//
//                        Log.i("Mytag", "onDataChange: "+list.toString());
//                        textView.setText(s);
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });

//        FirebaseDatabase.getInstance().getReference()
//                .child("Post")
//                .addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        String s = "";
//                        list = new ArrayList<>();
//                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
//                            s = s + "\n"+ "Title : "+ snapshot.child("title").getValue().toString()+"\n" +
//                                    "Description : "+  snapshot.child("description").getValue().toString()+"\n" +
//                                    "Author : "+  snapshot.child("author").getValue().toString()+"\n";
//
//                            Post post = snapshot.getValue(Post.class);
//                            list.add(post);
//                        }
//
//                        Log.i("Mytag", "onDataChange: "+list.toString());
//                        textView.setText(s);
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });



        FirebaseDatabase.getInstance().getReference()
                .child("Post")
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }
}
