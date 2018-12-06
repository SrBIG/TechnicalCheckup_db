package model.entity;

public class Car {
    private Integer id;
    private String number;
    private String registrationCertificate;
    private String engineNumber;
    private String color;
    private String model;

    public Car(Integer id,
               String number,
               String registrationCertificate,
               String engineNumber,
               String color,
               String model) {
        this.id = id;
        this.number = number;
        this.registrationCertificate = registrationCertificate;
        this.engineNumber = engineNumber;
        this.color = color;
        this.model = model;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRegistrationCertificate() {
        return registrationCertificate;
    }

    public void setRegistrationCertificate(String registrationCertificate) {
        this.registrationCertificate = registrationCertificate;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
