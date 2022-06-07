package com.example.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DiaplayPro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diaplay_pro);
        ListView pro=(ListView)findViewById(R.id.list_displaypro);
        ArrayAdapter<String> adapt=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
        pro.setAdapter(adapt);
        CustomerDatabase cus=new CustomerDatabase(getApplicationContext());
        cus.deletepro();
        cus.createpro();
        Cursor c=cus.GetProducts();
        if(c.getCount()==0)
        {
            Toast.makeText(getApplicationContext(),"count=0",Toast.LENGTH_LONG).show();
        }
        else
        {
            while(!c.isAfterLast())
            {
                adapt.add(c.getString(0));
                c.moveToNext();
            }
        }
        pro.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name=((TextView)view).getText().toString();
                ArrayList<String>cart=(ArrayList<String>)getIntent().getSerializableExtra("CArt");
                ArrayList<String>qua=(ArrayList<String>)getIntent().getSerializableExtra("Q");
                int custid=(Integer)getIntent().getSerializableExtra("customerid");
                Intent i=new Intent(DiaplayPro.this,AddToCart.class);
                i.putExtra("ProductName", name);
                i.putExtra("cart",cart);
                i.putExtra("Qua",qua);
                i.putExtra("customerid",custid);
                startActivity(i);
            }
        });
    }
}