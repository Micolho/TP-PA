package jogo.logica.dados;

public abstract class MiniJogoDados {

    public MiniJogoDados(){
        //to do
    }

    public void joga() {
        // Get current time
        long start = System.currentTimeMillis();

        // Do something ...

        // Get elapsed time in milliseconds
        long elapsedTimeMillis = System.currentTimeMillis() - start;

        // Get elapsed time in seconds
        float elapsedTimeSec = elapsedTimeMillis / 1000F;
    }
}
