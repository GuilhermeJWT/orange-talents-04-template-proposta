package br.com.zupacademy.guilhermesantos.proposta.monitoring;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

@Component
public class HealthCheckMonitoring {
 
	public Health health() {
		Map<String, Object> details = new HashMap<>();
		details.put("versão", "0.0.1");
		details.put("descrição", "Heath projeto Proposta");
		details.put("endereço", "127.0.0.1");

		return Health.status(Status.UP).withDetails(details).build();
	}

}
