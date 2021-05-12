package br.com.zupacademy.guilhermesantos.proposta.controller;

import java.net.URI;

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
public class PorpostaController {
	
	@Autowired
	private PropostaRepository repository;
	
	@Autowired
	private AvaliacaoClient avaliacaoClient;
	
	@PostMapping(value = "/salvar")
	public ResponseEntity<?> salvaProposta(@RequestBody @Valid ModelPropostaDTO modelPropostaDTO, UriComponentsBuilder componentBuilder){
		
		boolean valida = repository.existsByDocumento(modelPropostaDTO.getDocumento());
		
		if(valida) {
			return ResponseEntity.unprocessableEntity().body("");
		}
		
		var avaliacao = modelPropostaDTO.converte();
		var modelProposta = repository.save(avaliacao);
		
		try {
			var validandoRequisicao = new AvaliacaoSolicitanteRequestDTO(modelProposta.getId(), modelProposta.getNome(), modelProposta.getDocumento());
		
			avaliacaoClient.avaliaSolicitacao(validandoRequisicao);
			modelProposta.setStatusProposta(StatusProposta.ELEGIVEL);
		
		}catch(FeignException exception) {
			modelProposta.setStatusProposta(StatusProposta.NAO_ELEGIVEL);
		}
		
		ModelProposta propostaSalva = modelPropostaDTO.converte();
		repository.save(propostaSalva);
		
		URI uri = componentBuilder.path("/proposta/salvar/{id}").build(modelProposta.getId());
		return  ResponseEntity.created(uri).build();
		
	}
	
	/*
	 
	@PostMapping(value = "/salvar")
	public ResponseEntity<?> salvaProposta(@RequestBody @Valid ModelPropostaDTO modelPropostaDTO, UriComponentsBuilder componentBuilder){
		
		boolean valida = repository.existsByDocumento(modelPropostaDTO.getDocumento());
		
		if(valida) {
			return ResponseEntity.unprocessableEntity().body("");
		}
		
		ModelProposta modelProposta = modelPropostaDTO.converte();
		repository.save(modelProposta);
		
		URI uri = componentBuilder.path("/proposta/salvar/{id}").build(modelProposta.getId());
		return  ResponseEntity.created(uri).build();
		
	}

	 */
	
}
