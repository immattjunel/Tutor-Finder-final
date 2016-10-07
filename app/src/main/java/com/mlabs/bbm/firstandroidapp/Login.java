package com.mlabs.bbm.firstandroidapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends Activity {

    EditText pwdTxt,emailTxt;

    Button showbtn,logInBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        showbtn = (Button) findViewById(R.id.showbtn);
        pwdTxt = (EditText) findViewById(R.id.pass);
        emailTxt=(EditText) findViewById(R.id.email);
        logInBtn=(Button) findViewById(R.id.login);

        showbtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //int event = motionEvent.getAction();
                final int cursor=pwdTxt.getSelectionStart();
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d("Classname","ACTION_DOWN");
                        pwdTxt.setTransformationMethod(null);
                        pwdTxt.setSelection(cursor);
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d("Classname","ACTION_UP");
                        pwdTxt.setTransformationMethod(new PasswordTransformationMethod());
                        pwdTxt.setSelection(cursor);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        pwdTxt.setSelection(cursor);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }


    public void login(View view) {
        final DataBaseAdapter sqlDB = new DataBaseAdapter(getApplicationContext());


        if (!validateEmail(emailTxt.getText().toString())) {
            emailTxt.setError("Invalid Email or Username");
            emailTxt.requestFocus();
        } else if (!validatePwd(pwdTxt.getText().toString())) {
            pwdTxt.setError("Invalid Password");
            pwdTxt.requestFocus();
        }else{

        if (sqlDB.validateEmail(emailTxt.getText().toString(),pwdTxt.getText().toString())||sqlDB.validateUsername(emailTxt.getText().toString(),pwdTxt.getText().toString())) {
            Intent intent = new Intent(Login.this, MidtermHandson.class);
            startActivity(intent);
            finish();
        } else{

            Toast.makeText(getApplicationContext(), "Invalid email/username or password", Toast.LENGTH_SHORT).show();
        }}


    }



    @NonNull
    private Boolean validateEmail(String emailAdd) {
        if (emailAdd == null) {
            return false;
        } else {
            return true;
        }
    }

    @NonNull
    private Boolean validatePwd(String password) {
        if (password != null && password.length() >= 8) {
            return true;
        } else {
            return false;
        }
    }


    public void SignUp(View v) {
        Intent intent=new Intent(this,signup.class);
        startActivity(intent);
    }



}
