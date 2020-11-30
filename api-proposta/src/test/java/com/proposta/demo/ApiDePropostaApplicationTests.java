package com.proposta.demo;

import com.proposta.demo.controller.PropostaController;
import com.proposta.demo.model.Proposta;
import com.proposta.demo.model.enums.StatusAvaliacaoProposta;
import com.proposta.demo.request.PropostaRequest;
import com.proposta.demo.service.AvaliaProposta;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;

@SpringBootTest
@RunWith(SpringRunner.class)
class ApiDePropostaApplicationTests {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private AvaliaProposta avaliaProposta;

	@Test
	@Transactional
	void deveRetornarElegivel_QuandoAvaliarProposta() {

		//Cenário
		String documento = "111.111.111-11";
		String nome = "Ronaldo Melo";
		String endereco = "Tv Sebastião Antônio, Nº 100, Centro, Lago Grande-SP, 67-183-123";
		String email = "ronaldo.melo@gmail.com" ;
		BigDecimal salario = BigDecimal.valueOf(4000.00);
		PropostaRequest request = new PropostaRequest(documento, nome, email, endereco, salario);
		Proposta proposta = request.toModel();
		manager.persist(proposta);

		//Ação
		proposta.atualizaStatus(avaliaProposta.getStatusAvaliacao(proposta.obterDadosParaAnalise()));

		//Avalidação
		Assert.assertTrue(proposta.getEstadoProposta().equals(StatusAvaliacaoProposta.ELEGIVEL));
	}


}
