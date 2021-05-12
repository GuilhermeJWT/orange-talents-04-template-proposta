package br.com.zupacademy.guilhermesantos.proposta.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.zupacademy.guilhermesantos.proposta.dto.AvaliacaoSolicitanteRequestDTO;
import br.com.zupacademy.guilhermesantos.proposta.dto.AvaliacaoSolicitanteResponseDTO;

@FeignClient(value = "solicitacao", url = "http://localhost:9999/api")
@Component
public interface AvaliacaoClient {
	
	@PostMapping(value = "/solicitacao")
	public AvaliacaoSolicitanteResponseDTO avaliaSolicitacao(@RequestBody AvaliacaoSolicitanteRequestDTO requestDTO);

}
