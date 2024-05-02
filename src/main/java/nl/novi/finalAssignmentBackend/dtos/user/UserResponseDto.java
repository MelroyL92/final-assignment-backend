package nl.novi.finalAssignmentBackend.dtos.user;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import nl.novi.finalAssignmentBackend.entities.Authority;
import nl.novi.finalAssignmentBackend.entities.UploadOrder;

import java.util.Set;

public class UserResponseDto {

    public String username;
    public String email;

    private UploadOrder uploadOrder;


    @JsonSerialize
    public Set<Authority> authorities;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public UploadOrder getUploadOrder() {
        return uploadOrder;
    }

    public void setUploadOrder(UploadOrder uploadOrder) {
        this.uploadOrder = uploadOrder;
    }
}
