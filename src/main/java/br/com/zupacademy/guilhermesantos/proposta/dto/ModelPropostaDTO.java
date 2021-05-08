package br.com.zupacademy.guilhermesantos.proposta.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zupacademy.guilhermesantos.proposta.anotation.GenericUniqueColumn;
import br.com.zupacademy.guilhermesantos.proposta.anotation.ValidDocumentCpfCnpj;
import br.com.zupacademy.guilhermesantos.proposta.model.ModelProposta;

public class ModelPropostaDTO {
	
	@GenericUniqueColumn(domainClass = ModelProposta.class, fieldName = "documento", message = "Documento já Cadastro! Informe Outro")
	@NotEmpty(message = "O Documento deve ser Informado!")
	@ValidDocumentCpfCnpj(domainClass = ModelPropostaDTO.class, fieldName = "documento",message = "Documento Inválido!")
	private String documento;
	
	@NotBlank(message = "O Documento deve ser Informado!")
	@Email(message = "Formato de E-mail Inválido!")
	private String email;
	
	@NotBlank(message = "O Nome deve ser Informado!")
	private String nome;
	
	@NotBlank(message = "O Endereço deve ser Informado!")
	private String endereco;
	
	@Positive(message = "O Salário não pode ser Menor ou igual a 0!")
	@NotNull(message = "O Salário deve ser Informado!")
	private BigDecimal salario;

	public ModelProposta converte() {
		return new ModelProposta(this.documento, this.email, this.nome, this.endereco, this.salario);
	}
	
	public ModelPropostaDTO(String documento, String email, String nome, String endereco, BigDecimal salario) {
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}
	
	public String getDocumento() {
		return documento;
	}

}
