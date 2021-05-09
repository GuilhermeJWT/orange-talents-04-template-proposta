package br.com.zupacademy.guilhermesantos.proposta.dto;

public class AvaliacaoSolicitanteRequestDTO {

	private Long id;
	private String nome;
	private String documento;

	public AvaliacaoSolicitanteRequestDTO(Long id, String nome, String documento) {
		this.id = id;
		this.nome = nome;
		this.documento = documento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

}
