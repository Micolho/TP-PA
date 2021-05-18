package jogo.logica.estados;

import jogo.logica.dados.JogoDados;

public abstract class EstadoAdapter implements IEstado {
    private JogoDados jogoDados;

    public EstadoAdapter(JogoDados jogoDados){
        this.jogoDados = jogoDados;
    }
    public JogoDados getJogoDados(){
        return jogoDados;
    }
    @Override
    public IEstado opcoes_jogo(){
        return this;
    }
    @Override
    public IEstado iniciar_jogo(int tipo, String nome1, String nome2){
        return this;
    }
    @Override
    public IEstado terminar(){
        return this;
    }
    @Override
    public IEstado jogar_peca(int coluna){
        return this;
    }
    @Override
    public IEstado random_jogador(){
        return this;
    }
    @Override
    public IEstado apos4jogadas(){
        return this;
    }
    @Override
    public IEstado aguarda_opcao(){
        return this;
    }
    @Override
    public IEstado aceita_minijogo(){
        return this;
    }
    @Override
    public IEstado recusa_minijogo(){
        return this;
    }
    @Override
    public IEstado ganha_minijogo(){
        return this;
    }
    @Override
    public IEstado perde_minijogo(){
        return this;
    }
    @Override
    public IEstado guarda_peca_especial(){
        return this;
    }
    @Override
    public IEstado joga_peca_especial(){
        return this;
    }
}
