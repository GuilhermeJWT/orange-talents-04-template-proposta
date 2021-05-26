package br.com.zupacademy.guilhermesantos.proposta.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import br.com.zupacademy.guilhermesantos.proposta.dto.CriptografaDocumentoClienteDTO;
import br.com.zupacademy.guilhermesantos.proposta.enums.StatusProposta;

@Entity
@Table(name = "propostas")
public class ModelProposta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "O Documento deve ser Informado!")
	private String documento;

	@NotBlank(message = "O Documento deve ser Informado!")
	private String email;

	@NotBlank(message = "O Nome deve ser Informado!")
	private String nome;

	@NotBlank(message = "O Endereço deve ser Informado!")
	private String endereco;

	@Positive(message = "O Salário não pode ser Menor ou igual a 0!")
	private BigDecimal salario;
	
	@Enumerated(EnumType.STRING)
	private StatusProposta statusProposta = StatusProposta.NAO_ELEGIVEL;

	@OneToOne(cascade =  CascadeType.MERGE, orphanRemoval = true)
	private ModelCartao cartao;

	public ModelProposta(String email, String nome, String endereco, BigDecimal salario, CriptografaDocumentoClienteDTO criptografaDocumentoClienteDTO) {
	   this.email = email;
	   this.nome = nome;
	   this.endereco = endereco;
	   this.salario = salario;
	   this.documento = criptografaDocumentoClienteDTO.criptografaDocumento();
	}

	public void associaCartao(ModelCartao modelCartao){
		this.cartao = modelCartao;
	}

	public void adicionaRestricao(StatusProposta ELEGIVEL){
		this.statusProposta = ELEGIVEL;
	}

	@Deprecated
	public ModelProposta() {

	}

	public Long getId() {
		return id;
	}

	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}
	
	public StatusProposta getStatusProposta() {
		return statusProposta;
	}
	
	public void setStatusProposta(StatusProposta statusProposta) {
		this.statusProposta = statusProposta;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ModelProposta)) return false;
		ModelProposta that = (ModelProposta) o;
		return getId().equals(that.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}
}
