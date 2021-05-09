package br.com.zupacademy.guilhermesantos.proposta.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.zupacademy.guilhermesantos.proposta.dto.AvaliacaoSolicitanteRequestDTO;
import br.com.zupacademy.guilhermesantos.proposta.dto.AvaliacaoSolicitanteResponseDTO;

@FeignClient(value = "avaliacao", url = "http://localhost:9999/api")
public interface AvaliacaoClient {
	
	@PostMapping(value = "/avalia")
	AvaliacaoSolicitanteResponseDTO responseDTO(@RequestBody AvaliacaoSolicitanteRequestDTO requestDTO);

}
