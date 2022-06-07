package com.example.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.sql.BatchUpdateException;
import java.util.ArrayList;

public class choices extends AppCompatActivity {
    ArrayList<String> Cart=new ArrayList<>();
    ArrayList<String> Quantity=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choices);
        Button d=(Button)findViewById(R.id.btn_display);
        Button s=(Button)findViewById(R.id.btn_search);
        int cusid=Integer.parseInt(String.valueOf(getIntent().getSerializableExtra("customerid")));
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(choices.this, CATorPro.class);
                i.putExtra("Cart",Cart);
                i.putExtra("quantity",Quantity);
                i.putExtra("customerid",cusid);
                startActivity(i);
            }
        });
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(choices.this, SearchProduct.class);
                startActivity(i);
            }
        });
    }
}