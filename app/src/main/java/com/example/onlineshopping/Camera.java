package com.example.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

public class Camera extends AppCompatActivity {
    private final int camera_request=1888;
    private ImageView myimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        Button c=(Button)findViewById(R.id.btn_proCamera);
        ListView lc=(ListView)findViewById(R.id.list_procamera);
        myimage=(ImageView)findViewById(R.id.Image);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ca=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(ca,camera_request);
            }
        });
    }
     public void onActivityResult(int r,int rc,Intent data) {
        super.onActivityResult(r, rc, data);
        if(r==camera_request && rc==RESULT_OK)
        {
            Bitmap i=(Bitmap)data.getExtras().get("data");
            myimage.setImageBitmap(i);
        }
    }
}