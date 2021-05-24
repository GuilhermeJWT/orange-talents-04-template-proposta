package br.com.zupacademy.guilhermesantos.proposta.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.zupacademy.guilhermesantos.proposta.dto.AvaliacaoSolicitanteRequestDTO;
import br.com.zupacademy.guilhermesantos.proposta.dto.AvaliacaoSolicitanteResponseDTO;

@FeignClient(value = "analises", url = "${analises.host}")
@Component
public interface AvaliacaoClient {
	
	@PostMapping
	public AvaliacaoSolicitanteResponseDTO avaliaSolicitacao(@RequestBody AvaliacaoSolicitanteRequestDTO requestDTO);

}
