package com.example.poly.finalproject;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PatientForm extends Activity{

    protected static final String ACTIVITY_NAME = "PatientForm";
    Button patBackBtn;
    Button patSaveBtn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_form);


        Intent patIntent = getIntent();
        String patSelection = patIntent.getStringExtra("Form");


        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch(patSelection)
        {
            case "DoctorForm":

                DoctorFragment patSelDoc = new DoctorFragment ();
                fragmentTransaction.replace(R.id.patFrameLayout,patSelDoc);
                fragmentTransaction.commit ();
                break;

            case "DentistForm":
                DentistFragment patSelDen = new DentistFragment ();
                fragmentTransaction.replace(R.id.patFrameLayout,patSelDen);
                fragmentTransaction.commit ();
                break;

            case "OptometristForm":
                OptometristFragment patSelOpt = new OptometristFragment ();
                fragmentTransaction.replace(R.id.patFrameLayout,patSelOpt);
                fragmentTransaction.commit ();
                break;
            default: break;
        }


        patBackBtn = (Button) findViewById(R.id.patBack);
        patSaveBtn = (Button) findViewById(R.id.patSave);

        patBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientForm.this, ClinicActivity.class);
                startActivityForResult(intent, 50);
            }
        });



//        patSaveBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent clinic = getIntent();
//                String patSelection = clinic.getStringExtra("Form"); // will return "FirstKeyValue
//                Log.i("Value of ",patSelection);
//                Intent intent;
//                switch(patSelection){
//                    case ("DoctorForm"):
//                        intent = new Intent(PatientForm.this, DoctorForm.class);
//                        startActivityForResult(intent,50);
//                        break;
//                    case ("DentistForm"):
//                        intent = new Intent(PatientForm.this, DentistForm.class);
//                        startActivityForResult(intent,50);
//                        break;
//                    case ("OptometristForm"):
//                        intent = new Intent(PatientForm.this, OptometristForm.class);
//                        startActivityForResult(intent,50);
//                        break;
//                }
//
//
//            }
//        });

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
                LayoutInflater patMoviInflater = PatientForm.this.getLayoutInflater();
                patBuilder = new AlertDialog.Builder(PatientForm.this);
                patBuilder.setMessage("Do you want to exit the Patient Form and go to Movies app")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent mIntent = new Intent(PatientForm.this, MoviesActivity.class);
                                startActivityForResult(mIntent,50);
                            }
                        })

                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Log.i("User Clicked", "No");
                            }
                        });

                AlertDialog patMovieDialog = patBuilder.create();
                patMovieDialog.show();
                break;

            case R.id.patQuiz:
                Log.d("ToolBar", "Quiz Selected");
                LayoutInflater patQuizInflater = PatientForm.this.getLayoutInflater();
                patBuilder = new AlertDialog.Builder(PatientForm.this);
                patBuilder.setMessage("Do you want to exit the Patient Form and go to Quiz Creator app")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent mIntent = new Intent(PatientForm.this, QuizActivity.class);
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
                LayoutInflater patOCInflater = PatientForm.this.getLayoutInflater();
                patBuilder = new AlertDialog.Builder(PatientForm.this);
                patBuilder.setMessage("Do you want to exit the Patient Form and go to Quiz Creator app")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent mIntent = new Intent(PatientForm.this, TransportActivity.class);
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
                LayoutInflater patAbtInflater = PatientForm.this.getLayoutInflater();
                patBuilder = new AlertDialog.Builder(PatientForm.this);
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
