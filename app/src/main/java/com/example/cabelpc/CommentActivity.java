package com.example.cabelpc;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.example.cabelpc.Model.Comment;
import com.example.cabelpc.Prevalent.Prevalent;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class CommentActivity extends AppCompatActivity {
    private ListView recyclerView;
    private Button sendCom;
    private EditText comInput;
    private TextView com_conversation;
    private String name, commentRandomKey;
    private DatabaseReference ComRef;
    private String temp_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        sendCom = findViewById(R.id.btn_send_comment);
        comInput = findViewById(R.id.com_input);
        com_conversation = findViewById(R.id.textViewCom);
        ComRef = FirebaseDatabase.getInstance().getReference().child("Comment");
        name = Prevalent.currentOnlineUser.getName();

        sendCom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,Object> map = new HashMap<String, Object>();
                temp_key = ComRef.push().getKey();
                ComRef.updateChildren(map);

                DatabaseReference message_root = ComRef.child(temp_key);
                Map<String,Object> map2 = new HashMap<String, Object>();
                map2.put("name",name);
                map2.put("msg",comInput.getText().toString());

                comInput.setText("");

                message_root.updateChildren(map2);
            }
        });
        ComRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                getComment(dataSnapshot);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                getComment(dataSnapshot);
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
    private String comText,comName;
    private void getComment(DataSnapshot dataSnapshot) {
        Iterator i = dataSnapshot.getChildren().iterator();

        while (i.hasNext()){

            comText = (String) ((DataSnapshot)i.next()).getValue();
            comName = (String) ((DataSnapshot)i.next()).getValue();

            com_conversation.append(comName +" : "+comText +" \n");
        }
    }
}