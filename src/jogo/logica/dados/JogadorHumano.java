package jogo.logica.dados;

public class JogadorHumano extends Jogador{

    private String nome;
    private int nJogadas;
    private boolean pecaEspecial;
    private int posAJogar;
    private int id;
    private int creditos;
    private boolean recusaMiniGame, jaJogouMiniJogo;



    public JogadorHumano(String nome) {
        setNome(nome);
        this.nJogadas = 1;
        pecaEspecial = false;
        id = count++;
        creditos = 5;
        recusaMiniGame = false;
        jaJogouMiniJogo = false;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
    public String getNome(){
        return this.nome;
    }

    public void addJogada(){
        nJogadas++;
    }

    public  int getJogadas(){
        return this.nJogadas;
    }

    public boolean getPecaEspecial(){
        return pecaEspecial;
    }
    public void setPecaEspecial(boolean peca){
        pecaEspecial = peca;
    }

    public void joga(JogoDados jogoDados){
        jogoDados.registaTabuleiro(posAJogar, id);
        addJogada();
        setRecusaMiniGame(false);
    }

    public int getPosAJogar() {
        return posAJogar;
    }

    public int getCreditos(){
        return creditos;
    }
    public boolean deductCredit(int n){
        if (creditos - n >= 0) {
            creditos = creditos - n;
            return true;
        }
        return false;
    }

    public void setPosAJogar(int posAJogar) {
        this.posAJogar = posAJogar;
    }

    @Override
    public String toString() {
        return "\nNome do jogador: " + nome + " id: " + id + "\n";
    }

    public boolean recusaMiniGame() {
        return recusaMiniGame;
    }

    public void setRecusaMiniGame(boolean recusaMiniGame) {
        this.recusaMiniGame = recusaMiniGame;
    }

    public void resetJogadas(){
        this.nJogadas = 1;
    }

    public boolean jaJogouMiniJogo(){
        return jaJogouMiniJogo;
    }
    public void setJaJogouMiniJogo(boolean jaJogouMiniJogo){
        this.jaJogouMiniJogo = jaJogouMiniJogo;
    }
}
