package br.com.zupacademy.guilhermesantos.proposta.repository;

import br.com.zupacademy.guilhermesantos.proposta.model.ModelCarteira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteiraRepository extends JpaRepository<ModelCarteira, Long> {

}
