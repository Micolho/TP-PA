package jogo.logica.dados;

import java.io.Serializable;

public abstract class Jogador implements Serializable {
    protected static int count = 1;

    public abstract void setNome(String nome);
    public abstract String getNome();
    public abstract void addJogada();
    public abstract int getJogadas();
    public abstract boolean getPecaEspecial();
    public abstract void setPecaEspecial(boolean peca);
    @Override
    public abstract String toString();
    public abstract void joga(JogoDados jogoDados);
    public abstract void setPosAJogar(int posAJogar);
    public abstract int getCreditos();
    public abstract boolean deductCredit(int n);
    public abstract void setRecusaMiniGame(boolean recusaMiniGame);
    public abstract boolean recusaMiniGame();
    public abstract void setJaJogouMiniJogo(boolean jaJogouMiniJogo);
    public abstract boolean jaJogouMiniJogo();

    public abstract void resetJogadas();
}
