package com.challengedbackend.challengedbackend.Service;

import com.challengedbackend.challengedbackend.DTO.Account.AccountDTO;
import com.challengedbackend.challengedbackend.DTO.Account.CreditCardDTO;
import com.challengedbackend.challengedbackend.DTO.Transfer.TransferDTO;

import java.util.List;
import java.util.Map;

public interface iAccountService {
    List<TransferDTO> obtenerHistorialTransferencias(Long accountId);

    AccountDTO obtenerDatosCuenta(Long accountId);

    AccountDTO obtenerDatosCuentaUsuarioAutenticado();

    List<CreditCardDTO> obtenerMisTarjetas();

    List<TransferDTO> obtenerHistorialTransferenciasUsuarioAutenticado();

    Map<String, Object> obtenerMenu();

}
