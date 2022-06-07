package com.example.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Text extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        EditText pro=(EditText)findViewById(R.id.txt_protext);
        Button t=(Button)findViewById(R.id.btn_proText);
        ListView lt=(ListView)findViewById(R.id.list_pro);
        ArrayAdapter<String> empAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        lt.setAdapter(empAdapter);
        CustomerDatabase cus=new CustomerDatabase(getApplicationContext());
        cus.deletecat();
        empAdapter.clear();
        empAdapter.notifyDataSetChanged();
        cus.createcat();
        cus.createpro();

        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Cursor c= cus.getProducts(pro.getText().toString());
                while(!c.isAfterLast())
                {
                    empAdapter.add(c.getString(0));
                    c.moveToNext();
                }
            }
        });

    }
}