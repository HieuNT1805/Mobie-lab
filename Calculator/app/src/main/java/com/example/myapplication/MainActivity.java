package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Stack;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private int count=0;
    String history;
    Button goToActivityTwo;
    TextView resultTV, solutionTV;
    Button buttonC, buttonAC;
    Button buttonDivide, buttonMultiply, buttonPlus, buttonMinus, buttonEqual;
    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;

    private SharedPreferences mPreferences;
    private String sharedPrefFile="com.example.myapplication";

    private final String COUNT_KEY = "count";
    private final String HISTORY_KEY = "history";

    ArrayList<String> allHistory = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        count = mPreferences.getInt(COUNT_KEY,0);

        Gson gson = new Gson();
        String json=mPreferences.getString(HISTORY_KEY,null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        allHistory = gson.fromJson(json,type);


        goToActivityTwo=(Button) findViewById(R.id.activity_main);
        goToActivityTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,SecondActivity.class);
                intent.putExtra("KEY_SENDER",Integer.toString(count));
                intent.putExtra("HISTORY",allHistory);

                startActivity(intent);

            }
        });




        resultTV=findViewById(R.id.result_tv);
        solutionTV=findViewById(R.id.solution_tv);

        assignId(buttonC,R.id.button_c);
        assignId(buttonDivide,R.id.button_divide);
        assignId(buttonMultiply,R.id.button_multiply);
        assignId(buttonPlus,R.id.button_plus);
        assignId(buttonMinus,R.id.button_minus);
        assignId(buttonEqual,R.id.button_equal);
        assignId(button0,R.id.button_0);
        assignId(button1,R.id.button_1);
        assignId(button2,R.id.button_2);
        assignId(button3,R.id.button_3);
        assignId(button4,R.id.button_4);
        assignId(button5,R.id.button_5);
        assignId(button6,R.id.button_6);
        assignId(button7,R.id.button_7);
        assignId(button8,R.id.button_8);
        assignId(button9,R.id.button_9);
        assignId(buttonAC,R.id.button_ac);
    }



    void assignId(Button btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        Button button=(Button) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTV.getText().toString();

        if(buttonText.equals("AC")){
            solutionTV.setText("");
            resultTV.setText("0");
            return;
        }

        if(buttonText.equals("C")){
            dataToCalculate=dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else {
            dataToCalculate = dataToCalculate + buttonText;
        }
        if(buttonText.equals("=")){
            solutionTV.setText(resultTV.getText());
            count++;
            history = dataToCalculate + resultTV.getText();
            allHistory.add(history);

            return;
        }

        solutionTV.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Err")){
            resultTV.setText(finalResult);
        }

    }
    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putInt(COUNT_KEY, count);

        Gson gson= new Gson();
        String json = gson.toJson(allHistory);
        preferencesEditor.putString(HISTORY_KEY,json);
        preferencesEditor.apply();
    }


    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript",0,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }
}