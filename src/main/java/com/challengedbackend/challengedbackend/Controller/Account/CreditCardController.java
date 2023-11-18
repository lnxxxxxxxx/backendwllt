package com.challengedbackend.challengedbackend.Controller.Account;

import com.challengedbackend.challengedbackend.DTO.Account.CreditCardDTO;
import com.challengedbackend.challengedbackend.Service.iCreditCardService;
import com.challengedbackend.challengedbackend.Service.impl.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarjeta/{accountId}")
public class CreditCardController {

    @Autowired
    private iCreditCardService creditCardService;

    @PostMapping()
    public ResponseEntity<?> createCreditCard(@RequestBody CreditCardDTO creditCardDTO,
                                                          @PathVariable Long accountId) {
        try {
            CreditCardDTO createdCreditCard = creditCardService.createCreditCard(creditCardDTO, accountId);

            return ResponseEntity.status(HttpStatus.CREATED).body(createdCreditCard);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<List<CreditCardDTO>> getCreditCardsByAccountId(@PathVariable Long accountId) {
            List<CreditCardDTO> creditCards = creditCardService.getCreditCardsByAccountId(accountId);

            return new ResponseEntity<>(creditCards, HttpStatus.OK);
    }
}