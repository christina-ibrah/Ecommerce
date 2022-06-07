package com.example.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class Orders extends AppCompatActivity {
    private RecyclerView rec;
    private OrdersAdapter Adapter;
    private ArrayList<DisplayOrders>DisOrders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        ArrayList<String>Cart=(ArrayList<String>) (getIntent().getSerializableExtra("CART"));
        ArrayList<String>Quantity=(ArrayList<String>)(getIntent().getSerializableExtra("QuantitY"));
        int custid=(Integer)getIntent().getSerializableExtra("customerid");
        Button addorder=(Button)findViewById(R.id.btn_addorder);
        Button submit=(Button)findViewById(R.id.btn_submit);
        TextView totalp=(TextView)findViewById(R.id.txt_totalprice);

        rec=findViewById(R.id.dis_orders);
        DisOrders=new ArrayList<>();
        ArrayList<String>Price=new ArrayList<>();
        CustomerDatabase cus=new CustomerDatabase(this);
        for(int i=0;i<Cart.size();i++)
        {
            Cursor c1=cus.Getproductprice(Cart.get(i));
            Price.add(c1.getString(0));
        }
        for(int i=0;i<Cart.size();i++)
        {
            DisOrders.add(new DisplayOrders("name " +Cart.get(i), "quantity " +Quantity.get(i), "price " +Price.get(i)));
        }
        Adapter =new OrdersAdapter(this,DisOrders);
        rec.setLayoutManager(new LinearLayoutManager(this));
        rec.setAdapter(Adapter);
        Adapter.setOnItemClickListener(new OrdersAdapter.OnItemClickListener() {
           @Override
           public void OnItemClick(int position) {

           }
            @Override
            public void OnDeleteClick(int position) {
                DisplayOrders delorder=DisOrders.get(position);
                String delname=delorder.getName().replace("name ","");
                int index=Cart.indexOf(delname);
                Quantity.remove(index);
                Cart.remove(delname);
                DisOrders.remove(position);
                Adapter.notifyDataSetChanged();
                Price.remove(index);
                ArrayList<Integer>totalprice=new ArrayList<Integer>();
                for(int i=0;i<Cart.size();i++)
                {
                    int q=Integer.parseInt(Quantity.get(i));
                    int p=Integer.parseInt(Price.get(i));
                    int s=p*q;
                    Log.d("s",String.valueOf(s));
                    totalprice.add(s);
                    Log.d("add",String.valueOf(totalprice.get(i)));
                }
                int total=0;
                for(int i : totalprice)
                {
                    total +=i;
                    Log.d("to",String.valueOf(total));
                }
                totalp.setText("Total Price"+" "+String.valueOf(total)+"$");

            }
            @Override
            public void OnSum(int position) {
                DisplayOrders delorder=DisOrders.get(position);
                String delname=delorder.getName().replace("name ","");
                int index=Cart.indexOf(delname);
                int qua=Integer.parseInt(delorder.getQuantity().replace("quantity ",""));
                int sq=qua+1;
                delorder.setQuantity("quantity "+String.valueOf(sq));
                Quantity.set(index,String.valueOf(sq));
                Adapter.notifyDataSetChanged();
                ArrayList<Integer>totalprice=new ArrayList<Integer>();
                for(int i=0;i<Cart.size();i++)
                {
                    int q=Integer.parseInt(Quantity.get(i));
                    int p=Integer.parseInt(Price.get(i));
                    int s=p*q;
                    Log.d("s",String.valueOf(s));
                    totalprice.add(s);
                    Log.d("add",String.valueOf(totalprice.get(i)));
                }
                int total=0;
                for(int i : totalprice)
                {
                    total +=i;
                    Log.d("to",String.valueOf(total));
                }
                totalp.setText("Total Price"+" "+String.valueOf(total)+"$");
            }
            @Override
            public void Onsub(int position) {
                DisplayOrders delorder=DisOrders.get(position);
                String delname=delorder.getName().replace("name ","");
                int index=Cart.indexOf(delname);
                int qua=Integer.parseInt(delorder.getQuantity().replace("quantity ",""));
                int sq=qua-1;
                if(sq!=0)
                {
                    delorder.setQuantity("quantity "+String.valueOf(sq));
                    Quantity.set(index,String.valueOf(sq));
                    Adapter.notifyDataSetChanged();
                    ArrayList<Integer>totalprice=new ArrayList<Integer>();
                    for(int i=0;i<Cart.size();i++)
                    {
                        int q=Integer.parseInt(Quantity.get(i));
                        int p=Integer.parseInt(Price.get(i));
                        int s=p*q;
                        Log.d("s",String.valueOf(s));
                        totalprice.add(s);
                        Log.d("add",String.valueOf(totalprice.get(i)));
                    }
                    int total=0;
                    for(int i : totalprice)
                    {
                        total +=i;
                        Log.d("to",String.valueOf(total));
                    }
                    totalp.setText("Total Price"+" "+String.valueOf(total)+"$");
                }
            }
        });
        ArrayList<Integer>totalprice=new ArrayList<Integer>();
        for(int i=0;i<Cart.size();i++)
        {
            int q=Integer.parseInt(Quantity.get(i));
            int p=Integer.parseInt(Price.get(i));
            int s=p*q;
            totalprice.add(s);
        }
        int total=0;
        for(int i : totalprice)
        {
            total +=i;
        }
        totalp.setText("Total Price"+" "+String.valueOf(total)+"$");
        addorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(com.example.onlineshopping.Orders.this,CATorPro.class);
                i.putExtra("Cart",Cart);
                i.putExtra("quantity",Quantity);
                i.putExtra("customerid",custid);
                startActivity(i);
            }
        });
        EditText add=(EditText)findViewById(R.id.txt_address);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add in database
                 String currdate=java.text.DateFormat.getDateInstance().format(new Date());
                 String address=add.getText().toString();
                 cus.addorder(currdate,address,custid);
                 for(int i=0;i<Cart.size();i++)
                 {
                     Cursor c=cus.GetProductID(Cart.get(i));
                     int ordid=cus.Getorderid();
                     int proid=Integer.parseInt(c.getString(0));
                     cus.addorderdetails(Quantity.get(i),ordid,proid);
                     Toast.makeText(getApplicationContext(),"successfuly submit",Toast.LENGTH_LONG).show();
                     int c1=cus.Getnumorders();
                     Intent intent=new Intent(Orders.this,choices.class);
                     intent.putExtra("customerid",custid);
                     startActivity(intent);
                 }
            }
        });
    }
}