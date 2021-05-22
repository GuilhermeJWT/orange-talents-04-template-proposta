package br.com.zupacademy.guilhermesantos.proposta.dto;

import br.com.zupacademy.guilhermesantos.proposta.enums.StatusFinalSolicitacao;
import br.com.zupacademy.guilhermesantos.proposta.enums.StatusProposta;
import br.com.zupacademy.guilhermesantos.proposta.model.ModelProposta;

import java.util.Optional;

public class AvaliacaoSolicitanteResponseDTO {

	private Long idProposta;
	private String nome;
	private String documento;
	private StatusFinalSolicitacao statusFinalSolicitacao;
	private StatusProposta statusRetorno;

	public AvaliacaoSolicitanteResponseDTO(Long idProposta, String nome, String documento, StatusFinalSolicitacao statusFinalSolicitacao) {
		this.idProposta = idProposta;
		this.nome = nome;
		this.documento = documento;
		this.statusFinalSolicitacao = statusFinalSolicitacao;
	}

	public AvaliacaoSolicitanteResponseDTO(Optional<ModelProposta> modelProposta) {
		this.idProposta = modelProposta.get().getId();
		this.nome = modelProposta.get().getNome();
		this.documento = modelProposta.get().getDocumento();
		this.statusRetorno = modelProposta.get().getStatusProposta();
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

	public StatusProposta getStatusRetorno() {
		return statusRetorno;
	}

	public StatusFinalSolicitacao getStatusFinalSolicitacao() {
		return statusFinalSolicitacao;
	}

}
