package com.example.shopping;

import android.content.Context;
import android.util.Patterns;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class InputValidation {
    private Context context;
    public InputValidation(Context context){this.context=context;}
    public boolean isIETFilled(EditText tIET, TextInputLayout tIL,String msg){
        String val = tIET.getText().toString().trim();
        if(val.isEmpty()){
            tIL.setError(msg);
            return false;
        }else {
            tIL.setErrorEnabled(false);
        }
        return true;
    }
    public boolean isIETEmail(EditText tIET, TextInputLayout tIL,String msg){
        String val = tIET.getText().toString().trim();
        if(val.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(val).matches()){
            tIL.setError(msg);
            return false;
        }else {
            tIL.setErrorEnabled(false);
        }
        return true;
    }
    public boolean isIETMatches(EditText tIET1, EditText tIET2, TextInputLayout tIL,String msg){
        String val1 = tIET1.getText().toString().trim();
        String val2 = tIET2.getText().toString().trim();
        if(!val2.contentEquals(val1)){
            tIL.setError(msg);
            return false;
        }else {
            tIL.setErrorEnabled(false);
        }
        return true;
    }
}
