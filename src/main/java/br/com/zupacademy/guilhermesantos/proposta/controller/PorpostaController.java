package br.com.zupacademy.guilhermesantos.proposta.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.guilhermesantos.proposta.dto.ModelPropostaDTO;
import br.com.zupacademy.guilhermesantos.proposta.model.ModelProposta;
import br.com.zupacademy.guilhermesantos.proposta.repository.PropostaRepository;

@RestController
@RequestMapping(value = "/proposta")
public class PorpostaController {
	
	@Autowired
	private PropostaRepository repository;
	
	@PostMapping(value = "/salvar")
	public ResponseEntity<ModelPropostaDTO> salvaProposta(@RequestBody @Valid ModelPropostaDTO modelPropostaDTO){
		
		ModelProposta modelProposta = modelPropostaDTO.converte();
		repository.save(modelProposta);
		
		return new ResponseEntity<ModelPropostaDTO>(HttpStatus.CREATED);
		
	}
	
}
