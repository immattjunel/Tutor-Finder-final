package com.mlabs.bbm.firstandroidapp;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
public class signup extends AppCompatActivity{
    private Login Login;
    EditText passTxt,emailTxt,confirmpassTxt,unameTxt,fnameTxt,lnameTxt;
    String email,pass,conpass,uname,fname,lname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regex);

        passTxt = (EditText) findViewById(R.id.pass);
        emailTxt = (EditText) findViewById(R.id.email);
        confirmpassTxt = (EditText) findViewById(R.id.conpass);
        unameTxt = (EditText) findViewById(R.id.uname);
        fnameTxt = (EditText) findViewById(R.id.fname);
        lnameTxt = (EditText) findViewById(R.id.lname);
    }
    public void register(View view) {
        final DataBaseAdapter sqlDB = new DataBaseAdapter(getApplicationContext());
        email = emailTxt.getText().toString().trim();
        pass = passTxt.getText().toString().trim();
        conpass = confirmpassTxt.getText().toString().trim();
        uname = unameTxt.getText().toString().trim();
        fname=fnameTxt.getText().toString().trim();
        lname = lnameTxt.getText().toString().trim();
        if (email !=null&& pass !=null && conpass !=null) {
            if(validateEmail(email)==true){
                if(validatePwd(pass) && validatePwd(conpass)){
            if ((sqlDB.validateuname(unameTxt.getText().toString()))||(sqlDB.validateemail(emailTxt.getText().toString()))) {
                if (sqlDB.validateuname(unameTxt.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Username already exist.", Toast.LENGTH_LONG).show();
                    unameTxt.requestFocus();
                }
                if (sqlDB.validateemail(emailTxt.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Email address already exist.", Toast.LENGTH_LONG).show();
                    emailTxt.requestFocus();
                }
            }else if((!sqlDB.validateuname(unameTxt.getText().toString()))&&(!sqlDB.validateemail(emailTxt.getText().toString()))){
                if (pass.equals(conpass)) {

                    String msg=sqlDB.registerUser(fname, lname, uname, email, pass);
                    Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(signup.this, Login.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(getApplicationContext(), "Password did not match", Toast.LENGTH_LONG).show();
                    passTxt.setText("");
                    confirmpassTxt.setText("");
                    confirmpassTxt.requestFocus();
                }
            }}else{Toast.makeText(getApplicationContext(),"Password must atleast 8 Characters",Toast.LENGTH_LONG).show();}}
            else{Toast.makeText(getApplicationContext(),"Invalid Email",Toast.LENGTH_LONG).show();}
        }else {
            Toast.makeText(getApplicationContext(),"Please fill up all fields",Toast.LENGTH_LONG).show();
        }

    }

    private Boolean validateEmail(String emailAdd) {
        if (emailAdd == null || !Patterns.EMAIL_ADDRESS.matcher(emailAdd).matches()) {
            return false;
        } else {
            return true;
        }
    }

    private Boolean validatePwd(String password) {
        if (password != null && password.length() >= 8) {
            return true;
        } else {
            return false;
        }
    }
}


