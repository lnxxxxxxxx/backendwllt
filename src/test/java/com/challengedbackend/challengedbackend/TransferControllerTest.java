package com.challengedbackend.challengedbackend;

import com.challengedbackend.challengedbackend.Controller.Account.TransferController;
import com.challengedbackend.challengedbackend.DTO.Transfer.TransferDTO;
import com.challengedbackend.challengedbackend.Service.impl.TransferService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransferControllerTest {

    @Mock
    private TransferService transferService;

    @InjectMocks
    private TransferController transferController;

    @Test
    public void testRealizarTransferenciaCuenta() {
        // Configuración de datos de prueba
        BigDecimal monto = BigDecimal.valueOf(100);
        String cuentaOrigen = "cuentaOrigen";
        String cuentaDestino = "cuentaDestino";
        TransferDTO transferDTO = new TransferDTO(); // Puedes ajustar según tus necesidades

        // Configuración del comportamiento simulado del servicio
        when(transferService.realizarTransferencia(any(), any(), any())).thenReturn(transferDTO);

        // Llamada al controlador y verificación de la respuesta
        ResponseEntity<?> responseEntity = transferController.realizarTransferenciaCuenta(monto, cuentaOrigen, cuentaDestino);

        // Verificación de que el servicio fue llamado con los parámetros correctos
        verify(transferService, times(1)).realizarTransferencia(eq(monto), eq(cuentaOrigen), eq(cuentaDestino));

        // Verificación del resultado
        // Puedes ajustar estas aserciones según la lógica específica de tu aplicación
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(transferDTO, responseEntity.getBody());
    }
}