package jogo.logica.estados;

import jogo.logica.Situacao;
import jogo.logica.dados.JogoDados;

public class MiniJogoPalavras extends EstadoAdapter{

    public MiniJogoPalavras(JogoDados jogoDados){
        super(jogoDados);
    }

    public IEstado joga_minijogo_palavras(String n){
        getJogoDados().joga_palavras(n);
        return new AguardaJogada(getJogoDados());
    }

    public Situacao getSituacaoAtual(){
        return Situacao.MINIJOGO_PALAVRAS;
    }
}