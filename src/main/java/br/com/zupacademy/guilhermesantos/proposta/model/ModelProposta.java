package br.com.zupacademy.guilhermesantos.proposta.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import br.com.zupacademy.guilhermesantos.proposta.anotation.ValidDocumentCpfCnpj;

@Entity
@Table(name = "propostas")
public class ModelProposta implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ValidDocumentCpfCnpj(domainClass = ModelProposta.class, fieldName = "documento", message = "Documento Inválido!")
	@NotEmpty(message = "O Documento deve ser Informado@")
	private String documento;
	
	@NotBlank(message = "O Documento deve ser Informado!")
	@Email(message = "Formato de E-mail Inválido!")
	private String email;
	
	@NotBlank(message = "O Nome deve ser Informado!")
	private String nome;
	
	@NotBlank(message = "O Endereço deve ser Informado!")
	private String endereco;
	
	@Positive(message = "O Salário não pode ser Menor ou igual a 0!")
	@NotBlank(message = "O Salário deve ser Informado!")
	private BigDecimal salario;

}
