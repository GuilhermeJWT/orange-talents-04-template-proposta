package br.com.zupacademy.guilhermesantos.proposta.model;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "viagem")
public class ModelViagem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O Destino não pode ser Vázio!")
    @NotEmpty(message = "O Destino deve ser Informado!")
    private String destino;

    @NotBlank(message = "A Data do Término da Viagem deve ser Informada!")
    @Future(message = "OPS! A Data não pode ser no Passado, Informe Outra!")
    private LocalDate terminoViagem;

    private LocalDateTime dataHoraAvisoViagem = LocalDateTime.now();

    @NotNull(message = "O Ip Cliente deve ser Inforamdo!")
    private String ipCliente;

    @NotBlank(message = "User deve ser Informado!")
    private String userAgent;

    @ManyToOne(optional = false)
    private ModelCartao cartao;

    @Deprecated
    public ModelViagem(){

    }

    public Long getId() {
        return id;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getTerminoViagem() {
        return terminoViagem;
    }

    public LocalDateTime getDataHoraAvisoViagem() {
        return dataHoraAvisoViagem;
    }

    public String getIpCliente() {
        return ipCliente;
    }

    public String getUserAgent() {
        return userAgent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModelViagem)) return false;
        ModelViagem that = (ModelViagem) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
