package com.example.gravityleosmad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class CashOnDeliveryCreate extends AppCompatActivity {

    EditText House_Address, Street, City, Phone_Number, Email;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cach_on_delivery_create);

        House_Address = (EditText) findViewById(R.id.addHouse_Address);
        Street = (EditText) findViewById(R.id.addStreet);
        City = (EditText) findViewById(R.id.addCity);
        Phone_Number = (EditText) findViewById(R.id.addPhone_Number);
        Email = (EditText) findViewById(R.id.addEmail);


        submit = (Button) findViewById(R.id.Addbutton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processinsert();
            }
        });
    }
    private void processinsert()
    {

        Map<String,Object> map=new HashMap<>();
        map.put("House_Address",House_Address.getText().toString());
        map.put("Street",Street.getText().toString());
        map.put("City",City.getText().toString());
        map.put("Phone_Number",Phone_Number.getText().toString());
        map.put("Email",Email.getText().toString());


        FirebaseDatabase.getInstance().getReference().child("CashOnDeliveryModel").push().setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {


            @Override
            public void onSuccess(Void aVoid) {
                House_Address.setText("");
                Street.setText("");
                City.setText("");
                Phone_Number.setText("");
                Email.setText("");


                Intent intent = new Intent(CashOnDeliveryCreate.this,CashOnDeliverView.class);
                Toast.makeText(getApplicationContext(),"Inserted Successfully",Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(getApplicationContext(),"Could not insert",Toast.LENGTH_LONG).show();
                    }
                });

    }
}