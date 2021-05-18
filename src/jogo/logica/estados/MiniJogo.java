package jogo.logica.estados;

import jogo.logica.Situacao;
import jogo.logica.dados.JogoDados;

public class MiniJogo extends EstadoAdapter{

    public MiniJogo(JogoDados jogoDados){
        super(jogoDados);
    }



    public Situacao getSituacaoAtual(){
        return Situacao.MINIJOGO;
    }
}
