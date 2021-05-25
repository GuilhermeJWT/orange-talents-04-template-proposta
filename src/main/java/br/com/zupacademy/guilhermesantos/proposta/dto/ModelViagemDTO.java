package br.com.zupacademy.guilhermesantos.proposta.dto;

import br.com.zupacademy.guilhermesantos.proposta.model.ModelCartao;
import br.com.zupacademy.guilhermesantos.proposta.model.ModelViagem;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ModelViagemDTO {

    @NotNull(message = "O Destino não pode ser Vázio!")
    @NotEmpty(message = "O Destino deve ser Informado!")
    private String destino;

    @NotBlank(message = "A Data do Término da Viagem deve ser Informada!")
    @Future(message = "OPS! A Data não pode ser no Passado, Informe Outra!")
    private LocalDate terminoViagem;

    public ModelViagem converte(HttpServletRequest request){
        return new ModelViagem(request, destino, terminoViagem);
    }

    public ModelViagemDTO(ModelCartao modelCartao, String destino, LocalDate terminoViagem) {
        this.destino = destino;
        this.terminoViagem = terminoViagem;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getTerminoViagem() {
        return terminoViagem;
    }
}
