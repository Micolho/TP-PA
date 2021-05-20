package jogo.logica.dados;

public class MiniJogoPalavras extends MiniJogo {
        int nCertas;
        long initTime;

        public MiniJogoPalavras(JogoDados jogoDados){
            nCertas = 0;
            initTime = System.currentTimeMillis();

        }

    public boolean miniGameDone(JogoDados jogoDados){
        if(nCertas == 5)
            return true;

        return false;
    }

    public void verificaResultado(String palavra){
        //if( n == resultado) {
            nCertas++;
        //}
    }
    public void verificaResultado(int n, JogoDados jogoDados){}
    public void randomOperacao(JogoDados jogoDados){}

    public boolean ganhou(){
            return true;
    }
}
