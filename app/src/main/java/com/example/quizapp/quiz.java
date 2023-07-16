package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class quiz extends AppCompatActivity {


    TextView nametv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        String namepq = getIntent().getStringExtra("namep");
        nametv = (TextView)findViewById(R.id.textView10);
        nametv.setText("Hello "+namepq);
    }
}