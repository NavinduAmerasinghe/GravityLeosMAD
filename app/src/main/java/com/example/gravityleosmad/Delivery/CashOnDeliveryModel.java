package com.example.gravityleosmad.Delivery;

public class CashOnDeliveryModel {

    String House_Address, Street, City, Phone_Number, Email;
    CashOnDeliveryModel()
    {

    }

    public CashOnDeliveryModel(String house_Address, String street, String city, String phone_Number, String email) {
        House_Address = house_Address;
        Street = street;
        City = city;
        Phone_Number = phone_Number;
        Email = email;
    }

    public String getHouse_Address() {
        return House_Address;
    }

    public void setHouse_Address(String house_Address) {
        House_Address = house_Address;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getPhone_Number() {
        return Phone_Number;
    }

    public void setPhone_Number(String phone_Number) {
        Phone_Number = phone_Number;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
