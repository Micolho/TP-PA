package jogo.iu.gui.estados;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import jogo.logica.JogoObservavel;
import jogo.logica.Situacao;

import java.util.Optional;

import static jogo.iu.gui.ConstantesGUI.PROPRIEDADE_JOGO;

public class MiniJogoContasPane {
    private JogoObservavel jogoObservavel;
    private TextInputDialog dialog;
    private StringBuilder s = new StringBuilder();;

    public MiniJogoContasPane(JogoObservavel jogoObservavel){
        this.jogoObservavel = jogoObservavel;

        criaVista();
        registarObservador();
        atualiza();

    }

    private void criaVista() {
        dialog = new TextInputDialog();
        dialog.setTitle("Mini Jogo Contas");
        dialog.setHeaderText("Indique o resultado correto: ");


    }

    private void registarObservador() {
        jogoObservavel.addPropertyChangeListener(PROPRIEDADE_JOGO, evt -> atualiza());
    }


    private void atualiza() {

        if(jogoObservavel.getSituacaoAtual() == Situacao.MINIJOGO_CONTAS){

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
                try {
                    jogoObservavel.joga_minijogo_contas(Integer.parseInt(result.get()));
                }catch (NumberFormatException e){
                    Alert al = new Alert(Alert.AlertType.ERROR);
                    al.setTitle("Mini jogo Contas");
                    al.setHeaderText("Número inválido!");
                    al.setContentText("O número que introduziu é inválido!");
                    al.showAndWait();
                }
            }else{
                jogoObservavel.joga_minijogo_contas(-1);
            }
            jogoObservavel.setErros(false);
        }
    }
}
