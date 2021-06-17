package jogo.logica.estados;

import jogo.logica.Situacao;
import jogo.logica.dados.JogoDados;

public class MiniJogoContas extends EstadoAdapter{

    public MiniJogoContas(JogoDados jogoDados){
        super(jogoDados);
    }

    public IEstado joga_minijogo_contas(int n){
        return switch (getJogoDados().joga_contas(n)) {
            //ganhou minijogo ou perdeu minijogo
            case 1,2 -> new AguardaJogada(getJogoDados());
            // ainda nao acabou
            default -> this;
        };
    }

    public Situacao getSituacaoAtual(){
        return Situacao.MINIJOGO_CONTAS;
    }
}