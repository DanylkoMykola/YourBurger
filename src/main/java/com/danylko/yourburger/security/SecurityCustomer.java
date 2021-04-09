package com.danylko.yourburger.security;

import com.danylko.yourburger.entities.Customer;
import com.danylko.yourburger.enums.Status;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class SecurityCustomer implements UserDetails {

    private final String phoneNumber;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean isActive;

    public SecurityCustomer(String phoneNumber, String password, List<SimpleGrantedAuthority> authorities, boolean isActive) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.authorities = authorities;
        this.isActive = isActive;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return phoneNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static UserDetails fromUser(Customer customer) {
        return new org.springframework.security.core.userdetails.User(
                customer.getPhoneNumber(), customer.getPassword(),
                customer.getStatus().equals(Status.ACTIVE),
                customer.getStatus().equals(Status.ACTIVE),
                customer.getStatus().equals(Status.ACTIVE),
                customer.getStatus().equals(Status.ACTIVE),
                customer.getRole().getAuthorities()
        );
    }
}
