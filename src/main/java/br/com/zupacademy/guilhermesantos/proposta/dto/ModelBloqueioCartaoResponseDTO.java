package br.com.zupacademy.guilhermesantos.proposta.dto;

public class ModelBloqueioCartaoResponseDTO {

    private String statusCartao;

    public ModelBloqueioCartaoResponseDTO(String statusCartao) {
        this.statusCartao = statusCartao;
    }

    public String getStatusCartao() {
        return statusCartao;
    }
}
