package com.challengedbackend.challengedbackend.Service;

import com.challengedbackend.challengedbackend.DTO.Account.CreditCardDTO;

import java.util.List;

public interface iCreditCardService {
        CreditCardDTO createCreditCard(CreditCardDTO creditCardDTO, Long userId);

        List<CreditCardDTO> getCreditCardsByAccountId(Long accountId);
}
