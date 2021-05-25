package br.com.zupacademy.guilhermesantos.proposta.controller;

import br.com.zupacademy.guilhermesantos.proposta.dto.ModelViagemDTO;
import br.com.zupacademy.guilhermesantos.proposta.enums.StatusBloqueioCartao;
import br.com.zupacademy.guilhermesantos.proposta.model.ModelCartao;
import br.com.zupacademy.guilhermesantos.proposta.repository.CartaoRepository;
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

    @PostMapping(value = "salvar/{id}")
    public ResponseEntity<?> salvaAvisoViagemCliente(@PathVariable("id") Long id, @RequestBody @Valid ModelViagemDTO modelViagemDTO, HttpServletRequest request){

        Optional<ModelCartao> modelCartao = cartaoRepository.findById(id);

        if(modelCartao.isEmpty() || !modelCartao.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "OPS! Cartão não encontrado - 404");
        }
        if(modelCartao.get().getStatusBloqueioCartao() == StatusBloqueioCartao.BLOQUEADO){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "OPS! O Cartão já está Bloqueado! - 422");
        }

        modelCartao.get().adicionaNotificacaoViagem(modelViagemDTO.converte(request));
        cartaoRepository.save(modelCartao.get());

        return ResponseEntity.ok("Cadastro Efetuado com Sucesso! Tenha uma boa Viagem =) - 200");

    }

}
