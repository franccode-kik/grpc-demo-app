package com.example.myapplication.dto;

public class AddressDto {

    String country;
    String city;
    String state;
    String address;
    String postalCode;

    public AddressDto(String country, String city, String state, String address, String postalCode) {
        this.country = country;
        this.city = city;
        this.state = state;
        this.address = address;
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
