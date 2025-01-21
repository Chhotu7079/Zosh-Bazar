package com.zosh.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.zosh.domain.User_Role;
import com.zosh.modal.Seller;
import com.zosh.modal.User;
import com.zosh.repository.SellerRepository;
import com.zosh.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class CustomUserServiceImpl implements UserDetailsService{

    private final UserRepository userRepository;
    private final SellerRepository sellerRepository;
    private static final String SELLER_PREFIX = "seller_";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (username.startsWith(SELLER_PREFIX)) {
            String actualUsername = username.substring(SELLER_PREFIX.length());
            Seller seller = sellerRepository.findByEmail(actualUsername);

            if (seller != null) {
                return buildUserDetails(seller.getEmail(), seller.getPassword(), seller.getRole());
            }
        }else{
            User user = userRepository.findByEmail(username);
            if (user!=null) {
                return buildUserDetails(user.getEmail(),user.getPassword(),user.getRole());
            }
        }
        throw new UsernameNotFoundException("User or Seller not found with email: "+username);
    }

    // private UserDetails buildUserDetails(String email, String password, User_Role role) {
    //     if (role== null) {
    //         role=User_Role.ROLE_CUSTOMER;
    //     }
    //     List<GrantedAuthority>authorityList = new ArrayList<>();
    //     authorityList.add(new SimpleGrantedAuthority(role.toString()));
    //     return new org.springframework.security.core.userdetails.User(email, password,authorityList);
    // }
    private UserDetails buildUserDetails(String email, String password, User_Role role) {
        // Validate email
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
    
        // Validate password
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
    
        // Set default role if role is null
        if (role == null) {
            role = User_Role.ROLE_CUSTOMER;
        }
    
        // Build authority list
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority(role.toString()));
    
        // Ensure authority list is not empty
        if (authorityList.isEmpty()) {
            throw new IllegalArgumentException("Authorities cannot be empty");
        }
    
        // Return UserDetails object
        return new org.springframework.security.core.userdetails.User(email, password, authorityList);
    }
    

}
