package br.com.zupacademy.guilhermesantos.proposta.controller;

import br.com.zupacademy.guilhermesantos.proposta.dto.ModelBloqueioCartaoResponseDTO;
import br.com.zupacademy.guilhermesantos.proposta.dto.ModelBloqueioDTO;
import br.com.zupacademy.guilhermesantos.proposta.enums.StatusBloqueioCartao;
import br.com.zupacademy.guilhermesantos.proposta.feign.CartaoClient;
import br.com.zupacademy.guilhermesantos.proposta.model.ModelBloqueio;
import br.com.zupacademy.guilhermesantos.proposta.model.ModelCartao;
import br.com.zupacademy.guilhermesantos.proposta.repository.BloqueioRepository;
import br.com.zupacademy.guilhermesantos.proposta.repository.CartaoRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
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

    @Autowired
    private CartaoClient cartaoClient;

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity<?> salvaBloqueioCartao(@PathVariable("id") Long id, HttpServletRequest request, @RequestBody @Valid ModelBloqueioDTO modelBloqueioDTO, UriComponentsBuilder builder){
        Optional<ModelCartao> modelCartao = cartaoRepository.findById(id);

        /*Valida se o Cartão existe, se não lança um 404*/
        if(modelCartao.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        /*Valida o Cartão, caso já esteja bloqueado ele vai lançar 422 pro Cliente*/
        if(modelCartao.get().getStatusBloqueioCartao() == StatusBloqueioCartao.BLOQUEADO){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,"OPS! O Cartão já está Bloqueado!");
        }

        /*Retorna 201 pro cliente, Informando que deu tudo certo*/
        return realizaSolicitacaoBloqueio(modelCartao.get(), modelBloqueioDTO, request, builder);

    }

    public ResponseEntity<?> realizaSolicitacaoBloqueio(ModelCartao modelCartao, @RequestBody @Valid ModelBloqueioDTO modelBloqueioDTO, HttpServletRequest request, UriComponentsBuilder builder){
        try{
            ModelBloqueio modelBloqueio = new ModelBloqueio(modelCartao, request.getHeader("User-Agent"));

            modelBloqueio.bloqueiaCartao(modelCartao);
            modelBloqueio = bloqueioRepository.save(modelBloqueio);
            cartaoClient.bloqueioCartao(modelCartao.getNumeroCartao(), new ModelBloqueioCartaoResponseDTO(request));

            URI uriRedireciona = builder.path("/bloqueio/{id}").build(modelBloqueio.getId());
            return ResponseEntity.created(uriRedireciona).build();
        }catch(FeignException.UnprocessableEntity exceptionFeign){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "OPS! Erro ao tentar Bloquear o Cartão!");
        }

    }

}
