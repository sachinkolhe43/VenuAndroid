package com.example.venuebooking.activity;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.venuebooking.R;
import com.example.venuebooking.utils.RetrofitClient;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingActivity extends AppCompatActivity {
    TextView textUser_name,textVenue_name,textVenuePrice,
            textServicePrice,textTotalPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        textUser_name = findViewById(R.id.textUser_name);
        textVenue_name = findViewById(R.id.textVenue_name);
        textVenuePrice = findViewById(R.id.textVenuePrice);
        textServicePrice = findViewById(R.id.textServicePrice);
        textTotalPrice = findViewById(R.id.textTotalPrice);
    }

}