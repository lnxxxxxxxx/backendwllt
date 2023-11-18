package com.challengedbackend.challengedbackend.Mapper;

import com.challengedbackend.challengedbackend.DTO.Transfer.TransferDTO;
import com.challengedbackend.challengedbackend.Model.Transfer.Transfer;
import org.springframework.stereotype.Component;

@Component
public class TransferMapper {

    public TransferDTO toDto(Transfer transfer) {
        TransferDTO transferDTO = new TransferDTO();
        transferDTO.setId(transfer.getId());
        transferDTO.setNumero_transaccion(transfer.getNumero_transaccion());
        transferDTO.setFecha(transfer.getFecha());
        transferDTO.setMonto(transfer.getMonto());
        transferDTO.setTransfer(transfer.getTransferencia());
        return transferDTO;
    }

    public Transfer toModel(TransferDTO transferDTO) {
        Transfer transfer = new Transfer();
        transfer.setId(transferDTO.getId());
        transfer.setFecha(transferDTO.getFecha());
        transfer.setMonto(transferDTO.getMonto());
        transfer.setNumero_transaccion(transferDTO.getNumero_transaccion());
        transfer.setTransferencia(transferDTO.getTransfer());
        return transfer;
    }
}
