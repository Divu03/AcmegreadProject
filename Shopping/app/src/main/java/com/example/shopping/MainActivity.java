package com.example.shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    TextInputLayout namel,emaill,passl,cpassl;
    EditText namee,emaile,passe,cpasse;
    Button reg;
    TextView already;
    private DatabaseHelper dbh;
    private InputValidation iv;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        namel=findViewById(R.id.tiname);
        emaill=findViewById(R.id.tiemail);
        passl=findViewById(R.id.tipass);
        cpassl=findViewById(R.id.ticpass);

        namee=findViewById(R.id.name);
        emaile=findViewById(R.id.email);
        passe=findViewById(R.id.password);
        cpasse=findViewById(R.id.confirmpass);

        reg=findViewById(R.id.registerb);
        already=findViewById(R.id.already);

        dbh = new DatabaseHelper(MainActivity.this);
        iv = new InputValidation(MainActivity.this);
        user = new User();

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!iv.isIETFilled(namee,namel,"Enter Full Name")){
                    return;
                }
                if(!iv.isIETFilled(emaile,emaill,"Enter Valid Email")){
                    return;
                }
                if(!iv.isIETEmail(emaile,emaill,"Enter Valid Email")){
                    return;
                }
                if(!iv.isIETFilled(passe,passl,"Enter Password")){
                    return;
                }
                if(!iv.isIETMatches(passe,cpasse,cpassl,"Password Does Not Matches")){
                    return;
                }
                if(!dbh.checkUser(emaile.getText().toString().trim())){
                    user.setName(namee.getText().toString().trim());
                    user.setEmail(emaile.getText().toString().trim());
                    user.setPassword(passe.getText().toString().trim());
                    dbh.addUser(user);
                    Toast.makeText(MainActivity.this,"Registration Successful",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this,"Email Already Exist",Toast.LENGTH_LONG).show();
                }
            }
        });

        already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intental = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intental);
            }
        });
    }
}