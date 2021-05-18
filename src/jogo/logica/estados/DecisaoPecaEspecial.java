package jogo.logica.estados;

import jogo.logica.Situacao;
import jogo.logica.dados.JogoDados;

public class DecisaoPecaEspecial extends EstadoAdapter{
    public DecisaoPecaEspecial(JogoDados jogoDados){
        super(jogoDados);
    }

    public Situacao getSituacaoAtual(){
        return Situacao.DECISAO_PECA_ESPECIAL;
    }
}
