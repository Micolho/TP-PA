package jogo.logica.dados;

import java.io.Serializable;

public abstract class MiniJogo implements Serializable {
    public abstract void jogaMinijogo(JogoDados jogoDados);
    public abstract void verificaResultado(int n, JogoDados jogoDados);
    public abstract boolean verificaResultado(String n, JogoDados jogoDados);
    public abstract boolean miniGameDone(JogoDados jogoDados);
    public abstract boolean ganhou();
}
