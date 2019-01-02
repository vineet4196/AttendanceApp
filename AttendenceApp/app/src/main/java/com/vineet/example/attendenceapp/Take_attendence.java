package com.vineet.example.attendenceapp;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Take_attendence extends AppCompatActivity {
    LinearLayout linearMain;
    CheckBox checkBox;
    boolean is1;
    String Roll;
    public int m,c=0,g;
    public static int y=0;
    TextView text;
    Button button;
    public  int[] a = new int[500];
    public  int[]b=new int[500];
    ArrayList<String> al = new ArrayList<>();
    public DataBase db = new DataBase(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_attendence);

        linearMain = (LinearLayout) findViewById(R.id.linear_main);
        text = new TextView(this);
        text.setTypeface(text.getTypeface(), Typeface.BOLD_ITALIC);
        text.setTextColor(Color.RED);
        text.setText("PRESENT                         " + "ROLLNO");
        linearMain.addView(text);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

         final String strDate = sdf.format(new Date());
       // Toast.makeText(getApplicationContext(),strDate, Toast.LENGTH_LONG).show();

        m = db.sendout();
        c=0;
        Cursor res=db.getAlldata();
//
        while(res.moveToNext())
        {
            int x=res.getInt(1);
            int y=res.getInt(2);

            a[c]=x;
            b[c]=y;
            c=c+1;
        }
        g=res.getCount();

        for (int i = 0; i < m; i++) {
            checkBox = new CheckBox(this);

            checkBox.setId(i);
            checkBox.setText("                                            " + (i + 1));

            linearMain.addView(checkBox);

        }
        button = new Button(this);
        button.setText("SAVE");
        button.setId(0);
        button.setBackgroundResource(R.color.Green);
        linearMain.addView(button);
        button.setOnClickListener(new View.OnClickListener() {
            CheckBox cb;
            @Override
            public void onClick(View v) {
                al = new ArrayList<>();


                // select_checkbox();
                for (int i = 0; i <m; i++) {
                    cb = (CheckBox) findViewById(i);
                    if (cb.isChecked()) {
                        al.add("P");

                        Roll="ROLLNO_";
                        Roll=Roll+Integer.toString(i+1);




                        is1=db.Insert_Into_Table(Integer.toString(y),strDate,Roll,"P");




                    } else {

                        al.add("A");



                    }
                }
                if(g==0)
                {
                    Toast.makeText(getApplicationContext(), "NO VALUES IN DATABASE", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Take_attendence.this, MainActivity.class);
                    startActivity(intent);
                }

                // y = y + 1;
                for (int i = 0; i <m; i++) {
                    if (al.get(i) == "P") {
                        a[i] = a[i] + 1;
                        double z;
                        y=b[i];
                        y=y+1;
                        z = (a[i]*100) /y;



                        Roll="ROLLNO_";
                        Roll=Roll+Integer.toString(i+1);




                        is1=db.Insert_Into_Table(Integer.toString(y),strDate,Roll,"P");





                  //      Toast.makeText(getApplicationContext(),Integer.toString(y)+"-"+strDate+"-"+Roll+"-P", Toast.LENGTH_SHORT).show();
                        db.updateData(Integer.toString(i + 1), a[i],y, Double.toString(z));
                    }
                    else {
                        double z;
                        y=b[i];
                        y=y+1;
                        z = (a[i] * 100) /y;
                        Roll="ROLLNO_";
                        Roll=Roll+Integer.toString(i+1);
                        is1=db.Insert_Into_Table(Integer.toString(y),strDate,Roll,"A");
                        db.updateData1(Integer.toString(i + 1), a[i],y, Double.toString(z));
                    }
                    if(is1==true)
                        Toast.makeText(getApplicationContext(),"TABLE UPDATED", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(),"TABLE NOT UPDATED", Toast.LENGTH_SHORT).show();

                }

                Toast.makeText(getApplicationContext(), "ATTENDENCE SAVED", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Take_attendence.this, MainActivity.class);
                startActivity(intent);
                al.clear();
            }
        });
    }

}


