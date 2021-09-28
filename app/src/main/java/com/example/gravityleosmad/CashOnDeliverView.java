package com.example.gravityleosmad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;

public class CashOnDeliverView extends AppCompatActivity {

    RecyclerView recycler;
    DatabaseReference database;
    CashOnDeliveryAdapter adapter;
    ArrayList<CashOnDeliveryModel> list;
    ArrayList<String> getCashOnDeliveryId = new ArrayList<>();
    String CashOnDeliveryId;
    private CashOnDeliveryAdapter.RecyclerViewClickListner listner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_on_deliver_view);

        setOnClickListner();
        recycler = findViewById(R.id.recyclerView);
        database = FirebaseDatabase.getInstance().getReference("CashOnDeliveryModel");
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new CashOnDeliveryAdapter(this,list,listner);
        recycler.setAdapter(adapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot dn : snapshot.getChildren()){
                    CashOnDeliveryId = dn.getKey();
                    CashOnDeliveryModel ev = dn.getValue(CashOnDeliveryModel.class);
                    list.add(ev);
                    getCashOnDeliveryId.add(CashOnDeliveryId);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
    private void setOnClickListner() {
        listner = new CashOnDeliveryAdapter.RecyclerViewClickListner() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), CashOnDeliveryViewPage.class);

                intent.putExtra("CashOnDeliveryId",getCashOnDeliveryId.get(position));
                //pass the data to view page (display)
                intent.putExtra("House_Address",list.get(position).getHouse_Address());
                intent.putExtra("Street",list.get(position).getStreet());
                intent.putExtra("City",list.get(position).getCity());
                intent.putExtra("Phone_Number",list.get(position).getPhone_Number());
                intent.putExtra("Email",list.get(position).getEmail());

                startActivity(intent);
            }
        };

    }


    public void CashOnDeliveryViewPageToCreate(View view){
        Intent intent = new Intent(this,CashOnDeliveryCreate.class);
        startActivity(intent);
    }

    public void CashOnDeliveryViewPageToHome(View view){
        Intent intent = new Intent(this,DeliveryHome.class);
        startActivity(intent);
    }
}