package jogo.logica.estados;

import jogo.logica.Situacao;
import jogo.logica.dados.JogoDados;

public class MiniJogoContas extends EstadoAdapter{

    public MiniJogoContas(JogoDados jogoDados){
        super(jogoDados);
    }

    public IEstado joga_minijogo_contas(int n){
        switch(getJogoDados().joga_contas(n)){
            case 1: //ganhou minijogo
                return new DecisaoPecaEspecial(getJogoDados());
            case 2: //perdeu minijogo
                return new AguardaJogada(getJogoDados());
            default:
                return this; // ainda nao acabou
        }
    }

    public Situacao getSituacaoAtual(){
        return Situacao.MINIJOGO_CONTAS;
    }
}