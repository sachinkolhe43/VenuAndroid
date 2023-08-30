package com.example.venuebooking.entity;

public class DateStorage {
    private static DateStorage instance;
    private String startDate;
    private String endDate;

    private DateStorage() {}

    public static DateStorage getInstance() {
        if (instance == null) {
            instance = new DateStorage();
        }
        return instance;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}

