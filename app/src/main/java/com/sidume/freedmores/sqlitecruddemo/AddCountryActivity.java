package com.sidume.freedmores.sqlitecruddemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddCountryActivity extends Activity implements OnClickListener {

    private Button addRecordBtn;
    private EditText txt_country_name, txt_population,txt_currency,txt_continent;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Add Record");

        setContentView(R.layout.activity_add_record);

        txt_country_name =  findViewById(R.id.txt_country_name);
        txt_continent = findViewById(R.id.txt_continent);
        txt_population =  findViewById(R.id.txt_population);
        txt_currency = findViewById(R.id.txt_currency);


        addRecordBtn =  findViewById(R.id.add_record);

        dbHelper = new DatabaseHelper(this);
        addRecordBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_record:

                String c_name = txt_country_name.getText().toString();
                String c_cont = txt_continent.getText().toString();
                double pop = Double.parseDouble(txt_population.getText().toString());
                String curr = txt_currency.getText().toString();

                dbHelper.insert(c_name,c_cont,pop,curr);

                Intent main = new Intent(AddCountryActivity.this, CountryRecyclerActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);
                break;
        }
    }

}