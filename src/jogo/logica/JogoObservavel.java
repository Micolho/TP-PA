package jogo.logica;

import jogo.logica.memento.Memento;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static jogo.iu.gui.ConstantesGUI.PROPRIEDADE_JOGO;

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
        propertyChangeSupport.firePropertyChange( PROPRIEDADE_JOGO, null, null);
    }
    //jogoOriginator
    public String toString(){
        return jogoGestao.toString();
    }

    public void opcoesjogo(){
        jogoGestao.opcoesjogo();
        propertyChangeSupport.firePropertyChange( PROPRIEDADE_JOGO, null, null);
    }

    public void iniciar_jogo(int tipo, String nome1, String nome2){
        jogoGestao.iniciar_jogo(tipo,nome1,nome2);
        propertyChangeSupport.firePropertyChange( PROPRIEDADE_JOGO, null, null);
    }

    public List<String> getMsgLog(){
        return jogoGestao.getMsgLog();
    }

    public void clearMsgLog(){
        jogoGestao.clearMsgLog();
    }

    public void random_jogador(){
        jogoGestao.random_jogador();
        propertyChangeSupport.firePropertyChange( PROPRIEDADE_JOGO, null, null);
    }

    public void jogar_peca(int coluna){
        jogoGestao.jogar_peca(coluna);
        propertyChangeSupport.firePropertyChange( PROPRIEDADE_JOGO, null, null);
    }

    public void terminar(){
        jogoGestao.terminar();
        propertyChangeSupport.firePropertyChange( PROPRIEDADE_JOGO, null, null);
    }

    public void afterFinish(){
        jogoGestao.afterFinish();
    }

    //minijogo related
    public void recusa_minijogo(){
        jogoGestao.recusa_minijogo();
        propertyChangeSupport.firePropertyChange( PROPRIEDADE_JOGO, null, null);
    }

    public void aceita_minijogo(){
        jogoGestao.aceita_minijogo();
        propertyChangeSupport.firePropertyChange( PROPRIEDADE_JOGO, null, null);
    }

    public void joga_minijogo_contas(int n){
        jogoGestao.joga_minijogo_contas(n);
        propertyChangeSupport.firePropertyChange( PROPRIEDADE_JOGO, null, null);
    }

    public void joga_minijogo_palavras(String palavra){
        jogoGestao.joga_minijogo_palavras(palavra);
        propertyChangeSupport.firePropertyChange( PROPRIEDADE_JOGO, null, null);
    }

    public void guarda_peca_especial(){
        jogoGestao.guarda_peca_especial();
        propertyChangeSupport.firePropertyChange( PROPRIEDADE_JOGO, null, null);
    }

    public void joga_peca_especial(int coluna){
        jogoGestao.joga_peca_especial(coluna);
        propertyChangeSupport.firePropertyChange( PROPRIEDADE_JOGO, null, null);
    }

    public String printNextPlayer() {
        return jogoGestao.printNextPlayer();

    }

    public void leHist(String Filename){
        jogoGestao.leHist(Filename);
        propertyChangeSupport.firePropertyChange( PROPRIEDADE_JOGO, null, null);
    }

    public String fileInDirectory(){
        return jogoGestao.fileInDirectory();
    }

    public List<String> ListOfFileInDirectory(){
        return jogoGestao.ListOfFileInDirectory();
    }



    //por alterar e corrigir
    public void ler(File filename){
        jogoGestao.loadFromFile(filename.toString());
    }

    public void guardar(File filename){
        jogoGestao.saveToFile(filename.toString());
    }

    public int[][] getTabuleiro() {
        return jogoGestao.getTabuleiro();
    }
}
