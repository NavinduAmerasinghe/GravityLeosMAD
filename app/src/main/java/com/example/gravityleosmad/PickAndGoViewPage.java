package com.example.gravityleosmad;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;

public class PickAndGoViewPage extends AppCompatActivity {

    ImageButton EditButton, DeleteButton;
    String PickAndGoId;
    String PickAndGo1 = "not set";
    String PickAndGo2 = "not set";
    String PickAndGo3 = "not set";
    String PickAndGo4 = "not set";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_and_go_view_page);

        EditButton = (ImageButton) findViewById(R.id.editButton1);
        DeleteButton = (ImageButton) findViewById(R.id.deleButton1);

        TextView Pick_Up_Date = findViewById(R.id.view11);
        TextView Pick_Up_Time = findViewById(R.id.view22);
        TextView Pick_Up_Location = findViewById(R.id.view33);
        TextView Number = findViewById(R.id.view44);


        EditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PickAndGoViewPage.this, PickAndGoUpdate.class);

                intent.putExtra("PickAndGoId",PickAndGoId);
                intent.putExtra("Pick_Up_Date",PickAndGo1);
                intent.putExtra("Pick_Up_Time",PickAndGo2);
                intent.putExtra("Pick_Up_Location",PickAndGo3);
                intent.putExtra("Number",PickAndGo4);


                startActivity(intent);
            }
        });


        DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference("PickAndGoModel").child(PickAndGoId).removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable @org.jetbrains.annotations.Nullable DatabaseError error, @NonNull @NotNull DatabaseReference ref) {
                        Toast.makeText(PickAndGoViewPage.this, "deleted succesfully", Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(PickAndGoViewPage.this, PickAndGoView.class);
                        startActivity(in);
                    }
                });
            }
        });




        Bundle extras = getIntent().getExtras();
        if (extras != null) {
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

    }
    public void PickAndGoViewPageToPickAndGoView(View view){
        Intent intent = new Intent(this,PickAndGoView.class);
        startActivity(intent);
    }
}
