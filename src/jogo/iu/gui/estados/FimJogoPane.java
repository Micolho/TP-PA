package jogo.iu.gui.estados;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import jogo.logica.JogoObservavel;
import jogo.logica.Situacao;

import java.util.Optional;

import static jogo.iu.gui.ConstantesGUI.PROPRIEDADE_JOGO;

public class FimJogoPane {
    private JogoObservavel jogoObservavel;
    private Alert dialog;
    private ButtonType yes, no;

    public FimJogoPane(JogoObservavel jogoObservavel){
        this.jogoObservavel = jogoObservavel;

        criaVista();
        registarObservador();
        atualiza();

    }

    private void criaVista() {

        dialog = new Alert(Alert.AlertType.CONFIRMATION);
        dialog.setTitle("Jogo finalizado!");
        dialog.setHeaderText("Deseja jogar novamente?");
        yes = new ButtonType("Sim");
        no = new ButtonType("Não", ButtonBar.ButtonData.CANCEL_CLOSE);

        dialog.getButtonTypes().setAll(yes,no);

    }

    private void registarObservador() {
        jogoObservavel.addPropertyChangeListener(PROPRIEDADE_JOGO, evt -> atualiza());
    }

    private void atualiza() {
        StringBuilder s = new StringBuilder();
        if(jogoObservavel.getSituacaoAtual() == Situacao.FIM_JOGO){
            //gravar ficheiro para o historico
            jogoObservavel.afterFinish();

            if(jogoObservavel.getMsgLog().size()>0){

                s.append("\n");

                for(String msg:jogoObservavel.getMsgLog()){
                    s.append(msg).append("\n");
                }

                jogoObservavel.clearMsgLog();
            }
            //jogoObservavel.afterFinish();
            dialog.setContentText(s.toString());
            Optional<ButtonType> result = dialog.showAndWait();
            //noinspection OptionalGetWithoutIsPresent
            if(result.get() == yes){
                jogoObservavel.setErros(false);
                jogoObservavel.opcoesjogo();
            }else{
                jogoObservavel.setErros(false);
                jogoObservavel.opcoesjogo();
                Platform.exit();
            }
        }
    }
}
