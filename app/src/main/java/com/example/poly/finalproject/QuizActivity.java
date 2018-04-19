package com.example.poly.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class QuizActivity extends Activity
{
    Button quitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        quitButton = findViewById(R.id.btnquit);
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent quizIntend = new Intent(QuizActivity.this,MainActivity.class);
                startActivityForResult(quizIntend,50);
            }
        });
    }
}
