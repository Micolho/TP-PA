package jogo.logica.dados;

public class JogadorVirtual extends Jogador{
    private String nome;
    private int nJogadas;
    private int id;

    public JogadorVirtual(String nome){
        this.nJogadas = 0;
        setNome(nome);
        id = count++;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
    public String getNome(){
        return this.nome;
    }

    public void addJogada(){
        nJogadas++;
    }

    public  int getJogadas(){
        return this.nJogadas;
    }
    public boolean getPecaEspecial(){
        return false;
    }
    public void setPecaEspecial(boolean peca){
    }
    public String getLastMiniGame(){
        return null;
    }
    public void setLastMiniGame(String minigame) {
    }

    public boolean joga(JogoDados jogoDados){
        boolean sair = false;
        int random;
        while(!sair) {
             random = (int) (Math.random() * 7);
             if(jogoDados.validaJogada(random)){
                 jogoDados.registaTabuleiro(random, id);
                 sair = true;
             }
        }

        addJogada();
        return false;
    }

    public void setPosAJogar(int posAJogar){
    }

    @Override
    public String toString(){
        return "\nNome do jogador: " + nome + " id: " + id +"\n";
    }
}
