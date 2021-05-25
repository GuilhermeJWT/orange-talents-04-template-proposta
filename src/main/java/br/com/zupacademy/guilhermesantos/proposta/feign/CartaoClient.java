package br.com.zupacademy.guilhermesantos.proposta.feign;

import br.com.zupacademy.guilhermesantos.proposta.dto.ModelAvisoViagemDTO;
import br.com.zupacademy.guilhermesantos.proposta.dto.ModelBloqueioCartaoResponseDTO;
import br.com.zupacademy.guilhermesantos.proposta.dto.ModelCartaoDTO;
import br.com.zupacademy.guilhermesantos.proposta.dto.ModelViagemDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(url = "${cartoes.host}", name = "cartoes")
public interface CartaoClient {

    @GetMapping
    public ModelCartaoDTO associaCartaoProposta(@RequestParam(name = "propostaId") String id);

    @PostMapping("/{id}/bloqueios")
    public ModelBloqueioCartaoResponseDTO bloqueioCartao(@PathVariable(name = "id") String id, @RequestBody ModelBloqueioCartaoResponseDTO modelBloqueioCartaoResponseDTO);

    @PostMapping("/{id}/avisos")
    public ModelAvisoViagemDTO avisoSobreAViagem(@PathVariable(name = "id") String id, @RequestBody ModelViagemDTO modelViagemDTO);

}
