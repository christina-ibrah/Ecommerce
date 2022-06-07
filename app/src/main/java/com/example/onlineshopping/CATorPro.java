package com.example.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class CATorPro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_a_tor_pro);
        Button cat=(Button)findViewById(R.id.btn_cat);
        Button pro=(Button)findViewById(R.id.btn_pro);
        ArrayList<String>cart=(ArrayList<String>)getIntent().getSerializableExtra("Cart");
        ArrayList<String>qua=(ArrayList<String>)getIntent().getSerializableExtra("quantity");
        int custid=(Integer)getIntent().getSerializableExtra("customerid");
        cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CATorPro.this, DiaplayCat.class);
                i.putExtra("CArt",cart);
                i.putExtra("Q",qua);
                i.putExtra("customerid",custid);
                startActivity(i);
            }
        });
        pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CATorPro.this, DiaplayPro.class);
                i.putExtra("CArt",cart);
                i.putExtra("Q",qua);
                i.putExtra("customerid",custid);
                startActivity(i);
            }
        });
    }
}