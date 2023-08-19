package com.example.venuebooking.entity;

import java.io.Serializable;

public class Venue  implements Serializable {

    private int Venue_id;
    private String Venue_name;
    private String Venue_description;
    private int Venue_contact;
    private String Venue_address;
    private int Venue_amountPerDay;
    private String Venue_image;


    public Venue() {
    }


    public Venue(int venue_id, String venue_name, String venue_description, int venue_contact, String venue_address, int venue_amountPerDay, String venue_image) {
        Venue_id = venue_id;
        Venue_name = venue_name;
        Venue_description = venue_description;
        Venue_contact = venue_contact;
        Venue_address = venue_address;
        Venue_amountPerDay = venue_amountPerDay;
        Venue_image = venue_image;
    }


    public int getVenue_id() {
        return Venue_id;
    }

    public void setVenue_id(int venue_id) {
        Venue_id = venue_id;
    }

    public String getVenue_name() {
        return Venue_name;
    }

    public void setVenue_name(String venue_name) {
        Venue_name = venue_name;
    }

    public String getVenue_description() {
        return Venue_description;
    }

    public void setVenue_description(String venue_description) {
        Venue_description = venue_description;
    }

    public int getVenue_contact() {
        return Venue_contact;
    }

    public void setVenue_contact(int venue_contact) {
        Venue_contact = venue_contact;
    }

    public String getVenue_address() {
        return Venue_address;
    }

    public void setVenue_address(String venue_address) {
        Venue_address = venue_address;
    }

    public int getVenue_amountPerDay() {
        return Venue_amountPerDay;
    }

    public void setVenue_amountPerDay(int venue_amountPerDay) {
        Venue_amountPerDay = venue_amountPerDay;
    }

    public String getVenue_image() {
        return Venue_image;
    }

    public void setVenue_image(String venue_image) {
        Venue_image = venue_image;
    }

    @Override
    public String toString() {
        return "Venue{" +
                "Venue_id=" + Venue_id +
                ", Venue_name='" + Venue_name + '\'' +
                ", Venue_description='" + Venue_description + '\'' +
                ", Venue_contact=" + Venue_contact +
                ", Venue_address='" + Venue_address + '\'' +
                ", Venue_amountPerDay=" + Venue_amountPerDay +
                ", Venue_image='" + Venue_image + '\'' +
                '}';
    }
}
