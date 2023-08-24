package com.example.venuebooking.activity;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.venuebooking.R;
import com.example.venuebooking.entity.Booking;
import com.example.venuebooking.utils.API;
import com.example.venuebooking.utils.RetrofitClient;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingActivity extends AppCompatActivity {
    TextView textUser_name,textVenue_name,textTotalPrice, textStartDate, textEndDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        textUser_name = findViewById(R.id.textUser_name);
        textVenue_name = findViewById(R.id.textVenue_name);
        textTotalPrice = findViewById(R.id.textTotalPrice);
        textStartDate = findViewById(R.id.textStartDate);
        textEndDate = findViewById(R.id.textEndDate);

        Booking booking = (Booking) getIntent().getSerializableExtra("booking");

        int userId = booking.getUser_id();
        int venueId = booking.getVenue_id();
        double totalAmount = booking.getTotal_amount();
        String startDate = booking.getStart_date();
        String endDate = booking.getEnd_date();


        fetchBookingDetails(userId, venueId);

        textTotalPrice.setText("Total Price: Rs." + totalAmount);
        textStartDate.setText("Start Date: " + startDate);
        textEndDate.setText("End Date: " + endDate);

    }

    private void fetchBookingDetails(int userId, int venueId) {
        API apiInterface = RetrofitClient.getInstance().getApi();

        Call<JsonObject> call = apiInterface.getBookingDetails(userId, venueId);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    JsonObject jsonObject = response.body();

                    String userName = jsonObject.get("User_name").getAsString();
                    String venueName = jsonObject.get("Venue_name").getAsString();

                    textUser_name.setText("User Name: " + userName);
                    textVenue_name.setText("Venue Name: " + venueName);
                } else {
                    // Handle unsuccessful response
                    Toast.makeText(getApplicationContext(), "Error fetching booking details", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                // Handle failure
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

}