package jogo.logica.estados;

import jogo.logica.Situacao;
import jogo.logica.dados.JogoDados;

public class PrimeiroAJogar extends EstadoAdapter{

    public PrimeiroAJogar(JogoDados jogoDados){
        super(jogoDados);
    }

    @Override
    public IEstado random_jogador() {
        return this;
    }

    public Situacao getSituacaoAtual(){
        return Situacao.PRIMEIRO_A_JOGAR;
    }
}
