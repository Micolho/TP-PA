package jogo;

import jogo.iu.texto.IUTexto;
import jogo.logica.JogoMaqEstados;
import jogo.logica.dados.JogoDados;
import jogo.logica.dados.MiniJogoPalavras;

public class Main {
    public static void main(String [] args){
        try {
            JogoMaqEstados jogoMaqEstados = new JogoMaqEstados();
        IUTexto iu = new IUTexto(jogoMaqEstados);
        iu.corre();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
