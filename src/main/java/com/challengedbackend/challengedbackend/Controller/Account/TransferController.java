package com.challengedbackend.challengedbackend.Controller.Account;

import com.challengedbackend.challengedbackend.DTO.Transfer.DepositoRequest;
import com.challengedbackend.challengedbackend.DTO.Transfer.TransferDTO;
import com.challengedbackend.challengedbackend.Service.iTransferService;
import com.challengedbackend.challengedbackend.Service.impl.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/operaciones")
public class TransferController {

    @Autowired
    private iTransferService transferService;

    @PostMapping("/deposito")
    public ResponseEntity<TransferDTO> realizarDeposito(@RequestParam BigDecimal monto, @RequestParam String cuentaDestino) {
        try {
            TransferDTO resultado = transferService.realizarDeposito(monto, cuentaDestino);
            return ResponseEntity.ok(resultado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/extraccion")
    public ResponseEntity<?> realizarExtraccion(@RequestParam BigDecimal monto, @RequestParam String cuentaOrigen) {
        try {
            TransferDTO resultado = transferService.realizarExtraccion(monto, cuentaOrigen);
            return ResponseEntity.ok(resultado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @PostMapping("/transferir")
    public ResponseEntity<?> realizarTransferenciaCuenta(@RequestParam BigDecimal monto,
                                                                   @RequestParam String cuentaOrigen,
                                                                   @RequestParam String cuentaDestino) {
        try {
            TransferDTO resultado = transferService.realizarTransferencia(monto, cuentaOrigen, cuentaDestino);
            return ResponseEntity.ok(resultado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @PostMapping("/realizar-transferencia")
    public ResponseEntity<?> realizarTransferenciaUsuarioAutenticado(
            @RequestParam BigDecimal monto,
            @RequestParam String cuentaDestino) {
        try {
            TransferDTO transferDTO = transferService.realizarTransferenciaUsuarioAutenticado(monto, cuentaDestino);
            return ResponseEntity.ok(transferDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }



    @PostMapping("/realizar-deposito")
    public ResponseEntity<?> realizarDepositoUsuarioAutenticado(@RequestParam BigDecimal monto) {
        try {
            TransferDTO transferDTO = transferService.realizarDepositoUsuarioAutenticado(monto);
            return ResponseEntity.ok(transferDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @PostMapping("/realizar-extraccion")
    public ResponseEntity<?> realizarExtraccionUsuarioAutenticado(@RequestParam BigDecimal monto) {
        try {
            TransferDTO transferDTO = transferService.realizarExtraccionUsuarioAutenticado(monto);
            return ResponseEntity.ok(transferDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
