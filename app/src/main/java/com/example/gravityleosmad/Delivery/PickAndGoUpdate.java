package com.example.gravityleosmad.Delivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gravityleosmad.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class PickAndGoUpdate extends AppCompatActivity {

    EditText Pick_Up_Date, Pick_Up_Time,  Pick_Up_Location, Number;
    String PickAndGoId;
    Button Update;
    String PickAndGo1 = "not set";
    String PickAndGo2 = "not set";
    String PickAndGo3 = "not set";
    String PickAndGo4 = "not set";
    Task<Void> db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_and_go_update);

        Pick_Up_Date=(EditText)findViewById(R.id.editDate);
        Pick_Up_Time =(EditText)findViewById(R.id.editTime);
        Pick_Up_Location=(EditText)findViewById(R.id.editLocation);
        Number =(EditText)findViewById(R.id.editNumber);


        Bundle extras = getIntent().getExtras();
        if(extras != null){
            PickAndGoId = extras.getString("PickAndGoId");
            PickAndGo1 = extras.getString("Pick_Up_Date");
            PickAndGo2 = extras.getString("Pick_Up_Time");
            PickAndGo3 = extras.getString("Pick_Up_Location");
            PickAndGo4 = extras.getString("Number");


        }

        Pick_Up_Date.setText(PickAndGo1);
        Pick_Up_Time.setText(PickAndGo2);
        Pick_Up_Location.setText(PickAndGo3);
        Number.setText(PickAndGo4);


        Update=(Button)findViewById(R.id.Updatebutton1);
        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PickAndGo1  = Pick_Up_Date.getText().toString();
                PickAndGo2  = Pick_Up_Time.getText().toString();
                PickAndGo3  = Pick_Up_Location.getText().toString();
                PickAndGo4  = Number.getText().toString();


                Map<String,Object> map = new HashMap<>();
                map.put("Pick_Up_Date",PickAndGo1);
                map.put("Pick_Up_Time",PickAndGo2);
                map.put("Pick_Up_Location",PickAndGo3);
                map.put("Number",PickAndGo4);



                db = FirebaseDatabase.getInstance().getReference("PickAndGoModel").child(PickAndGoId)
                        .updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(PickAndGoUpdate.this, "success", Toast.LENGTH_SHORT).show();
                                Intent in = new Intent(PickAndGoUpdate.this, PickAndGoView.class);
                                startActivity(in);
                            }
                        });

            }
        });

    }
}