package com.parse.starter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {
    EditText adminid ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        adminid = findViewById(R.id.adminID);

        setTitle("Admin Login");
    }


    public void adminLogIN(View view){


        if(adminid.getText().toString().matches("123456")){


            Intent intent = new Intent(getApplicationContext(),CustomerList.class);
            startActivity(intent);
            Toast.makeText(AdminLogin.this,"Login Successful",Toast.LENGTH_LONG).show();

        }
        else
            Toast.makeText(AdminLogin.this,"Invalid ID",Toast.LENGTH_LONG).show();




    }

}