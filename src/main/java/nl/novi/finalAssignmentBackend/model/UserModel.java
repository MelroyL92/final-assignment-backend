package nl.novi.finalAssignmentBackend.model;

import nl.novi.finalAssignmentBackend.entities.Authority;
import nl.novi.finalAssignmentBackend.entities.UploadOrder;

import java.util.HashSet;
import java.util.Set;

public class UserModel {


    private String username;
    private String email;
    private String password;
    private String apikey;

    private OrderModel Order;

    private UploadOrder uploadOrder;

    private Set<Authority> authorities = new HashSet<>();


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public UploadOrder getUploadOrder() {
        return uploadOrder;
    }

    public void setUploadOrder(UploadOrder uploadOrder) {
        this.uploadOrder = uploadOrder;
    }
}
