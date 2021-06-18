package com.parse.starter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Random;

public class CustomerSubmit extends AppCompatActivity {
    EditText weight;
    TextView stars;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_submit);
         weight = findViewById(R.id.weightET);
         stars = findViewById(R.id.starcountTV);
        setTitle("Customer Submit");
    }
   public void submit(View view) {
       if (weight.getText().toString().matches("")) {

           Toast.makeText(CustomerSubmit.this, "Please enter weight", Toast.LENGTH_LONG).show();
       } else {

           ParseObject object = new ParseObject("Weight");

           object.put("weight", weight.getText().toString());
           object.put("username", ParseUser.getCurrentUser().getUsername());
           object.saveInBackground(new SaveCallback() {
               @Override
               public void done(ParseException e) {
                   if (e == null)
                       Toast.makeText(CustomerSubmit.this, "Weight sent for verification", Toast.LENGTH_LONG).show();
                   else {
                       Toast.makeText(CustomerSubmit.this, "Service failed", Toast.LENGTH_LONG).show();
                   }
               }
           });

           cuslogout(view);
       }
   }



    public void cuslogout(View view){
        ParseUser.logOut();

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    public void setStars(){
        Random random = null;
        int randomNum = random.nextInt((1)) ;
        stars.setText(Integer.toString(randomNum));

    }


}