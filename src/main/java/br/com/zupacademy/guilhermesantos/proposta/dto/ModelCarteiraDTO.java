package br.com.zupacademy.guilhermesantos.proposta.dto;

import br.com.zupacademy.guilhermesantos.proposta.enums.StatusIdentificaCarteira;
import br.com.zupacademy.guilhermesantos.proposta.model.ModelCartao;
import br.com.zupacademy.guilhermesantos.proposta.model.ModelCarteira;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class ModelCarteiraDTO {

    @NotBlank(message = "O E-Mail deve ser Informado!")
    @Email(message = "Formato de E-Mail Inválido!")
    private String email;

    @NotBlank(message = "A Carteira deve ser Informada!")
    private StatusIdentificaCarteira statusIdentificaCarteira;

    public ModelCarteira converte(ModelCartao modelCartao, String id){
        return new ModelCarteira(email, modelCartao, id);
    }

    public ModelCarteiraDTO(String email, StatusIdentificaCarteira statusIdentificaCarteira) {
        this.email = email;
        this.statusIdentificaCarteira = statusIdentificaCarteira;
    }

    public String getEmail() {
        return email;
    }

    public StatusIdentificaCarteira getStatusIdentificaCarteira() {
        return statusIdentificaCarteira;
    }
}
