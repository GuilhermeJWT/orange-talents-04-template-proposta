package br .com.zupacademy.guilhermesantos.proposta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@EnableFeignClients
@SpringBootApplication
@EnableJpaRepositories(enableDefaultTransactions = false)
@EnableScheduling
public class PropostaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropostaApplication.class, args);
	}

	@PostConstruct
	void aoIniciar() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

}
