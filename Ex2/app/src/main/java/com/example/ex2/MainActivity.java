package com.example.ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.Buffer;

public class MainActivity extends AppCompatActivity {
    EditText edata;
    Button read, write;
    String fileName="file.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edata = (EditText) findViewById(R.id.editdata);
        read =(Button)  findViewById(R.id.read);
        write =(Button)  findViewById(R.id.write);

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream in= openFileInput("file.txt");
                    BufferedReader reader=new BufferedReader(new InputStreamReader(in));
                    String data="";
                    StringBuilder builder=new StringBuilder();
                    while((data=reader.readLine())!=null){
                        builder.append(data);
                        builder.append("\n");
                    }
                    in.close();
                    edata.setText(builder.toString());
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream out=openFileOutput("file.txt",0);
                    OutputStreamWriter writer=new OutputStreamWriter(out);
                    writer.write(edata.getText().toString());
                    writer.close();
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    };

}
