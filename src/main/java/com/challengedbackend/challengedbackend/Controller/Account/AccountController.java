package com.challengedbackend.challengedbackend.Controller.Account;


import com.challengedbackend.challengedbackend.DTO.Account.AccountDTO;
import com.challengedbackend.challengedbackend.DTO.Account.CreditCardDTO;
import com.challengedbackend.challengedbackend.DTO.Transfer.TransferDTO;
import com.challengedbackend.challengedbackend.Service.iAccountService;
import com.challengedbackend.challengedbackend.Service.iUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cuenta")
public class AccountController {

    @Autowired
    private iAccountService accountService;

    @Autowired
    private iUsersService usersService;

    @GetMapping("/historial/{accountId}")
    public ResponseEntity<List<TransferDTO>> obtenerHistorialTransferencias(@PathVariable Long accountId) {
        try {
            List<TransferDTO> historialTransferencias = accountService.obtenerHistorialTransferencias(accountId);
            return ResponseEntity.ok(historialTransferencias);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> obtenerDatosCuenta(@PathVariable Long id) {
        try {
            AccountDTO resultado = accountService.obtenerDatosCuenta(id);
            return ResponseEntity.ok(resultado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/micuenta")
    public ResponseEntity<AccountDTO> obtenerDatosCuentaUsuarioAutenticado() {
        try {
            AccountDTO resultado = accountService.obtenerDatosCuentaUsuarioAutenticado();
            return ResponseEntity.ok(resultado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/mistarjetas")
    public ResponseEntity<List<CreditCardDTO>> obtenerMisTarjetas() {
        try {
            List<CreditCardDTO> tarjetas = accountService.obtenerMisTarjetas();
            return ResponseEntity.ok(tarjetas);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/mihistorial")
    public ResponseEntity<List<TransferDTO>> obtenerHistorialTransferenciasUsuarioAutenticado() {
        try {
            List<TransferDTO> historialTransferencias = accountService.obtenerHistorialTransferenciasUsuarioAutenticado();
            return ResponseEntity.ok(historialTransferencias);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/menu")
    public ResponseEntity<Map<String, Object>> obtenerMenu() {
        Map<String, Object> menu = accountService.obtenerMenu();
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }
}
