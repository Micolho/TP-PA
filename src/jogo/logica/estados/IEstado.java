package jogo.logica.estados;


import jogo.logica.Situacao;

public interface IEstado {

    IEstado opcoes_jogo();
    IEstado iniciar_jogo(int tipo, String nome1, String nome2);
    IEstado terminar();
    IEstado jogar_peca(int coluna);
    IEstado random_jogador();
    IEstado apos4jogadas();
    IEstado aguarda_opcao();
    IEstado aceita_minijogo();
    IEstado recusa_minijogo();
    IEstado ganha_minijogo();
    IEstado perde_minijogo();
    IEstado guarda_peca_especial();
    IEstado joga_peca_especial();

    Situacao getSituacaoAtual();


}
