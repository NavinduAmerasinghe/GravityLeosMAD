package com.example.gravityleosmad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class CashOnDeliveryUpdate extends AppCompatActivity {

    EditText House_Address, Street, City, Phone_Number, Email;
    String CashOnDeliveryId;
    Button Update;
    String cashOnDelivery1 = "not set";
    String cashOnDelivery2 = "not set";
    String cashOnDelivery3 = "not set";
    String cashOnDelivery4 = "not set";
    String cashOnDelivery5 = "not set";
    Task<Void> db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_on_delivery_update);

        House_Address=(EditText)findViewById(R.id.editHouse_Address);
        Street =(EditText)findViewById(R.id.editStreet);
        City=(EditText)findViewById(R.id.editCity);
        Phone_Number =(EditText)findViewById(R.id.editPhone_Number);
        Email=(EditText)findViewById(R.id.editEmail);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            CashOnDeliveryId = extras.getString("CashOnDeliveryId");
            cashOnDelivery1 = extras.getString("House_Address");
            cashOnDelivery2 = extras.getString("Street");
            cashOnDelivery3 = extras.getString("City");
            cashOnDelivery4 = extras.getString("Phone_Number");
            cashOnDelivery5 = extras.getString("Email");

        }

        House_Address.setText(cashOnDelivery1);
        Street.setText(cashOnDelivery2);
        City.setText(cashOnDelivery3);
        Phone_Number.setText(cashOnDelivery4);
        Email.setText(cashOnDelivery5);

        Update=(Button)findViewById(R.id.Updatebutton);
        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cashOnDelivery1  = House_Address.getText().toString();
                cashOnDelivery2  = Street.getText().toString();
                cashOnDelivery3  = City.getText().toString();
                cashOnDelivery4  = Phone_Number.getText().toString();
                cashOnDelivery5  = Email.getText().toString();

                Map<String,Object> map = new HashMap<>();
                map.put("House_Address",cashOnDelivery1);
                map.put("Street",cashOnDelivery2);
                map.put("City",cashOnDelivery3);
                map.put("Phone_Number",cashOnDelivery4);
                map.put("Email",cashOnDelivery5);


                db = FirebaseDatabase.getInstance().getReference("CashOnDeliveryModel").child(CashOnDeliveryId)
                        .updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(CashOnDeliveryUpdate.this, "success", Toast.LENGTH_SHORT).show();
                                Intent in = new Intent(CashOnDeliveryUpdate.this,CashOnDeliverView.class);
                                startActivity(in);
                            }
                        });

            }
        });

    }
}