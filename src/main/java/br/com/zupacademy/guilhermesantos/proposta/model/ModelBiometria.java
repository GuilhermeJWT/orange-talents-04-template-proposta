package br.com.zupacademy.guilhermesantos.proposta.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "biometria")
public class ModelBiometria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime instanteCadastro = LocalDateTime.now();

    @NotNull(message = "A Biometria deve ser Informada!")
    private byte[] fingerPrintBiometria;

    @NotNull(message = "O Cartão deve ser Informado!")
    @ManyToOne(optional = false)
    private ModelCartao cartao;

    public ModelBiometria(byte[] fingerPrintBiometria) {
        this.fingerPrintBiometria = fingerPrintBiometria;
    }

    @Deprecated
    public ModelBiometria(){

    }

    public ModelBiometria(ModelCartao modelCartao, byte[] fingerPrintBiometriaBase64) {
        this.cartao = modelCartao;
        this.fingerPrintBiometria = fingerPrintBiometriaBase64;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getInstanteCadastro() {
        return instanteCadastro;
    }

    public byte[] getFingerPrintBiometria() {
        return fingerPrintBiometria;
    }

    public ModelCartao getCartao() {
        return cartao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModelBiometria)) return false;
        ModelBiometria that = (ModelBiometria) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
