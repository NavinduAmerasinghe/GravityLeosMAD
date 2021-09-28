package com.example.gravityleosmad.Delivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gravityleosmad.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;

public class PickAndGoView extends AppCompatActivity {

    RecyclerView recycler;
    DatabaseReference database;
    PickAndGoAdapter adapter;
    ArrayList<PickAndGoModel> list;
    ArrayList<String> getPickAndGoId = new ArrayList<>();
    String PickAndGoId;
    private PickAndGoAdapter.RecyclerViewClickListner listner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_and_go_view);

        setOnClickListner();
        recycler = findViewById(R.id.recyclerView1);
        database = FirebaseDatabase.getInstance().getReference("PickAndGoModel");
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new PickAndGoAdapter(this,list,listner);
        recycler.setAdapter(adapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot dn : snapshot.getChildren()){
                    PickAndGoId = dn.getKey();
                    PickAndGoModel ev = dn.getValue(PickAndGoModel.class);
                    list.add(ev);
                    getPickAndGoId.add(PickAndGoId);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
    private void setOnClickListner() {
        listner = new PickAndGoAdapter.RecyclerViewClickListner() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), PickAndGoViewPage.class);

                intent.putExtra("PickAndGoId",getPickAndGoId.get(position));
                //pass the data to view page (display)
                intent.putExtra("Pick_Up_Date",list.get(position).getPick_Up_Date());
                intent.putExtra("Pick_Up_Time",list.get(position).getPick_Up_Time());
                intent.putExtra("Pick_Up_Location",list.get(position).getPick_Up_Location());
                intent.putExtra("Number",list.get(position).getNumber());


                startActivity(intent);
            }
        };

    }


    public void CashOnDeliveryViewPageToCreate(View view){
        Intent intent = new Intent(this, PickAndGoCreate.class);
        startActivity(intent);
    }
    public void PickAndGoViewPageToHome(View view){
        Intent intent = new Intent(this,DeliveryHome.class);
        startActivity(intent);
    }
}