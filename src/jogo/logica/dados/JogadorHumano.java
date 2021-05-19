package jogo.logica.dados;

public class JogadorHumano extends Jogador{
    private String nome;
    private int nJogadas;
    private boolean pecaEspecial; // se tem ou nao guardada
    private String lastMiniGame;
    private int posAJogar;

    public JogadorHumano(String nome){
        setNome(nome);
        this.nJogadas = 0;
        pecaEspecial = false;
        lastMiniGame = "";
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

    public String getLastMiniGame(){
        return lastMiniGame;
    }
    public void setLastMiniGame(String minigame){
        lastMiniGame = minigame;
    }

    public boolean joga(JogoDados jogoDados){
        jogoDados.registaTabuleiro(posAJogar);
        addJogada();
        return false;
    }

    public int getPosAJogar() {
        return posAJogar;
    }

    public void setPosAJogar(int posAJogar) {
        this.posAJogar = posAJogar;
    }

    @Override
    public String toString(){
        return "\nNome do jogador: " + nome + "\n";
    }

}
