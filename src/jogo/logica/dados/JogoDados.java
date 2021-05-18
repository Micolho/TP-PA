package jogo.logica.dados;

import java.util.ArrayList;
import java.util.List;

public class JogoDados {
    private static final int nLinhas = 6, nColunas = 7;

    //decorrer do jogo
    private int nextPlayer; // 1 - player1, 2 - player2
    //dados player 1 e 2
    private Jogador j1, j2;
    //tabuleiro
    private int[][] tabuleiro = new int[nLinhas][nColunas];
    //logs para o main
    private List<String> msgLog = new ArrayList<>();
    /*   private ArrayList<ArrayList<Integer>> tabuleiro = new ArrayList<>(6);// 6 = numLinhas

    public JogoDados(){
        for(int i=0; i <= 6; i++){// 7 = numLinhas
            tabuleiro.add(new ArrayList(7));
        }
    }*/

    public JogoDados(){
        //inicializacao do tabuleiro a zeros
        for(int i=0; i < nLinhas; i++){
            for(int j=0; j < nColunas; j++){
                tabuleiro[i][j] = 0;
            }
        }
    }

    public void inicia(int tipo, String nome1, String nome2) throws Exception{
        if (nome1.replace(" ","").equalsIgnoreCase(nome2.replace(" ",""))){
            //addMsgLog("Nomes dos jogadores nao podem ser iguais!");
            throw new Exception("O nome dos jogadores nao podem ser iguais!");
        }
        if (nome1.equals("") || nome2.equals("")){
            //addMsgLog("Nomes nao podem ser vazios!");
            throw new Exception("Os nomes dos jogadores nao podem ser vazios!");
        }
        setTipoJogo(tipo, nome1, nome2);
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
                this.j1 = new JogadorHumano(nome1);
                this.j2 = new JogadorHumano(nome2);
            }
            case 2 -> {
                this.j1 = new JogadorHumano(nome1);
                this.j2 = new JogadorVirtual(nome2);
            }
            case 3 -> {
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
}
