package br.com.zupacademy.guilhermesantos.proposta.model;

import br.com.zupacademy.guilhermesantos.proposta.enums.StatusBloqueioCartao;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "cartao")
public class ModelCartao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O Número do Cartão deve ser Informado!")
    private String numeroCartao;

    @Enumerated(EnumType.STRING)
    private StatusBloqueioCartao statusBloqueioCartao;

    @OneToOne(mappedBy = "cartao", cascade = CascadeType.ALL, orphanRemoval = true)
    private ModelProposta proposta;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ModelBiometria> biometrias;

    @OneToOne(mappedBy = "cartao")
    private ModelBloqueio bloqueio;

    @OneToMany(mappedBy = "cartao",cascade = CascadeType.MERGE, orphanRemoval = true)
    private Set<ModelViagem> viagem = new HashSet<>();

    public ModelCartao(String id, ModelProposta proposta){
        this.numeroCartao = id;
        this.proposta = proposta;
    }

    public void adicionaBloqueioCartao(){
        this.statusBloqueioCartao = StatusBloqueioCartao.BLOQUEADO;
    }

    public void adicionaNotificacaoViagem(ModelViagem modelViagem){
        this.viagem.add(modelViagem);
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

    public List<ModelBiometria> getBiometrias() {
        return biometrias;
    }

    public ModelBloqueio getBloqueio() {
        return bloqueio;
    }

    public Set<ModelViagem> getViagem() {
        return viagem;
    }

    public StatusBloqueioCartao getStatusBloqueioCartao() {
        return statusBloqueioCartao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModelCartao)) return false;
        ModelCartao that = (ModelCartao) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
