package com.vineet.example.attendenceapp;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Print_Table extends AppCompatActivity {
    LinearLayout linearMain;
   // public databaseEntry dbe=new databaseEntry();
    DataBase db;

    TextView text,text1;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print__table);
        linearMain=(LinearLayout)findViewById(R.id.linear_main);
        db=new DataBase(this);
        text=new TextView(this);
        text.setText("ID    PRESENTS    TOTAL LEC    PERCENTAGE\n");
        text.setTypeface(text.getTypeface(), Typeface.BOLD_ITALIC);
        text.setTextColor(Color.RED);

        linearMain.addView(text);

         /*PRINT DATA*/

        Cursor res=db.getAlldata();

        while(res.moveToNext()){
            text1=new TextView(this);

           text1.setText(" "+res.getString(0)+"           "+res.getString(1)+"              "+res.getString(2)+"             "+res.getString(3)+"\n\n");
         //   text1.setText(" "+res.getString(0)+"           "+res.getString(1)+"\n");
        //   text1.setText("\n"+res.getString(0)+res.getString(1)+res.getString(2)+res.getString(3)+res.getString(4)+res.getString(5)+res.getString(6)+res.getString(7));

            text1.setTextSize(18);
            text1.setTextColor(Color.BLACK);
            linearMain.addView(text1);

        }
        button = new Button(this);
        button.setText("DONE");
        button.setId(0);
        button.setBackgroundResource(R.color.Green);
        linearMain.addView(button);
         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                 startActivity(intent);
             }
         });

    }


}
