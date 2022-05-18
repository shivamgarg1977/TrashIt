package com.shivamgarg.trashit.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shivamgarg.trashit.R;
import com.shivamgarg.trashit.helperClasses.userAdapter.ProgressAdapterClass;
import com.shivamgarg.trashit.helperClasses.userAdapter.ProgressDataClass;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class OrderInProgress extends AppCompatActivity {
    DatabaseReference database;
    private RecyclerView recyclerView;
    List<ProgressDataClass> userDataOrder;
    private ProgressAdapterClass madapterOrder;
    private ProgressAdapterClass.ProgressCardListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_in_progress);
        recyclerView=(RecyclerView) findViewById(R.id.order_in_progress_recycler);
        database=FirebaseDatabase.getInstance().getReference("Users").child(getIntent().getStringExtra("Uid")).child("Orders");
        LinearLayoutManager layoutManagerFeaturedRest = new LinearLayoutManager(this);
//        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManagerFeaturedRest);
        userDataOrder=new ArrayList<>();
        madapterOrder =new ProgressAdapterClass(userDataOrder);
        recyclerView.setAdapter(madapterOrder);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                   ProgressDataClass progressDataClass= dataSnapshot.getValue(ProgressDataClass.class);
                   userDataOrder.add(0,progressDataClass);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        


    }



}

