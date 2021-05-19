package jogo.logica;

import jogo.logica.dados.JogoDados;
import jogo.logica.estados.EscolheJogo;
import jogo.logica.estados.IEstado;
import jogo.logica.estados.MenuInformativo;

import java.util.List;

public class JogoMaqEstados {
    private JogoDados jogoDados;
    private IEstado estado;

    public JogoMaqEstados(){
        jogoDados = new JogoDados();
        estado = new MenuInformativo(jogoDados);
    }

    public void opcoesjogo(){
        estado = estado.opcoes_jogo();
    }

    public void iniciar_jogo(int tipo, String nome1, String nome2){
        estado = estado.iniciar_jogo(tipo, nome1, nome2);
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

    public void ganha_minijogo(){
        estado = estado.ganha_minijogo();
    }

    public void perde_minijogo(){
        estado = estado.perde_minijogo();
    }

    public void terminar(){
        estado = estado.terminar();
    }



    @Override
    public String toString() {
        return jogoDados.toString();
    }
}
