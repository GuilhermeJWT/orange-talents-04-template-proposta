package br.com.zupacademy.guilhermesantos.proposta.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public enum StatusIdentificaCarteira {

    PAYPAL,
    SAMSUNG_PLAY;

    @JsonCreator
    public static StatusIdentificaCarteira retorna(String value) {
        if (value == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Inválido!");
        for (var identificadorCarteira : values()) {
            if (value.equalsIgnoreCase(identificadorCarteira.toString())) {
                return identificadorCarteira;
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Inválido");
    }

}
