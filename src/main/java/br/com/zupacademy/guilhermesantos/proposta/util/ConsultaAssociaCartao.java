package br.com.zupacademy.guilhermesantos.proposta.util;

import br.com.zupacademy.guilhermesantos.proposta.dto.ModelCartaoDTO;
import br.com.zupacademy.guilhermesantos.proposta.enums.StatusProposta;
import br.com.zupacademy.guilhermesantos.proposta.feign.CartaoClient;
import br.com.zupacademy.guilhermesantos.proposta.model.ModelProposta;
import br.com.zupacademy.guilhermesantos.proposta.repository.PropostaRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class ConsultaAssociaCartao {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private CartaoClient cartaoClient;

    @Autowired
    private PropostaRepository propostaRepository;

    @Scheduled(fixedDelayString = "${periodicidade.executa-operacao}")
    @Transactional
    private void verificaSePropostaEstaElegivel(){

        List<ModelProposta> modelProposta = propostaRepository.findByRestricao(StatusProposta.ELEGIVEL);

        try{

            for (ModelProposta percorreList : modelProposta){
                ModelCartaoDTO cartaoDTO = cartaoClient.associaCartaoProposta(percorreList.getId().toString());
                percorreList.associaCartao(cartaoDTO.converte(percorreList));
                percorreList.adicionaRestricao(StatusProposta.ELEGIVEL);
                propostaRepository.save(percorreList);
            }

        }catch (FeignException.UnprocessableEntity exception){
            exception.printStackTrace();
            System.out.println("OPS Deu erro em alguma parte do Feign!");
        }

    }

    public ConsultaAssociaCartao(CartaoClient cartaoClient, PropostaRepository propostaRepository){
        this.cartaoClient = cartaoClient;
        this.propostaRepository = propostaRepository;
    }

}
