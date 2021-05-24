package br.com.zupacademy.guilhermesantos.proposta.repository;

import br.com.zupacademy.guilhermesantos.proposta.model.ModelBiometria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiometriaRepository extends JpaRepository<ModelBiometria, Long> {

}
