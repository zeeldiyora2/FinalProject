package com.example.poly.finalproject;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ClinicActivity extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "ClinicActivity";

    Button btnDoctor;
    Button btnDentist;
    Button btnOptometrist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_acitivity);
        Log.i(ACTIVITY_NAME, "In OnCreate() of ClinicActivity");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnDoctor = (Button) findViewById(R.id.docBtn);
        btnDentist = (Button) findViewById(R.id.denBtn);
        btnOptometrist = (Button) findViewById(R.id.optBtn);

        btnDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClinicActivity.this, PatientForm.class);
                intent.putExtra("Form","DoctorForm");
                startActivityForResult(intent, 50);
                Log.i(ACTIVITY_NAME, "Doctor was selected");
            }
        });

        btnDentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClinicActivity.this, PatientForm.class);
                intent.putExtra("Form","DentistForm");
                startActivityForResult(intent, 50);
                Log.i(ACTIVITY_NAME, "Dentist was selected");
            }
        });

        btnOptometrist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClinicActivity.this, PatientForm.class);
                intent.putExtra("Form","OptometristForm");
                startActivityForResult(intent, 50);
                Log.i(ACTIVITY_NAME, "Optometrist was selected");
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_patient, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        AlertDialog.Builder patBuilder;
        AlertDialog.Builder patBuilder1;

        switch (item.getItemId()){
            case R.id.patMovies:
                Log.d("ToolBar", "Movies Selected");
                LayoutInflater inflater = ClinicActivity.this.getLayoutInflater();
                patBuilder = new AlertDialog.Builder(ClinicActivity.this);
                patBuilder.setMessage("Do you want to exit the Patient Form and go to Movies app")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent mIntent = new Intent(ClinicActivity.this, MoviesActivity.class);
                                startActivityForResult(mIntent,50);
                            }
                        })

                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Log.i("User Clicked", "No");
                            }
                        });

                AlertDialog dialog = patBuilder.create();
                dialog.show();
                break;

            case R.id.patQuiz:
                Log.d("ToolBar", "Quiz Selected");
                LayoutInflater patQuizInflater = ClinicActivity.this.getLayoutInflater();
                patBuilder = new AlertDialog.Builder(ClinicActivity.this);
                patBuilder.setMessage("Do you want to exit the Patient Form and go to Quiz Creator app")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent mIntent = new Intent(ClinicActivity.this, QuizActivity.class);
                                startActivityForResult(mIntent,50);
                            }
                        })

                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Log.i("User Clicked", "No");
                            }
                        });

                AlertDialog patQuizDialog = patBuilder.create();
                patQuizDialog.show();
                break;

            case R.id.patOCTranspo:
                Log.d("ToolBar", "OCTranspo Selected");
                LayoutInflater patOCInflater = ClinicActivity.this.getLayoutInflater();
                patBuilder = new AlertDialog.Builder(ClinicActivity.this);
                patBuilder.setMessage("Do you want to exit the Patient Form and go to Quiz Creator app")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent mIntent = new Intent(ClinicActivity.this, TransportActivity.class);
                                startActivityForResult(mIntent,50);
                            }
                        })

                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Log.i("User Clicked", "No");
                            }
                        });

                AlertDialog patOCDialog = patBuilder.create();
                patOCDialog.show();
                break;

            case R.id.patAbt:
                Log.d("ToolBar", "About Selected");
                LayoutInflater patAbtInflater = ClinicActivity.this.getLayoutInflater();
                patBuilder = new AlertDialog.Builder(ClinicActivity.this);
                patBuilder.setMessage("Version 1.0 by Zeel Diyora");

                AlertDialog patAbtDialog = patBuilder.create();
                patAbtDialog.show();
                break;

//                Log.d("Toolbar", "About Selected");
//                Toast.makeText(getApplicationContext(), "Version 1.0 by Zeel Diyora", Toast.LENGTH_LONG).show();
//                break;


        }

        return true;
    }
}
