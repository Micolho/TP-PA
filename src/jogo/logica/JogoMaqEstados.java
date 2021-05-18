package jogo.logica;

import jogo.logica.dados.JogoDados;
import jogo.logica.estados.EscolheJogo;
import jogo.logica.estados.IEstado;

import java.util.List;

public class JogoMaqEstados {
    private JogoDados jogoDados;
    private IEstado estado;

    public JogoMaqEstados(){
        jogoDados = new JogoDados();
        estado = new EscolheJogo(jogoDados);
    }

    public void iniciar_jogo(int tipo, String nome1, String nome2) throws Exception{
        try {
            estado = estado.iniciar_jogo(tipo, nome1, nome2);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public Situacao getSituacaoAtual(){
        return estado.getSituacaoAtual();
    }

    public List<String> getMsgLog(){
        return jogoDados.getMsgLog();
    }

    public void clearMsgLog(){
        jogoDados.clearMsgLog();
    }

    public void random_jogador(){
        estado = estado.random_jogador();
    }

    public void jogar_peca(int coluna){
        estado = estado.jogar_peca(coluna);
    }

}
