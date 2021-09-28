package com.example.gravityleosmad.Delivery;

public class PickAndGoModel {

    String Pick_Up_Date, Pick_Up_Time, Pick_Up_Location,Number;
    PickAndGoModel()
    {

    }

    public PickAndGoModel(String Pick_Up_Date, String Pick_Up_Time, String Pick_Up_Location, String Number) {
        Pick_Up_Date = Pick_Up_Date;
        Pick_Up_Time = Pick_Up_Time;
        Pick_Up_Location = Pick_Up_Location;
        Number = Number;

    }

    public String getPick_Up_Date() {
        return Pick_Up_Date;
    }

    public void setPick_Up_Date(String Pick_Up_Date) {
        Pick_Up_Date = Pick_Up_Date;
    }

    public String getPick_Up_Time() {
        return Pick_Up_Time;
    }

    public void setPick_Up_Time(String Pick_Up_Time) {
        Pick_Up_Time = Pick_Up_Time;
    }

    public String getPick_Up_Location() {
        return Pick_Up_Location;
    }

    public void setPick_Up_Location(String Pick_Up_Location) {
        Pick_Up_Location = Pick_Up_Location;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String Number) {
        Number = Number;
    }
}


