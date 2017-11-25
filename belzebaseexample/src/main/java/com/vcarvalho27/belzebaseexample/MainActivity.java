package com.vcarvalho27.belzebaseexample;

import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.vcarvalho27.belzebaseexample.dao.MasterDAO;
import com.vcarvalho27.belzebaseexample.model.Master;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button)findViewById(R.id.create_db)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Master obj = new Master();
                new MasterDAO(MainActivity.this).listAll();
            }
        });
    }
}
