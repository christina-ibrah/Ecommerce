package com.example.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgetPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        EditText email=(EditText)findViewById(R.id.txt_pemail);
        Button conf=(Button)findViewById(R.id.btn_confirm);
        EditText user=(EditText)findViewById(R.id.txt_puser);
        CustomerDatabase cus=new CustomerDatabase(this);
        conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String User = user.getText().toString();
                Cursor c = cus.Foundname(User);
                if (c.getCount() == 0) {
                    Toast.makeText(getApplicationContext(), "User Name not found", Toast.LENGTH_LONG).show();
                } else {
                    String Email = email.getText().toString();
                    Cursor c1 = cus.Foundemail(User);
                    if (c1.getCount() == 0) {
                    }
                    if (c1.getString(0).equals(Email)) {
                        Intent i = new Intent(ForgetPassword.this, Confirm_password.class);
                        i.putExtra("username",User);
                        startActivity(i);
                    }
                }
            }
        });
    }
}