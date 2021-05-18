package jogo;

import jogo.iu.texto.IUTexto;
import jogo.logica.JogoMaqEstados;
import jogo.logica.dados.JogoDados;

public class Main {
    public static void main(String [] args){
        JogoMaqEstados jogoMaqEstados = new JogoMaqEstados();
        IUTexto iu = new IUTexto(jogoMaqEstados);
        iu.corre();
    }
}
