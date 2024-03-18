package nl.novi.finalAssignmentBackend.dtos.user;

import nl.novi.finalAssignmentBackend.entities.Product;

public class UserResponseDto extends Product {
    private int id;
    private String name;
    private String email;

    private String BirthYear;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthYear() {
        return BirthYear;
    }

    public void setBirthYear(String birthYear) {
        BirthYear = birthYear;
    }
}
