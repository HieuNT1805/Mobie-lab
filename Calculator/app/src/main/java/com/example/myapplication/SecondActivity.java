package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    Button button;
    TextView textView;

    ListView list;
    String history[]= {"1 2","22","3 3"};

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);



        ArrayList<String> allHistory = (ArrayList<String>) getIntent().getSerializableExtra("HISTORY");

        list = (ListView)findViewById(R.id.list);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.activity_listview,R.id.textView,allHistory);
        list.setAdapter(arrayAdapter);
        button=(Button) findViewById(R.id.activity_two);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(SecondActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        textView=(TextView) findViewById(R.id.times);

        Intent receiver = getIntent();
        String values = receiver.getStringExtra("KEY_SENDER");
        String history = receiver.getStringExtra("HISTORY");

        textView.setText(values);

    }
}
