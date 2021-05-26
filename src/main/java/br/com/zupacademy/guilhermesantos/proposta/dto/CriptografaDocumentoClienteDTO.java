package br.com.zupacademy.guilhermesantos.proposta.dto;

import br.com.zupacademy.guilhermesantos.proposta.anotation.GenericUniqueColumn;
import br.com.zupacademy.guilhermesantos.proposta.anotation.ValidDocumentCpfCnpj;
import br.com.zupacademy.guilhermesantos.proposta.model.ModelProposta;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotEmpty;

public class CriptografaDocumentoClienteDTO {

    @GenericUniqueColumn(domainClass = ModelProposta.class, fieldName = "documento", message = "Documento já Cadastro! Informe Outro")
    @NotEmpty(message = "O Documento deve ser Informado!")
    @ValidDocumentCpfCnpj(domainClass = ModelPropostaDTO.class, fieldName = "documento", message = "Documento Inválido!")
    private String documento;

    public String criptografaDocumento() {
        return new BCryptPasswordEncoder().encode(documento);
    }

    public CriptografaDocumentoClienteDTO(String documento){
        this.documento = documento;
    }

    public String getDocumento() {
        return documento;
    }
}
