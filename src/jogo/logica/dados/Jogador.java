package jogo.logica.dados;

public abstract class Jogador {
    protected static int count = 1;

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
    public abstract boolean joga(JogoDados jogoDados);
    public abstract void setPosAJogar(int posAJogar);
}
