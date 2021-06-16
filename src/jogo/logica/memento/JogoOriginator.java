package jogo.logica.memento;

import jogo.logica.JogoMaqEstados;
import jogo.logica.Situacao;
import jogo.logica.dados.Jogador;

import java.io.*;
import java.util.List;

public class JogoOriginator {
    private JogoMaqEstados jogoMaqEstados;

    public JogoOriginator() throws Exception{
        try {
            this.jogoMaqEstados = new JogoMaqEstados();
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public void opcoesjogo(){
        jogoMaqEstados.opcoesjogo();
    }

    public void iniciar_jogo(int tipo, String nome1, String nome2){
        jogoMaqEstados.iniciar_jogo(tipo, nome1, nome2);
    }

    public List<String> getMsgLog(){
        return jogoMaqEstados.getMsgLog();
    }

    public void clearMsgLog(){
        jogoMaqEstados.clearMsgLog();
    }

    public void random_jogador(){
        jogoMaqEstados.random_jogador();
    }

    public void jogar_peca(int coluna){
        jogoMaqEstados.jogar_peca(coluna);
    }

    public void terminar(){
        jogoMaqEstados.terminar();
    }

    public void recusa_minijogo(){
        jogoMaqEstados.recusa_minijogo();
    }

    public void aceita_minijogo(){
        jogoMaqEstados.aceita_minijogo();
    }

    public void joga_minijogo_contas(int n){
        jogoMaqEstados.joga_minijogo_contas(n);
    }

    public void joga_minijogo_palavras(String palavra){
        jogoMaqEstados.joga_minijogo_palavras(palavra);
    }

    public void guarda_peca_especial(){
        jogoMaqEstados.guarda_peca_especial();
    }

    public void joga_peca_especial(int coluna){
        jogoMaqEstados.joga_peca_especial(coluna);
    }


    public Situacao getSituacaoAtual(){
        return jogoMaqEstados.getSituacaoAtual();
    }

    public String toString(){
        return "\n" + jogoMaqEstados.toString();
    }

    public boolean deduzCreditos(int n){
        return jogoMaqEstados.deduzCreditos(n);
    }

    public void resetUndo(Jogador tmp){
        jogoMaqEstados.resetUndo(tmp);
    }

    public void addMsgLog(String msg){
        jogoMaqEstados.addMsgLog(msg);
    }

    public int getCreditos(){
        return jogoMaqEstados.getCreditos();
    }

    public String printNextPlayer() {
        return jogoMaqEstados.printNextPlayer();
    }

    public Jogador getPlayer(){
        return jogoMaqEstados.getPlayer();
    }


    //for caretaker
    public Memento getMemento()throws Exception{
        byte[] snapshot = null;

        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this.jogoMaqEstados);
            oos.close();
            snapshot = baos.toByteArray();
        }catch (IOException e){
            throw new Exception(e.getMessage());
            //return null;
        }
        return new Memento(snapshot);
    }


    public void setMemento(Memento memento) throws Exception{ // restore
        if(memento==null)
            return;

        try{
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(memento.getSnapshot()));
            this.jogoMaqEstados = (JogoMaqEstados) ois.readObject();
            ois.close();
        }catch (ClassNotFoundException | IOException e){
            throw new Exception(e.getMessage());
        }
    }

    public int[][] getTabuleiro() {
        return jogoMaqEstados.getTabuleiro();
    }

    public boolean temErros() {
        return jogoMaqEstados.temErros();
    }

    public void setErros(boolean b) {
        jogoMaqEstados.setErros(b);
    }
}
