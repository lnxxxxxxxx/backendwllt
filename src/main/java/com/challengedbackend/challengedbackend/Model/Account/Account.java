package com.challengedbackend.challengedbackend.Model.Account;

import com.challengedbackend.challengedbackend.Model.Transfer.Transfer;
import com.challengedbackend.challengedbackend.Model.User.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cuenta")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuenta")
    private Long id;

    @Column(name = "alias", unique = true)
    private String alias;

    @Column(name = "cvu", unique = true)
    private String cvu;

    @Column(name = "saldo")
    private BigDecimal saldo;


    @OneToOne(mappedBy = "cuenta", cascade = CascadeType.ALL)
    private Users usuario;

    // Relación con la tarjeta de crédito
    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL)
    private List<CreditCard> tarjetaDeCredito = new ArrayList<>();

    @OneToMany(mappedBy = "cuentaOrigen")
    private List<Transfer> transferenciasEnviadas;

    @OneToMany(mappedBy = "cuentaDestino")
    private List<Transfer> transferenciasRecibidas;


    public List<Transfer> getTransferenciasEnviadas() {
        return transferenciasEnviadas;
    }

    public void setTransferenciasEnviadas(List<Transfer> transferenciasEnviadas) {
        this.transferenciasEnviadas = transferenciasEnviadas;
    }

    public List<Transfer> getTransferenciasRecibidas() {
        return transferenciasRecibidas;
    }

    public void setTransferenciasRecibidas(List<Transfer> transferenciasRecibidas) {
        this.transferenciasRecibidas = transferenciasRecibidas;
    }

    public List<CreditCard> getTarjetaDeCredito() {
        return tarjetaDeCredito;
    }

    public void setTarjetaDeCredito(List<CreditCard> tarjetaDeCredito) {
        this.tarjetaDeCredito = tarjetaDeCredito;
    }

    public String getCvu() {
        return cvu;
    }

    public void setCvu(String cvu) {
        this.cvu = cvu;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Users getUsuario() {
        return usuario;
    }

    public void setUsuario(Users usuario) {
        this.usuario = usuario;
    }
}
