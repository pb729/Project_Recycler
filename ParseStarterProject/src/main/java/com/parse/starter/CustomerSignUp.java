package com.parse.starter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class CustomerSignUp extends AppCompatActivity {

      TextView name;
       EditText emailID , passcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_sign_up);

      name =  findViewById(R.id.PersonName);
      emailID = findViewById(R.id.CustomerEmailLogIn);
      passcode = findViewById(R.id.CustomerPassword);
        setTitle("Customer Sign up");



    }
    public void signupClicked (View view){

        if(emailID.getText().toString() .matches("") || name.getText().toString().matches("")  || passcode.getText().toString().matches("")    ) {
            Toast.makeText(this, "Credentials cannot be empty", Toast.LENGTH_LONG).show();
        } else{

                ParseUser user = new ParseUser();
                user.setUsername(name.getText().toString());
                user.setPassword(passcode.getText().toString());
                user.setEmail(emailID.getText().toString());
                user.put("Stars",0);


                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(CustomerSignUp.this, "Sign Up Successful", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }
                        //Log.i("Success", "DONE!");
                        else
                            Toast.makeText(CustomerSignUp.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }

            /* else{
                // Login case

                ParseUser.logInInBackground(userET.getText().toString(), passwordET.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user != null){
                            showUserList();
                            Log.i("DONE!","LOG IN COMPLETE");
                        }
                        else{
                            Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }*/
    }

}