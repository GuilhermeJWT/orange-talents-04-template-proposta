package br.com.zupacademy.guilhermesantos.proposta.dto;

import br.com.zupacademy.guilhermesantos.proposta.enums.StatusFinalSolicitacao;

public class AvaliacaoSolicitanteResponseDTO {

	private Long id;
	private String nome;
	private String documento;
	private StatusFinalSolicitacao statusFinalSolicitacao;

	public AvaliacaoSolicitanteResponseDTO(Long id, String nome, String documento, StatusFinalSolicitacao statusFinalSolicitacao) {
		this.id = id;
		this.nome = nome;
		this.documento = documento;
		this.statusFinalSolicitacao = statusFinalSolicitacao;
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

	public StatusFinalSolicitacao getStatusFinalSolicitacao() {
		return statusFinalSolicitacao;
	}

}
