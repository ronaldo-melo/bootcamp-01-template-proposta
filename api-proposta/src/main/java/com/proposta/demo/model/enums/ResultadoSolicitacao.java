package com.proposta.demo.model.enums;

/*Converte o Status da resposta para Elivel ou não elegivel pq a resposta devolve apenas
* com restrição ou sem restrição*/
public enum ResultadoSolicitacao {

    COM_RESTRICAO(StatusAvaliacaoProposta.NAO_ELEGIVEL),
    SEM_RESTRICAO(StatusAvaliacaoProposta.ELEGIVEL);

    private StatusAvaliacaoProposta statusAvaliaca;

    private ResultadoSolicitacao(StatusAvaliacaoProposta statusAvaliaca){
        this.statusAvaliaca = statusAvaliaca;
    }

    public StatusAvaliacaoProposta getStatusAvaliacao(){
        return this.statusAvaliaca;
    }

}
