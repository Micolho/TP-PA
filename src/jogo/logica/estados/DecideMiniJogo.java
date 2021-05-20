package jogo.logica.estados;

import jogo.logica.Situacao;
import jogo.logica.dados.JogoDados;

public class DecideMiniJogo extends EstadoAdapter{

    public DecideMiniJogo(JogoDados jogoDados){
        super(jogoDados);
    }

    public IEstado aceita_minijogo() {
        getJogoDados().inicializaMiniJogo();
        if(getJogoDados().isContas())
            return new MiniJogoContas(getJogoDados());

        return new MiniJogoPalavras(getJogoDados());
    }
    public IEstado recusa_minijogo(){
        getJogoDados().recusaMiniGame();
        return new AguardaJogada(getJogoDados());
    }


    public Situacao getSituacaoAtual(){
        return Situacao.QUER_MINIJOGO;
    }
}
