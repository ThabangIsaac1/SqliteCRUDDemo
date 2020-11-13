package com.sidume.freedmores.sqlitecruddemo;

import android.content.Intent;
import android.database.Cursor;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CountryRecyclerActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;

    private RecyclerView recyclerView;
    List<Country> countries;

    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_recycler);

        dbHelper = new DatabaseHelper(this);
        countries = dbHelper.getAllCountries();

        recyclerView = findViewById(R.id.recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new CustomAdapter(this,countries);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.add_record) {

            Intent add_mem = new Intent(this, AddCountryActivity.class);
            startActivity(add_mem);

        }
        return super.onOptionsItemSelected(item);
    }
}
