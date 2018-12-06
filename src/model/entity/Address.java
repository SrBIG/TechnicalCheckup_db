package model.entity;

public class Address {
    private Integer id;
    private String country;
    private String city;
    private String street;
    private String house;
    private String flat;

    public Address(Integer id, String country, String city, String street, String house, String flat) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.street = street;
        this.house = house;
        this.flat = flat;
    }

    @Override
    public String toString() {
        return country +
                "," + city +
                ", str." + street +
                ", " + house +
                "/" + flat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }
}
