package com.proposta.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/*permite ao Spring fazer o scanning de interfaces que declaram ser clientes Feign*/
@EnableFeignClients
@SpringBootApplication
@EnableScheduling
public class ApiDePropostaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiDePropostaApplication.class, args);
	}

}
