package jogo.logica.estados;

import jogo.logica.Situacao;
import jogo.logica.dados.JogoDados;

public class AguardaJogada extends EstadoAdapter{

    public AguardaJogada(JogoDados jogoDados){
        super(jogoDados);
    }

    @Override
    public IEstado jogar_peca(int coluna) {
        if(!getJogoDados().validaJogada(coluna))
            return this;

        getJogoDados().joga(coluna);
        return this;
    }

    @Override
    public IEstado terminar() {
        return new FimJogo(getJogoDados());
    }

    @Override
    public Situacao getSituacaoAtual() {
        return Situacao.AGUARDA_JOGADA;
    }
}
