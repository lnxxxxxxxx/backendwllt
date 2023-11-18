package com.challengedbackend.challengedbackend.Controller.Authentication;

import com.challengedbackend.challengedbackend.DTO.User.RegisterDTO;
import com.challengedbackend.challengedbackend.Service.iUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Pattern;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private iUsersService userService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody RegisterDTO usersDTO) {
        try {
            validateDni(usersDTO.getDni());

            RegisterDTO usersCreate = userService.create(usersDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(usersCreate);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }


    private void validateDni(String dni) {
        if (dni.length() < 8) {
            throw new IllegalArgumentException("El DNI debe tener al menos 8 caracteres");
        }

        if (!Pattern.matches("\\d+", dni)) {
            throw new IllegalArgumentException("El DNI solo debe contener números sin puntos");
        }
    }
}