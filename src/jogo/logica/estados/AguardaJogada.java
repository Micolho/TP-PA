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

        if(getJogoDados().joga(coluna)){
            return
        }
        return this;
    }

    @Override
    public IEstado terminar() {
        return new FimJogo(getJogoDados());
    }

    @Override
    public Situacao getSituacaoAtual() {
        //if (getJogoDados())
        return Situacao.AGUARDA_JOGADA;
    }
}
