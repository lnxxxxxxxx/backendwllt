package com.challengedbackend.challengedbackend.Mapper;

import com.challengedbackend.challengedbackend.DTO.Account.CreditCardDTO;
import com.challengedbackend.challengedbackend.Model.Account.CreditCard;
import org.springframework.stereotype.Component;

@Component
public class CreditCardMapper {


    public CreditCardDTO toDto(CreditCard creditCard) {
        CreditCardDTO creditCardDTO = new CreditCardDTO();
        creditCardDTO.setId(creditCard.getId());
        creditCardDTO.setNombreCompleto(creditCard.getNombreCompleto());
        creditCardDTO.setClave(creditCard.getClave());
        creditCardDTO.setFechaExpiracion(creditCard.getFechaExpiracion());
        creditCardDTO.setNumeroTarjeta(creditCard.getNumeroTarjeta());
        return creditCardDTO;
    }

    public CreditCard toModel(CreditCardDTO creditCardDTO) {
        CreditCard creditCard = new CreditCard();
        creditCard.setId(creditCardDTO.getId());
        creditCard.setClave(creditCardDTO.getClave());
        creditCard.setNumeroTarjeta(creditCardDTO.getNumeroTarjeta());
        creditCard.setFechaExpiracion(creditCardDTO.getFechaExpiracion());
        creditCard.setNombreCompleto(creditCardDTO.getNombreCompleto());
        return creditCard;
    }

}
