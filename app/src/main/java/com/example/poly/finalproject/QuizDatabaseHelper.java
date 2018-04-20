package com.example.poly.finalproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class QuizDatabaseHelper extends SQLiteOpenHelper{
    static final String DATABASE_NAME = "quiz.db";
    static final int VERSION_NUM = 2;
    static final String TABLE_NAME ="QuizTable";
    static String KEY_ID = "_ID";
    static String KEY_QUIZ = "QUIZ";
    static String KEY_ANSWER1 = "ANS1";
    static String KEY_ANSWER2 = "ANS2";
    static String KEY_ANSWER3 = "ANS3";
    static String KEY_ANSWER4 = "ANS4";


    public QuizDatabaseHelper(Context ctx){
        super(ctx, DATABASE_NAME, null, VERSION_NUM );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME +
                "( _ID INTEGER PRIMARY KEY AUTOINCREMENT, QUIZ text, ANS1 text, ANS2 text, ANS3 text, ANS4 text);");
        Log.i("QuizDatabaseHelper", "Calling onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer)
    {
        Log.i("QuizDatabaseHelper", "Calling onUprade, oldVersion="
                + oldVer + "newVersion=" + newVer);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
