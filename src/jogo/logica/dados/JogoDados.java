package jogo.logica.dados;

import jogo.logica.Tipo;

import java.util.ArrayList;
import java.util.List;

import static jogo.logica.Tipo.*;

public class JogoDados {
    private static final int nLinhas = 6, nColunas = 7;
    private Tipo tipoJogo;
    private Jogador nextPlayer;
    private Jogador j1, j2;
    private int[][] tabuleiro = new int[nLinhas][nColunas];
    private List<String> msgLog = new ArrayList<>();


    public JogoDados(){
        setTabuleiroZeros();
    }

    public void setTabuleiroZeros(){
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
            nextPlayer = j1;
        } else {
            nextPlayer = j2;
            addMsgLog("O Jogador " + j2.getNome() + " ganhou o sorteio do primeiro a jogar!");

        }

        addMsgLog(this.toString());//imprimir o tabuleiro
    }

    public void setTipoJogo(int tipo, String nome1, String nome2) {
        // 1 - player vs player
        // 2 - player vs computer
        // 3 - computer vs computer
        switch (tipo) { //suggest do intelij switch enhanced
            case 1 -> {
                this.tipoJogo = PlayerVsPlayer;
                this.j1 = new JogadorHumano(nome1); //id impar
                this.j2 = new JogadorHumano(nome2); //id par
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
        //imprimir o identificador da coluna
        for(int i = 0; i < nColunas; i++){
            if (i == 0 ){
                s.append("  | ").append(i).append(" | ");
                continue;
            }
            s.append(i).append(" | ");
        }
        s.append("\n");

        //imprime o tabuleiro
        for( int i = 0; i < nLinhas; i++){
            //s.append("\n" + i + " | ");
            s.append("\n").append(i).append(" | ");
            for( int j = 0; j < nColunas; j++){
                if (tabuleiro[i][j] == 0){
                    s.append("  | ");
                }else{
                    if(tabuleiro[i][j] % 2 == 0)//se par O senao x
                        s.append("O").append(" | ");
                    else
                        s.append("X").append(" | ");
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

    public boolean registaTabuleiro(int coluna, int idJogador) {
    //versao muito simples e incompleta

        for(int i = nLinhas - 1; i >= 0; --i){
            if(tabuleiro[i][coluna] == 0){
                tabuleiro[i][coluna] = idJogador;
                //addMsgLog("aposta com sucesso!\n");
                return true;
            }
        }
        return false;
    }

    public int joga(int coluna){

        addMsgLog("############## JOGADOR "+ nextPlayer.getNome()+" ###################");

        int value;
        //verifica o jogador a jogar NAO E VERSAL FINAL ALTERAR
        //System.out.println(nextPlayer.getNome() + " " + precisaInput() + " " + nextPlayer.toString());
        //registaTabuleiro(coluna);

        if(nextPlayer.equals(j1)){
            if(precisaInput())//verifica se jogador instanceof JogadorHumano
                j1.setPosAJogar(coluna);

            j1.joga(this);
            nextPlayer = j2;
        }else if(nextPlayer.equals(j2)){
            if(precisaInput())//verifica se jogador instanceof JogadorHumano
                j2.setPosAJogar(coluna);
            j2.joga(this);
            nextPlayer = j1;

        }
        addMsgLog(this.toString());//imprimir o tabuleiro

        value = verifica4EmLinha();
        value = verificaVencedor(value); // verifica se ha vencedores, e se houver
                                                    // devolve 1 se ha vencedor // 0 se nao
                                                    // regista no msglog e envia true pra terminar o jogo
        addMsgLog("VALUE:" + value + "\n");
        return value;
    }

    public boolean validaJogada(int coluna){

        if (coluna > 6 || coluna < 0) {
            addMsgLog("Coluna Invalida\n");
            return false;
        }

        for(int i = nLinhas-1; i >= 0; --i){
            if(tabuleiro[i][coluna] == 0){
                return true;
            }
        }

        //addMsgLog("$$$$$$$$$$$$$$$$$$$Coluna cheia!\n");
        ///addMsgLog(this.toString() + "\ncoluna " + coluna);
        return false;
    }

    public int verificaVencedor(int jogadorVencedor){

        if(isTabuleiroCheio() && jogadorVencedor == 0){
            addMsgLog("Tabuleiro cheio e sem vencedores");
            addMsgLog(this.toString());//imprimir o tabuleiro
            return 2; //fim de jogo tabuleiro cheio sem vencedores
        }

        if (jogadorVencedor == 0){
            //addMsgLog("Sem vencedores ainda\n");
            return 0;
        }

        if (jogadorVencedor % 2 == 0){ //se for par e o jogador 2
            addMsgLog(j2.getNome() + " vencedor\n");
            //addMsgLog(this.toString());//imprimir o tabuleiro
            return 1;
        }else { // se nao for par, logo impar e o jogador 1
            addMsgLog(j1.getNome() + " vencedor\n");
            //addMsgLog(this.toString());//imprimir o tabuleiro
            return 1;
        }
    }

    public int verifica4EmLinha(){
        for(int l = 0; l < nLinhas; l++){//linhas
            for(int c = 0; c < nColunas; c++) {//colunas
                int jogadorVencedor = tabuleiro[l][c]; // guarda o valor da posicao onde esta

                if (jogadorVencedor == 0) {
                    continue; // evitar verificar linhas onde ainda ninguem jogou
                }
                if (c + 3 < nColunas && // verificar vencedor numa coluna
                        jogadorVencedor == tabuleiro[l][c+1] &&
                        jogadorVencedor == tabuleiro[l][c+2] &&
                        jogadorVencedor == tabuleiro[l][c+3]){
                    addMsgLog(" l:"+ l + " c:"+c +
                            " l:"+ (l) + " c:" +(c+1)+
                            " l:"+ (l) + " c:" +(c+2)+
                            " l:"+ (l) + " c:"  +(c+3));
                    return jogadorVencedor;
                }
                if (l + 3 < nLinhas){

                    if (jogadorVencedor == tabuleiro[l+1][c] && //verificar vencedor numa linha
                            jogadorVencedor == tabuleiro[l+2][c] &&
                            jogadorVencedor == tabuleiro[l+3][c]) {
                        addMsgLog(" l:"+ l + " c:"+c +
                                " l:"+ (l+1) + " c:" +(c)+
                                " l:"+ (l+2) + " c:" +(c)+
                                " l:"+ (l+3) + " c:"  +(c));
                        return jogadorVencedor;
                    }
                    if (c + 3 < nColunas && // verificar vencedor numa diagonal ex: x sentido cima direita
                            jogadorVencedor == tabuleiro[l+1][c+1] &&//            x
                            jogadorVencedor == tabuleiro[l+1][c+2] &&//           x
                            jogadorVencedor == tabuleiro[l+1][c+3]) {//            x
                        addMsgLog(" l:"+ l + " c:"+c +
                                " l:"+ (l+1) + " c:" +(c+1)+
                                " l:"+ (l+2) + " c:" +(c+2)+
                                " l:"+ (l+3) + " c:"  +(c+3));
                        return jogadorVencedor;
                    }
                    if (c - 3 >= 0 && // verificar vencedor numa diagonal ex: x   sentido cima esquerda
                            jogadorVencedor == tabuleiro[l+1][c-1] &&//        x
                            jogadorVencedor == tabuleiro[l+2][c-2] &&//         x
                            jogadorVencedor == tabuleiro[l+3][c-3]) {//            x
                        addMsgLog(" l:"+ l + " c:"+c +
                                " l:"+ (l+1) + " c:" +(c-1)+
                                " l:"+ (l+2) + " c:" +(c-2)+
                                " l:"+ (l+3) + " c:"  +(c-3));
                        return jogadorVencedor;
                    }
                }
            }
        }
        return 0;//sem vencedor
    }

    public boolean precisaInput(){
        return nextPlayer instanceof JogadorHumano;
    }

    public boolean isTabuleiroCheio(){
        for(int i=0; i < nLinhas; i++) {
            for (int j = 0; j < nColunas; j++) {
                if (tabuleiro[i][j] == 0)
                    return false;
            }
        }
        return true;
    }
}
