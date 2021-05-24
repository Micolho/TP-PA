package jogo.logica.memento;

import jogo.logica.JogoMaqEstados;
import jogo.logica.dados.Jogador;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CareTaker {

    private final JogoOriginator jogoOriginator;

    private List<Memento> listHist = new ArrayList<>();

    public CareTaker(JogoOriginator jogoOriginator) {
        this.jogoOriginator = jogoOriginator;
    }

    public void gravaMemento() {
        Memento memento;
        try {
            memento = jogoOriginator.getMemento();
        } catch (Exception e) {
            memento = null;
            jogoOriginator.addMsgLog("Erro getMemento: " + e.getMessage());
        }
        if (memento != null) {
            listHist.add(memento);
        } else {
            listHist.clear();
        }
    }

    public void undo(int n) {
        if (listHist.isEmpty()) {
            jogoOriginator.addMsgLog("Sem jogadas anteriores, logo nao e possivel desfazer a jogada!");
            return;
        }

        if (n <= 0) {
            jogoOriginator.addMsgLog("Introduza um valor que seja valido inteiro maior que 0.");
            return;
        }

        if (listHist.size() < n) {
            jogoOriginator.addMsgLog("O numero de jogadas a retroceder(" + n + ") nao possibilita retroceder pois so ha " + listHist.size() + " pra retroceder!");
            return;
        }

        if (!jogoOriginator.deduzCreditos(n)) {
            jogoOriginator.addMsgLog("Numero de creditos nao possibilita retroceder, tem " + jogoOriginator.getCreditos() + " creditos.");
            return;
        }

        Jogador tmp = jogoOriginator.getPlayer(); // saber o jogador que requesitou o voltar atras
//        int listMaxSize = listHist.size();
//        int aux = listHist.size() - n;

        Memento anterior = listHist.get(listHist.size() - n);

//        for (; aux < listMaxSize;aux++){
//            listHist.remove(listHist.size() - 1);
//        }
        try {
            jogoOriginator.setMemento(anterior);
            jogoOriginator.resetUndo(tmp);
        } catch (Exception e) {
            jogoOriginator.addMsgLog("Erro setMemento: " + e.getMessage());
        }
    }

    public void clearHist() {
        listHist.clear();
    }

    public void gravaHist() throws IOException {
        ObjectOutputStream out = null;

        try {
            out = new ObjectOutputStream(new FileOutputStream(".\\ficheiros\\historico1.bin"));
            out.writeObject(listHist);
        } catch (IOException e) {
            jogoOriginator.addMsgLog("Erro GravaHist: " + e.getMessage());
        } finally {
            if (out != null) out.close();
        }
    }

    public Memento getLastMemento() {
        return listHist.get(listHist.size()-1);
    }
}