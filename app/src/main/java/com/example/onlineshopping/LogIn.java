package com.example.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LogIn extends AppCompatActivity {
 public static  int customerid=0;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor preferencesEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        Button back=(Button)findViewById(R.id.btn_Lback);
        Button log=(Button)findViewById(R.id.btn_Login);
        EditText Lname=(EditText)findViewById(R.id.txt_Lname);
        EditText Lpass=(EditText)findViewById(R.id.txt_LPass);
        CheckBox forget=(CheckBox)findViewById(R.id.check_forget);
        CustomerDatabase s=new CustomerDatabase(this);
        CheckBox rem =(CheckBox)findViewById(R.id.check_rem);

        sharedPreferences =getSharedPreferences("rem_file",MODE_PRIVATE);

        boolean login = false;
        login = sharedPreferences.getBoolean("login", false);
        if (login) {
            Intent i = new Intent(LogIn.this,choices.class);
            i.putExtra("customerid",customerid);
            startActivity(i);
            finish();
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LogIn.this,MainActivity.class);
                startActivity(i);
            }
        });
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=Lname.getText().toString();
                if(forget.isChecked()==true)
                {
                    Cursor c=s.fetchpass(username);
                    if(c.getCount()==0)
                    {
                        Toast.makeText(getApplicationContext(), "UserName Not found ,Please check it or first Signup", Toast.LENGTH_LONG).show();
                        forget.setChecked(false);
                    }
                    else {
                       Intent i=new Intent(LogIn.this,ForgetPassword.class);
                       startActivity(i);
                    }
                }
            }
        });
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=Lname.getText().toString();
                String pass=Lpass.getText().toString();
                if(username.isEmpty())
                {
                    if(pass.isEmpty())
                    {
                        Toast.makeText(getApplicationContext(),"Enter name and password",Toast.LENGTH_LONG).show();
                    }
                    Toast.makeText(getApplicationContext(),"Enter name",Toast.LENGTH_LONG).show();
                }
                else if(pass.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter password",Toast.LENGTH_LONG).show();
                }
                else {
                    Cursor c = s.Foundcustomer(username, pass);
                    if (c.getCount() == 0)
                    {
                        Toast.makeText(getApplicationContext(), "Not found name or password,Please check or SignUp", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        if (rem.isChecked())
                        {
                            preferencesEditor=sharedPreferences.edit();
                            preferencesEditor.putString(username, username);
                            preferencesEditor.putString(pass, pass);
                            preferencesEditor.putBoolean("login", true);
                            preferencesEditor.apply();
                        }
                        customerid=Integer.parseInt(c.getString(0));
                        Toast.makeText(getApplicationContext(), "successfully login", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(LogIn.this, choices.class);
                        i.putExtra("customerid",customerid);
                        startActivity(i);
                    }
                }
            }
        });


    }
}