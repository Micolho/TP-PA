package jogo.logica;

import jogo.logica.dados.Jogador;
import jogo.logica.dados.JogoDados;
import jogo.logica.estados.IEstado;
import jogo.logica.estados.MenuInformativo;

import java.io.Serializable;
import java.util.List;

public class JogoMaqEstados implements Serializable {
    private static final long serialVersionUID = 1L;
    private JogoDados jogoDados;
    private IEstado estado;

    public JogoMaqEstados() throws Exception{
        try{
        jogoDados = new JogoDados();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
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

    public void addMsgLog(String msg){
        jogoDados.addMsgLog(msg);
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
        return estado.getSituacaoAtual() +"\n"
                + jogoDados.printNextPlayer() +"\n"+ jogoDados.toString();
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

    public boolean deduzCreditos(int n){
        return jogoDados.deduzCreditos(n);
    }

    public void resetUndo(Jogador jogador){
        jogoDados.resetUndo(jogador);
    }

    public int getCreditos(){
        return jogoDados.getCreditos();
    }

    public String printNextPlayer() {
        return jogoDados.printNextPlayer();
    }

    public Jogador getPlayer(){
        return jogoDados.getPlayer();
    }

}
