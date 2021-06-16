package jogo.logica.estados;

import jogo.logica.Situacao;
import jogo.logica.dados.JogoDados;

public class EscolheJogo extends EstadoAdapter{

    public EscolheJogo(JogoDados jogoDados){
        super(jogoDados);
    }

    @Override
    public IEstado iniciar_jogo(int tipo, String nome1, String nome2){
//        try {
//            getJogoDados().inicia(tipo, nome1, nome2);
//        } catch (Exception e){
//            throw new Exception(e.getMessage());
//        }
        if (!getJogoDados().inicia(tipo, nome1, nome2))
            return this;
        getJogoDados().setTabuleiroZeros();
        getJogoDados().randomJogador();
        return new AguardaJogada(getJogoDados());
    }

    public Situacao getSituacaoAtual(){
        return Situacao.ESCOLHE_JOGO;
    }
}
