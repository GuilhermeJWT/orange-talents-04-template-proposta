package br.com.zupacademy.guilhermesantos.proposta.dto;

import br.com.zupacademy.guilhermesantos.proposta.model.ModelBiometria;
import br.com.zupacademy.guilhermesantos.proposta.model.ModelCartao;

import javax.validation.constraints.NotBlank;
import java.util.Base64;
import java.util.Optional;

public class ModelBiometriaDTO {

    @NotBlank(message = "A Biometria deve ser Informada!")
    private String fingerPrintBiometria;

    public ModelBiometria converte(Optional<ModelCartao> cartao){
        byte[] fingerPrintBiometriaBase64 = Base64.getEncoder().encode(fingerPrintBiometria.getBytes());
        return new ModelBiometria(cartao.get(), fingerPrintBiometriaBase64);
    }

    public String getFingerPrintBiometria() {
        return fingerPrintBiometria;
    }
}
