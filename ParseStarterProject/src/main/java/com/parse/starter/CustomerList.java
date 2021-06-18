package com.parse.starter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class CustomerList extends AppCompatActivity {

    ListView listView;
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);



        setTitle("Customer List");

        listView = findViewById(R.id.customerlist);
         final ArrayList<String> usernames = new ArrayList<String>();

           arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, usernames);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),VerifyActivity.class);
                intent.putExtra("username",usernames.get(position));
                startActivity(intent);
            }
        });



        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereNotEqualTo("username", ParseUser.getCurrentUser().getUsername());
        query.addAscendingOrder("username");

        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if (e == null) {


                    if (objects.size() > 0) {
                        for (ParseUser object : objects) {
                            usernames.add(object.getUsername());
                        }
                        listView.setAdapter(arrayAdapter);
                    }
                }
                else{
                    e.printStackTrace();
                }
            }
        });
    }
}