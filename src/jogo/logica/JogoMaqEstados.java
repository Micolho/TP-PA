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

    public void terminar(){
        estado = estado.terminar();
    }

    @Override
    public String toString() {
        return jogoDados.toString();
    }

    public void recusa_minijogo() {
        estado = estado.recusa_minijogo();
    }

    public void aceita_minijogo() {
        estado = estado.aceita_minijogo();
    }

    public void joga_minijogo_contas(int n){
        estado = estado.joga_minijogo_contas(n);
    }

    public void joga_minijogo_palavras(String n){
        estado = estado.joga_minijogo_palavras(n);
    }

    public void guarda_peca_especial(){
        estado = estado.guarda_peca_especial();
    }

    public void joga_peca_especial(int coluna){
        estado = estado.joga_peca_especial(coluna);
    }
}
