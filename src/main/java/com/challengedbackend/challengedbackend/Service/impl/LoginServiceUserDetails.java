package com.challengedbackend.challengedbackend.Service.impl;

import java.util.HashSet;
import java.util.Set;


import com.challengedbackend.challengedbackend.Model.User.Users;
import com.challengedbackend.challengedbackend.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceUserDetails implements UserDetailsService {


    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Users users = usersRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("The email " + username + " is incorrect."));

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + users.getRol().name()));


        return new User(users.getEmail(),
                users.getPassword(),
                true,
                true,
                true,
                true,
                authorities);


    }
}