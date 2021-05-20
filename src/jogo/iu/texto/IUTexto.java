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

    private void iuMenuInformativo() {
        System.out.println();
        System.out.println("  Jogo do 4 em linha");
        System.out.println();
        System.out.println("  Objetivo:");
        System.out.println("  Colocar 4 peças em linha na horizontal, vertical ou diagonal.");
        System.out.println();
        System.out.println("  Regras:");
        System.out.println("  Apenas se pode jogar 1 peça por jogada.");
        System.out.println("  O primeiro jogador a começar é selecionado de forma aleatória.");
        System.out.println("  A cada 4 jogadas, o jogador tem a opcao de aceitar participar num mini jogo" +
                "para obter uma peça especial caso ganhe.");
        System.out.println();
        System.out.println("  Mini Jogos:");
        System.out.println();
        System.out.println("  Contas:");
        System.out.println("  PorFazer");
        System.out.println();
        System.out.println("  Palavras:");
        System.out.println("  PorFazer");
        System.out.println();
        System.out.println("Clique no Enter para continuar!");
        System.out.print("> ");
        s.nextLine();


        jogoMaqEstados.opcoesjogo();
    }

    private void iuEscolheJogo()
    {
        int value;
        String name1, name2;

        System.out.println("1 - Jogador vs Jogador");
        System.out.println("2 - Jogador vs Computador");
        System.out.println("3 - Computador vs Computador");
        System.out.print("> ");

        while(!s.hasNextInt()) s.next();

        value=s.nextInt();

        switch(value){
            case 1,2,3 -> {
                System.out.println("Introduza o nome do jogador 1:");
                System.out.print("> ");

                while(!s.hasNext()) s.next();
                name1 = s.next();

                System.out.println("Introduza o nome do jogador 2:");
                System.out.print("> ");

                while(!s.hasNext()) s.next();
                name2 = s.next();

                jogoMaqEstados.iniciar_jogo(value, name1, name2);
                nextState = true;

            }
            default -> System.out.println("Escolha uma opcao valida!");
        }
    }

    private void iuPrimeiroAJogar()
    {
        System.out.println("\nVai ser executada a escolha do primeiro jogador: \n");
        jogoMaqEstados.random_jogador();
    }

    private void iuAguardaJogadaHumana()
    {
        int value;


        //System.out.println(jogoMaqEstados.toString());

        System.out.println("Introduza a coluna onde quer  ou -1 se deseja sair do jogo:");
        System.out.print("> ");

        while(!s.hasNextInt()) s.next();
        value=s.nextInt();

        if (value == -1)
            jogoMaqEstados.terminar();
        else
            jogoMaqEstados.jogar_peca(value);
    }

    private void iuAguardaJogadaVirtual()
    {

        jogoMaqEstados.jogar_peca(0); // 0 porque vai ser ignorado este valor
                                            // a coluna a jogar vai ser escolhida pelo computador
    }

    private void iuAguardaOpcao()
    {
        int value;

        System.out.println("\n\n---------------------------------");
        //System.out.println(jogoMaqEstados);

        System.out.println();
        System.out.println("Ronda do Minijogo");
        System.out.println();
        System.out.println("1-Aceitar");
        System.out.println("2-Recusar");
        System.out.print("> ");

        while(!s.hasNextInt()) s.next();

        value = s.nextInt();

        switch(value){
            case 1 -> jogoMaqEstados.aceita_minijogo();
            case 2 -> jogoMaqEstados.recusa_minijogo();
            default -> System.out.println("Escolha uma opcao valida!");
        }
    }

    private void iuMiniJogoContas()
    {
        int value;

        System.out.print("> ");

        while(!s.hasNextInt()) s.next();

        value = s.nextInt();

        jogoMaqEstados.joga_minijogo_contas(value);

    }

    private void iuMiniJogoPalavras()
    {
        String answer;

        System.out.print("> ");

        while(!s.hasNextInt()) s.next();

        while(!s.hasNext()) s.next();
        answer = s.next();

        jogoMaqEstados.joga_minijogo_palavras(answer);
    }

    private void iuDecidePecaEspecial()
    {
        int value;

        System.out.println("1-Jogar peca especial");
        System.out.println("2-Guardar peca especial");
        System.out.print("> ");

        while(!s.hasNextInt()) s.next();

        value=s.nextInt();

        switch(value){
            case 1 -> {
                System.out.println("Coluna em que deseja jogar?");
                System.out.print("> ");

                while(!s.hasNextInt()) s.next();
                value=s.nextInt();

                jogoMaqEstados.joga_peca_especial(value);
            }
            case 2 -> jogoMaqEstados.guarda_peca_especial();
            default -> System.out.println("Escolha uma opcao valida!");
        }
    }

    private void iuFinalJogo()
    {
        int value;

        System.out.println("1-Jogar de novo");
        System.out.println("2-Sair");
        System.out.print("> ");

        while(!s.hasNextInt()) s.next();

        value=s.nextInt();

        if(value==1){
            jogoMaqEstados.opcoesjogo();
        }else if(value == 2){
            sair = true;
        }
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
                case MENU_INFORMATIVO:
                    iuMenuInformativo();
                    break;
                case ESCOLHE_JOGO:
                    iuEscolheJogo();
                    break;
                case PRIMEIRO_A_JOGAR:
                    iuPrimeiroAJogar();
                    break;
                case AGUARDA_JOGADA_HUMANA:
                    iuAguardaJogadaHumana();
                    break;
                case AGUARDA_JOGADA_VIRTUAL:
                    iuAguardaJogadaVirtual();
                    break;
                case QUER_MINIJOGO:
                    iuAguardaOpcao();
                    break;
                case MINIJOGO_CONTAS:
                    iuMiniJogoContas();
                    break;
                case MINIJOGO_PALAVRAS:
                    iuMiniJogoPalavras();
                    break;
                case DECISAO_PECA_ESPECIAL:
                    iuDecidePecaEspecial();
                    break;

                case FIM_JOGO:
                    iuFinalJogo();
                    break;

                default:
                    System.out.println("Fora de controlo");
                    break;
            }
        }

        System.out.println();
        System.out.println("************** Fim do Jogo *****************");
        //System.out.println(jogoMaqEstados);
    }


}
