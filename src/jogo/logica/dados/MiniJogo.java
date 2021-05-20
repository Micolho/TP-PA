package jogo.logica.dados;

public abstract class MiniJogo {

/*    public MiniJogoDados(){
        //to do
    }*/

/*    public void joga() {
        // Get current time
        long start = System.currentTimeMillis();

        // Do something ...

        // Get elapsed time in milliseconds
        long elapsedTimeMillis = System.currentTimeMillis() - start;

        // Get elapsed time in seconds
        float elapsedTimeSec = elapsedTimeMillis / 1000F;
    }*/
    public abstract void randomOperacao(JogoDados jogoDados);
    public abstract void verificaResultado(int n, JogoDados jogoDados);
    public abstract void verificaResultado(String n);
    public abstract boolean miniGameDone(JogoDados jogoDados);
    public abstract boolean ganhou();
}
