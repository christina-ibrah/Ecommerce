package com.example.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class AddToCart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);
        CustomerDatabase cus=new CustomerDatabase(this);
        TextView proname=(TextView)findViewById(R.id.txt_proname);
        TextView proprice=(TextView)findViewById(R.id.txt_proprice);

        EditText q=(EditText)findViewById(R.id.txt_quan);
        Button sum=(Button) findViewById(R.id.btn_sum);
        Button sub=(Button) findViewById(R.id.btn_sub);

        Button add=(Button)findViewById(R.id.btn_addcart);
        String productname =(String)getIntent().getSerializableExtra("ProductName");

        proname.setText(productname);
        Cursor c=cus.Getproductprice(productname);
        proprice.setText(c.getString(0)+"$");

        sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity=Integer.parseInt(q.getText().toString());
                int sq=quantity+1;
                q.setText(String.valueOf(sq));
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity=Integer.parseInt(q.getText().toString());
                Toast.makeText(getApplicationContext(),"Quantity equal zero:"+quantity,Toast.LENGTH_LONG).show();
                if(quantity==0)
                {
                    Toast.makeText(getApplicationContext(),"Quantity equal zero",Toast.LENGTH_LONG).show();
                }
                else if(quantity>0)
                    {
                    int sq = quantity - 1;
                    q.setText(String.valueOf(sq));
                }
            }
        });
         ArrayList<String> CA =(ArrayList<String>) getIntent().getSerializableExtra("cart");
        ArrayList<String>QUE=(ArrayList<String>) getIntent().getSerializableExtra("Qua");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity=Integer.parseInt(q.getText().toString());
                if(quantity==0)
                {
                    Toast.makeText(getApplicationContext(),"Quantity equal zero",Toast.LENGTH_LONG).show();
                }
                else if(quantity >0) {
                    Toast.makeText(getApplicationContext(), "Successfully add", Toast.LENGTH_LONG).show();
                    int cusid=Integer.parseInt(String.valueOf(getIntent().getSerializableExtra("customerid")));
                    CA.add(productname);
                    QUE.add(String.valueOf(quantity));
                    Intent i = new Intent(AddToCart.this, Orders.class);
                    i.putExtra("CART", CA);
                    i.putExtra("QuantitY",QUE);
                    i.putExtra("customerid",cusid);
                    startActivity(i);
                }
            }
        });




    }
}