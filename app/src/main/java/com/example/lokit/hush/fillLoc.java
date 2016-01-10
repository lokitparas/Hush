package com.example.lokit.hush;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;

public class fillLoc extends AppCompatActivity {
    public static final String TABLE_RECORDS = "records";
    public static final String EVENT_NAME = "event_name";
    public static final String EVENT_TYPE = "event_type";
    public static final String START_TIME = "start_time";
    private static final String END_TIME = "end_time";
    private static final String MUTE = "mute" ;
    private static final String VIBRATE = "vibrate" ;
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String RADIUS = "radius";

    private Button cr;
    private TextView tmp;
    private TextView Locdata;
    private Double currLong;
    private Double currLat;
    private Button fab;
    private Button Add;
    public Double rad;
    public Double Longit;
    public Double Lat;
    protected SQLiteDatabase mDB;
    int Loc_req = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDB = ((MyApplication)getApplication()).mDB;
        Bundle bun = getIntent().getExtras();
        final String eventtype = bun.getString("eventtype").toString();

        setContentView(R.layout.activity_fill_loc);
        tmp = (TextView) findViewById(R.id.textView2);
        Locdata = (TextView) findViewById(R.id.Locdata);
        tmp.setText(eventtype);
        Add = (Button) findViewById(R.id.addtsk);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Record temp = new Record();

                final EditText ename = (EditText) findViewById(R.id.editText);
                final TimePicker stime = (TimePicker) findViewById(R.id.timePicker);
                final TimePicker etime = (TimePicker) findViewById(R.id.timePicker2);
                final CheckBox mute = (CheckBox) findViewById(R.id.mute);
                final CheckBox vibrate = (CheckBox) findViewById(R.id.vibr);
                temp.setename(ename.getText().toString());
                temp.setetype(eventtype);
                stime.clearFocus();
                etime.clearFocus();
                String hour = String.valueOf(stime.getCurrentHour());
                String hour1 = String.valueOf(etime.getCurrentHour());
                String minute = String.valueOf(stime.getCurrentMinute());
                String minute1 = String.valueOf(etime.getCurrentMinute());
                temp.setstime(hour + ":" + minute);
                temp.setetime(hour1 + ":" + minute1);
                if(mute.isChecked()){temp.setmute(1);}
                else{temp.setmute(0);}
                if(vibrate.isChecked()){temp.setvibr(1);}
                else{temp.setvibr(0);}
                ArrayList<Double> temp1 = new ArrayList<Double>();
                temp1.add(Lat);
                temp1.add(Longit);
                temp1.add(rad);
//                Toast.makeText(fillLoc.this,Lat,Toast.LENGTH_SHORT).show();
                temp.setLocation(temp1);
                addRecord(temp);

                Intent intent = new Intent(fillLoc.this, Home.class);
                fillLoc.this.startActivity(intent);
                fillLoc.this.finish();
            }
        });
        fab = (Button) findViewById(R.id.select_loc);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectLocation(view);
            }
        });

    }

    public void selectLocation(View view){
        Intent intent = new Intent(fillLoc.this, MapsActivity.class);
        startActivityForResult(intent, Loc_req);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == Loc_req) {
            if (resultCode == RESULT_OK) {

                // Toast.makeText(this, data.getData().toString(),
                //Toast.LENGTH_SHORT).show();
                 rad = Double.parseDouble(data.getStringExtra("radius"));
                 Longit = Double.parseDouble(data.getStringExtra("Long"));
                 Lat = Double.parseDouble(data.getStringExtra("Lat"));
//                Toast.makeText(fillLoc.this,rad,Toast.LENGTH_SHORT).show();
//                Toast.makeText(fillLoc.this,Long,Toast.LENGTH_SHORT).show();
//                Toast.makeText(fillLoc.this,Lat,Toast.LENGTH_SHORT).show();
                currLat = Double.parseDouble(data.getStringExtra("CurrLat"));
                currLong = Double.parseDouble(data.getStringExtra("CurrLong"));

                Locdata.setText("Your location is stored");
                Locdata.setVisibility(View.VISIBLE);
                fab.setText("Change Location");
            }

        }
    }

    public void addRecord(Record record) {

        ContentValues values = new ContentValues();
        values.put(EVENT_NAME, record.getename()); // Contact Name
        values.put(EVENT_TYPE, record.getetype()); // Contact Phone Number
        values.put(START_TIME, record.getstime()); // Contact Phone Number
        values.put(END_TIME, record.getetime());
        values.put(MUTE, record.getmute());
        values.put(VIBRATE, record.getvibr());
        values.put(LATITUDE, record.getLocation().get(0));
        values.put(LONGITUDE, record.getLocation().get(1));
        //values.put(RADIUS, record.getLocation().get(2));

        // Inserting Row
        mDB.insert(TABLE_RECORDS, null, values);

//        String ROW1 = "INSERT INTO records (end_time,radius,longitude,event_name,latitude,mute,start_time,event_type,vibrate)"+
//                    "Values ("+ "\'"+ record.getetime() + "\',"+record.getLocation().get(2)+","+record.getLocation().get(1)+","+
//                    "\'"+record.getename()+"\',"+record.getLocation().get(0)+","+record.getmute()+",\'"+record.getstime()+"\',"+
//                    "\'"+record.getetype()+"\',"+record.getvibr()+")";
//
//        mDB.execSQL(ROW1);
    }

}
