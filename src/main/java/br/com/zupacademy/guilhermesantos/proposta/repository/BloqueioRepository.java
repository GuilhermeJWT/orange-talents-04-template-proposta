package br.com.zupacademy.guilhermesantos.proposta.repository;

import br.com.zupacademy.guilhermesantos.proposta.model.ModelBloqueio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloqueioRepository extends JpaRepository<ModelBloqueio, Long> {

}
