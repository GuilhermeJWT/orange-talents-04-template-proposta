package br.com.zupacademy.guilhermesantos.proposta.dto;

import br.com.zupacademy.guilhermesantos.proposta.model.ModelCartao;
import br.com.zupacademy.guilhermesantos.proposta.model.ModelProposta;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ModelCartaoDTO {

    @NotEmpty(message = "O ID deve ser Informado!")
    private String id;

    @NotNull(message = "O ID da proposta deve ser Informado!")
    @Positive(message = "OPS! Id deve ser maior que 0!")
    private Long propostaId;

    public ModelCartao converte(ModelProposta modelProposta){
        return new ModelCartao(id, modelProposta);
    }

    public ModelCartaoDTO (String id, Long propostaId){
        this.id = id;
        this.propostaId = propostaId;
    }

    public String getId() {
        return id;
    }

    public Long getPropostaId() {
        return propostaId;
    }
}
