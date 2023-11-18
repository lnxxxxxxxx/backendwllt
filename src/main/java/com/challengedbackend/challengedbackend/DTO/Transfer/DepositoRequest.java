package com.challengedbackend.challengedbackend.DTO.Transfer;

import java.math.BigDecimal;

public class DepositoRequest {
    private BigDecimal monto;
    private String identificadorCuenta;

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getIdentificadorCuenta() {
        return identificadorCuenta;
    }

    public void setIdentificadorCuenta(String identificadorCuenta) {
        this.identificadorCuenta = identificadorCuenta;
    }
}
