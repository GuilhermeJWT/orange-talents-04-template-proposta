package br.com.zupacademy.guilhermesantos.proposta.dto;

public class ModelCarteiraResponseDTO {

    private String resultado;

    private String id;

    public ModelCarteiraResponseDTO(String resultado, String id) {
        this.resultado = resultado;
        this.id = id;
    }

    public String getResultado() {
        return resultado;
    }

    public String getId() {
        return id;
    }
}
