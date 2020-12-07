package com.proposta.demo;

import com.proposta.demo.controller.CartaoController;
import com.proposta.demo.controller.PropostaController;
import com.proposta.demo.model.Proposta;
import com.proposta.demo.model.enums.StatusAvaliacaoProposta;
import com.proposta.demo.request.BiometriaRequest;
import com.proposta.demo.request.CarteiraDigitalRequest;
import com.proposta.demo.request.PropostaRequest;
import com.proposta.demo.service.AssociaCartaoIDcomProposta;
import com.proposta.demo.service.AvaliaProposta;
import com.proposta.demo.service.ServiceCartoesFactoty;
import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
@SpringJUnitConfig(ApiDePropostaApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApiDePropostaApplicationTests {

	@Autowired
	private PropostaController propostaController;

	@Autowired
	private CartaoController cartaoController;

	private UUID cartaoId;

	@Test
	@Transactional
	@Order(1)
	void deveRetornar201_QuandoSalvarProposta(){

		//Cenário
		String documento = "111.111.111-11";
		String nome = "Ronaldo Melo";
		String endereco = "Tv Sebastião Antônio, Nº 100, Centro, Lago Grande-SP, 67-183-123";
		String email = "ronaldo.melo@gmail.com" ;
		BigDecimal salario = BigDecimal.valueOf(4000.00);
		PropostaRequest request = new PropostaRequest(documento, nome, email, endereco, salario);
		//Proposta proposta = request.toModel();
		UriComponentsBuilder builder = UriComponentsBuilder.newInstance();

		//Ação
		ResponseEntity<?> response = propostaController.salvarProposta(request, builder);
		//Avalidação
		Assert.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
	}

	@Test
	@Order(2)
	void deveRetornar200_QuandoConsultarProposta() throws InterruptedException {
		Thread.sleep(7000L);
		ResponseEntity<?> response = propostaController.getProposta(1L);
		Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

}
