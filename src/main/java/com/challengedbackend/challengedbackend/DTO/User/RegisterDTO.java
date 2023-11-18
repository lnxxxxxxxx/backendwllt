package com.challengedbackend.challengedbackend.DTO.User;


import com.challengedbackend.challengedbackend.EnumROL.EnumROL;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {

    private Long id;
    private String nombre;
    private String dni;
    private String email;
    private String password;
    private EnumROL rol;
}