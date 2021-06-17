package jogo.iu.gui.estados;

import javafx.scene.control.TextInputDialog;
import jogo.logica.JogoObservavel;
import jogo.logica.Situacao;

import java.util.Optional;

import static jogo.iu.gui.ConstantesGUI.PROPRIEDADE_JOGO;

public class MiniJogoPalavrasPane {
    private JogoObservavel jogoObservavel;
    private TextInputDialog dialog;
    private StringBuilder s = new StringBuilder();;

    public MiniJogoPalavrasPane(JogoObservavel jogoObservavel){
        this.jogoObservavel = jogoObservavel;

        criaVista();
        registarObservador();
        atualiza();

    }

    private void criaVista() {
        dialog = new TextInputDialog();
        dialog.setTitle("Mini Jogo Palavras");
        dialog.setHeaderText("Escreva as seguintes palavras por ondem incluindo os espaços:");
    }

    private void registarObservador() {
        jogoObservavel.addPropertyChangeListener(PROPRIEDADE_JOGO, evt -> atualiza());
    }


    private void atualiza() {

        if(jogoObservavel.getSituacaoAtual() == Situacao.MINIJOGO_PALAVRAS){

            if(jogoObservavel.getMsgLog().size()>0){
                s = new StringBuilder();
                s.append("\n");

                for(String msg:jogoObservavel.getMsgLog()){
                    s.append(msg).append("\n");
                }

                jogoObservavel.clearMsgLog();
            }

            dialog.setContentText(s.toString());
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()){
                jogoObservavel.joga_minijogo_palavras(result.get());
            }else{
                jogoObservavel.joga_minijogo_palavras("");
            }
            jogoObservavel.setErros(false);
        }
    }
}