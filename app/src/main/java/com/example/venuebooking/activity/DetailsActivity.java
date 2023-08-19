package com.example.venuebooking.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.venuebooking.R;
import com.example.venuebooking.entity.Venue;
import com.example.venuebooking.utils.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailsActivity extends AppCompatActivity {

    TextView textVenueName,txtVenueDescription,textVenueContact,textVenueAddress,textVenueBookingPrice;
    ImageView imageView;
    Venue venue;

    
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
        getVenueDetails();
    }
    private void getVenueDetails() {
        textVenueName.setText("Name : "+venue.getVenue_name());
        txtVenueDescription.setText("Description : "+venue.getVenue_description());
        textVenueContact.setText("Contact : "+venue.getVenue_contact());
        textVenueAddress.setText("Address : "+venue.getVenue_address());
        textVenueBookingPrice.setText("Booking Price : "+venue.getVenue_amountPerDay());
        Glide.with(this).load(API.BASE_URL+"/"+venue.getVenue_image()).into(imageView);
    }





}






