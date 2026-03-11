package models;

import models.enums.City;

public class Address {
    private City city;
    private String street_name;
    private int house_no;

    public City get_city() {
        return city;
    }
    public String get_street() {
        return street_name;
    }
    public int get_house_no() {
        return house_no;
    }

    public void set_city(City cityy) {
        city = cityy;
    }
    public void set_street(String street) {
        street_name = street;
    }
    public void set_house_no(int nr) {
        house_no = nr;
    }
    public Address() {}

    public Address(City city, String street_name, int house_no) {
        set_city(city);
        set_street(street_name);
        set_house_no(house_no);
    }

    @Override
    public String toString() {
        return "Address{" +
                "city=" + city +
                ", street_name='" + street_name + '\'' +
                ", house_no=" + house_no +
                '}';
    }
}
