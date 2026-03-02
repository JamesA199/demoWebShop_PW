package com.demoWebShop.Models;

/**
 * Generic address POJO used for both Billing and Shipping.
 *
 * This is a pure data container:
 *  - All fields are public for simplicity
 *  - Builder-style setters make it easy to construct test data
 *  - No business logic lives here
 */
public class Address_POJO {

    // -----------------------------
    // Public fields (simple data container)
    // -----------------------------
    public String firstName;
    public String lastName;
    public String email;
    public String company;
    public String country;
    public String state;      // ⭐ REQUIRED for dropdown
    public String city;
    public String address1;
    public String address2;
    public String zip;
    public String phone;
    public String fax;

    // -----------------------------
    // Required no-argument constructor
    // -----------------------------
    public Address_POJO() {}

    // -----------------------------
    // Builder-style setters
    // -----------------------------
    public Address_POJO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Address_POJO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Address_POJO setEmail(String email) {
        this.email = email;
        return this;
    }

    public Address_POJO setCompany(String company) {
        this.company = company;
        return this;
    }

    public Address_POJO setCountry(String country) {
        this.country = country;
        return this;
    }

    // ⭐ THIS WAS MISSING — now added
    public Address_POJO setState(String state) {
        this.state = state;
        return this;
    }

    public Address_POJO setCity(String city) {
        this.city = city;
        return this;
    }

    public Address_POJO setAddress1(String address1) {
        this.address1 = address1;
        return this;
    }

    public Address_POJO setAddress2(String address2) {
        this.address2 = address2;
        return this;
    }

    public Address_POJO setZip(String zip) {
        this.zip = zip;
        return this;
    }

    public Address_POJO setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Address_POJO setFax(String fax) {
        this.fax = fax;
        return this;
    }
}
