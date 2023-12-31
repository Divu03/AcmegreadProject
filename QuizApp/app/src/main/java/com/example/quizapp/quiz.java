package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class quiz extends AppCompatActivity {

    Button nb,qb;
    RadioGroup rg;
    RadioButton rb1,rb2,rb3,rb4;
    String[] question ={"Which method can be defined only once in a program?",
            "Which keyword is used by method to refer to the current object that invoked it?",
            "Which of these access specifiers can be used for an interface?",
            "Which of the following is correct way of importing an entire package‘pkg’?",
            "What is the return type of Constructors?"
    };
    String[] opt={
            "finalize method","main method","static method","private method",
            "import","this","catch","abstract",
            "public","protected","private","All of the mentioned",
            "Import pkg.","import pkg.*","Import pkg.*","import pkg.",
            "int","float","void","None of the mentioned"
    };
    String[] ans={
            "main method", "this","public","import pkg.*","None of the mentioned"
    };
    int flag = 0;
    public int marks=0,correct=0,wrong=5;
    TextView nametv,score,qt,s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        score = findViewById(R.id.yscore);
        s = findViewById(R.id.score);
        nametv = findViewById(R.id.textView10);

        String namepq = "Hello " + getIntent().getStringExtra("namep");
        nametv.setText(namepq);

        rg = findViewById(R.id.rg);
        rb1 = findViewById(R.id.radio1);
        rb2 = findViewById(R.id.radio2);
        rb3 = findViewById(R.id.radio3);
        rb4 = findViewById(R.id.radio4);
        nb = findViewById(R.id.nqb);
        qb = findViewById(R.id.qb);
        qt = findViewById(R.id.tquesion);

        qt.setText(question[flag]);
        rb1.setText(opt[0]);
        rb2.setText(opt[1]);
        rb3.setText(opt[2]);
        rb4.setText(opt[3]);
        nb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rg.getCheckedRadioButtonId()==-1){
                    Toast.makeText(getApplicationContext(),"Please select one choice",Toast.LENGTH_SHORT).show();
                    return;
                }
                RadioButton ansrb= findViewById(rg.getCheckedRadioButtonId());
                String at=ansrb.getText().toString();
                if(at.equals(ans[flag])){
                    correct++;
                    wrong -= 1;
                    Toast.makeText(getApplicationContext(),"Correct",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "wrong", Toast.LENGTH_SHORT).show();
                }
                flag++;
                s.setText(String.valueOf(correct));
                if(flag<question.length){
                    qt.setText(question[flag]);
                    rb1.setText(opt[flag*4]);
                    rb2.setText(opt[flag*4 +1]);
                    rb3.setText(opt[flag*4 +2]);
                    rb4.setText(opt[flag*4 +3]);
                }
                if(flag == question.length){
                    marks=correct;
                    Intent inte = new Intent(getApplicationContext(), result.class);

                    inte.putExtra("cor",correct);
                    inte.putExtra("wro",wrong);
                    startActivity(inte);
                }
                rg.clearCheck();
            }
        });

        qb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton ansrq= findViewById(rg.getCheckedRadioButtonId());
                if(rg.getCheckedRadioButtonId() != -1) {
                    String aq = ansrq.getText().toString();
                    if (aq.equals(ans[flag])) {
                        correct++;
                        wrong -= 1;
                    }
                    flag++;
                }
                s.setText(String.valueOf(correct));
                Intent inte2 = new Intent(getApplicationContext(), result.class);
                inte2.putExtra("cor",correct);
                inte2.putExtra("wro",wrong);
                startActivity(inte2);
            }
        });
    }
}