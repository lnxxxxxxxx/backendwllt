package com.challengedbackend.challengedbackend.Service.impl;

import com.challengedbackend.challengedbackend.DTO.Account.AccountDTO;
import com.challengedbackend.challengedbackend.DTO.Account.CreditCardDTO;
import com.challengedbackend.challengedbackend.DTO.Transfer.TransferDTO;
import com.challengedbackend.challengedbackend.Mapper.AccountMapper;
import com.challengedbackend.challengedbackend.Mapper.CreditCardMapper;
import com.challengedbackend.challengedbackend.Mapper.TransferMapper;
import com.challengedbackend.challengedbackend.Model.Account.Account;
import com.challengedbackend.challengedbackend.Model.Account.CreditCard;
import com.challengedbackend.challengedbackend.Model.User.Users;
import com.challengedbackend.challengedbackend.Repository.AccountRepository;
import com.challengedbackend.challengedbackend.Repository.UsersRepository;
import com.challengedbackend.challengedbackend.Service.iAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AccountService implements iAccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransferMapper transferMapper;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private CreditCardMapper creditCardMapper;

    public List<TransferDTO> obtenerHistorialTransferencias(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("No se encontró la cuenta con el ID: " + accountId));

        List<TransferDTO> historialTransferencias = account.getTransferenciasEnviadas().stream()
                .map(transferMapper::toDto)
                .collect(Collectors.toList());

        historialTransferencias.addAll(account.getTransferenciasRecibidas().stream()
                .map(transferMapper::toDto)
                .collect(Collectors.toList()));

        historialTransferencias.sort(Comparator.comparing(TransferDTO::getFecha).reversed());

        return historialTransferencias;
    }



    public AccountDTO obtenerDatosCuenta(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("No se encontró la cuenta con el ID: " + accountId));

        return accountMapper.toDto(account);
    }


    public AccountDTO obtenerDatosCuentaUsuarioAutenticado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        Optional<Users> optionalUser = usersRepository.findByEmail(userEmail);
        if (optionalUser.isPresent()) {
            Account cuenta = optionalUser.get().getCuenta();
            return accountMapper.toDto(cuenta);
        } else {
            throw new RuntimeException("No se encontró al usuario autenticado");
        }
    }


    public List<CreditCardDTO> obtenerMisTarjetas() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        Optional<Users> optionalUser = usersRepository.findByEmail(userEmail);
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            List<CreditCard> tarjetas = user.getCuenta().getTarjetaDeCredito();

            return tarjetas.stream()
                    .map(creditCardMapper::toDto)
                    .collect(Collectors.toList());
        } else {
            throw new RuntimeException("No se encontró al usuario autenticado");
        }
    }

    public List<TransferDTO> obtenerHistorialTransferenciasUsuarioAutenticado() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();


        Optional<Users> optionalUser = usersRepository.findByEmail(userEmail);
        if (optionalUser.isPresent()) {
            Account account = optionalUser.get().getCuenta();

            List<TransferDTO> historialTransferencias = account.getTransferenciasEnviadas().stream()
                    .map(transferMapper::toDto)
                    .collect(Collectors.toList());

            historialTransferencias.addAll(account.getTransferenciasRecibidas().stream()
                    .map(transferMapper::toDto)
                    .collect(Collectors.toList()));


            historialTransferencias.sort(Comparator.comparing(TransferDTO::getFecha).reversed());

            return historialTransferencias;
        } else {
            throw new RuntimeException("No se encontró al usuario autenticado");
        }
    }

    public Map<String, Object> obtenerMenu() {

        List<CreditCardDTO> tarjetas = obtenerMisTarjetas();

        AccountDTO datosCuenta = obtenerDatosCuentaUsuarioAutenticado();

        List<TransferDTO> historialTransferencias = obtenerHistorialTransferenciasUsuarioAutenticado();

        Map<String, Object> menu = new HashMap<>();
        menu.put("creditCards", tarjetas);
        menu.put("accountInfo", datosCuenta);
        menu.put("transferHistory", historialTransferencias);

        return menu;
    }

}
