package hu.bba.myfirstapp.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root()
public class Address implements Serializable {

    @Attribute(name = "country")
    private String country;

    @Attribute(name = "zip")
    private int zip;

    @Attribute(name = "city")
    private String city;

    @Attribute(name = "street")
    private String street;

    @Attribute(name = "streetType")
    private String streetType;

    @Attribute(name = "number")
    private String number;

    public Address(String country, int zip, String city, String street, String streetType, String number) {
        this.country = country;
        this.zip = zip;
        this.city = city;
        this.street = street;
        this.streetType = streetType;
        this.number = number;
    }

    Address() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetType() {
        return streetType;
    }

    public void setStreetType(String streetType) {
        this.streetType = streetType;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return country + " " + zip + " " + city + " " + street + " " + streetType + " " + number;
    }
}
