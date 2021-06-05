package jogo.iu.gui;

import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import jogo.logica.JogoObservavel;
import jogo.logica.Situacao;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static jogo.iu.gui.ConstantesGUI.PROPRIEDADE_JOGO;

public class Root extends BorderPane {
    private JogoObservavel jogoObservavel;
    private MenuItem novoJogoMI;

    public Root(JogoObservavel jogoObservavel){
        this.jogoObservavel = jogoObservavel;

        registarObservador();
        atualiza();
    }

    private void registarObservador(){
        // regista um observador do jogoObservavel
        jogoObservavel.addPropertyChangeListener(PROPRIEDADE_JOGO,
                new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        atualiza();
                    }
                }
        );
    }

    private void atualiza() {
        novoJogoMI.setDisable(!(jogoObservavel.getSituacaoAtual() ==  Situacao.AGUARDA_JOGADA_VIRTUAL ));
    }
}
