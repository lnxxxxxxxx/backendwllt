package com.challengedbackend.challengedbackend.DTO.Transfer;


import com.challengedbackend.challengedbackend.EnumROL.EnumTransfer;
import com.challengedbackend.challengedbackend.Model.Account.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferDTO {

    private Long id;
    private BigDecimal monto;
    private Long numero_transaccion;
    private LocalDateTime fecha;
    private EnumTransfer transfer;

}
