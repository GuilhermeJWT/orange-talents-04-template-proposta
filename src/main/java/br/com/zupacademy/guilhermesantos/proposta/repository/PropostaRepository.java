package br.com.zupacademy.guilhermesantos.proposta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zupacademy.guilhermesantos.proposta.model.ModelProposta;

@Repository
public interface PropostaRepository extends JpaRepository<ModelProposta, Long>{


}
