package jogo.logica.dados;

import jogo.logica.Tipo;

import java.util.ArrayList;
import java.util.List;

import static jogo.logica.Tipo.*;

public class JogoDados {
    private static final int nLinhas = 6, nColunas = 7;
    private Tipo tipoJogo;
    private int nextPlayer;
    private Jogador j1, j2;
    private int[][] tabuleiro = new int[nLinhas][nColunas];
    private List<String> msgLog = new ArrayList<>();


    public JogoDados(){
        //inicializacao do tabuleiro a zeros
        for(int i=0; i < nLinhas; i++){
            for(int j=0; j < nColunas; j++){
                tabuleiro[i][j] = 0;
            }
        }
    }

    public boolean inicia(int tipo, String nome1, String nome2){
        if (nome1.replace(" ","").equalsIgnoreCase(nome2.replace(" ",""))){
            addMsgLog("O nome dos jogadores nao podem ser iguais!\n");
            return false;
        }
        if (nome1.equals("") || nome2.equals("")){
            addMsgLog("Os nomes dos jogadores nao podem ser vazios!\n");
            return false;
        }
        setTipoJogo(tipo, nome1, nome2);
        return true;
    }

    public String getPlayers(){
        return j1.toString() + j2.toString();
    }

    public void randomJogador() {
        //addMsgLog("\nVai ser executada a escolhar do primeiro jogador: \n");
        //switch((Math.random() <= 0.5) ? 1 : 2){ //valores gerados sao 1 ou 2, ambos com chance de 50% pra ser justo
        if (((int) ( Math.random() * 2 + 1)) == 1) { //ganhou o sorteio
            addMsgLog("O Jogador " + j1.getNome() + " ganhou o sorteio do primeiro a jogar!");
        } else {
            nextPlayer = 2;// Player 1 ganhou o sorteio
            addMsgLog("O Jogador " + j2.getNome() + " ganhou o sorteio do primeiro a jogar!");
        }
    }

    public void setTipoJogo(int tipo, String nome1, String nome2) {
        // 1 - player vs player
        // 2 - player vs computer
        // 3 - computer vs computer
        switch (tipo) { //suggest do intelij switch enhanced
            case 1 -> {
                this.tipoJogo = PlayerVsPlayer;
                this.j1 = new JogadorHumano(nome1);
                this.j2 = new JogadorHumano(nome2);
            }
            case 2 -> {
                this.tipoJogo = PlayerVsComputer;
                this.j1 = new JogadorHumano(nome1);
                this.j2 = new JogadorVirtual(nome2);
            }
            case 3 -> {
                this.tipoJogo = ComputerVsComputer;
                this.j1 = new JogadorVirtual(nome1);
                this.j2 = new JogadorVirtual(nome2);
            }
        }
    }

    // log
    public void clearMsgLog(){
        msgLog.clear();
    }

    public void addMsgLog(String msg){
        msgLog.add(msg);
    }

    public List<String> getMsgLog(){
        return msgLog;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(); // mais eficas que concatenar strings
        s.append("\nTabuleiro: \n\n");
        for(int i = 0; i <= nColunas; i++){
            if (i == 0 ){
                s.append(" | ");
                continue;
            }
            s.append(i).append(" | ");
        }
        s.append("\n");
        for( int i = 0; i < nLinhas; i++){
            s.append("\n" + " | ");
            for( int j = 0; j < nColunas; j++){
                if (tabuleiro[i][j] == 0){
                    s.append("  | ");
                }else{
                    s.append(tabuleiro[i][j]).append(" | ");
                }
            }
        }
        return s.toString();
    }

    public boolean apos4jogadas() {

        return switch (tipoJogo) {
            case PlayerVsPlayer -> true;
            // ainda nao esta totalmente correto
            case PlayerVsComputer -> true;
            case ComputerVsComputer -> false;
        };
    }

    public boolean joga(int coluna) {
    //versao muito simples e incompleta
        for(int i = nLinhas - 1; i >= 0; --i){
            if(tabuleiro[i][coluna-1] == 0){
                tabuleiro[i][coluna-1] = 1;
                addMsgLog("aposta com sucesso!\n");
            }
        }
        return false;
    }

    public boolean validaJogada(int coluna){
        if (coluna > 7 || coluna < 1) {
            addMsgLog("Coluna Invalida\n");
            return false;
        }

        for(int i = nLinhas - 1; i >= 0; --i){
            if(tabuleiro[i][coluna-1] == 0){
                return true;
            }
        }
        addMsgLog("Coluna cheia!\n");
        return false;
    }
}
