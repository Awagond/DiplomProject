package com.example.cabelpc;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import com.example.cabelpc.Prevalent.Prevalent;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CommentActivity extends AppCompatActivity {
    private ListView recyclerView;
    private Button addComBtn;
    private EditText commentField;
    private String name, commentRandomKey;
    private DatabaseReference ComRef;
    private ArrayAdapter<String> arratAdapter;
    private ArrayList<String> list_com = new ArrayList<>();
    private TextView chat_conversation;
    private String chat_msg,chat_user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        addComBtn = (Button) findViewById(R.id.send_button);
        commentField = (EditText) findViewById(R.id.commentField);
        recyclerView = (ListView) findViewById(R.id.listMessage);
        arratAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list_com);
        recyclerView.setAdapter(arratAdapter);
        ComRef = FirebaseDatabase.getInstance().getReference().child("Comment");
        name = Prevalent.currentOnlineUser.getName();
        chat_conversation = (TextView) findViewById(R.id.textView);

        chat_user_name = getIntent().getExtras().get("user_name").toString();
        chat_msg = getIntent().getExtras().get("comment_text").toString();


        ComRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Set<String> set = new HashSet<String>();
                Iterator i = dataSnapshot.getChildren().iterator();

                while (i.hasNext()) {
                    set.add(((DataSnapshot) i.next()).getKey());
                }

                list_com.clear();
                list_com.addAll(set);

                arratAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ComRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                append_chat_conversation(dataSnapshot);
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

    private void append_chat_conversation(DataSnapshot dataSnapshot) {
        Iterator i = dataSnapshot.getChildren().iterator();

        while (i.hasNext()) {

            chat_msg = (String) ((DataSnapshot) i.next()).getValue();
            chat_user_name = (String) ((DataSnapshot) i.next()).getValue();

            chat_conversation.append("\n" + chat_user_name + " : " + chat_msg + " \n");
        }
    }
}