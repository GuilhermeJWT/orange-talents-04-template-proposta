package br.com.zupacademy.guilhermesantos.proposta.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.guilhermesantos.proposta.model.ModelProposta;

public class AvaliacaoSolicitanteRequestDTO {

	@NotNull(message = "O Id deve ser informdo!")
	private Long idProposta;
	
	@NotEmpty(message = "O Nome deve ser Informado!")
	private String nome;
	
	@NotEmpty(message = "O Documento deve ser Informado!")
	private String documento;

	public AvaliacaoSolicitanteRequestDTO(String documento, String nome, Long id) {
		this.idProposta = id;
		this.nome = nome;
		this.documento = documento;
	}

	public Long getId() {
		return idProposta;
	}

	public String getNome() {
		return nome;
	}

	public String getDocumento() {
		return documento;
	}

}
