package com.example.lokit.hush;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class fillLoc extends AppCompatActivity {

    private Button cr;
    private TextView tmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bun = getIntent().getExtras();
        String eventtype = bun.getString("eventtype").toString();

        setContentView(R.layout.activity_fill_loc);
        tmp = (TextView) findViewById(R.id.textView2);
        tmp.setText(eventtype);

        Button fab = (Button) findViewById(R.id.select_loc);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectLocation(view);
            }
        });

    }

    public void selectLocation(View view){
        Intent intent = new Intent(fillLoc.this, MapsActivity.class);
        fillLoc.this.startActivity(intent);
        fillLoc.this.finish();
    }

}
