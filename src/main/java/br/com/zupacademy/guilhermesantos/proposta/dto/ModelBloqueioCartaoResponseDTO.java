package br.com.zupacademy.guilhermesantos.proposta.dto;

import javax.servlet.http.HttpServletRequest;

public class ModelBloqueioCartaoResponseDTO {

    private String statusCartao;

    public ModelBloqueioCartaoResponseDTO(String statusCartao) {
        this.statusCartao = statusCartao;
    }

    public ModelBloqueioCartaoResponseDTO(HttpServletRequest request) {

    }

    public String getStatusCartao() {
        return statusCartao;
    }
}