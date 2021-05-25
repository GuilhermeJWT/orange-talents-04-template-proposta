package br.com.zupacademy.guilhermesantos.proposta.controller;

import br.com.zupacademy.guilhermesantos.proposta.dto.ModelAvisoViagemDTO;
import br.com.zupacademy.guilhermesantos.proposta.dto.ModelViagemDTO;
import br.com.zupacademy.guilhermesantos.proposta.enums.StatusBloqueioCartao;
import br.com.zupacademy.guilhermesantos.proposta.feign.CartaoClient;
import br.com.zupacademy.guilhermesantos.proposta.model.ModelCartao;
import br.com.zupacademy.guilhermesantos.proposta.repository.CartaoRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/viagem")
public class ViagemController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private CartaoClient cartaoClient;

    @PostMapping(value = "salvar/{id}")
    public ResponseEntity<ModelAvisoViagemDTO> salvaAvisoViagemCliente(@PathVariable("id") Long id, @RequestBody @Valid ModelViagemDTO modelViagemDTO, HttpServletRequest request){

        Optional<ModelCartao> modelCartao = cartaoRepository.findById(id);

        if(modelCartao.isEmpty() || !modelCartao.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "OPS! Cartão não encontrado - 404");
        }
        if(modelCartao.get().getStatusBloqueioCartao() == StatusBloqueioCartao.BLOQUEADO){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "OPS! O Cartão já está Bloqueado! - 422");
        }
        if(modelCartao.get().getStatusBloqueioCartao() == StatusBloqueioCartao.VIAJANDO){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "OPS! O Status do cartão já está como Viagem - 400");
        }

        try{
            ModelAvisoViagemDTO modelAvisoViagemDTO = cartaoClient.avisoSobreAViagem(modelCartao.get().getNumeroCartao(), modelViagemDTO);
            modelCartao.get().adicionaNotificacaoViagem(modelViagemDTO.converte(request));
            cartaoRepository.save(modelCartao.get());

            return ResponseEntity.ok(modelAvisoViagemDTO);
        }catch (FeignException.UnprocessableEntity exception){
            exception.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "OPS! Algo deu Errado, tente Novamente! - 400");
        }

    }

}
