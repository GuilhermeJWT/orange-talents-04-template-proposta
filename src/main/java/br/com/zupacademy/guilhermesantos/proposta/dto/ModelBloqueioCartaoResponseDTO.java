package br.com.zupacademy.guilhermesantos.proposta.dto;

import javax.servlet.http.HttpServletRequest;

public class ModelBloqueioCartaoResponseDTO {

    private String statusCartao;

    public ModelBloqueioCartaoResponseDTO(HttpServletRequest statusCartao) {
        this.statusCartao = statusCartao;
    }

    public String getStatusCartao() {
        return statusCartao;
    }
}
