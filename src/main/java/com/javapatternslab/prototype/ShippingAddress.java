package com.javapatternslab.prototype;

public class ShippingAddress implements Prototype<ShippingAddress> {

    private String street;
    private String city;

    public ShippingAddress(String street, String city) {
        this.street = street;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public ShippingAddress copy() {
        return new ShippingAddress(street, city);
    }

    @Override
    public String toString() {
        return street + ", " + city;
    }
}
