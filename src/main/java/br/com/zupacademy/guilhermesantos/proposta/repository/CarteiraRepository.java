package br.com.zupacademy.guilhermesantos.proposta.repository;

import br.com.zupacademy.guilhermesantos.proposta.enums.StatusIdentificaCarteira;
import br.com.zupacademy.guilhermesantos.proposta.model.ModelCartao;
import br.com.zupacademy.guilhermesantos.proposta.model.ModelCarteira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarteiraRepository extends JpaRepository<ModelCarteira, Long> {

    public Optional<ModelCarteira> findByCartaoAndStatusIdentificaCarteira(ModelCartao modelCartao, StatusIdentificaCarteira statusIdentificaCarteira);

}
