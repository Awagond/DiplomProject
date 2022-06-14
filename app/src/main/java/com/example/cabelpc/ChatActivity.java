package com.example.cabelpc;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cabelpc.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ChatActivity extends AppCompatActivity {
    private ListView recyclerView;
    private Button addBtn;
    private TextView messageText, messageUser;
    private EditText messageField;
    private String name;
    private DatabaseReference ChatsRef;
    private ArrayAdapter<String> arratAdapter;
    private ArrayList<String> list_rooms = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        addBtn = (Button) findViewById(R.id.send_button);

        messageField = (EditText) findViewById(R.id.messageField);

        messageText = (TextView) findViewById(R.id.message_content);
        messageUser = (TextView) findViewById(R.id.message_username);

        recyclerView = (ListView) findViewById(R.id.listMessage);

        arratAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list_rooms);

        recyclerView.setAdapter(arratAdapter);

        ChatsRef = FirebaseDatabase.getInstance().getReference().child("Message");

        name = Prevalent.currentOnlineUser.getName();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(messageField.getText().toString(), "");
                ChatsRef.updateChildren(map);
            }
        });

        ChatsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Set<String> set = new HashSet<String>();
                Iterator i = dataSnapshot.getChildren().iterator();

                while (i.hasNext()) {
                    set.add(((DataSnapshot)i.next()).getKey());
                }

                list_rooms.clear();
                list_rooms.addAll(set);

                arratAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplication(), ChatRoom.class);
                intent.putExtra("room_name",((TextView)view).getText().toString());
                intent.putExtra("user_name", name);
                startActivity(intent);
            }
        });
    }

}

