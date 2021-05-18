package jogo.iu.texto;

import jogo.logica.JogoMaqEstados;
import jogo.logica.Situacao;

import java.util.Scanner;

public class IUTexto {
    private JogoMaqEstados jogoMaqEstados;
    private Scanner s = new Scanner(System.in);
    private boolean sair = false;
    private boolean nextState = false;

    public IUTexto(JogoMaqEstados game) {
        jogoMaqEstados = game;
    }

    private void iuEscolheJogo()
    {
        int value;
        String name1 = "", name2 = "";

        System.out.println("1 - Jogador vs Jogador");
        System.out.println("2 - Jogador vs Computador");
        System.out.println("3 - Computador vs Computador");
        System.out.print("> ");

        while(!s.hasNextInt()) s.next();

        value=s.nextInt();

        switch(value){
            case 1,2,3 -> {
                while(!nextState){
                    System.out.println("Introduza o nome do jogador 1:");
                    System.out.print("> ");

                    while(!s.hasNext()) s.next();
                    name1 = s.next();

                    System.out.println("Introduza o nome do jogador 2:");
                    System.out.print("> ");

                    while(!s.hasNext()) s.next();
                    name2 = s.next();
                    try {
                        jogoMaqEstados.iniciar_jogo(value, name1, name2);
                        nextState = true;
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }
            }
            default -> {
                System.out.println("Escolha uma opcao valida!");
            }

        }

    }

    private void iuPrimeiroAJogar()
    {
        System.out.println("\nVai ser executada a escolhar do primeiro jogador: \n");
        jogoMaqEstados.random_jogador();
    }

    public void corre(){

        while(!sair){

            if(jogoMaqEstados.getMsgLog().size()>0){

                System.out.println();

                for(String msg:jogoMaqEstados.getMsgLog()){
                    System.out.println("--> " + msg);
                }

                jogoMaqEstados.clearMsgLog();

            }
            Situacao situacao = jogoMaqEstados.getSituacaoAtual();

            switch (situacao) {
                case ESCOLHE_JOGO:
                    iuEscolheJogo();
                    break;
                case  PRIMEIRO_A_JOGAR:
                    iuPrimeiroAJogar();
                    break;
                case  AGUARDA_JOGADA:
                    //iuAguardaOpcao();
                    break;
                case  QUER_MINIJOGO:
                    //iuFinalJogo();
                    break;
                case  MINIJOGO:
                    //iuFinalJogo();
                    break;
                case  DECISAO_PECA_ESPECIAL:
                    //iuFinalJogo();
                    break;
                case  FIM_JOGO:
                    //iuFinalJogo();
                    break;

                default:
                    System.out.println("Fora de controlo");
                    break;
            }
        }

        System.out.println();
        System.out.println("************** Final Jogo *****************");
        System.out.println(jogoMaqEstados);

    }
}
