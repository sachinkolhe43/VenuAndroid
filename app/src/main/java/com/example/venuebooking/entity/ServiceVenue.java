package com.example.venuebooking.entity;

public class ServiceVenue
{
    private int  Venue_id ;
   private int Service_id;
   private double Service_amount;

    public ServiceVenue(int venue_id, int service_id, double service_amount) {
        Venue_id = venue_id;
        Service_id = service_id;
        Service_amount = service_amount;
    }

    public int getVenue_id() {
        return Venue_id;
    }

    public void setVenue_id(int venue_id) {
        Venue_id = venue_id;
    }

    public int getService_id() {
        return Service_id;
    }

    public void setService_id(int service_id) {
        Service_id = service_id;
    }

    public double getService_amount() {
        return Service_amount;
    }

    public void setService_amount(double service_amount) {
        Service_amount = service_amount;
    }

    public ServiceVenue() {
    }

    @Override
    public String toString() {
        return "ServiceVenue{" +
                "Venue_id=" + Venue_id +
                ", Service_id=" + Service_id +
                ", Service_amount=" + Service_amount +
                '}';
    }
}

