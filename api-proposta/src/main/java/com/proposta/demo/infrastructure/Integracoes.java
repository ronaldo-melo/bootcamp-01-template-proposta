package com.proposta.demo.infrastructure;

import com.proposta.demo.model.ResultadoAnalise;
import com.proposta.demo.model.SolicitacaoAnalise;
import com.proposta.demo.request.PropostaRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "${feign.analise-url}", name = "integracoes")
public interface Integracoes {

    /*
     O que é Feign? é um declarador de serviços web. Torna a escrita de web services mais fácil.
     Para dizer para a aplicação SpringBoot que o Feign será usado é preciso usar a anotação @EnableFeignClients
     no arquivo que faz o start da aplicação. Esta anotação irá tornar as interfaces que declaram ser Feign Clients
     componentes no Spring.
     Para usar o Fiegn é preciso criar uma interface e anota-lá com @FeignClient.
     Na anotação @FeignClient para configurar um cliente de API são usados parâmetros.
     O parâmetro value recebe um valor arbitrário para designar o nome do cliente. Com o argumento
     'url' é especificado o caminho da API.
     Desde que uma interface seja um cliente Feign, é possível usar as annotations do Spring Web para
     declarar outros endereços de API's diferentes para serem consumidas por uma mesma aplicação.
     OBS: Na anotação @FeignClient em versões anteriores era precisa informar apenas o atributo
     'url', atualmente é obrigatório forncer o nome da api no atributo 'name'.
     * */

    @PostMapping("/api/solicitacao")
    ResponseEntity<ResultadoAnalise> avalia(SolicitacaoAnalise solicitacaoAnalise);

}
