package com.example.ex4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contectinfo);

        Button back=(Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(SecondActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        TextView name=(TextView)findViewById(R.id.nameValue);
        TextView email=(TextView)findViewById(R.id.emailValue);
        TextView project=(TextView)findViewById(R.id.projectvalue);

        Intent receiver = getIntent();
        String nameValue = receiver.getStringExtra("name");
        String emailValue = receiver.getStringExtra("email");
        String projectValue = receiver.getStringExtra("project");

        name.setText(nameValue);
        email.setText(emailValue);
        project.setText(projectValue);
    }
}
