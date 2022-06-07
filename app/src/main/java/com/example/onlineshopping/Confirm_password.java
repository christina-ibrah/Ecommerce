package com.example.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.ZoneId;

public class Confirm_password extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_password);
        EditText pass=(EditText)findViewById(R.id.txt_cpass);
        EditText cpass=(EditText)findViewById(R.id.txt_confirmp);
        Button confirm=(Button)findViewById(R.id.btn_confirmpassword);
        CustomerDatabase cus=new CustomerDatabase(this);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Pass=pass.getText().toString();
                String Cpass=cpass.getText().toString();
                if(!Pass.equals(Cpass))
                {
                    cpass.setTextColor(Color.parseColor("Red"));
                }
                else
                {
                    String User=getIntent().getExtras().getString("username");
                    cus.ConfirmPassword(User,Cpass);
                    Intent i = new Intent(Confirm_password.this, LogIn.class);
                    startActivity(i);
                }
            }
        });
    }
}