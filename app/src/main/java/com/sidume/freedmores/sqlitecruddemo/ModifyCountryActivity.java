package com.sidume.freedmores.sqlitecruddemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ModifyCountryActivity extends Activity implements OnClickListener {

    private EditText txt_country_name,txt_country_cont, txt_population,txt_currency;
    private Button updateBtn, deleteBtn;


    private int _id;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Modify Record");

        setContentView(R.layout.activity_modify_record);

        dbHelper = new DatabaseHelper(this);

        txt_country_name =  findViewById(R.id.txt_country_name);
        txt_country_cont =  findViewById(R.id.txt_continets);
        txt_population =  findViewById(R.id.txt_population);
        txt_currency = findViewById(R.id.txt_currency);

        updateBtn = findViewById(R.id.btn_update);
        deleteBtn = findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        _id = Integer.parseInt(String.valueOf(intent.getStringExtra("rec_id")));
        String country_name = intent.getStringExtra("country_name");
        String country_cont = intent.getStringExtra("continent");
        String population = intent.getStringExtra("population");
        String currency = intent.getStringExtra("currency");


        txt_country_name.setText(country_name);
        txt_country_cont.setText(country_cont);
        txt_population.setText(population);
        txt_currency.setText(currency);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                String c_name = txt_country_name.getText().toString();
                String c_cont = txt_country_cont.getText().toString();
                double pop = Double.parseDouble(txt_population.getText().toString());
                String curr = txt_currency.getText().toString();
                Toast.makeText(this,"Record Updated",Toast.LENGTH_LONG).show();

                dbHelper.update(_id, c_name,c_cont, pop,curr);
                this.returnHome();
                break;

            case R.id.btn_delete:
                dbHelper.delete(_id);
                this.returnHome();
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), CountryRecyclerActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}
