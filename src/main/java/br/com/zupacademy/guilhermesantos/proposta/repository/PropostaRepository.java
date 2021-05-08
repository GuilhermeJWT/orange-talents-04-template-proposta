package br.com.zupacademy.guilhermesantos.proposta.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zupacademy.guilhermesantos.proposta.model.ModelProposta;

@Repository
public interface PropostaRepository extends JpaRepository<ModelProposta, Long>{

	boolean existsByDocumento(String documento);
	
	public Optional<ModelProposta> findByDocumento(String documento);

}
