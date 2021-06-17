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

    public void joga(JogoDados jogoDados){
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
    }

    public void setPosAJogar(int posAJogar){
    }

    public int getCreditos() {
        return 0;
    }
    public boolean deductCredit(int n) {
        return false;
    }
    public boolean jaJogouMiniJogo(){
        return false;
    }

    public void setJaJogouMiniJogo(boolean jaJogouMiniJogo) {

    }

    public void setRecusaMiniGame(boolean recusaMiniGame){}
    public boolean recusaMiniGame(){
        return false;
    }

    @Override
    public String toString(){
        return "\nNome do jogador: " + nome + " id: " + id +"\n";
    }

    public void resetJogadas(){}
}
