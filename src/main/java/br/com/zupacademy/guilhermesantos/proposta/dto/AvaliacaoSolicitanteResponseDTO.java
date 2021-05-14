package br.com.zupacademy.guilhermesantos.proposta.dto;

import br.com.zupacademy.guilhermesantos.proposta.enums.StatusFinalSolicitacao;

public class AvaliacaoSolicitanteResponseDTO {

	private Long idProposta;
	private String nome;
	private String documento;
	private StatusFinalSolicitacao statusFinalSolicitacao;

	public AvaliacaoSolicitanteResponseDTO(Long idProposta, String nome, String documento, StatusFinalSolicitacao statusFinalSolicitacao) {
		this.idProposta = idProposta;
		this.nome = nome;
		this.documento = documento;
		this.statusFinalSolicitacao = statusFinalSolicitacao;
	}

	public Long getIdProposta() {
		return idProposta;
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
