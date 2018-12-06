package model.entity;

public class Officer {
    private Integer id;
    private String name;
    private String surname;
    private String rank;
    private String position;

    public Officer(Integer id, String name, String surname, String rank, String position) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.rank = rank;
        this.position = position;
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

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
