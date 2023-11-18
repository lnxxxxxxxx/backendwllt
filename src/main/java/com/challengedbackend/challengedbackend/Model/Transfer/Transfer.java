package com.challengedbackend.challengedbackend.Model.Transfer;

import com.challengedbackend.challengedbackend.EnumROL.EnumTransfer;
import com.challengedbackend.challengedbackend.Model.Account.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transferencia")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "monto", nullable = false)
    private BigDecimal monto;

    @Column(name = "numero_transaccion", nullable = false)
    private Long numero_transaccion;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('DEPOSITO', 'TRANSFERENCIA', 'EXTRACCION') DEFAULT 'DEPOSITO'", nullable = false)
    private EnumTransfer transferencia;

    @ManyToOne
    @JoinColumn(name = "id_cuenta_origen")
    private Account cuentaOrigen;

    @ManyToOne
    @JoinColumn(name = "id_cuenta_destino")
    private Account cuentaDestino;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Long getNumero_transaccion() {
        return numero_transaccion;
    }

    public void setNumero_transaccion(Long numero_transaccion) {
        this.numero_transaccion = numero_transaccion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public EnumTransfer getTransferencia() {
        return transferencia;
    }

    public void setTransferencia(EnumTransfer transferencia) {
        this.transferencia = transferencia;
    }

    public Account getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(Account cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public Account getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(Account cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }
}

