package com.challengedbackend.challengedbackend.DTO.Account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardDTO {

    private Long id;
    private String nombreCompleto;
    private String numeroTarjeta;
    private String fechaExpiracion;
    private int clave;

}
