package model.entity;

import java.util.Objects;

public class Driver {
    private Integer id;
    private String name;
    private String surname;
    private String driverLicense;
    private Integer idAddress;
    private Address address;
    private String sex;
    private Integer yearBirth;

    public Driver(Integer id,
                  String name,
                  String surname,
                  String driverLicense,
                  Address address,
                  String sex,
                  Integer yearBirth) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.driverLicense = driverLicense;
        this.address = address;
        this.sex = sex;
        this.yearBirth = yearBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return id.equals(driver.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    public Integer getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(Integer idAddress) {
        this.idAddress = idAddress;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getYearBirth() {
        return yearBirth;
    }

    public void setYearBirth(Integer yearBirth) {
        this.yearBirth = yearBirth;
    }
}
