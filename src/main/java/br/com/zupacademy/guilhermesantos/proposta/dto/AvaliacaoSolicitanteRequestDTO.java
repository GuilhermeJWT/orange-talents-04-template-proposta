package br.com.zupacademy.guilhermesantos.proposta.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.guilhermesantos.proposta.model.ModelProposta;

public class AvaliacaoSolicitanteRequestDTO {

	@NotNull(message = "O Id deve ser informdo!")
	private Long id;
	
	@NotEmpty(message = "O Nome deve ser Informado!")
	private String nome;
	
	@NotEmpty(message = "O Documento deve ser Informado!")
	private String documento;
	
	public AvaliacaoSolicitanteRequestDTO(ModelProposta modelProposta) {
		this.id = modelProposta.getId();
		this.nome = modelProposta.getNome();
		this.documento = modelProposta.getDocumento();
	}

	public AvaliacaoSolicitanteRequestDTO(Long id, String nome, String documento) {
		this.id = id;
		this.nome = nome;
		this.documento = documento;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getDocumento() {
		return documento;
	}

}
