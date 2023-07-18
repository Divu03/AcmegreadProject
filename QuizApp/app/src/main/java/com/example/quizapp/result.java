package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class result extends AppCompatActivity {
    TextView c,w,f;
    Button rs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        c = findViewById(R.id.ca);
        int correct = getIntent().getIntExtra("cor",0);
        String cans = "Correct Answers: " + correct;
        c.setText(cans);

        w = findViewById(R.id.wa);
        int wrong = getIntent().getIntExtra("wro",0);
        String wans = "Wrong Answers: " + wrong;
        w.setText(wans);

        f = findViewById(R.id.fs);
        String fs = "Final Score: " + correct;
        f.setText(fs);

        rs = findViewById(R.id.restart);
        rs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ints = new Intent(getApplicationContext(), quiz.class);
                startActivity(ints);
            }
        });
    }


}