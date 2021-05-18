package jogo.logica.estados;

import jogo.logica.Situacao;
import jogo.logica.dados.JogoDados;

public class MiniJogo extends EstadoAdapter{

    public MiniJogo(JogoDados jogoDados){
        super(jogoDados);
    }

    public IEstado ganha_minijogo(){
        //falta coisas aqui
        return new DecisaoPecaEspecial(getJogoDados());
    }

    public IEstado perde_minijogo(){
        //falta coisas aqui
        return new AguardaJogada(getJogoDados());
    }

    public Situacao getSituacaoAtual(){
        return Situacao.MINIJOGO;
    }
}