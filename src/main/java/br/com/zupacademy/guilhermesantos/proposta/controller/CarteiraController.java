package br.com.zupacademy.guilhermesantos.proposta.controller;

import br.com.zupacademy.guilhermesantos.proposta.dto.ModelCarteiraDTO;
import br.com.zupacademy.guilhermesantos.proposta.dto.ModelCarteiraResponseDTO;
import br.com.zupacademy.guilhermesantos.proposta.feign.CartaoClient;
import br.com.zupacademy.guilhermesantos.proposta.model.ModelCartao;
import br.com.zupacademy.guilhermesantos.proposta.model.ModelCarteira;
import br.com.zupacademy.guilhermesantos.proposta.repository.CartaoRepository;
import br.com.zupacademy.guilhermesantos.proposta.repository.CarteiraRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(value = "/carteira")
public class CarteiraController {

    @Autowired
    private CartaoClient cartaoClient;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private CarteiraRepository carteiraRepository;

    @PostMapping("/{id}")
    public ResponseEntity<?> salvaAssciaCarteira(@PathVariable("id") Long id, @RequestBody @Valid ModelCarteiraDTO modelCarteiraDTO, UriComponentsBuilder builder){

        Optional<ModelCartao> modelCartao = cartaoRepository.findById(id);
        Optional<ModelCarteira> modelCarteira = carteiraRepository.findByCartaoAndStatusIdentificaCarteira(modelCartao.get(), modelCarteiraDTO.getStatusIdentificaCarteira());

        if(modelCartao.isEmpty() || !modelCartao.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "OPS! Cartão não encontrado - 404");
        }

        if(modelCarteira.isPresent()){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "OPS! A Carteira já está Associada para esse Cartão! - 422");
        }

        try{
            ModelCarteiraResponseDTO modelCarteiraResponseDTO = cartaoClient.carteiraAssocia(modelCartao.get().getNumeroCartao(), modelCarteiraDTO);
            ModelCarteira novaCarteira = carteiraRepository.save(modelCarteiraDTO.converte(modelCartao.get(), modelCarteiraResponseDTO.getId()));

            URI uriRedireciona = builder.path("/carteira/{id}").build(novaCarteira.getId());
            return ResponseEntity.created(uriRedireciona).build();

        }catch(FeignException.FeignClientException | FeignException.FeignServerException exception){
            exception.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }

}
