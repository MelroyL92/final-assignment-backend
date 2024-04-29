package nl.novi.finalAssignmentBackend.dtos.user;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import nl.novi.finalAssignmentBackend.entities.Authority;
import nl.novi.finalAssignmentBackend.entities.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserDto {
    public String username;
    public String password;
    public Boolean enabled;
    public String apikey;
    public String email;

    private List<Order>Order = new ArrayList<>();

    @JsonSerialize
    public Set<Authority> authorities;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public String getApikey() {
        return apikey;
    }

    public String getEmail() {
        return email;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

}

