package jogo.logica.estados;

import jogo.logica.Situacao;
import jogo.logica.dados.JogoDados;

public class AguardaJogada extends EstadoAdapter{

    public AguardaJogada(JogoDados jogoDados){
        super(jogoDados);
    }

    @Override
    public IEstado jogar_peca(int coluna) {
        int ret;

        if(!getJogoDados().validaJogada(coluna) && getJogoDados().precisaInput())
            return this;

        ret = getJogoDados().joga(coluna);

        switch(ret){
            case 1,2:
                return new FimJogo(getJogoDados());
            case 0:
                return this;
        }

        return this;
    }

    @Override
    public IEstado terminar() {
        return new FimJogo(getJogoDados());
    }

    @Override
    public Situacao getSituacaoAtual() {
        if (getJogoDados().precisaInput())
            return Situacao.AGUARDA_JOGADA_HUMANA;
        return Situacao.AGUARDA_JOGADA_VIRTUAL;
    }
}
