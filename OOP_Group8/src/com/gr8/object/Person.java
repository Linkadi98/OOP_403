package com.gr8.object;

/**
 * @author nickf
 * @project OOP_Group8
 * @created 10/12/2018
 */

public class Person extends Entity {
    private Country country;
    private Time dateOfBirth;
    private String contact;
    private String address;
    private String phoneNumber;
    public Person(String identifier, String name, String description, String link, String contact, String address) {

        super(identifier, name, description, link);
        this.contact = contact;
        this.address = address;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Time getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Time dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
