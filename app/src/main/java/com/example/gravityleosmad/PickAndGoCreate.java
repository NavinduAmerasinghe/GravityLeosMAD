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

public class PickAndGoCreate extends AppCompatActivity {

    EditText Pick_Up_Date, Pick_Up_Time, Pick_Up_Location, Number;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_and_go_create);

        Pick_Up_Date = (EditText) findViewById(R.id.addDate);
        Pick_Up_Time = (EditText) findViewById(R.id.addTime);
        Pick_Up_Location = (EditText) findViewById(R.id.addLocation);
        Number = (EditText) findViewById(R.id.addNumber);



        submit = (Button) findViewById(R.id.Addbutton1);
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
        map.put("Pick_Up_Date",Pick_Up_Date.getText().toString());
        map.put("Pick_Up_Time",Pick_Up_Time.getText().toString());
        map.put("Pick_Up_Location",Pick_Up_Location.getText().toString());
        map.put("Number",Number.getText().toString());



        FirebaseDatabase.getInstance().getReference().child("PickAndGoModel").push().setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {


            @Override
            public void onSuccess(Void aVoid) {
                Pick_Up_Date.setText("");
                Pick_Up_Time.setText("");
                Pick_Up_Location.setText("");
                Number.setText("");



                Intent intent = new Intent(PickAndGoCreate.this, PickAndGoView.class);
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