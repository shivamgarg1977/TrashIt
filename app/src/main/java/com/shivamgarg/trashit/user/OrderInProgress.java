package com.shivamgarg.trashit.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shivamgarg.trashit.R;
import com.shivamgarg.trashit.helperClasses.userAdapter.ProgressAdapterClass;
import com.shivamgarg.trashit.helperClasses.userAdapter.ProgressDataClass;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class OrderInProgress extends AppCompatActivity {
    private RecyclerView recyclerView;
    List<ProgressDataClass> userDataOrder;
    private ProgressAdapterClass madapterOrder;
    private ProgressAdapterClass.ProgressCardListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_in_progress);
        initOrderInProgress();

        recyclerView=(RecyclerView) findViewById(R.id.order_in_progress_recycler);

        LinearLayoutManager layoutManagerFeaturedRest = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManagerFeaturedRest);

        madapterOrder =new ProgressAdapterClass(userDataOrder);
        recyclerView.setAdapter(madapterOrder);
        


    }

    private void initOrderInProgress() {
        userDataOrder=new ArrayList<>();
        userDataOrder.add(0,new ProgressDataClass("on 05 May 2021 at 19:43 ","12kgs"));
        userDataOrder.add(0,new ProgressDataClass("on 05 June 2021 at 19:43 ","12kgs"));
        userDataOrder.add(0,new ProgressDataClass("on 05 July 2021 at 19:43 ","12kgs"));
        userDataOrder.add(0,new ProgressDataClass("on 05 August 2021 at 19:43 ","12kgs"));

    }


}