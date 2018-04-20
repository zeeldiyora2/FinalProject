package com.example.poly.finalproject;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class QuizActivity extends Toolbar
{
    protected static final String ACTIVITY_NAME = "QuizMain";
    ListView listViewMain;
    Button newQuiz;
    ArrayList<String> quizMessage;
    ContentValues cv;
    static QuizAdapter quizAdapter;
    QuizDatabaseHelper helper;
    SQLiteDatabase db;
    Cursor c;
    String ans1, ans2, ans3, ans4, question, questionType, ans;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        initToolbar();

        listViewMain = (ListView) findViewById(R.id.listviewMain);
        newQuiz = (Button) findViewById(R.id.newQuiz);
        quizMessage = new ArrayList<>();
        cv = new ContentValues();
        quizAdapter = new QuizAdapter(this);
        listViewMain.setAdapter(quizAdapter);
        helper = new QuizDatabaseHelper(this);
        db = helper.getWritableDatabase();

        try
        {
            helper.onCreate(db);
        }
        catch (SQLiteException e) {}

        c = db.query(false, helper .TABLE_NAME, new String[]{helper.KEY_ID,
                        helper.KEY_QUIZ,helper.KEY_ANSWER1,helper.KEY_ANSWER2,
                        helper.KEY_ANSWER3, helper.KEY_ANSWER4},
                null,null,null,null,null,null);
        c.moveToFirst();
        if(c!=null&&c.moveToFirst()) {
            while (!c.isLast()) {
                quizMessage.add(c.getString(c.getColumnIndex(helper.KEY_QUIZ)));
                Log.i(ACTIVITY_NAME, "SQL MESSAGE:" + c.getString(c.getColumnIndex(helper.KEY_QUIZ)));
                c.moveToNext();
            }
        }
        Log.i(ACTIVITY_NAME, "Cursor's column count = " + c.getColumnCount());
        for(int i=0; i<c.getColumnCount(); i++){
            Log.i(ACTIVITY_NAME,c.getColumnName(i));
        }
        listViewMain.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putLong("ID", quizAdapter.getItemId(i));
                bundle.putString("quiz", quizAdapter.getItem(i));
                bundle.putInt("position", i);

                Intent displayQuiz = new Intent(QuizActivity.this, DisplayQuiz.class);
                displayQuiz.putExtras(bundle);
                startActivity(displayQuiz);
            }
        });

        newQuiz.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.i(ACTIVITY_NAME, "New quiz clicked()");
                Intent newQuiz = new Intent(QuizActivity.this,CreateQuiz.class);
                Log.i(ACTIVITY_NAME, "in intend");
                startActivity(newQuiz);
                Log.i(ACTIVITY_NAME, "starting the activity");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1)
        {
            if(resultCode == RESULT_OK){
                questionType = data.getStringExtra("type");
                question = data.getStringExtra("question");
                quizMessage.add(question);
                ContentValues cv = new ContentValues();
                if(questionType.equalsIgnoreCase("mc")){
                    ans1 = data.getStringExtra("ans1");
                    ans2 = data.getStringExtra("ans2");
                    ans3 = data.getStringExtra("ans3");
                    ans4 = data.getStringExtra("ans4");
                    cv.put(helper.KEY_QUIZ,question);
                    cv.put(helper.KEY_ANSWER1, ans1);
                    cv.put(helper.KEY_ANSWER2, ans2);
                    cv.put(helper.KEY_ANSWER3, ans3);
                    cv.put(helper.KEY_ANSWER4, ans4);
                }
                else if (questionType.equalsIgnoreCase("tf")
                        ||questionType.equalsIgnoreCase("nu")){
                    ans=data.getStringExtra("ans");
                    cv.put(helper.KEY_QUIZ,question);
                    cv.put(helper.KEY_ANSWER1, ans);
                    cv.put(helper.KEY_ANSWER2, 0);
                    cv.put(helper.KEY_ANSWER3, 0);
                    cv.put(helper.KEY_ANSWER4, 0);
                }
                db.insert(helper.TABLE_NAME, "null Replacement Value", cv);
                quizAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public boolean onPrepareOptionsMenu (Menu menu)
    {

        menu.findItem(R.id.help).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                new AlertDialog.Builder(QuizActivity.this)
                        .setTitle("Help")
                        .setMessage("Activity developed by Vijaykumar "+ "\n" +
                                "Version: 1.0"+ "\n" +
                                "This activity is designed to create 3 types of quizzes, " +
                                "multiple choice, true or false, and numeric "+
                                "You can view, add, update and delete quiz records. ")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {

                            }
                        }).show();
                return true;
            }
        });
        return true;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        db.close();
    }

    public class QuizAdapter extends ArrayAdapter<String> {
        QuizAdapter(Context ctx) {super(ctx, 0);}
        public int getCount(){return quizMessage.size();}

        public String getItem(int position){return quizMessage.get(position);}

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = QuizActivity.this.getLayoutInflater();
            View result = null;
            result = inflater.inflate(R.layout.quiz_row, null);

            TextView quiz_row = (TextView)result.findViewById(R.id.quizRow);
            quiz_row.setText(getItem(position));
            return result;
        }

        public long getId(int position){
            return position;
        }

        public long getItemId(int position){
            c.moveToPosition(position);
            return c.getLong(c.getColumnIndex(helper.KEY_ID));
        }
    }
}
