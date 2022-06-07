package com.example.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Voice extends AppCompatActivity {
    int voice = 1;
    EditText ev;
    ListView lv;
    CustomerDatabase cus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice);
        cus = new CustomerDatabase(this);
        Button v = (Button) findViewById(R.id.btn_provoice);
        lv = (ListView) findViewById(R.id.list_provoice);
        ev = (EditText) findViewById(R.id.txt_v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                startActivityForResult(i, voice);
            }
        });
    }

   public void onActivityResult(int r, int rc, Intent data) {

       super.onActivityResult(r, rc, data);
       if (r == voice && rc == RESULT_OK) {
           ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
           ev.setText(text.get(0));
           ArrayAdapter<String> p = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);
           lv.setAdapter(p);
            Cursor c = cus.getProducts(text.get(0).toString());
                while (!c.isAfterLast()) {
                    p.add(c.getString(0));
                    c.moveToNext();
                }
        } else {
            Toast.makeText(getApplicationContext(), "NO", Toast.LENGTH_LONG).show();

       }
   }
}
