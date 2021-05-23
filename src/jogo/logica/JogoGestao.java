package jogo.logica;

import jogo.logica.memento.CareTaker;
import jogo.logica.memento.JogoOriginator;
import jogo.logica.memento.Memento;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JogoGestao {
    private JogoOriginator jogoOriginator;
    private CareTaker careTaker;


    public JogoGestao() throws Exception{
        try {
            this.jogoOriginator = new JogoOriginator();
            this.careTaker = new CareTaker(jogoOriginator);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    //caretaker
    public void undo(int n){
        careTaker.undo(n);
    }
    //jogoOriginator
    public String toString(){
        return jogoOriginator.toString();
    }

    public void opcoesjogo(){
        jogoOriginator.opcoesjogo();
    }

    public void iniciar_jogo(int tipo, String nome1, String nome2){
        jogoOriginator.iniciar_jogo(tipo,nome1,nome2);
    }

    public List<String> getMsgLog(){
        return jogoOriginator.getMsgLog();
    }

    public void clearMsgLog(){
        jogoOriginator.clearMsgLog();
    }

    public void random_jogador(){
        jogoOriginator.random_jogador();
    }

    public void jogar_peca(int coluna){
        careTaker.gravaMemento();
        jogoOriginator.jogar_peca(coluna);
    }

    public void terminar(){
        jogoOriginator.terminar();
    }

    public void afterFinish(){
        try {
            careTaker.gravaHist();
        }catch (IOException e){
            jogoOriginator.addMsgLog("Erro gravaHist: " + e.getMessage());
        }
        careTaker.clearHist();
    }

    //minijogo related
    public void recusa_minijogo(){
        jogoOriginator.recusa_minijogo();
    }

    public void aceita_minijogo(){
        jogoOriginator.aceita_minijogo();
    }

    public void joga_minijogo_contas(int n){
        jogoOriginator.joga_minijogo_contas(n);
    }

    public void joga_minijogo_palavras(String palavra){
        jogoOriginator.joga_minijogo_palavras(palavra);
    }

    public void guarda_peca_especial(){
        jogoOriginator.guarda_peca_especial();
    }

    public void joga_peca_especial(int coluna){
        careTaker.gravaMemento();
        jogoOriginator.joga_peca_especial(coluna);
    }

    public String printNextPlayer() {
        return jogoOriginator.printNextPlayer();
    }

    public Situacao getSituacaoAtual(){
        return jogoOriginator.getSituacaoAtual();
    }

    public void leHist(String Filename){
        try {
            JogoMaqEstados tmp = null;
            ObjectInputStream ois = null;
            ois = new ObjectInputStream(new FileInputStream("./ficheiros/"+Filename));

            ArrayList<Memento> histFromFile;
            histFromFile = (ArrayList<Memento>) ois.readObject();

            for (Memento memento : histFromFile) {
                if (memento == null)
                    return;
                ois = new ObjectInputStream(new ByteArrayInputStream(memento.getSnapshot()));
                tmp = (JogoMaqEstados) ois.readObject();
                jogoOriginator.addMsgLog(tmp + "\n");
            }
            ois.close();
        }catch(IOException | ClassNotFoundException e){
            jogoOriginator.addMsgLog("Erro leHist: " + e.getMessage());
        }
    }

    public String fileInDirectory(){
        StringBuilder s = new StringBuilder();
        File dir = new File("./ficheiros/");
        File [] files = dir.listFiles((File d, String name) ->{ // lambda para retornar ficheiros acabados em .bin
            return name.endsWith(".bin") && name.contains("historico");
        });

        for (File f : files) {
            s.append(f.getName()).append("\n");
        }
        return s.toString();
    }
}
