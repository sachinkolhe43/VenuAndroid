package com.example.venuebooking.activity;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.venuebooking.R;
import com.example.venuebooking.entity.Booking;
import com.example.venuebooking.entity.Venue;
import com.example.venuebooking.utils.API;
import com.example.venuebooking.utils.RetrofitClient;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {

    TextView textVenueName,txtVenueDescription,textVenueContact,textVenueAddress,textVenueBookingPrice,textTotalAmount;
    ImageView imageView;
    Venue venue;

    private CheckBox checkBoxCatering;
    private CheckBox checkBoxDecoration;
    private CheckBox checkBoxTransportation;
    private CheckBox checkBoxSoundSystem;
    private CheckBox checkBoxPhotography;

    private double totalAmount = 0.0;
    private Map<String, Double> servicePrices = new HashMap<>();


    private static final String PREFS_NAME = "DatePrefs";
    private static final String FROM_DATE_KEY = "from_date";
    private static final String TO_DATE_KEY = "to_date";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        textVenueName = findViewById(R.id.textVenueName);
        txtVenueDescription = findViewById(R.id.textVenueDescription);
        textVenueContact = findViewById(R.id.textVenueContact);
        textVenueAddress = findViewById(R.id.textVenueAddress);
        textVenueBookingPrice = findViewById(R.id.textVenueBookingPrice);
        imageView = findViewById(R.id.imageView);
        venue = (Venue) getIntent().getSerializableExtra("venue");
        textTotalAmount = findViewById(R.id.textTotalAmount); // TextView to display total amount

        // Initialize CheckBoxes
        checkBoxCatering = findViewById(R.id.checkBoxCatering);
        checkBoxDecoration = findViewById(R.id.checkBoxDecoration);
        checkBoxTransportation = findViewById(R.id.checkBoxTransportation);
        checkBoxSoundSystem = findViewById(R.id.checkBoxSoundSystem);
        checkBoxPhotography = findViewById(R.id.checkBoxPhotography);

        // Set service prices (modify this according to your data)
        servicePrices.put("Catering", 1000.0);
        servicePrices.put("Decoration", 500.0);
        servicePrices.put("Transportation", 300.0);
        servicePrices.put("SoundSystem", 800.0);
        servicePrices.put("Photography", 1200.0);

        checkBoxCatering.setText("Catering (Rs." + servicePrices.get("Catering") + ")");
        checkBoxDecoration.setText("Decoration (Rs." + servicePrices.get("Decoration") + ")");
        checkBoxTransportation.setText("Transportation (Rs." + servicePrices.get("Transportation") + ")");
        checkBoxSoundSystem.setText("Sound System (Rs." + servicePrices.get("SoundSystem") + ")");
        checkBoxPhotography.setText("Photography (Rs." + servicePrices.get("Photography") + ")");

        checkBoxCatering.setOnClickListener(this::onServiceCheckboxChanged);
        checkBoxDecoration.setOnClickListener(this::onServiceCheckboxChanged);
        checkBoxTransportation.setOnClickListener(this::onServiceCheckboxChanged);
        checkBoxSoundSystem.setOnClickListener(this::onServiceCheckboxChanged);
        checkBoxPhotography.setOnClickListener(this::onServiceCheckboxChanged);

        getVenueDetails();
       onTotalPrice(null);
    }
    private void getVenueDetails() {
        textVenueName.setText("Name : "+venue.getVenue_name());
        txtVenueDescription.setText("Description : "+venue.getVenue_description());
        textVenueContact.setText("Contact : "+venue.getVenue_contact());
        textVenueAddress.setText("Address : "+venue.getVenue_address());
        textVenueBookingPrice.setText("Booking Price : "+venue.getVenue_amountPerDay());
        Glide.with(this).load(API.BASE_URL+"/"+venue.getVenue_image()).into(imageView);
    }
    public void onServiceCheckboxChanged(View view) {
        CheckBox checkBox = (CheckBox) view;
        String serviceName = checkBox.getText().toString();
        double serviceAmount = getServiceAmount(serviceName);
        if (checkBox.isChecked()) {
            totalAmount += serviceAmount;
            Log.d("Checkbox", serviceName + " checked. Total amount: " + totalAmount);
        } else {
            totalAmount -= serviceAmount;
            Log.d("Checkbox", serviceName + " unchecked. Total amount: " + totalAmount);
        }
    }
    private double getServiceAmount(String serviceName) {
        if (servicePrices.containsKey(serviceName)) {
            return servicePrices.get(serviceName);
        } else {
            return 0.0; // Default value if service price is not found
        }
    }
    public void onTotalPrice(View view) {
        double calculatedTotal = venue.getVenue_amountPerDay();
        if (checkBoxCatering.isChecked()) {
            calculatedTotal += getServiceAmount("Catering");
        }
        if (checkBoxDecoration.isChecked()) {
            calculatedTotal += getServiceAmount("Decoration");
        }
        if (checkBoxTransportation.isChecked()) {
            calculatedTotal += getServiceAmount("Transportation");
        }
        if (checkBoxSoundSystem.isChecked()) {
            calculatedTotal += getServiceAmount("SoundSystem");
        }
        if (checkBoxPhotography.isChecked()) {
            calculatedTotal += getServiceAmount("Photography");
        }
        // double venueBookingPrice = venue.getVenue_amountPerDay();
        // double totalPrice = venueBookingPrice + calculatedTotal;
        // textTotalAmount.setText("Total: Rs." + totalPrice);
        textTotalAmount.setText( "Total Amount : " + calculatedTotal);
    }

    public void book(View view) {

        int User_id = getSharedPreferences("VENUEBOOKING",MODE_PRIVATE).getInt("User_id",0);
        int Venue_id = venue.getVenue_id();


        Booking booking = new Booking();
        booking.setUser_id(User_id);
        booking.setVenue_id(Venue_id);

        String[] totalAmountSplit = textTotalAmount.getText().toString().split(":");
        booking.setTotal_amount(Double.parseDouble(totalAmountSplit[1]));


        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String Start_date = preferences.getString(FROM_DATE_KEY, "");
        String End_date = preferences.getString(TO_DATE_KEY, "");

        booking.setStart_date(Start_date); // Set the retrieved from date
        booking.setEnd_date(End_date);






        RetrofitClient.getInstance().getApi().bookVenue(booking).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.body().getAsJsonObject().get("status").getAsString().equals("success")){
                    Toast.makeText(DetailsActivity.this, "Booking Confirmed", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(DetailsActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

        Intent intent = new Intent(this, BookingActivity.class);
        startActivity(intent);
    }
}