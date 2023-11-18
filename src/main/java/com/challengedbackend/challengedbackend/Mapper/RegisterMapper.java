package com.challengedbackend.challengedbackend.Mapper;

import com.challengedbackend.challengedbackend.DTO.User.RegisterDTO;
import com.challengedbackend.challengedbackend.Model.User.Users;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class RegisterMapper {

    private PasswordEncoder passwordEncoder;

    public RegisterMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public RegisterDTO toDto(Users users) {
        RegisterDTO usersDTO = new RegisterDTO();
        usersDTO.setId(users.getId());
        usersDTO.setNombre(users.getNombre());
        usersDTO.setDni(users.getDni());
        usersDTO.setEmail(users.getEmail());
        usersDTO.setPassword(passwordEncoder.encode(users.getPassword()));
        usersDTO.setRol(users.getRol());
        return usersDTO;
    }

    public Users toModel(RegisterDTO usersDTO) {
        Users users = new Users();
        users.setId(usersDTO.getId());
        users.setNombre(usersDTO.getNombre());

        String dni = usersDTO.getDni();
        if (dni.length() < 8 && !Pattern.matches("\\d+", dni)) {
            throw new IllegalArgumentException("El DNI debe tener al menos 8 caracteres");
        }

        users.setDni(dni);
        users.setEmail(usersDTO.getEmail());
        users.setPassword(passwordEncoder.encode(usersDTO.getPassword()));
        users.setRol(usersDTO.getRol());
        return users;
    }


}