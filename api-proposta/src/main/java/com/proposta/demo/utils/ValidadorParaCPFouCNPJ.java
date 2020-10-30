package com.proposta.demo.utils;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

public class ValidadorParaCPFouCNPJ {

    /*Como funciona? EX: 123.121.365-85
    *\d representa qualquer número na faixa 0-9
    *
    *{3} representa o total de ocorrência de alguma coisa, neste caso, \d{3} representa a
    *    ocorrência de 3 números.
    *
    *() agrupam partes de uma expressão regular, no caso "(\d{3}\.){2} a grupa a parte do CPF
    *   que correponde ao 123.121., ou seja, duas ocorrências de três números seguidos de um ponto
    *
    * \\ ignora o ponto(.) e traço(-) porque ambos são metacaracteres
    *
    * \d{3} representa a ocorrência de dois números -> 365
    *
    * \d{2} representa a ocorrência de dois números, no caso a última parte do CPF -> 85*/
    private static String cpfRegex = "(\\d{3}\\.){2}\\d{3}\\-\\d{2}";

    private static String cnpjRegex = "\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}";

    public static String isValid(String cpfOuCnpj){

        String saidaValida = null;

        if(cpfOuCnpj.matches(cnpjRegex)){
            @CNPJ
            String cnpjValido = cpfOuCnpj;
            saidaValida = cnpjValido;
        }

        if(cpfOuCnpj.matches(cpfRegex)){
            @CPF
            String cpfValido = cpfOuCnpj;
            saidaValida = cpfValido;
        }

        return saidaValida;

    }

}
