package com.example.venuebooking.entity;

import java.io.Serializable;
import java.util.Date;

public class Booking implements Serializable {
    private int VenueBooking_id;
    private int User_id;
    private int Venue_id;
    private double Total_amount;
    private String Start_date;
    private String End_date;

    public Booking() {
    }

    public Booking(int venueBooking_id, int user_id,
                   int venue_id, double total_amount, Date start_date, Date end_date) {
        VenueBooking_id = venueBooking_id;
        User_id = user_id;
        Venue_id = venue_id;
        Total_amount = total_amount;
        Start_date = String.valueOf(start_date);
        End_date = String.valueOf(end_date);
    }

    public int getVenueBooking_id() {
        return VenueBooking_id;
    }

    public void setVenueBooking_id(int venueBooking_id) {
        VenueBooking_id = venueBooking_id;
    }

    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int user_id) {
        User_id = user_id;
    }

    public int getVenue_id() {
        return Venue_id;
    }

    public void setVenue_id(int venue_id) {
        Venue_id = venue_id;
    }

    public double getTotal_amount() {
        return Total_amount;
    }

    public void setTotal_amount(double total_amount) {
        Total_amount = total_amount;
    }

    public String getStart_date() {
        return Start_date;
    }

    public void setStart_date(String start_date) {
        Start_date = start_date;
    }

    public String getEnd_date() {
        return End_date;
    }

    public void setEnd_date(String end_date) {
        End_date = end_date;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "VenueBooking_id=" + VenueBooking_id +
                ", User_id=" + User_id +
                ", Venue_id=" + Venue_id +
                ", Total_amount=" + Total_amount +
                ", Start_date=" + Start_date +
                ", End_date=" + End_date +
                '}';
    }
}
