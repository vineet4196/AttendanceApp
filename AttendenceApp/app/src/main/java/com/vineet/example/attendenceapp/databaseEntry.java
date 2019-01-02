package com.vineet.example.attendenceapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class databaseEntry extends AppCompatActivity {
    EditText t1;
    Button b1;
    public int t;
    public String x;
    boolean isInsertedto,is;
    int c=0;
    DataBase db=new DataBase(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_entry);
        t1=(EditText)findViewById(R.id.et);
        b1=(Button)findViewById(R.id.btn);


        ADD();
        //UPDATE();

    }

    public void ADD()
    {
       b1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               /*Counting the values*/
               x=t1.getText().toString();
               t=Integer.parseInt(x);
               c=db.sendout();
               if(c==0)
               {

               if(!x.isEmpty()) {

                   for (int i = 1; i <= t; i++) {

                       isInsertedto = db.insertData();
                   }
                   if (isInsertedto == true) {
                       Toast.makeText(getApplicationContext(), "Data inserted", Toast.LENGTH_LONG).show();
                       /*Insert into Date Table*/
                         /*THIS WORKS PROPERLY*/
                         for(int j=1;j<=100;j++)
                          is=db.insertData12();

                       if(is==true)
                           Toast.makeText(getApplicationContext(),"ALL TABLES ARE INTIALIZED", Toast.LENGTH_SHORT).show();
                       else
                           Toast.makeText(getApplicationContext(),"NHI DALA", Toast.LENGTH_SHORT).show();

                       Intent intent = new Intent(databaseEntry.this, MainActivity.class);
                       startActivity(intent);
                   } else
                       Toast.makeText(getApplicationContext(), "Data is not inserted", Toast.LENGTH_LONG).show();
               }

               }
               else
               {
                   Toast.makeText(getApplicationContext(), "Data Already Presented", Toast.LENGTH_LONG).show();
                   Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                   startActivity(intent);
               }
           }
       });
    }

}


