package br.com.zupacademy.guilhermesantos.proposta.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import br.com.zupacademy.guilhermesantos.proposta.dto.AvaliacaoSolicitanteResponseDTO;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
	private PropostaRepository propostaRepository;
	
	@Autowired
	private AvaliacaoClient avaliacaoClient;

	@Autowired
	private Tracer tracer;

	@GetMapping("/acompanhamento/{id}")
	public ResponseEntity<AvaliacaoSolicitanteResponseDTO> verificaAcompanhamentoProposta(@PathVariable("id") Long id){
		Optional<ModelProposta> modelProposta = propostaRepository.findById(id);
		/*Verifica se possui proposta e Retorna pro Cliente*/
		if(!modelProposta.isPresent()){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		return ResponseEntity.ok(new AvaliacaoSolicitanteResponseDTO(modelProposta));

	}
	
	@PostMapping(value = "/salvar")
	@Transactional
	public ResponseEntity<?> salvaProposta(@RequestBody @Valid ModelPropostaDTO modelPropostaDTO, UriComponentsBuilder componentBuilder){
		
		boolean valida = propostaRepository.existsByDocumento(modelPropostaDTO.getDocumento());
		
		if(valida) {
			return ResponseEntity.unprocessableEntity().body("");
		}
		
		ModelProposta modelProposta = modelPropostaDTO.converte();
		ModelProposta salva = propostaRepository.save(modelProposta);
		
		try {

			Span activeSpan = tracer.activeSpan();
			activeSpan.setTag("user.email", "guilhermezup@teste.com");
			activeSpan.log("Testando Log");

			AvaliacaoSolicitanteRequestDTO validandoRequisicao = new AvaliacaoSolicitanteRequestDTO(modelProposta.getDocumento(), modelProposta.getNome(), modelProposta.getId());

			avaliacaoClient.avaliaSolicitacao(validandoRequisicao);
			modelProposta.setStatusProposta(StatusProposta.ELEGIVEL);
		
		}catch(FeignException.UnprocessableEntity exception) {
			modelProposta.setStatusProposta(StatusProposta.NAO_ELEGIVEL);
		}
		
		ModelProposta propostaSalva = modelPropostaDTO.converte();
		propostaRepository.save(propostaSalva);
		
		URI uri = componentBuilder.path("/proposta/salvar/{id}").build(modelProposta.getId());
		return  ResponseEntity.created(uri).build();
		
	}
}
