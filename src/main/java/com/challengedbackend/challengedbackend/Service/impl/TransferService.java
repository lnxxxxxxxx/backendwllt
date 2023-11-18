package com.challengedbackend.challengedbackend.Service.impl;


import com.challengedbackend.challengedbackend.DTO.Transfer.TransferDTO;
import com.challengedbackend.challengedbackend.EnumROL.EnumTransfer;
import com.challengedbackend.challengedbackend.Mapper.TransferMapper;
import com.challengedbackend.challengedbackend.Model.Account.Account;
import com.challengedbackend.challengedbackend.Model.Transfer.Transfer;
import com.challengedbackend.challengedbackend.Model.User.Users;
import com.challengedbackend.challengedbackend.Repository.AccountRepository;
import com.challengedbackend.challengedbackend.Repository.TransferRepository;
import com.challengedbackend.challengedbackend.Repository.UsersRepository;
import com.challengedbackend.challengedbackend.Service.iTransferService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class TransferService implements iTransferService {


    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransferMapper transferMapper;


    @Autowired
    private UsersRepository usersRepository;

    public TransferDTO realizarDeposito(BigDecimal monto, String identificadorCuenta) {

        Optional<Account> cuentaDestino = buscarCuentaPorIdentificador(identificadorCuenta);

        if (cuentaDestino.isPresent()) {
            Transfer transfer = new Transfer();
            transfer.setMonto(monto);
            transfer.setNumero_transaccion(generateTransactionCode());
            transfer.setFecha(LocalDateTime.now());
            transfer.setTransferencia(EnumTransfer.DEPOSITO);


            cuentaDestino.ifPresent(transfer::setCuentaDestino);

            transferRepository.save(transfer);

            cuentaDestino.get().setSaldo(cuentaDestino.get().getSaldo().add(monto));
            accountRepository.save(cuentaDestino.get());

            return transferMapper.toDto(transfer);
        } else {
            throw new RuntimeException("No se encontró la cuenta con el identificador: " + identificadorCuenta);
        }
    }


    private Optional<Account> buscarCuentaPorIdentificador(String identificadorCuenta) {
        try {
            Long id = Long.parseLong(identificadorCuenta);
            return accountRepository.findById(id);
        } catch (NumberFormatException e) {
            return buscarPorAliasOporCvu(identificadorCuenta);
        }
    }

    private Optional<Account> buscarPorAliasOporCvu(String identificadorCuenta) {
        return accountRepository.findByAlias(identificadorCuenta)
                .or(() -> accountRepository.findByCvu(identificadorCuenta));
    }


    public Long generateTransactionCode() {
        Random random = new Random();

        long firstNumbers = 2107L;

        return firstNumbers * 100000000L + random.nextInt(100000000);
    }



    public TransferDTO realizarExtraccion(BigDecimal monto, String identificadorCuenta) {

        Optional<Account> cuentaOrigen = buscarCuentaPorIdentificador(identificadorCuenta);

        if (cuentaOrigen.isPresent()) {
            BigDecimal saldoActual = cuentaOrigen.get().getSaldo();


            if (saldoActual.compareTo(monto) >= 0) {
                Transfer transfer = new Transfer();
                transfer.setMonto(monto.negate());
                transfer.setNumero_transaccion(generateTransactionCode());
                transfer.setFecha(LocalDateTime.now());
                transfer.setTransferencia(EnumTransfer.EXTRACCION);

                cuentaOrigen.ifPresent(transfer::setCuentaOrigen);

                transferRepository.save(transfer);

                cuentaOrigen.get().setSaldo(saldoActual.subtract(monto));
                accountRepository.save(cuentaOrigen.get());

                return transferMapper.toDto(transfer);
            } else {
                throw new RuntimeException("Saldo insuficiente para realizar la extracción en la cuenta con el identificador: " + identificadorCuenta);
            }
        } else {
            throw new RuntimeException("No se encontró la cuenta con el identificador: " + identificadorCuenta);
        }
    }

    public TransferDTO realizarTransferencia(BigDecimal monto, String cuentaOrigenIdentificador, String cuentaDestinoIdentificador) {
        Optional<Account> cuentaOrigen = buscarCuentaPorIdentificador(cuentaOrigenIdentificador);
        Optional<Account> cuentaDestino = buscarCuentaPorIdentificador(cuentaDestinoIdentificador);

        if (cuentaOrigen.isPresent() && cuentaDestino.isPresent()) {
            BigDecimal saldoActual = cuentaOrigen.get().getSaldo();

            if (saldoActual.compareTo(monto) >= 0) {
                Transfer transfer = new Transfer();
                transfer.setMonto(monto.negate());
                transfer.setNumero_transaccion(generateTransactionCode());
                transfer.setFecha(LocalDateTime.now());
                transfer.setTransferencia(EnumTransfer.TRANSFERENCIA);

                cuentaOrigen.ifPresent(transfer::setCuentaOrigen);
                cuentaDestino.ifPresent(transfer::setCuentaDestino);

                transferRepository.save(transfer);

                cuentaOrigen.get().setSaldo(saldoActual.subtract(monto));
                accountRepository.save(cuentaOrigen.get());

                cuentaDestino.get().setSaldo(cuentaDestino.get().getSaldo().add(monto));
                accountRepository.save(cuentaDestino.get());

                return transferMapper.toDto(transfer);
            } else {
                throw new RuntimeException("Saldo insuficiente para realizar la transferencia desde la cuenta con el identificador: " + cuentaOrigenIdentificador);
            }
        } else {
            throw new RuntimeException("No se encontró alguna de las cuentas con los identificadores proporcionados.");
        }
    }

    public TransferDTO realizarTransferenciaUsuarioAutenticado(BigDecimal monto, String cuentaDestinoIdentificador) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        Optional<Users> optionalUser = usersRepository.findByEmail(userEmail);

        if (optionalUser.isPresent()) {
            Account cuentaOrigen = optionalUser.get().getCuenta();

            Optional<Account> cuentaDestino = buscarCuentaPorIdentificador(cuentaDestinoIdentificador);

            if (cuentaDestino.isPresent()) {
                BigDecimal saldoActual = cuentaOrigen.getSaldo();

                if (saldoActual.compareTo(monto) >= 0) {
                    Transfer transfer = new Transfer();
                    transfer.setMonto(monto.negate());
                    transfer.setNumero_transaccion(generateTransactionCode());
                    transfer.setFecha(LocalDateTime.now());
                    transfer.setTransferencia(EnumTransfer.TRANSFERENCIA);

                    transfer.setCuentaOrigen(cuentaOrigen);
                    cuentaDestino.ifPresent(transfer::setCuentaDestino);

                    transferRepository.save(transfer);

                    cuentaOrigen.setSaldo(saldoActual.subtract(monto));
                    accountRepository.save(cuentaOrigen);

                    cuentaDestino.get().setSaldo(cuentaDestino.get().getSaldo().add(monto));
                    accountRepository.save(cuentaDestino.get());

                    return transferMapper.toDto(transfer);
                } else {
                    throw new RuntimeException("Saldo insuficiente para realizar la transferencia desde la cuenta del usuario autenticado.");
                }
            } else {
                throw new RuntimeException("No se encontró la cuenta destino con el identificador: " + cuentaDestinoIdentificador);
            }
        } else {
            throw new RuntimeException("No se encontró al usuario autenticado");
        }
    }


    public TransferDTO realizarDepositoUsuarioAutenticado(BigDecimal monto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();


        Optional<Users> optionalUser = usersRepository.findByEmail(userEmail);
        if (!optionalUser.isPresent()) {
            throw new RuntimeException("No se encontró al usuario autenticado");
        }

        Account cuentaDestino = optionalUser.get().getCuenta();

        Transfer transfer = new Transfer();
        transfer.setMonto(monto);
        transfer.setNumero_transaccion(generateTransactionCode());
        transfer.setFecha(LocalDateTime.now());
        transfer.setTransferencia(EnumTransfer.DEPOSITO);
        transfer.setCuentaDestino(cuentaDestino);

        transferRepository.save(transfer);

        cuentaDestino.setSaldo(cuentaDestino.getSaldo().add(monto));
        accountRepository.save(cuentaDestino);

        return transferMapper.toDto(transfer);
    }


    public TransferDTO realizarExtraccionUsuarioAutenticado(BigDecimal monto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        Optional<Users> optionalUser = usersRepository.findByEmail(userEmail);

        if (optionalUser.isPresent()) {
            Users usuario = optionalUser.get();
            Account cuentaOrigen = usuario.getCuenta();

            if (cuentaOrigen != null) {
                BigDecimal saldoActual = cuentaOrigen.getSaldo();

                if (saldoActual.compareTo(monto) >= 0) {
                    Transfer transfer = new Transfer();
                    transfer.setMonto(monto.negate());
                    transfer.setNumero_transaccion(generateTransactionCode());
                    transfer.setFecha(LocalDateTime.now());
                    transfer.setTransferencia(EnumTransfer.EXTRACCION);

                    transfer.setCuentaOrigen(cuentaOrigen);

                    transferRepository.save(transfer);

                    cuentaOrigen.setSaldo(saldoActual.subtract(monto));
                    accountRepository.save(cuentaOrigen);

                    return transferMapper.toDto(transfer);
                } else {
                    throw new RuntimeException("Saldo insuficiente para realizar la extracción.");
                }
            } else {
                throw new RuntimeException("No se encontró la cuenta del usuario autenticado.");
            }
        } else {
            throw new RuntimeException("No se encontró al usuario autenticado.");
        }
    }
}

