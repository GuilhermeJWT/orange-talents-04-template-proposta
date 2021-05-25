package br.com.zupacademy.guilhermesantos.proposta.model;

import br.com.zupacademy.guilhermesantos.proposta.enums.StatusIdentificaCarteira;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "carteira")
public class ModelCarteira implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O E-Mail deve ser Informado!")
    @Email(message = "Formato de E-Mail Inválido!")
    private String email;

    @NotBlank(message = "O ID deve ser Informado!")
    private String associaId;

    @NotBlank(message = "A Carteira deve ser Informada!")
    @Enumerated(EnumType.STRING)
    private StatusIdentificaCarteira statusIdentificaCarteira;

    @ManyToOne(optional = false)
    private ModelCartao cartao;

    public ModelCarteira(String email, String associaId, StatusIdentificaCarteira statusIdentificaCarteira, ModelCartao cartao) {
        this.email = email;
        this.associaId = associaId;
        this.statusIdentificaCarteira = StatusIdentificaCarteira.PAYPAL;
        this.cartao = cartao;
    }

    @Deprecated
    public ModelCarteira(){

    }

    public ModelCarteira(String email, ModelCartao modelCartao, String id) {
        this.email = email;
        this.associaId = id;
        this.cartao = modelCartao;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getAssociaId() {
        return associaId;
    }

    public StatusIdentificaCarteira getStatusIdentificaCarteira() {
        return statusIdentificaCarteira;
    }

    public ModelCartao getCartao() {
        return cartao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModelCarteira)) return false;
        ModelCarteira that = (ModelCarteira) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
