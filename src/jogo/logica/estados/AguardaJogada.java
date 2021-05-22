package jogo.logica.estados;

import jogo.logica.Situacao;
import jogo.logica.dados.JogoDados;

public class AguardaJogada extends EstadoAdapter{

    public AguardaJogada(JogoDados jogoDados){
        super(jogoDados);
    }

    @Override
    public IEstado jogar_peca(int coluna) {
        //int ret;

        if(!getJogoDados().validaJogada(coluna) && getJogoDados().isHumano())
            return this;

        //ret = getJogoDados().joga(coluna);
        if (getJogoDados().apos4jogadas())
            return new DecideMiniJogo(getJogoDados());

        if(getJogoDados().joga(coluna)){
            return new FimJogo(getJogoDados());
        }

        return this;
    }
    @Override
    public IEstado joga_peca_especial(int coluna){
        if (!getJogoDados().jogadorTemPecaEspecial())
            return new AguardaJogada(getJogoDados());

        if(!getJogoDados().colunaValida(coluna))
            return new AguardaJogada(getJogoDados());

        getJogoDados().jogaPecaEspecial(coluna);
        return new AguardaJogada(getJogoDados());
    }


    @Override
    public IEstado terminar() {
        return new FimJogo(getJogoDados());
    }

    @Override
    public Situacao getSituacaoAtual() {
        if (getJogoDados().isHumano() &&
                !getJogoDados().apos4jogadas())
            return Situacao.AGUARDA_JOGADA_HUMANA;
        return Situacao.AGUARDA_JOGADA_VIRTUAL;
    }
}
