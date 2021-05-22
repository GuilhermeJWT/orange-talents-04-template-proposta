package br.com.zupacademy.guilhermesantos.proposta.repository;

import java.util.List;
import java.util.Optional;

import br.com.zupacademy.guilhermesantos.proposta.enums.StatusProposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zupacademy.guilhermesantos.proposta.model.ModelProposta;

@Repository
public interface PropostaRepository extends JpaRepository<ModelProposta, Long>{

	boolean existsByDocumento(String documento);
	
	Optional<ModelProposta> findByDocumento(String documento);

	List<ModelProposta> findByRestricao(StatusProposta statusProposta);

}
