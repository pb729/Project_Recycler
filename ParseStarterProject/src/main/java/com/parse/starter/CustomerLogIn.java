package com.parse.starter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class CustomerLogIn extends AppCompatActivity {
    EditText EmailLogin,passLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_log_in);
        EmailLogin = findViewById(R.id.CustomerEmailLogIn);
        passLogin = findViewById(R.id.CustomerPasswordLogin);
        setTitle("Customer Login");
    }


    public void signupClicked (View view){

        if(EmailLogin.getText().toString() .matches("") || passLogin.getText().toString().matches("")){
            Toast.makeText(this,"Username or Password cannot be empty",Toast.LENGTH_LONG).show();
        }else {

                // Login case

                ParseUser.logInInBackground(EmailLogin.getText().toString(), passLogin.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user != null){
                            Intent intent = new Intent(getApplicationContext(),CustomerSubmit.class);
                            startActivity(intent);
                            Log.i("DONE!","LOG IN COMPLETE");
                        }
                        else{
                            Toast.makeText(CustomerLogIn.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }