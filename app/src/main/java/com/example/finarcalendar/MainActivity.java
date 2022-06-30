package com.example.finarcalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button b1,b2,b3;
    CalendarView c1;
    TextView t1,t2;
    EditText e1;
    String data;
    Map<String,String> events;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b2:
                String text = e1.getText().toString();
                String e = t1.getText().toString();
                t2.setText(text);
                events.put(e, text);
                Toast.makeText(getApplicationContext(), "Message saved", Toast.LENGTH_SHORT).show();
                break;
            case R.id.b3:
                sendEmail();

        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t2 = findViewById(R.id.t2);
//        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        t1 = findViewById(R.id.t1);
        e1 = findViewById(R.id.e1);
        b3= findViewById(R.id.b3);
//        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        c1 = findViewById(R.id.calendarView);
        events = new HashMap<>();
//        Intent intent = getIntent();
//        if(intent.hasExtra("data")){
//            data = intent.getExtras().getString("data");
//            t2.setText(data);
//        }
//        else {
//            data = new String("");
//            t2.setText("fsdfwefrwefwef");
//        }

        c1.setOnDateChangeListener(
                new CalendarView
                        .OnDateChangeListener() {
                    @Override

                    public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth)
                    {
                        String Date
                                = dayOfMonth + "-"
                                + (month + 1) + "-" + year;
                        t1.setText(Date);
                        if(events.containsKey(Date))
                            t2.setText(events.get(Date));
                        else
                            t2.setText("Not Found");
                    }
                });

    }



    protected void sendEmail() {
        Log.i("Send email", "");
        String[] TO = {"spcrsankar@gmail.com"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
//        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Hello");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "hello from my android app  " + t2.getText() + " on " + t1.getText());

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished...", "");  // to print log cat
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}