package com.example.poly.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


public class Toolbar extends AppCompatActivity {
    private MenuItem helpMenu;

    protected void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
    }

    public void initToolbar()
    {
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Intent intent;
        switch (item.getItemId()){
            case R.id.movie:
                intent=new Intent(Toolbar.this, MoviesActivity.class);
                startActivity(intent);
                break;

            case R.id.clinic:
                intent=new Intent(Toolbar.this, ClinicActivity.class);
                startActivity(intent);
                break;
            case R.id.octranspo:
                intent=new Intent(Toolbar.this, TransportActivity.class);
                startActivity(intent);
                break;
            default:

        }
        return true;
    }
}
