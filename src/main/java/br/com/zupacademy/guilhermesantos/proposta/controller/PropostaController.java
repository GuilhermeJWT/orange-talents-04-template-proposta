package br.com.zupacademy.guilhermesantos.proposta.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.guilhermesantos.proposta.dto.AvaliacaoSolicitanteRequestDTO;
import br.com.zupacademy.guilhermesantos.proposta.dto.ModelPropostaDTO;
import br.com.zupacademy.guilhermesantos.proposta.enums.StatusProposta;
import br.com.zupacademy.guilhermesantos.proposta.feign.AvaliacaoClient;
import br.com.zupacademy.guilhermesantos.proposta.model.ModelProposta;
import br.com.zupacademy.guilhermesantos.proposta.repository.PropostaRepository;
import feign.FeignException;

@RestController
@RequestMapping(value = "/proposta")
public class PropostaController {
	
	@Autowired
	private PropostaRepository repository;
	
	@Autowired
	private AvaliacaoClient avaliacaoClient;
	
	@PostMapping(value = "/salvar")
	@Transactional
	public ResponseEntity<?> salvaProposta(@RequestBody @Valid ModelPropostaDTO modelPropostaDTO, UriComponentsBuilder componentBuilder){
		
		boolean valida = repository.existsByDocumento(modelPropostaDTO.getDocumento());
		
		if(valida) {
			return ResponseEntity.unprocessableEntity().body("");
		}
		
		ModelProposta modelProposta = modelPropostaDTO.converte();
		ModelProposta salva = repository.save(modelProposta);
		
		try {
			AvaliacaoSolicitanteRequestDTO validandoRequisicao = new AvaliacaoSolicitanteRequestDTO(modelProposta.getDocumento(), modelProposta.getNome(), modelProposta.getId());

			avaliacaoClient.avaliaSolicitacao(validandoRequisicao);
			modelProposta.setStatusProposta(StatusProposta.ELEGIVEL);
		
		}catch(FeignException.UnprocessableEntity exception) {
			modelProposta.setStatusProposta(StatusProposta.NAO_ELEGIVEL);
		}
		
		ModelProposta propostaSalva = modelPropostaDTO.converte();
		repository.save(propostaSalva);
		
		URI uri = componentBuilder.path("/proposta/salvar/{id}").build(modelProposta.getId());
		return  ResponseEntity.created(uri).build();
		
	}
}
