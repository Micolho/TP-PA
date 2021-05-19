package jogo.logica.dados;

public class JogadorVirtual extends Jogador{
    private String nome;
    private int nJogadas;

    public JogadorVirtual(String nome){
        this.nJogadas = 0;
        setNome(nome);
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
        int random, colunaAJogar = 0;
        while(!sair) {
             random = (int) (Math.random() * 6);
             if(jogoDados.validaJogada(random)){
                 colunaAJogar = random;
                 sair = true;
             }
        }


        return false;
    }

    public void setPosAJogar(int posAJogar){

    }

    @Override
    public String toString(){
        return "\nNome do jogador: " + nome + "\n";
    }
}
