package com.challengedbackend.challengedbackend.Service.impl;


import com.challengedbackend.challengedbackend.DTO.Account.CreditCardDTO;
import com.challengedbackend.challengedbackend.Exceptions.GeneralException;
import com.challengedbackend.challengedbackend.Exceptions.Message;
import com.challengedbackend.challengedbackend.Mapper.CreditCardMapper;
import com.challengedbackend.challengedbackend.Model.Account.Account;
import com.challengedbackend.challengedbackend.Model.Account.CreditCard;
import com.challengedbackend.challengedbackend.Repository.AccountRepository;
import com.challengedbackend.challengedbackend.Repository.CreditCardRepository;
import com.challengedbackend.challengedbackend.Service.iCreditCardService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CreditCardService implements iCreditCardService {

    @Autowired
    private CreditCardMapper creditCardMapper;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CreditCardRepository creditCardRepository;


    public CreditCardDTO createCreditCard(CreditCardDTO creditCardDTO, Long userId) {
        try {
            Account account = accountRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("Cuenta no encontrada para el usuario con ID: " + userId));


            CreditCard creditCard = creditCardMapper.toModel(creditCardDTO);


            creditCard.setCuenta(account);


            creditCard = creditCardRepository.save(creditCard);


            return creditCardMapper.toDto(creditCard);
        } catch (RuntimeException e){
            throw new RuntimeException("Los datos son incorrectos");
        }
    }


    public List<CreditCardDTO> getCreditCardsByAccountId(Long accountId) {

            Account account = accountRepository.findById(accountId)
                    .orElseThrow(() -> new GeneralException("Cuenta no encontrada para el ID: " + accountId, HttpStatus.NOT_FOUND));


            List<CreditCard> creditCards = account.getTarjetaDeCredito();


            return creditCards.stream()
                    .map(creditCardMapper::toDto)
                    .collect(Collectors.toList());
    }
}