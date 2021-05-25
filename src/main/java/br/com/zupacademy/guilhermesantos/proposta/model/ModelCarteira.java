package br.com.zupacademy.guilhermesantos.proposta.model;

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

    private String associaId;

    @ManyToOne(optional = false)
    private ModelCartao cartao;

    @Deprecated
    public ModelCarteira(){

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
