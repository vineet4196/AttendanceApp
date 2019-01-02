package com.vineet.example.attendenceapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.SimpleDateFormat;

import java.util.Date;


/**
 * Created by Vineet on 4/2/2018.
 */
public class DataBase extends SQLiteOpenHelper {
    public String  c="ROLLNO_";
    public static final String DATABASE_NAME = "ATTENDENCE_DATABASE.db"; // database name
    public static final String TABLE_NAME="Attendence";
    public static final String TABLE_NAME1="Attendence2";
    public final String COL_1 = "ROLLNO";
    public final String COL_2 = "LECTURE_ATTENDED";
    public final String COL_3 = "TOTAL_LECTURE_DELIVERED"; // 4 final col for database
    public final String COL_4 = "PERCENTAGE";
    public double total_percentage=0.0;
     public int x;


    /*NEW TABLE NAME AND COLUMN NAME*/
    public static final String TABLE_NAME2="DATE_WISE_ATTENDANCE";
    public  final String LECNO="LECTURE";
    String s1;

    // function to get date
    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String s="create table " + TABLE_NAME +
                "( ROLLNO INTEGER PRIMARY KEY AUTOINCREMENT, LECTURE_ATTENDED INTEGER, TOTAL_LECTURE_DELIVERED INTEGER, PERCENTAGE TEXT)";

        db.execSQL(s);
        db.execSQL("create table Attendence2 (LECNO INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,ROLLNO_1 TEXT,ROLLNO_2 TEXT,ROLLNO_3 TEXT,ROLLNO_4 TEXT,ROLLNO_5 TEXT,ROLLNO_6 TEXT,ROLLNO_7 TEXT,ROLLNO_8 TEXT,ROLLNO_9 TEXT,ROLLNO_10 TEXT,ROLLNO_11 TEXT,ROLLNO_12 TEXT,ROLLNO_13 TEXT,ROLLNO_14 TEXT,ROLLNO_15 TEXT,ROLLNO_16 TEXT,ROLLNO_17 TEXT,ROLLNO_18 TEXT,ROLLNO_19 TEXT,ROLLNO_20 TEXT,ROLLNO_21 TEXT,ROLLNO_22 TEXT,ROLLNO_23 TEXT,ROLLNO_24 TEXT,ROLLNO_25 TEXT,ROLLNO_26 TEXT,ROLLNO_27 TEXT,ROLLNO_28 TEXT,ROLLNO_29 TEXT,ROLLNO_30 TEXT,ROLLNO_31 TEXT,ROLLNO_32 TEXT,ROLLNO_33 TEXT,ROLLNO_34 TEXT,ROLLNO_35 TEXT,ROLLNO_36 TEXT,ROLLNO_37 TEXT,ROLLNO_38 TEXT,ROLLNO_39 TEXT,ROLLNO_40 TEXT,ROLLNO_41 TEXT,ROLLNO_42 TEXT,ROLLNO_43 TEXT,ROLLNO_44 TEXT,ROLLNO_45 TEXT,ROLLNO_46 TEXT,ROLLNO_47 TEXT,ROLLNO_48 TEXT,ROLLNO_49 TEXT,ROLLNO_50 TEXT,ROLLNO_51 TEXT,ROLLNO_52 TEXT,ROLLNO_53 TEXT,ROLLNO_54 TEXT,ROLLNO_55 TEXT,ROLLNO_56 TEXT,ROLLNO_57 TEXT,ROLLNO_58 TEXT,ROLLNO_59 TEXT,ROLLNO_60 TEXT,ROLLNO_61 TEXT,ROLLNO_62 TEXT,ROLLNO_63 TEXT,ROLLNO_64 TEXT,ROLLNO_65 TEXT,ROLLNO_66 TEXT,ROLLNO_67 TEXT,ROLLNO_68 TEXT,ROLLNO_69 TEXT,ROLLNO_70 TEXT,ROLLNO_71 TEXT,ROLLNO_72 TEXT,ROLLNO_73 TEXT,ROLLNO_74 TEXT,ROLLNO_75 TEXT,ROLLNO_76 TEXT,ROLLNO_77 TEXT,ROLLNO_78 TEXT,ROLLNO_79 TEXT,ROLLNO_80 TEXT,ROLLNO_81 TEXT,ROLLNO_82 TEXT,ROLLNO_83 TEXT,ROLLNO_84 TEXT,ROLLNO_85 TEXT,ROLLNO_86 TEXT,ROLLNO_87 TEXT,ROLLNO_88 TEXT,ROLLNO_89 TEXT,ROLLNO_90 TEXT,ROLLNO_91 TEXT,ROLLNO_92 TEXT,ROLLNO_93 TEXT,ROLLNO_94 TEXT,ROLLNO_95 TEXT,ROLLNO_96 TEXT,ROLLNO_97 TEXT,ROLLNO_98 TEXT,ROLLNO_99 TEXT,ROLLNO_100 TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // on upgrade DROP OLDER TABLES.

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);

        onCreate(db);
    }

    public boolean updateData(String id,int x,int y,String z)
    {
        if(!id.isEmpty()) {
               z=z+"%";
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COL_1, id);
            values.put(COL_2,x);
            values.put(COL_3, y);
            values.put(COL_4, z);
            db.update(TABLE_NAME, values, "ROLLNO = ?", new String[]{id});
            return true;
        }
         else
            return false;
    }

    public boolean updateData1(String id,int x,int y,String z)
    {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COL_1, id);
            values.put(COL_2, x);
            values.put(COL_3, y);
             z=z+"%";
            values.put(COL_4,z);
            db.update(TABLE_NAME, values, "ROLLNO = ?", new String[]{id});
            return true;
    }

    /*INSERT INTO DATE TABLE2*/

    public  boolean insertData12() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put("DATE", "-");

        for(int i=1;i<=100;i++) {
            c =c+Integer.toString(i);
            values.put(c,"-");
            c="ROLLNO_";
        }

        long r= db.insert(TABLE_NAME1, null,values);
        if(r==-1)
            return false;
        else
            return true;
    }

    /*INSERT INTO TABLE1*/
    public  boolean insertData() {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2,0);
        values.put(COL_3,0);
        values.put(COL_4,total_percentage);
        long result= db.insert(TABLE_NAME, null,values);
        if(result==-1)
            return false;
        else
            return true;

    }

    /*Update TABLE DATEWISE ATTENDENACE*/

    public boolean Insert_Into_Table(String id,String date1,String col_name,String status)
    {
       SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("LECNO",id);
        values.put("DATE",date1);
        values.put(col_name,status);

        long r=db.update(TABLE_NAME1, values, "LECNO = ?", new String[]{id});



        if(r==-1)
            return false;
        else
            return true;
    }


    public int sendout()
    {
        Cursor m=getAlldata();
        x=m.getCount();
        return x;
    }

    public Cursor getAlldata(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("Select *from "+TABLE_NAME,null);
        return res;
    }

    public Cursor getAlldata1(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res1=db.rawQuery("Select *from "+TABLE_NAME1,null);
        return res1;
    }


}




