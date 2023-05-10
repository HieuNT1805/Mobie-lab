package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    TextView editData;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editData=(TextView) findViewById(R.id.data);
        btn=(Button) findViewById(R.id.read);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text="";
                try{
                    InputStream is = getAssets().open("myfile.txt");
                    int size = is.available();
                    byte[] buffer=new byte[size];
                    is.read(buffer);
                    is.close();
                    text = new String(buffer);
                }
                catch (IOException ex){
                    ex.printStackTrace();
                }
                editData.setText(text);
            }
        });
    }
}