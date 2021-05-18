package jogo.logica.estados;

import jogo.logica.Situacao;
import jogo.logica.dados.JogoDados;

public class FimJogo extends EstadoAdapter{
    public FimJogo(JogoDados jogoDados){
        super(jogoDados);
    }

    @Override
    public IEstado opcoes_jogo(){
        return new EscolheJogo(getJogoDados());
    }

    public Situacao getSituacaoAtual(){
        return Situacao.FIM_JOGO;
    }
}
