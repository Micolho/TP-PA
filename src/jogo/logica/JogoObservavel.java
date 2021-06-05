package jogo.logica;

import jogo.logica.memento.Memento;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JogoObservavel {
    private JogoGestao jogoGestao;
    private final PropertyChangeSupport propertyChangeSupport;

    public JogoObservavel(JogoGestao jogoGestao){
        this.jogoGestao = jogoGestao;
        propertyChangeSupport = new PropertyChangeSupport(jogoGestao);
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    public Situacao getSituacaoAtual(){
        return jogoGestao.getSituacaoAtual();
    }

    // metodos que alteram
    //caretaker
    public void undo(int n){
        jogoGestao.undo(n);
    }
    //jogoOriginator
    public String toString(){
        return jogoGestao.toString();
    }

    public void opcoesjogo(){
        jogoGestao.opcoesjogo();
    }

    public void iniciar_jogo(int tipo, String nome1, String nome2){
        jogoGestao.iniciar_jogo(tipo,nome1,nome2);
    }

    public List<String> getMsgLog(){
        return jogoGestao.getMsgLog();
    }

    public void clearMsgLog(){
        jogoGestao.clearMsgLog();
    }

    public void random_jogador(){
        jogoGestao.random_jogador();
    }

    public void jogar_peca(int coluna){
        jogoGestao.jogar_peca(coluna);
    }

    public void terminar(){
        jogoGestao.terminar();
    }

    public void afterFinish(){
        jogoGestao.afterFinish();
    }

    //minijogo related
    public void recusa_minijogo(){
        jogoGestao.recusa_minijogo();
    }

    public void aceita_minijogo(){
        jogoGestao.aceita_minijogo();
    }

    public void joga_minijogo_contas(int n){
        jogoGestao.joga_minijogo_contas(n);
    }

    public void joga_minijogo_palavras(String palavra){
        jogoGestao.joga_minijogo_palavras(palavra);
    }

    public void guarda_peca_especial(){
        jogoGestao.guarda_peca_especial();
    }

    public void joga_peca_especial(int coluna){
        jogoGestao.joga_peca_especial(coluna);
    }

    public String printNextPlayer() {
        return jogoGestao.printNextPlayer();
    }

    public void leHist(String Filename){
        jogoGestao.leHist(Filename);
    }

    public String fileInDirectory(){
        return jogoGestao.fileInDirectory();
    }



    //por alterar e corrigir
    public void ler(File filename){
        jogoGestao.loadFromFile(filename.toString());
    }

    public void guardar(File filename){
        jogoGestao.saveToFile(filename.toString());
    }
}
