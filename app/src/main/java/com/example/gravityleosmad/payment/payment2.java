package com.example.gravityleosmad.payment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

//import android.support.v7.app.AppCompatActivity;

import java.util.Calendar;

import com.example.gravityleosmad.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class payment2 extends AppCompatActivity {
    EditText edt;
    DatePickerDialog datePickerDialog;

    private EditText et_cvc;
    private String blockCharacterSet = "~#^|$%&*!";

    EditText et_cname,et_cno,et_country;

    Button btn_submit,btn_update,btn_delete,btn_retrieve;
    FloatingActionButton fbtn_back;
    //database connection
    DatabaseReference dbRef;
    DatabaseReference readRef;
    DatabaseReference delRef;
    DatabaseReference upRef;
    Payment pym;
    //clear user input
    private void clearControls(){
        et_cname.setText("");
        et_cno.setText("");
        et_country.setText("");
        et_cvc.setText("");
        edt.setText("");

    }


    private InputFilter filter = new InputFilter() {

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

            if (source != null && blockCharacterSet.contains(("" + source))) {
                return "";
            }
            return null;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment2);
        fbtn_back=findViewById(R.id.fbtn_back);
        //Intent intent=getIntent();
        et_cname=findViewById(R.id.et_cname);
        et_cno=findViewById(R.id.et_cno);
        et_country=findViewById(R.id.et_country);
        //et_cvc=findViewById(R.id.et_cvc);
        edt=findViewById(R.id.edt);
        et_cvc = (EditText) findViewById(R.id.et_cvc);
        et_cvc.setFilters(new InputFilter[] { filter });

        btn_submit=findViewById(R.id.btn_submit);
        btn_update=findViewById(R.id.btn_update);
        btn_delete=findViewById(R.id.btn_delete);
        btn_retrieve=findViewById(R.id.btn_retrieve);

        pym=new Payment();

        //insert btn(submit)
        dbRef= FirebaseDatabase.getInstance().getReference().child("Payment");
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //checks if fields are empty and displays toast
                try {
                    if (TextUtils.isEmpty(et_cname.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Enter card holder's name",Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(et_cno.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Enter credit card number",Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(et_country.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Enter country",Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(et_cvc.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Enter cvc/cvv",Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(edt.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Enter expiry date",Toast.LENGTH_SHORT).show();
                    else{
                        //take input from user and assign them to instance pym of Payment
                        pym.setEt_cname(et_cname.getText().toString().trim());
                        pym.setEt_cno(et_cno.getText().toString().trim());
                        pym.setEt_country(et_country.getText().toString().trim());
                        pym.setEt_cvc(et_cvc.getText().toString().trim());
                        pym.setEdt(edt.getText().toString().trim());


                        //insert to database
                        //dbRef.push().setValue(pym);
                        dbRef.child(pym.getEt_cno()).setValue(pym);
                        //feedback to user via toast
                        Toast.makeText(getApplicationContext(),"data saved successfully",Toast.LENGTH_SHORT).show();
                        clearControls();
                    }
                }
                catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"invalid credit card no",Toast.LENGTH_SHORT).show();
                }
            }
        });
        //pick an id from db
//        btn_retrieve.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//               // final String uid = "-MkTgbyQ6ZzDqnk3gcNB";
//
//                DatabaseReference readRef =  FirebaseDatabase.getInstance().getReference().child("Payment").child("2222");
//                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                    //public void onDataChange(DataSnapshot snapshot) {
//
//                        if(snapshot.hasChildren()) {
//                            et_cname.setText(snapshot.child("et_cname").getValue().toString());
//                            et_cno.setText(snapshot.child("et_cno").getValue().toString());
//                            et_country.setText(snapshot.child("et_country").getValue().toString());
//                            et_cvc.setText(snapshot.child("et_cvc").getValue().toString());
//
//                        }else{
//                            Toast.makeText(getApplicationContext(), "No source to display", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull  DatabaseError error) {
//
//                    }
//                });
//               // Toast.makeText(getApplicationContext(), "No source to display", Toast.LENGTH_SHORT).show();
//            }
//        });

        //delete
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Payment");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange( DataSnapshot snapshot) {
                        if (snapshot.hasChild(et_cno.getText().toString())){
                            dbRef=FirebaseDatabase.getInstance().getReference().child("Payment").child(et_cno.getText().toString());
                            dbRef.getRef().removeValue();
                            clearControls();
                            Toast.makeText(getApplicationContext(),"Data deleted successfully",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"No source to delete",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        //calender
        edt=findViewById(R.id.edt);
        Calendar calendar=Calendar.getInstance();
        final int day=calendar.get(Calendar.DAY_OF_MONTH);
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        edt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog=new DatePickerDialog(payment2.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edt.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },year,month,day);
                datePickerDialog.show();
            }

        });


        //update
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference upRef=FirebaseDatabase.getInstance().getReference().child("Payment");
                upRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull  DataSnapshot snapshot) {
                        if (snapshot.hasChild(et_cno.getText().toString())){
                            try {
                                pym.setEt_cname(et_cname.getText().toString().trim());
                                pym.setEt_cno(et_cno.getText().toString().trim());
                                pym.setEt_country(et_country.getText().toString().trim());
                                pym.setEt_cvc(et_cvc.getText().toString().trim());
                                pym.setEdt(edt.getText().toString().trim());

                                dbRef=FirebaseDatabase.getInstance().getReference().child("Payment").child(et_cno.getText().toString());
                                dbRef.setValue(pym);
                                clearControls();
                                Toast.makeText(getApplicationContext(),"Successfully updated",Toast.LENGTH_SHORT).show();

                            }catch (NumberFormatException e){
                                Toast.makeText(getApplicationContext(),"Invalid Credit card number",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                            Toast.makeText(getApplicationContext(),"No source to update",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

//        //birthday
//        final Calendar myCalendar = Calendar.getInstance();
//
//        EditText edittext= (EditText) findViewById(R.id.birthday);
//        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
//
//            @Override
//            public void onDateSet(DatePicker view, int year, int monthOfYear,
//                                  int dayOfMonth) {
//                // TODO Auto-generated method stub
//                myCalendar.set(Calendar.YEAR, year);
//                myCalendar.set(Calendar.MONTH, monthOfYear);
//                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//                updateLabel();
//            }
//
//            private void updateLabel() {
//                String myFormat = "MM/dd/yy"; //In which you need put here
//                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
//
//                edittext.setText(sdf.format(myCalendar.getTime()));
//            }
//
//        };
//
//        edittext.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public  void onClick(View v) {
//                // TODO Auto-generated method stub
//                new DatePickerDialog(payment2.this, date, myCalendar
//                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
//                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
//            }
//        });


    }
    //navigate to payment1
    public void openFirst(View view){
        Intent intent=new Intent(this , payment1.class);

        //String ok=btn_proceed.getText().toString();

        //intent.putExtra("proceed",ok);

        startActivity(intent);
    }


    public void Ret(View view){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Payment").child(et_cno.getText().toString());
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){
                    et_cname.setText(snapshot.child("et_cname").getValue().toString());
                    et_cno.setText(snapshot.child("et_cno").getValue().toString());
                    et_country.setText(snapshot.child("et_country").getValue().toString());
                    et_cvc.setText(snapshot.child("et_cvc").getValue().toString());
//                           edt.setText((Integer) snapshot.child("etd").getValue());
//                           edt.setText(snapshot.child("etd").getValue().toString());
                    Toast.makeText(getApplicationContext(), "Working", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(getApplicationContext(), "Not Working", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}