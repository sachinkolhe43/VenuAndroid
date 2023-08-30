package com.example.venuebooking.utils;

import com.example.venuebooking.entity.Booking;
import com.example.venuebooking.entity.User;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {

    String BASE_URL ="http://192.168.161.86:4000";

    @POST("/user/login")
    Call<JsonObject> loginUser(@Body User user);

    @POST("/user/register")
    Call<JsonObject> registerUser(@Body User user);
    @GET("/venue/")
   Call<JsonObject> getAllVenues();
    @GET("/user/{id}")
    Call<JsonObject> getUserById(@Path("id")int id);

//    @GET("/service/{id}")
//    Call<JsonObject> getServiceById(@Path("Service_id")int Service_id);


       @GET("/service/{id}/serviceprices")
      Call<JsonObject> getVenueServices(@Path("id")int id);



//    @GET("/user/{id}")
//    Call<JsonObject> getUserById(@Path("id")int id);

//    @GET("/mobile/")
//    Call<JsonObject> getAllMobiles();

    @POST("/booking/bookVenue")
    Call<JsonObject> bookVenue(@Body Booking booking);

    @GET("/booking/{id}")
    Call<JsonObject> getUserBookings(@Path("id")int id);

    @GET("/booking-details/{userId}/{venueId}")
    Call<JsonObject> getBookingDetails(
            @Path("userId") int userId,
            @Path("venueId") int venueId
    );
}