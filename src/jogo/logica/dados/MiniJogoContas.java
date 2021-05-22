package jogo.logica.dados;

public class MiniJogoContas extends MiniJogo {
    int resultado, nCertas;
    long initTime;
    float elapsedTimeSec;


    public MiniJogoContas(JogoDados jogoDados){
        nCertas = 0;
        elapsedTimeSec = 0;
        jogaMinijogo(jogoDados);
        initTime = System.currentTimeMillis();
    }

    public void jogaMinijogo(JogoDados jogoDados){
        int x = (int)(Math.random() * 4);
        int random1 = (int)(Math.random() * 9);
        int random2 = (int)(Math.random() * 9);

        switch(x){
            case 0: // +
                jogoDados.addMsgLog(random1 + "+" + random2);
                resultado = random1+random2;
                break;
            case 1: // -
                jogoDados.addMsgLog(random1 + "-" + random2);
                resultado = random1-random2;
                break;
            case 2: // /
                jogoDados.addMsgLog(random1 + "/" + random2);
                resultado = random1/random2;
                break;
            case 3: // x
                jogoDados.addMsgLog(random1 + "*" + random2);
                resultado = random1 * random2;
                break;
        }
    }

    public void verificaResultado(int n, JogoDados jogoDados){
        if( n == resultado) {
            jogoDados.addMsgLog("Resposta Correta!("+resultado+")");
            nCertas++;
        }else{
        jogoDados.addMsgLog("Resposta Errada!("+resultado+")");
        }
    }

    public boolean miniGameDone(JogoDados jogoDados){
        if(nCertas == 5){
            elapsedTimeSec =  (System.currentTimeMillis() - initTime) / 1000F;
            jogoDados.addMsgLog("Tempo decorrido:" + elapsedTimeSec+" segundos!");
            return true;
        }

        return false;
    }

    public boolean ganhou(){
        if(elapsedTimeSec != 0){
            if(elapsedTimeSec <= 30){
                return true;
            }
        }
        return false;
    }

    public boolean verificaResultado(String n, JogoDados jogoDados){
        return false;
    }

    //public void joga
}
