package com.proposta.demo;

import com.proposta.demo.model.Proposta;
import com.proposta.demo.model.enums.StatusAvaliacaoProposta;
import com.proposta.demo.request.PropostaRequest;
import com.proposta.demo.service.AvaliaProposta;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@SpringBootTest
@RunWith(SpringRunner.class)
class ApiDePropostaApplicationTests {

	@Autowired
	private AvaliaProposta avaliaProposta;

	@Test
	void deveRetornarElegivel_QuandoAvaliarProposta() {

		//Cenário
		String documento = "111.111.111-11";
		String nome = "Ronaldo Melo";
		String email = "Tv Sebastião Andrade, Nº 4, Centro, Belterra-PA, 68-143-000";
		String endereco = "ronaldo@gmail.com" ;
		BigDecimal salario = BigDecimal.valueOf(4000.00);
		PropostaRequest request = new PropostaRequest(documento, nome, email, endereco, salario);
		Proposta proposta = request.toModel();

		//Ação
		proposta.atualizaStatus(avaliaProposta.getStatusAvaliacao(proposta.obterDadosParaAnalise()));

		//Avalidação
		Assert.assertTrue(proposta.getEstadoProposta().equals(StatusAvaliacaoProposta.ELEGIVEL));
	}

}
