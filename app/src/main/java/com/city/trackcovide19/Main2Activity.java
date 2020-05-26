package com.city.trackcovide19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
TextView tvCountryName;
TextView TotalCasest;
TextView TodayCasest;
TextView TotalDeatht;
TextView Recoveredt;
TextView Criticalt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tvCountryName=findViewById(R.id.countryName);
        TotalCasest=findViewById(R.id.TotalCases);
        TodayCasest=findViewById(R.id.TodayCases);
        TotalDeatht=findViewById(R.id.TotalDeath);
        Recoveredt=findViewById(R.id.Recovered);
        Criticalt=findViewById(R.id.Critical);
        Intent intent=getIntent();
        String name=intent.getStringExtra("Name");
        String TotalCases=intent.getStringExtra("TotalCases");
        String TodayCases=intent.getStringExtra("TodayCases");
        String TotalDeath=intent.getStringExtra("TotalDeath");
        String Recovered=intent.getStringExtra("Recovered");
        String Critical=intent.getStringExtra("Critical");


        tvCountryName.setText(name);
        TotalCasest.setText(TotalCases);
        TodayCasest.setText(TodayCases);
        TotalDeatht.setText(TotalDeath);
        Recoveredt.setText(Recovered);
        Criticalt.setText(Critical);

    }
}
