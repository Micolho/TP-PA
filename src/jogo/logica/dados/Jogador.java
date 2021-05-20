package jogo.logica.dados;

public abstract class Jogador {
    protected static int count = 1;

    public abstract void setNome(String nome);
    public abstract String getNome();
    public abstract void addJogada();
    public abstract int getJogadas();
    public abstract boolean getPecaEspecial();
    public abstract void setPecaEspecial(boolean peca);
/*    public abstract String getLastMiniGame();
    public abstract void setLastMiniGame(String minigame);*/
    @Override
    public abstract String toString();
    public abstract void joga(JogoDados jogoDados);
    public abstract void setPosAJogar(int posAJogar);
    public abstract int getCreditos();
    public abstract void deductCredit();
    public abstract void setRecusaMiniGame(boolean recusaMiniGame);
    public abstract boolean recusaMiniGame();
    public abstract void setJaJogouMiniJogo(boolean jaJogouMiniJogo);
    public abstract boolean jaJogouMiniJogo();
}
