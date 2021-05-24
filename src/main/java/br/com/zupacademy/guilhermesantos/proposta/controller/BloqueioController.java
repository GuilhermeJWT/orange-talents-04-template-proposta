package br.com.zupacademy.guilhermesantos.proposta.controller;

import br.com.zupacademy.guilhermesantos.proposta.dto.ModelBloqueioDTO;
import br.com.zupacademy.guilhermesantos.proposta.model.ModelBloqueio;
import br.com.zupacademy.guilhermesantos.proposta.model.ModelCartao;
import br.com.zupacademy.guilhermesantos.proposta.repository.BloqueioRepository;
import br.com.zupacademy.guilhermesantos.proposta.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(value = "/bloqueio")
public class BloqueioController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private BloqueioRepository bloqueioRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity<?> salvaBloqueioCartao(@PathVariable("id") Long id, HttpServletRequest request, ModelBloqueioDTO modelBloqueioDTO, UriComponentsBuilder builder){
        Optional<ModelCartao> modelCartao = cartaoRepository.findById(id);

        /*Valida se o Cartão existe, se não lança um 404*/
        if(modelCartao.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        /*Valida o Cartão, caso já esteja bloqueado ele vai lançar 422 pro Cliente*/
        if(modelCartao.get().getBloqueio() != null){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,"OPS! O Cartão já está Bloqueado!");
        }

        ModelBloqueio modelBloqueio = modelBloqueioDTO.converte(modelCartao, request);
        bloqueioRepository.save(modelBloqueio);

        URI uriRedireciona = builder.path("/bloqueio/{id}").build(modelBloqueio.getId());

        /*Retorna 201 pro cliente, Informando que deu tudo certo*/
        return ResponseEntity.created(uriRedireciona).build();

    }

}
