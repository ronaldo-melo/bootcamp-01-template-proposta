package com.proposta.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/*Diz ao Spring que a aplicação irá usar a configuração de clientes web services dinâmicas*/
@EnableFeignClients
@SpringBootApplication
public class ApiDePropostaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiDePropostaApplication.class, args);
	}

}
