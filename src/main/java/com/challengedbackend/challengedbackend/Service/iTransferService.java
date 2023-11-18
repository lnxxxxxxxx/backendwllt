package com.challengedbackend.challengedbackend.Service;

import com.challengedbackend.challengedbackend.DTO.Transfer.TransferDTO;

import java.math.BigDecimal;

public interface iTransferService {
    TransferDTO realizarDeposito(BigDecimal monto, String identificadorCuenta);

    TransferDTO realizarExtraccion(BigDecimal monto, String identificadorCuenta);

    TransferDTO realizarTransferencia(BigDecimal monto, String cuentaOrigenIdentificador, String cuentaDestinoIdentificador);
    TransferDTO realizarTransferenciaUsuarioAutenticado(BigDecimal monto, String cuentaDestinoIdentificador);

    TransferDTO realizarDepositoUsuarioAutenticado(BigDecimal monto);

    TransferDTO realizarExtraccionUsuarioAutenticado(BigDecimal monto);
}

