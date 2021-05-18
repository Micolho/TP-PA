package jogo.logica.estados;

import jogo.logica.Situacao;
import jogo.logica.dados.JogoDados;

public class MenuInformativo extends EstadoAdapter{

    public MenuInformativo(JogoDados jogoDados){
        super(jogoDados);
    }

    public IEstado opcoes_jogo(){
        return new EscolheJogo(getJogoDados());
    }

    public Situacao getSituacaoAtual(){
        return Situacao.MENU_INFORMATIVO;
    }
}
