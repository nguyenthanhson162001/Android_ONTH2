package com.example.onth2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {
    private EditText edtTask;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        edtTask = findViewById(R.id.txtPlant);

        Button btnDe = findViewById(R.id.btnMDe);
        Button btnUp = findViewById(R.id.btnMUpdate);
        Button btnFi = findViewById(R.id.btnMFi);
        Button btnAdd = findViewById(R.id.button);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        DatabaseReference ref1 = database.getReference("users").
                child(mAuth.getCurrentUser().getUid());

        List<Task> list = new ArrayList<>();
        Adapter adapter = new Adapter(MainActivity.this,R.layout.adapter_item,list);
        listView.setAdapter(adapter);

        ref1.child("tasks").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot item : snapshot.getChildren()){
                    Task task = new Task(item.getKey(),item.child("task").getValue().toString(),item.child("status").getValue().toString());
                    list.add(task);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ref1.child("tasks").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot item : snapshot.getChildren()){
                    Task task = new Task(item.getKey(),item.child("task").getValue().toString(),item.child("status").getValue().toString());
                    list.add(task);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = edtTask.getText().toString();

                Date date =  new Date();
                Map<String,String> address = new HashMap<>();
                address.put("task",t);
                address.put("status","Unfinished");
                System.out.println(t);
                ref1.child("tasks").child(date.getTime()+"").setValue(address);
                System.out.println(mAuth.getCurrentUser().getUid());
                if (edtTask.length() > 0) {
                    edtTask.getText().clear();
                }
          
            }
        });

        AtomicInteger index = new AtomicInteger(-1);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index.set(position);
                edtTask.setText(list.get(position).getName());
            }
        });

        btnDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index.get()!=-1) {
                    ref1.child("tasks").child(list.get(index.get()).getId()).removeValue();
                }
            }
        });

        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index.get()!=-1) {
                    ref1.child("tasks").child(list.get(index.get()).getId()).child("task").setValue(edtTask.getText().toString());
                }
            }
        });
        btnFi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index.get()!=-1) {
                    ref1.child("tasks").child(list.get(index.get()).getId()).child("status").setValue("Finished");
                }
            }
        });
    }
}