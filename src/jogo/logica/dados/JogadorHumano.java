package jogo.logica.dados;

public class JogadorHumano extends Jogador{
    private String nome;
    private int nJogadas;
    private boolean pecaEspecial; // se tem ou nao guardada
    private String lastMiniGame;

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

    @Override
    public String toString(){
        return "\nNome do jogador: " + nome + "\n";
    }

}
