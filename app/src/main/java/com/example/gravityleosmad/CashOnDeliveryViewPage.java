package com.example.gravityleosmad;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;

public class CashOnDeliveryViewPage extends AppCompatActivity {

    ImageButton EditButton, DeleteButton;
    String CashOnDeliveryId;
    String cashOnDelivery1 = "not set";
    String cashOnDelivery2 = "not set";
    String cashOnDelivery3 = "not set";
    String cashOnDelivery4 = "not set";
    String cashOnDelivery5 = "not set";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_on_delivery_view_page);

        EditButton = (ImageButton) findViewById(R.id.editButton);
        DeleteButton = (ImageButton) findViewById(R.id.deleButton);

        TextView House_Address = findViewById(R.id.view1);
        TextView Street = findViewById(R.id.view2);
        TextView City = findViewById(R.id.view3);
        TextView Phone_Number = findViewById(R.id.view4);
        TextView Email = findViewById(R.id.view5);

        EditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CashOnDeliveryViewPage.this,CashOnDeliveryUpdate.class);

                intent.putExtra("CashOnDeliveryId",CashOnDeliveryId);
                intent.putExtra("House_Address",cashOnDelivery1);
                intent.putExtra("Street",cashOnDelivery2);
                intent.putExtra("City",cashOnDelivery3);
                intent.putExtra("Phone_Number",cashOnDelivery4);
                intent.putExtra("Email",cashOnDelivery5);

                startActivity(intent);
            }
        });


        DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference("CashOnDeliveryModel").child(CashOnDeliveryId).removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable @org.jetbrains.annotations.Nullable DatabaseError error, @NonNull @NotNull DatabaseReference ref) {
                        Toast.makeText(CashOnDeliveryViewPage.this, "deleted succesfully", Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(CashOnDeliveryViewPage.this,CashOnDeliverView.class);
                        startActivity(in);
                    }
                });
            }
        });




        Bundle extras = getIntent().getExtras();
        if (extras != null) {
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

    }
}
