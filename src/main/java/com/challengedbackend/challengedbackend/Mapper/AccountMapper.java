package com.challengedbackend.challengedbackend.Mapper;


import com.challengedbackend.challengedbackend.DTO.Account.AccountDTO;
import com.challengedbackend.challengedbackend.Model.Account.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountDTO toDto(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setAlias(account.getAlias());
        accountDTO.setCvu(account.getCvu());
        accountDTO.setSaldo(account.getSaldo());
        return accountDTO;
    }

    public Account toModel(AccountDTO accountDTO) {
        Account account = new Account();
        account.setId(accountDTO.getId());
        account.setAlias(accountDTO.getAlias());
        account.setCvu(accountDTO.getCvu());
        account.setSaldo(accountDTO.getSaldo());
        return account;
    }
}
