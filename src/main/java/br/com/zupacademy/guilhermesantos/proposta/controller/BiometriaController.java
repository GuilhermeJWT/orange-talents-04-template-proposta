package br.com.zupacademy.guilhermesantos.proposta.controller;

import br.com.zupacademy.guilhermesantos.proposta.dto.ModelBiometriaDTO;
import br.com.zupacademy.guilhermesantos.proposta.model.ModelBiometria;
import br.com.zupacademy.guilhermesantos.proposta.model.ModelCartao;
import br.com.zupacademy.guilhermesantos.proposta.repository.BiometriaRepository;
import br.com.zupacademy.guilhermesantos.proposta.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(value = "/biometria")
public class BiometriaController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private BiometriaRepository biometriaRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @PostMapping(value = "/salvar/{id}")
    @Transactional
    public ResponseEntity<?> salvaBiometria(@PathVariable("id") Long id, @RequestBody @Valid ModelBiometriaDTO modelBiometriaDTO, UriComponentsBuilder builder){

        Optional<ModelCartao> modelCartao = cartaoRepository.findById(id);

        /*Não possui Cartão - 404*/
        if(modelCartao.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        ModelBiometria modelBiometria = modelBiometriaDTO.converte(modelCartao);
        biometriaRepository.save(modelBiometria);

        /*Retorna 200 se deu tudo certo*/
        URI uriRedireciona = builder.path("/biometria/salvar/{id}").build(modelBiometria.getId());
        return ResponseEntity.created(uriRedireciona).build();

    }

}
