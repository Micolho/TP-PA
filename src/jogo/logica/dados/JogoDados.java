package jogo.logica.dados;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JogoDados implements Serializable {
    private static final int nLinhas = 6, nColunas = 7;
    private Jogador nextPlayer;
    private Jogador j1, j2;
    private int[][] tabuleiro = new int[nLinhas][nColunas];
    private List<String> msgLog = new ArrayList<>();
    private MiniJogo miniJogo;
    static final String SEPARADORES_FICH_TEXTO = "[;,. ]+";
    static String nomeFich = "ficheiros/palavras.txt";
    static List<String> palavras = new ArrayList<>();
    private List<String> DecorrerMiniJogo = new ArrayList<>();


    public JogoDados() throws Exception{
        setTabuleiroZeros();
        miniJogo = null;
        try {
            if (!preencheArrayPalavras()) {
                throw new Exception("ficheiro nao tem 100 palavras com + de 5 letras cada");
            }
        }catch (IOException e){
            throw new Exception("erro ao ler do ficheiro!");
        }
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

    public Jogador getPlayer(){
        return nextPlayer;
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
        //addMsgLog("############## JOGADOR "+ nextPlayer.getNome()+" ###################");
        //addMsgLog(this.toString());//imprimir o tabuleiro
    }

    public void setTipoJogo(int tipo, String nome1, String nome2) {
        // 1 - player vs player
        // 2 - player vs computer
        // 3 - computer vs computer
        switch (tipo) { //suggest do intelij switch enhanced
            case 1 -> {
                this.j1 = new JogadorHumano(nome1); //id impar
                this.j2 = new JogadorHumano(nome2); //id par
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

        //s.append("\n Vez do jogador ").append(nextPlayer.getNome()).append("!");
        return s.toString();
    }

    public String printNextPlayer(){
        StringBuilder s = new StringBuilder();
        s.append("\n Vez do jogador ").append(nextPlayer.getNome()).append("!");
        return s.toString();
    }

    public boolean apos4jogadas() {
        return  nextPlayer.getJogadas() != 0 &&
                nextPlayer.getJogadas() % 4 == 0 &&
                isHumano() &&
                !getRecusouMiniGame() &&
                !nextPlayer.jaJogouMiniJogo(); //para o estado DecideMiniJogo
    }

    public void registaTabuleiro(int coluna, int idJogador) {
    //versao muito simples e incompleta

        for(int i = nLinhas - 1; i >= 0; --i){
            if(tabuleiro[i][coluna] == 0){
                tabuleiro[i][coluna] = idJogador;
                //addMsgLog("aposta com sucesso!\n");
                break;
            }
        }
        //return false;
    }

    public boolean joga(int coluna){

        if(nextPlayer.getNome().equals(j1.getNome())){
            if(isHumano()) //verifica se jogador instanceof JogadorHumano
                j1.setPosAJogar(coluna);

            j1.joga(this);
            nextPlayer = j2;
        }else if(nextPlayer.getNome().equals(j2.getNome())){
            if(isHumano())//verifica se jogador instanceof JogadorHumano
                j2.setPosAJogar(coluna);
            j2.joga(this);
            nextPlayer = j1;

        }
        //addMsgLog(this.toString());//imprimir o tabuleiro

        return verificaVencedor(verifica4EmLinha());
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

        if(isHumano())
            addMsgLog("Coluna cheia!\n");
        return false;
    }

    public boolean verificaVencedor(int jogadorVencedor){

        if(isTabuleiroCheio() && jogadorVencedor == 0){
            addMsgLog("Tabuleiro cheio e sem vencedores");
            //addMsgLog(this.toString());//imprimir o tabuleiro
            return true; //fim de jogo tabuleiro cheio sem vencedores
        }

        if (jogadorVencedor == 0){
            //addMsgLog("Sem vencedores ainda\n");
            //addMsgLog("############## JOGADOR "+ nextPlayer.getNome()+" ###################");
            return false;
        }

        if (jogadorVencedor % 2 == 0){ //se for par e o jogador 2
            addMsgLog(j2.getNome() + " vencedor\n");
            //addMsgLog(this.toString());//imprimir o tabuleiro
        }else { // se nao for par, logo impar e o jogador 1
            addMsgLog(j1.getNome() + " vencedor\n");
            //addMsgLog(this.toString());//imprimir o tabuleiro
        }
        return true;
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
//                    addMsgLog(" l:"+ l + " c:"+c +
//                            " l:"+ (l) + " c:" +(c+1)+
//                            " l:"+ (l) + " c:" +(c+2)+
//                            " l:"+ (l) + " c:"  +(c+3));
                    return jogadorVencedor;
                }
                if (l + 3 < nLinhas){

                    if (jogadorVencedor == tabuleiro[l+1][c] && //verificar vencedor numa linha
                            jogadorVencedor == tabuleiro[l+2][c] &&
                            jogadorVencedor == tabuleiro[l+3][c]) {
//                        addMsgLog(" l:"+ l + " c:"+c +
//                                " l:"+ (l+1) + " c:" +(c)+
//                                " l:"+ (l+2) + " c:" +(c)+
//                                " l:"+ (l+3) + " c:"  +(c));
                        return jogadorVencedor;
                    }
                    if (c + 3 < nColunas && // verificar vencedor numa diagonal ex: x -> aspecto visual na consola
                            jogadorVencedor == tabuleiro[l+1][c+1] &&//              x
                            jogadorVencedor == tabuleiro[l+2][c+2] &&//               x
                            jogadorVencedor == tabuleiro[l+3][c+3]) {//                x
//                        addMsgLog(" l:"+ l + " c:"+c +
//                                " l:"+ (l+1) + " c:" +(c+1)+
//                                " l:"+ (l+2) + " c:" +(c+2)+
//                                " l:"+ (l+3) + " c:"  +(c+3));
                        return jogadorVencedor;
                    }
                    if (c - 3 >= 0 && // verificar vencedor numa diagonal ex: x -> aspecto visual na consola
                            jogadorVencedor == tabuleiro[l+1][c-1] &&//      x
                            jogadorVencedor == tabuleiro[l+2][c-2] &&//     x
                            jogadorVencedor == tabuleiro[l+3][c-3]) {//    x
//                        addMsgLog(" l:"+ l + " c:"+c +
//                                " l:"+ (l+1) + " c:" +(c-1)+
//                                " l:"+ (l+2) + " c:" +(c-2)+
//                                " l:"+ (l+3) + " c:"  +(c-3));
                        return jogadorVencedor;
                    }
                }
            }
        }
        return 0;//sem vencedor
    }

    public boolean isHumano(){
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

    public void recusaMiniGame() {
        nextPlayer.setRecusaMiniGame(true);
    }

    public boolean getRecusouMiniGame() {
        return nextPlayer.recusaMiniGame();
    }

    public boolean isContas(){
        return miniJogo instanceof MiniJogoContas;
    }

    public boolean isMiniGameDone(){
        return miniJogo.miniGameDone(this);
    }

    public void guardaPecaEspecial(){
        addMsgLog("Peca especial guardada! Pode jogar a jogada normal:");
    }

    public void jogaPecaEspecial(int x){
        for(int i = 0; i < nLinhas; i++){
          if(tabuleiro[i][x] !=0 ){
              tabuleiro[i][x] = 0; // apaga as jogadas na coluna indicada
          }
        }
        nextPlayer.setPecaEspecial(false); // remove a peca especial (dada como usada)
    }

    public boolean colunaValida(int coluna){
        if (coluna > 6 || coluna < 0) {
            addMsgLog("Coluna Invalida\n");
            return false;
        }
        return true;
    }

    public void inicializaMiniJogo() {

        if(miniJogo == null) {
            miniJogo = new MiniJogoContas(this);
            return;
        }

        if(isContas())
            miniJogo = new MiniJogoPalavras(palavras.get(randomPosPalavras()),
                                            palavras.get(randomPosPalavras()),
                                            palavras.get(randomPosPalavras()),
                                            palavras.get(randomPosPalavras()),
                                            palavras.get(randomPosPalavras()),
                                            this);
        else
            miniJogo = new MiniJogoContas(this);
    }

    public int randomPosPalavras(){
        return (int)(Math.random() * palavras.size()-1);
    }

    public int joga_contas(int contaRes){

        miniJogo.verificaResultado(contaRes,this);

        if(miniJogo.miniGameDone(this)){
            if(miniJogo.ganhou()) {
                addMsgLog("Ganhou o minijogo!");
                nextPlayer.setPecaEspecial(true);
                nextPlayer.setJaJogouMiniJogo(true);
                return 1; // return vitoria
            }
            addMsgLog("Perdeu o minijogo!");
            if (nextPlayer.getNome().equals(j1.getNome())){
                j1.addJogada();
                nextPlayer = j2;
                return 2; // return derrota
            }else{// if (nextPlayer.getNome() == j2.getNome()){
                j2.addJogada();
                nextPlayer = j1;
                return 2; // return derrota
            }
        }

        miniJogo.jogaMinijogo(this);
        return 0;
    }
    public boolean joga_palavras(String palavra){
        if(miniJogo.verificaResultado(palavra, this)) {
            nextPlayer.setPecaEspecial(true);
            nextPlayer.setJaJogouMiniJogo(true);
            return true;
        }

        if (nextPlayer.getNome().equals(j1.getNome())){
            j1.addJogada();
            nextPlayer = j2;
        }else{
            j2.addJogada();
            nextPlayer = j1;
        }
        return false;
    }

    public boolean jogadorTemPecaEspecial() {
        if ( nextPlayer.getPecaEspecial()){
            return true;
        }
        addMsgLog("Nao possui uma peca especial!");
        return false;
    }

    public static boolean preencheArrayPalavras()
            throws IOException
    {
        BufferedReader in = null;
        String linha, tmp;

        try{

            in = new BufferedReader(new FileReader(nomeFich));

            while((linha = in.readLine()) != null){ //Lê uma linha de texto.
                // Retorna uma String contendo a linha lida
                // sem o caracter de terminação da linha ou null
                // no caso de ter atingido o fim de ficheiro.

                Scanner scLinha = new Scanner(linha);
                scLinha.useDelimiter(SEPARADORES_FICH_TEXTO);
                if (!scLinha.hasNext()) {
                    continue;
                }

                while (scLinha.hasNext()){
                    tmp = scLinha.next().trim();
                    if(tmp.length()>= 5)
                        palavras.add(tmp);
                }

            }
        }finally{ // sempre executado, em caso de sucesso ou insucesso
            if(in != null) in.close();
        }

        return palavras.size() >= 100;
    }

    public void resetUndo(Jogador jogador){
        if(jogador.getNome().equals(j1.getNome())){
            j1.resetJogadas();
            j1.setPecaEspecial(false);
        }else{
            j2.resetJogadas();
            j2.setPecaEspecial(false);
        }
    }

    public boolean deduzCreditos(int n){
        if(nextPlayer.getNome().equals(j1.getNome())){
            return j1.deductCredit(n);
        }else{
            return j2.deductCredit(n);
        }
    }

    public int getCreditos(){
        return nextPlayer.getCreditos();
    }

}
