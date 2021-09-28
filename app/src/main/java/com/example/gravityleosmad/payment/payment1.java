package com.example.gravityleosmad.payment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gravityleosmad.R;

public class payment1 extends AppCompatActivity {
    Button btn_proceed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment1);
        btn_proceed=findViewById(R.id.btn_proceed);
    }
    //navigate to payment2
    public void openSecond(View view){
        Intent intent=new Intent(this , payment2.class);

        //String ok=btn_proceed.getText().toString();

        //intent.putExtra("proceed",ok);

        startActivity(intent);
    }
}