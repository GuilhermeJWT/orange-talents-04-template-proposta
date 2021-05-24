package br.com.zupacademy.guilhermesantos.proposta.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = "bloqueio")
public class ModelBloqueio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime instanteBloqueio = LocalDateTime.now();

    @NotBlank(message = "Deve existir um IP!")
    private String ipRemotoCliente;

    @NotBlank(message = "O User deve Existir!")
    private String userAgent;

    @OneToOne(cascade = CascadeType.MERGE)
    private Optional<ModelCartao> cartao;

    public ModelBloqueio(Optional<ModelCartao> cartao, String ipRemotoCliente, String userAgent) {
        this.cartao = cartao;
        this.ipRemotoCliente = ipRemotoCliente;
        this.userAgent = userAgent;
    }

    @Deprecated
    public ModelBloqueio(){

    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getInstanteBloqueio() {
        return instanteBloqueio;
    }

    public String getIpRemotoCliente() {
        return ipRemotoCliente;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public Optional<ModelCartao> getCartao() {
        return cartao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModelBloqueio)) return false;
        ModelBloqueio that = (ModelBloqueio) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
