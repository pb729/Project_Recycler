package com.parse.starter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class VerifyActivity extends AppCompatActivity {
    String Checkingwegiht;
   ArrayList weightChecker;
   EditText weightReceived;
    String objectId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

          weightReceived = findViewById(R.id.verifyingweight);
        Intent intent = getIntent();
        String username =   intent.getStringExtra("");
        setTitle("Verification page");


      /*  List <ParseObject> results = null;
        try{
            results = query.find();
            if(!results.isEmpty()){
                objectId = results.get(0).getObjectId();
                System.out.println(objectId);
            }

        }catch (com.parse.ParseException e){
            Toast.makeText(VerifyActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
*/



      //   query.orderByDescending("CreatedAt");


        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Weight");

        query.whereEqualTo("username", username);
        query.findInBackground(new FindCallback<ParseObject>() {
           @Override
            public void done(List<ParseObject> objects, ParseException e) {
              if(e==null && objects.size()>0){
                    for(ParseObject object : objects){
                    Log.i("username",object.getString("username"));
                    Log.i("weight",Integer.toString(object.getInt("weight")));
                    }
                }
            }

        });


    }

    public void logoutfunc(View view) {
        ParseUser.logOut();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void verifyfunc(View view){


        if(weightReceived.getText().toString().matches(""))
            Toast.makeText(VerifyActivity.this,"Please enter weight",Toast.LENGTH_LONG).show();
        else {
          /*  for (int i = 0; i < weightChecker.size(); i++) {
                System.out.println(weightChecker.get(i));
            }*/
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }

}