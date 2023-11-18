package com.challengedbackend.challengedbackend.DTO.Account;

import com.challengedbackend.challengedbackend.DTO.Transfer.TransferDTO;
import com.challengedbackend.challengedbackend.Model.Account.CreditCard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    private Long id;
    private String alias;
    private String cvu;
    private BigDecimal saldo;
}
