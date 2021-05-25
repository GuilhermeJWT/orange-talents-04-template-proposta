package br.com.zupacademy.guilhermesantos.proposta.feign;

import br.com.zupacademy.guilhermesantos.proposta.dto.*;
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

    @PostMapping("/{id}/carteiras")
    public ModelCarteiraResponseDTO carteiraAssocia(@PathVariable(name = "id") String id, @RequestBody ModelCarteiraDTO ModelCarteiraDTO);

}
