package jogo.logica.estados;


import jogo.logica.Situacao;

import java.io.Serializable;

public interface IEstado extends Serializable {

    IEstado opcoes_jogo();
    IEstado iniciar_jogo(int tipo, String nome1, String nome2);
    IEstado terminar();
    IEstado jogar_peca(int coluna);
    IEstado aceita_minijogo();
    IEstado recusa_minijogo();
    IEstado joga_minijogo_contas(int n);
    IEstado joga_minijogo_palavras(String palavra);
    IEstado joga_peca_especial(int coluna);
    Situacao getSituacaoAtual();

}
