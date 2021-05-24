package br.com.zupacademy.guilhermesantos.proposta.dto;

import br.com.zupacademy.guilhermesantos.proposta.model.ModelBloqueio;
import br.com.zupacademy.guilhermesantos.proposta.model.ModelCartao;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class ModelBloqueioDTO {

    @NotBlank(message = "Deve existir um IP!")
    private String ipRemotoCliente;

    @NotBlank(message = "O User deve Existir!")
    private String userAgent;

    @NotBlank(message = "Não pode ser Vazio!")
    private String sistemaRemoto;

    public ModelBloqueio converteDadosBloqueio(Optional<ModelCartao> modelCartao, String ip, String userAgent){
        return new ModelBloqueio(modelCartao, ip, userAgent);
    }

    public ModelBloqueio converte(Optional<ModelCartao> cartao, HttpServletRequest request){
        return new ModelBloqueio(cartao, request.getRemoteAddr(), request.getHeader("User-Agent"));
    }

    public ModelBloqueioDTO(String sistemaRemoto){
        this.sistemaRemoto = sistemaRemoto;
    }

    public ModelBloqueioDTO(String ipRemotoCliente, String userAgent) {
        this.ipRemotoCliente = ipRemotoCliente;
        this.userAgent = userAgent;
    }

    public String getIpRemotoCliente() {
        return ipRemotoCliente;
    }

    public String getUserAgent() {
        return userAgent;
    }
}
