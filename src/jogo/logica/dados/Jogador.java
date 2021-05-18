package jogo.logica.dados;

public abstract class Jogador {

    public abstract void setNome(String nome);
    public abstract String getNome();
    public abstract void addJogada();
    public abstract int getJogadas();
    public abstract boolean getPecaEspecial();
    public abstract void setPecaEspecial(boolean peca);
    public abstract String getLastMiniGame();
    public abstract void setLastMiniGame(String minigame);
    @Override
    public abstract String toString();
}
