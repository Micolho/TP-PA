package jogo.logica.estados;

import jogo.logica.Situacao;
import jogo.logica.dados.JogoDados;

public class FimJogo extends EstadoAdapter{
    public FimJogo(JogoDados jogoDados){
        super(jogoDados);
    }

    @Override
    public IEstado iniciar_jogo(int tipo, String nome1, String nome2) throws Exception{
        try {
            getJogoDados().inicia(tipo, nome1, nome2);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return new PrimeiroAJogar(getJogoDados());
    }

    public Situacao getSituacaoAtual(){
        return Situacao.FIM_JOGO;
    }
}
