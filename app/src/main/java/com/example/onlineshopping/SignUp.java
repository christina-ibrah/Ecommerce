package com.example.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;

public class SignUp extends AppCompatActivity {
    DatePickerDialog.OnDateSetListener setListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Button Sback=(Button)findViewById(R.id.btn_back);
        Button Sign=(Button)findViewById(R.id.btn_signup);
        EditText sname=(EditText)findViewById(R.id.txt_name);
        EditText spass=(EditText)findViewById(R.id.txt_pass);
        EditText semail=(EditText)findViewById(R.id.txt_email);
        EditText sphone=(EditText)findViewById(R.id.txt_phone);
        EditText suser=(EditText)findViewById(R.id.txt_user);
        EditText birth=(EditText)findViewById(R.id.txt_birth);
        EditText sjob=(EditText)findViewById(R.id.txt_job);

        Spinner gender=(Spinner)findViewById(R.id.spinner_gender);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Male");
        arrayList.add("Famele");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(arrayAdapter);
        final String[] gen = {""};

        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gen[0] = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(),"choose gender",Toast.LENGTH_LONG).show();
            }
        });
        Sback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SignUp.this,MainActivity.class);
                startActivity(i);
            }
        });

        Calendar c=Calendar.getInstance();
        final int year=c.get(Calendar.YEAR);
        final int month=c.get(Calendar.MONTH);
        final int day=c.get(Calendar.DAY_OF_MONTH);
        birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog s=new DatePickerDialog(SignUp.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,setListener,year,month,day);

                s.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                s.show();
            }
        });
        setListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                String date=day+"/"+month+"/"+year;
                birth.setText(date);
            }
        };
        CustomerDatabase cus=new CustomerDatabase(this);
        Sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=sname.getText().toString();
                String pass=spass.getText().toString();
                String email=semail.getText().toString();
                String phone=sphone.getText().toString();
                String job=sjob.getText().toString();
                String Day=String.valueOf(day);
                String Month=String.valueOf(month);
                String Year=String.valueOf(year);
                String user=suser.getText().toString();
                if(name.isEmpty() || pass.isEmpty() || email.isEmpty() || phone.isEmpty() || job.isEmpty() || Day.isEmpty() || Month.isEmpty()||Year.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Please Enter All Info",Toast.LENGTH_LONG).show();
                }
                else {
                    Cursor c=cus.Foundname(user);
                    if(c.getCount()==0)
                    {
                        cus.createNewCustomer(user,name,pass,email,phone,gen[0],job,Day,Month,Year);
                        Toast.makeText(getApplicationContext(), "Done",
                                Toast.LENGTH_LONG).show();
                        /**activity*/
                        Intent i = new Intent(SignUp.this, LogIn.class);
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"username found,Please change",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }
}