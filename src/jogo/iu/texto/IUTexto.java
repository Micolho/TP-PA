package jogo.iu.texto;

import jogo.logica.JogoGestao;
import jogo.logica.Situacao;

import java.util.Scanner;

public class IUTexto {
    private JogoGestao jogoGestao;
    private Scanner s = new Scanner(System.in);
    private boolean sair = false;

    public IUTexto(JogoGestao game) {
        jogoGestao = game;
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
        System.out.println("  Cada jogador Humano, tem 5 creditos que pode usar para desfazer jogadas(ate 5)");
        System.out.println();
        System.out.println("  Mini Jogos:");
        System.out.println();
        System.out.println("  Contas:");
        System.out.println("  Introduza apenas o valor das operacoes aritmeticas. Em caso de divisao, arredondar para o inteiro abaixo.");
        System.out.println();
        System.out.println("  Palavras:");
        System.out.println("  Escreva a sequencia de palavras seguidas de acordo como aparecem no ecra, com os mesmos espacos em branco!");
        System.out.println();
        System.out.println("  Peca especial:");
        System.out.println("  Ao usar, pode apagar uma coluna de jogadas.");
        System.out.println("Clique no Enter para continuar!");
        System.out.print("> ");
        s.nextLine();


        jogoGestao.opcoesjogo();
    }

