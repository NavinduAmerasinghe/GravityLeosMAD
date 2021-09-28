package com.example.gravityleosmad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DeliveryHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_home);
    }

    public void HometoCOD(View view){
        Intent intent = new Intent(this,CashOnDeliverView.class);
        startActivity(intent);
    }
    public void HometoPAG(View view){
        Intent intent = new Intent(this,PickAndGoView.class);
        startActivity(intent);
    }
}