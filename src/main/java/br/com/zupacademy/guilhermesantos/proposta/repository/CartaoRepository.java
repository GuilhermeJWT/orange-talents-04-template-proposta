package br.com.zupacademy.guilhermesantos.proposta.repository;

import br.com.zupacademy.guilhermesantos.proposta.model.ModelCartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepository extends JpaRepository<ModelCartao, Long> {

}
