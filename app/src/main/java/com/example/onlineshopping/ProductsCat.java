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

public class ProductsCat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_cat);
        ArrayList<String> myList = (ArrayList<String>) getIntent().getSerializableExtra("mylist");

        ListView profromcat = (ListView) findViewById(R.id.list_procat);
        ArrayAdapter<String> adaptp = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        profromcat.setAdapter(adaptp);
        CustomerDatabase cus=new CustomerDatabase(this);
        for(int i=0;i<myList.size();i++)
        {
            adaptp.add(myList.get(i));
        }
        profromcat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name=((TextView)view).getText().toString();

                ArrayList<String>cart=(ArrayList<String>)getIntent().getSerializableExtra("CART");
                ArrayList<String>qua=(ArrayList<String>)getIntent().getSerializableExtra("QU");
                int custid=(Integer)getIntent().getSerializableExtra("customerid");
                Intent i=new Intent(ProductsCat.this,AddToCart.class);
                i.putExtra("ProductName", name);
                i.putExtra("cart",cart);
                i.putExtra("Qua",qua);
                i.putExtra("customerid",custid);
                startActivity(i);
            }
        });
    }
}