package jogo.logica.dados;

public class MiniJogoPalavras extends MiniJogo {
        long initTime;
        float elapsedTime;
        String palavras;

        float elapsedTimeSec;

        public MiniJogoPalavras(String p1, String p2, String p3, String p4, String p5, JogoDados jogoDados){
            elapsedTimeSec = 0;
            palavras = p1 + " " + p2 + " " + p3 + " " + p4 + " " + p5;
            jogoDados.setErros(true);
            jogoDados.addMsgLog("Digite as seguintes palavras corretamente(inclui os espacos)!");
            jogaMinijogo(jogoDados);
            initTime = System.currentTimeMillis();
        }

    public boolean miniGameDone(JogoDados jogoDados){
        return palavras.length() == 0;
    }

    public boolean verificaResultado(String palavra, JogoDados jogoDados){
        elapsedTime = (System.currentTimeMillis() - initTime) / 1000F;
        jogoDados.setErros(true);
        if(palavras.equals(palavra)){
            jogoDados.addMsgLog("Palavras digitadas corretamente");
            if(elapsedTime <= (float)(palavras.length()/2)){
                jogoDados.addMsgLog("Ganhou o minijogo. Demorou " +elapsedTime+ " segundos.");
                return true;
            }
            jogoDados.addMsgLog("Palavras digitadas corretamente, mas demorou " +
                            "demasiado, o tempo maximo era " + (palavras.length()/2)
                            + " demorou " + elapsedTime+ " segundos.");
            return false;
        }
        jogoDados.addMsgLog("Palavras digitadas incorretamente!");
        return false;
    }

    public void verificaResultado(int n, JogoDados jogoDados){}

    public void jogaMinijogo(JogoDados jogoDados){
         jogoDados.addMsgLog(palavras);
    }

    public boolean ganhou(){
            return false;
    }


}
