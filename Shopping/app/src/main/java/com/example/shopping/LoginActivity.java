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

public class LoginActivity extends AppCompatActivity {

    EditText email,pass;
    Button login;
    TextView reg;
    TextInputLayout emaill,passl;
    private DatabaseHelper dbh;
    private InputValidation iv;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=findViewById(R.id.email);
        pass=findViewById(R.id.password);
        login=findViewById(R.id.button);
        reg=findViewById(R.id.reg);
        emaill=findViewById(R.id.emaill);
        passl=findViewById(R.id.passl);
        dbh = new DatabaseHelper(LoginActivity.this);
        iv = new InputValidation(LoginActivity.this);
        user = new User();

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!iv.isIETFilled(email,emaill,"Enter Valid Email")){
                    return;
                }
                if(!iv.isIETEmail(email,emaill,"Enter Valid Email")){
                    return;
                }
                if(!iv.isIETFilled(pass,passl,"Enter Password")){
                    return;
                }
                if(dbh.checkUserLogin(email.getText().toString().trim(),pass.getText().toString().trim())){
                    Intent acIntent = new Intent(LoginActivity.this, MainActivity.class);
                    acIntent.putExtra("Email",email.getText().toString().trim());
                    startActivity(acIntent);
                }
                else {
                    Toast.makeText(LoginActivity.this,"Wrong Email or Password",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}