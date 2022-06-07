package com.example.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DiaplayCat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diaplay_cat);
        ListView cat=(ListView)findViewById(R.id.list_displaycat);
        ArrayAdapter<String>adapt=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
        cat.setAdapter(adapt);
        CustomerDatabase cus=new CustomerDatabase(getApplicationContext());
        cus.deletecat();
        adapt.clear();
        adapt.notifyDataSetChanged();
        cus.createcat();
        cus.createpro();
        Cursor c=cus.GetCategories();
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
        cat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String cat=((TextView)view).getText().toString();
                Cursor c= cus.GetIdCat(cat);
                int cid=Integer.parseInt(c.getString(0));
                Cursor c1=cus.GetProfromCatID(String.valueOf(cid));
                ArrayList<String> pro=new ArrayList<>();
                while(!c1.isAfterLast())
                {
                    pro.add(c1.getString(0));
                    c1.moveToNext();
                }
                ArrayList<String>Cart=(ArrayList<String>) (getIntent().getSerializableExtra("CArt"));
                ArrayList<String>Quantity=(ArrayList<String>)(getIntent().getSerializableExtra("Q"));
                int custid=(Integer)getIntent().getSerializableExtra("customerid");
                Intent i = new Intent(getApplicationContext(), ProductsCat.class);
                i.putExtra("mylist", pro);
                i.putExtra("CART",Cart);
                i.putExtra("QU",Quantity);
                i.putExtra("customerid",custid);
                startActivity(i);
            }
        });
    }
}