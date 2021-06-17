package jogo.iu.gui.estados;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import jogo.logica.JogoObservavel;
import jogo.logica.Situacao;

import java.util.Optional;

import static jogo.iu.gui.ConstantesGUI.PROPRIEDADE_JOGO;

public class DecideMiniJogoDialog {
    private JogoObservavel jogoObservavel;
    private Alert dialog;
    private ButtonType yes, no;

    public DecideMiniJogoDialog(JogoObservavel jogoObservavel){
        this.jogoObservavel = jogoObservavel;

        criaVista();
        registarObservador();
        atualiza();

    }

    private void criaVista() {
        dialog = new Alert(Alert.AlertType.CONFIRMATION);
        dialog.setTitle("Mini Jogo");
        dialog.setHeaderText("Deseja jogar o mini jogo?");
        yes = new ButtonType("Sim");
        no = new ButtonType("Não", ButtonBar.ButtonData.CANCEL_CLOSE);

        dialog.getButtonTypes().setAll(yes,no);

    }

    private void registarObservador() {
        jogoObservavel.addPropertyChangeListener(PROPRIEDADE_JOGO, evt -> atualiza());
    }

    private void atualiza() {
        if(jogoObservavel.getSituacaoAtual() == Situacao.QUER_MINIJOGO){
            Optional<ButtonType> result = dialog.showAndWait();
            if(result.get() == yes){
                jogoObservavel.aceita_minijogo();
            }else{
                jogoObservavel.recusa_minijogo();
            }
        }
    }
}
