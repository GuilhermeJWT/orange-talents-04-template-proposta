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

import br.com.zupacademy.guilhermesantos.proposta.dto.ModelPropostaDTO;
import br.com.zupacademy.guilhermesantos.proposta.model.ModelProposta;
import br.com.zupacademy.guilhermesantos.proposta.repository.PropostaRepository;

@RestController
@RequestMapping(value = "/proposta")
public class PorpostaController {
	
	@Autowired
	private PropostaRepository repository;
	
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
	
}
