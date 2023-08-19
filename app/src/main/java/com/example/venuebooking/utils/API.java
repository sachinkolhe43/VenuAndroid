package com.example.venuebooking.utils;

import com.example.venuebooking.entity.User;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface API {

    String BASE_URL ="http://192.168.1.16:4000";

    @POST("/user/login")
    Call<JsonObject> loginUser(@Body User user);

    @POST("/user/register")
    Call<JsonObject> registerUser(@Body User user);
    @GET("/venue/")
   Call<JsonObject> getAllVenues();

    @GET("/user/{id}")
    Call<JsonObject> getUserById(@Path("id")int id);

    @GET("/service/{id}/service-prices")
    Call<List<String>> getVenueServices(@Path("venueId") int venueId);


                // Handle failure

//    @GET("/user/{id}")
//    Call<JsonObject> getUserById(@Path("id")int id);

//    @GET("/mobile/")
//    Call<JsonObject> getAllMobiles();
//
//    @POST("/orders/placeorder")
//    Call<JsonObject> placeOrder(@Body Order order);
//
//    @GET("/orders/{id}")
//    Call<JsonObject> getuserOrders(@Path("id")int id);
}