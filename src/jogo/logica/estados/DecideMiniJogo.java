package jogo.logica.estados;

import jogo.logica.Situacao;
import jogo.logica.dados.JogoDados;

public class DecideMiniJogo extends EstadoAdapter{

    public DecideMiniJogo(JogoDados jogoDados){
        super(jogoDados);
    }

    public IEstado apos4jogadas(int escolha) {
        //por fazer
        if(getJogoDados().apos4jogadas(escolha)){
            return new MiniJogo(getJogoDados());
        }else{
            return new AguardaJogada(getJogoDados());
        }
    }

    public Situacao getSituacaoAtual(){
        return Situacao.QUER_MINIJOGO;
    }
}
