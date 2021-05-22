package br.com.zupacademy.guilhermesantos.proposta.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "cartao")
public class ModelCartao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O Número do Cartão deve ser Informado!")
    private String numeroCartao;

    @OneToOne(mappedBy = "cartao")
    private ModelProposta proposta;

    public ModelCartao(String id, ModelProposta proposta){
        this.numeroCartao = id;
        this.proposta = proposta;
    }

    @Deprecated
    public ModelCartao(){

    }

    public Long getId() {
        return id;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public ModelProposta getProposta() {
        return proposta;
    }
}
