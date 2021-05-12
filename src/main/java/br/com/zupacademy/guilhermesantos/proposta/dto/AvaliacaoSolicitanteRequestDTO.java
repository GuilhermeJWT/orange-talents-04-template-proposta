package br.com.zupacademy.guilhermesantos.proposta.dto;

import br.com.zupacademy.guilhermesantos.proposta.model.ModelProposta;

public class AvaliacaoSolicitanteRequestDTO {

	private Long id;
	private String nome;
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