    private void iuEscolheJogo()
    {
        int value;
        String name1, name2, filename;

        System.out.println("1 - Jogador vs Jogador");
        System.out.println("2 - Jogador vs Computador");
        System.out.println("3 - Computador vs Computador");
        System.out.println("4 - Historico de jogos(Replay)");
        System.out.println("5 - Carregar jogo");
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

                jogoGestao.iniciar_jogo(value, name1, name2);

            }
            case 4 ->{
                System.out.println("Ficheiros Disponiveis no Historico\n");
                System.out.println(jogoGestao.fileInDirectory());
                System.out.println("\nQual o nome do replay que deseja ver:");
                System.out.print("> ");

                while(!s.hasNext()) s.next();

                filename=s.next();

                jogoGestao.leHist(filename);
            }
            case 5 -> {
                System.out.println("Insira o nome do ficheiro a carregar!\n");
                System.out.print("> ");

                while(!s.hasNext()) s.next();

                //filename=s.next();
                //jogoGestao.loadFromFile(filename);
            }
            default -> System.out.println("Escolha uma opcao valida!");
        }
    }

    private void iuPrimeiroAJogar()
    {
        System.out.println("\nVai ser executada a escolha do primeiro jogador: \n");
        //jogoGestao.random_jogador();
    }

    private void iuAguardaJogadaHumana() {
        int value, coluna, coluna2, undoNumber;
        String filename;

        System.out.println();
        System.out.println(jogoGestao);
        System.out.println();
        System.out.println("1-Jogar peca normal");
        System.out.println("2-Jogar peca especial(pode jogar depois a peca normal)");
        System.out.println("3-Retroceder 1 ou varias jogadas(consome 1 credito por jogada retrocedida)");
        System.out.println("4-Sair");
        System.out.print("> ");

        while (!s.hasNextInt()) s.next();

        value = s.nextInt();

        switch (value) {
            case 1 -> {
                System.out.println("Introduza a coluna onde quer jogar:");
                System.out.print("> ");

                while (!s.hasNextInt()) s.next();
                coluna = s.nextInt();

                jogoGestao.jogar_peca(coluna);
            }
            case 2 -> {
                System.out.println(jogoGestao);
                System.out.println("Coluna em que deseja jogar?");
                System.out.print("> ");

                while (!s.hasNextInt()) s.next();
                coluna2 = s.nextInt();

                jogoGestao.joga_peca_especial(coluna2);
            }
            case 3 -> {
                System.out.println(jogoGestao);
                System.out.println("Quantas jogadas deseja retroceder?");
                System.out.print("> ");

                while (!s.hasNextInt()) s.next();
                undoNumber = s.nextInt();

                jogoGestao.undo(undoNumber);
            }
            case 4 -> {
                System.out.println("1 - Gravar, para jogar mais tarde!");
                System.out.println("2 - Sair, sem gravar!");
                System.out.print("> ");

                while (!s.hasNextInt()) s.next();
                value = s.nextInt();
                switch (value){
                    case 1 -> {
                        System.out.println("Indique o nome do ficheiro a gravar!");
                        System.out.print("> ");

                        while(!s.hasNext()) s.next();

                       // filename=s.next();
                        //jogoGestao.saveToFile(filename);
                        jogoGestao.terminar();
                    }
                    case 2 -> {
                        jogoGestao.terminar();
                    }
                    default -> System.out.println("Escolha uma opcao valida!");
                }
            }
            default -> System.out.println("Escolha uma opcao valida!");
        }
    }
    private void iuAguardaJogadaVirtual()
    {
        System.out.println(jogoGestao);
        jogoGestao.jogar_peca(0); // 0 porque vai ser ignorado este valor
                                            // a coluna a jogar vai ser escolhida pelo computador
    }

    private void iuAguardaOpcao()
    {
        int value;

        System.out.println("\n\n---------------------------------");
        System.out.println(jogoGestao.printNextPlayer());
        System.out.println();
        System.out.println("Ronda do Minijogo");
        System.out.println();
        System.out.println("1-Aceitar");
        System.out.println("2-Recusar");
        System.out.print("> ");

        while(!s.hasNextInt()) s.next();

        value = s.nextInt();

        switch(value){
            case 1 -> jogoGestao.aceita_minijogo();
            case 2 -> jogoGestao.recusa_minijogo();
            default -> System.out.println("Escolha uma opcao valida!");
        }
    }

    private void iuMiniJogoContas()
    {
        int value;

        System.out.print("> ");

        while(!s.hasNextInt()) s.next();

        value = s.nextInt();

        jogoGestao.joga_minijogo_contas(value);

    }

    private void iuMiniJogoPalavras()
    {
        Scanner answer = new Scanner(System.in);
        String answerString = "";

        System.out.print("> ");

        answerString += answer.nextLine();

        jogoGestao.joga_minijogo_palavras(answerString);
        answer.close();
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
                System.out.println(jogoGestao);
                System.out.println("Coluna em que deseja jogar?");
                System.out.print("> ");

                while(!s.hasNextInt()) s.next();
                value=s.nextInt();

                jogoGestao.joga_peca_especial(value);
            }
            //case 2 -> jogoGestao.guarda_peca_especial();
            default -> System.out.println("Escolha uma opcao valida!");
        }
    }

    private void iuFinalJogo()
    {
        int value;

        jogoGestao.afterFinish();
        System.out.println(jogoGestao);
        System.out.println("1-Jogar de novo");
        System.out.println("2-Sair");
        System.out.print("> ");

        while(!s.hasNextInt()) s.next();

        value=s.nextInt();

        if(value==1){
            jogoGestao.opcoesjogo();
        }else if(value == 2){
            sair = true;
        }
    }

    public void corre(){

        while(!sair){

            if(jogoGestao.getMsgLog().size()>0){

                System.out.println();

                for(String msg:jogoGestao.getMsgLog()){
                    System.out.println("--> " + msg);
                }

                jogoGestao.clearMsgLog();

            }
            Situacao situacao = jogoGestao.getSituacaoAtual();

            switch (situacao) {
                case MENU_INFORMATIVO -> iuMenuInformativo();
                case ESCOLHE_JOGO -> iuEscolheJogo();
                case PRIMEIRO_A_JOGAR -> iuPrimeiroAJogar();
                case AGUARDA_JOGADA_HUMANA -> iuAguardaJogadaHumana();
                case AGUARDA_JOGADA_VIRTUAL -> iuAguardaJogadaVirtual();
                case QUER_MINIJOGO -> iuAguardaOpcao();
                case MINIJOGO_CONTAS -> iuMiniJogoContas();
                case MINIJOGO_PALAVRAS -> iuMiniJogoPalavras();
                case DECISAO_PECA_ESPECIAL -> iuDecidePecaEspecial();
                case FIM_JOGO -> iuFinalJogo();
                default -> System.out.println("Fora de controlo");
            }
        }

        System.out.println();
        System.out.println("************** Fim do Jogo *****************");
        //System.out.println(jogoMaqEstados);
    }


}
