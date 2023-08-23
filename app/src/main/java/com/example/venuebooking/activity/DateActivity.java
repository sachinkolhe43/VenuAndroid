package com.example.venuebooking.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.venuebooking.R;

import java.util.Calendar;

public class DateActivity extends AppCompatActivity {
TextView textFdate,textTodate,textTotal;
ImageView imageCalander1,imageCalender2;
Button btnNext,btnTotalDays;
int y1,y2;
int m1,m2;
int d1,d2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        textFdate = findViewById(R.id.textFdate);
        imageCalander1 = findViewById(R.id.imageCalander1);
        textTodate = findViewById(R.id.textTodate);
        imageCalender2 = findViewById(R.id.imageCalander2);
        btnNext = findViewById(R.id.btnNext);
        btnTotalDays = findViewById(R.id.btnTotalDays);
        textTotal = findViewById(R.id.textTotal);
        Calendar cal = Calendar.getInstance();
        btnTotalDays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fromDateText = textFdate.getText().toString();
                String toDateText = textTodate.getText().toString();

                if (!fromDateText.isEmpty() && !toDateText.isEmpty()) {
                    String[] fromDateParts = fromDateText.split("/");
                    String[] toDateParts = toDateText.split("/");

                    int fromYear = Integer.parseInt(fromDateParts[0]);
                    int fromMonth = Integer.parseInt(fromDateParts[1]);
                    int fromDay = Integer.parseInt(fromDateParts[2]);

                    int toYear = Integer.parseInt(toDateParts[0]);
                    int toMonth = Integer.parseInt(toDateParts[1]);
                    int toDay = Integer.parseInt(toDateParts[2]);

                    Calendar fromCalendar = Calendar.getInstance();
                    fromCalendar.set(fromYear, fromMonth - 1, fromDay);

                    Calendar toCalendar = Calendar.getInstance();
                    toCalendar.set(toYear, toMonth - 1, toDay);

                    long diffMillis = toCalendar.getTimeInMillis() - fromCalendar.getTimeInMillis();
                    long diffDays = diffMillis / (24 * 60 * 60 * 1000);
                    textTotal.setText(String.valueOf(diffDays));
                    Toast.makeText(DateActivity.this, "Difference in days" + diffDays, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DateActivity.this, "Please select both dates" , Toast.LENGTH_SHORT).show();
                   // textTotal.setText(Long.parseLong(diffDays));
                }
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        imageCalander1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                y1 = cal.get(Calendar.YEAR);
                m1 = cal.get(Calendar.MONTH);
                d1 = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dp = new DatePickerDialog(DateActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        textFdate.setText(i+"/"+i1+"/"+i2);
                    }
                },y1,m1,d1);
                dp.show();
            }
        });
        imageCalender2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                y2 = cal.get(Calendar.YEAR);
                m2 = cal.get(Calendar.MONTH);
                d2 = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dp = new DatePickerDialog(DateActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        textTodate.setText(i+"/"+i1+"/"+i2);
                    }
                },y2,m2,d2);
                dp.show();
            }
        });
    }
}