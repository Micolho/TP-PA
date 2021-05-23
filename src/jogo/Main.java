package jogo;

import jogo.iu.texto.IUTexto;
import jogo.logica.JogoGestao;
import jogo.logica.JogoMaqEstados;
import jogo.logica.dados.JogoDados;
import jogo.logica.dados.MiniJogoPalavras;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;

public class Main {
    public static void main(String [] args){
        try {
            JogoGestao jogoGestao = new JogoGestao();
            IUTexto iu = new IUTexto(jogoGestao);
            iu.corre();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
