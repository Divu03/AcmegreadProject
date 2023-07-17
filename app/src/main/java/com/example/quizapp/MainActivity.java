package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    Button about;
    Button start;
    EditText name = null;
    CharSequence text = "Enter the Name";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        about = findViewById(R.id.aboutButton);
        start = findViewById(R.id.startButton);
        name = findViewById(R.id.enterName);
        about.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(getApplicationContext(),AboutUs.class);
                startActivity(i);
            }
        });

        start.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                String namep = name.getText().toString().trim();
                if (namep.isEmpty() || namep.equals("null")){
                    Log.d("namep",namep);
                    Toast.makeText(MainActivity.this,text,Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent i = new Intent(getApplicationContext(), quiz.class);
                    i.putExtra("namep",namep);
                    startActivity(i);
                }
            }
        });
    }
}