package com.vineet.example.attendenceapp;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Print_Tabel_Date extends AppCompatActivity {
    LinearLayout linearMain;
    TextView text,text1;
    DataBase db=new DataBase(this);
    int m;
    int x=0,t;
    String s=" LEC \t\t\t\t LECTURE DATE";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print__tabel__date);

        linearMain=(LinearLayout)findViewById(R.id.linear_main);


        text=new TextView(this);
        //String s=" LEC \t\t\t\tDATE\t";
        m=db.sendout();
        for(int i=1;i<=m;i++) {

            String t = "\t\t\t\t\tROLLNO-"+Integer.toString(i);
            s=s+t;
        }
        s=s+"\n";
        text.setText(s);
        text.setTextSize(18);
        text.setTypeface(text.getTypeface(), Typeface.BOLD_ITALIC);
        text.setTextColor(Color.RED);
        linearMain.addView(text);


        /*Printing the data*/


        Cursor res = db.getAlldata1();

        int m=db.sendout();



        while (res.moveToNext())
        {

            String y="  "+res.getString(0)+"\t\t\t\t\t\t"+res.getString(1);
            for(int i=1;i<=m;i++)
            {
                y=y+"\t\t\t\t\t\t\t\t\t\t\t"+res.getString(i+1);
            }
            y=y+"\n";
            text1=new TextView(this);
            text1.setText(y);
            text1.setTextSize(18);
            text1.setTextColor(Color.BLACK);
            linearMain.addView(text1);
        }

    }


}
