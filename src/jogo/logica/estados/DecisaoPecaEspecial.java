package jogo.logica.estados;

import jogo.logica.Situacao;
import jogo.logica.dados.JogoDados;

public class DecisaoPecaEspecial extends EstadoAdapter{
    public DecisaoPecaEspecial(JogoDados jogoDados){
        super(jogoDados);
    }

    public IEstado guarda_peca_especial(){
        getJogoDados().guardaPecaEspecial();
        return new AguardaJogada(getJogoDados());
    }

    public IEstado joga_peca_especial(int coluna){
        if(!getJogoDados().colunaValida(coluna))
            return this;

        getJogoDados().jogaPecaEspecial(coluna);
        return new AguardaJogada(getJogoDados());
    }

    public Situacao getSituacaoAtual(){
        return Situacao.DECISAO_PECA_ESPECIAL;
    }
}
