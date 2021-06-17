package jogo.iu.gui;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import jogo.iu.gui.estados.*;
import jogo.logica.JogoObservavel;

import java.util.Timer;

import static jogo.iu.gui.ConstantesGUI.PROPRIEDADE_JOGO;

public class PrincipalPane extends StackPane {
    private JogoObservavel jogoObservavel;


    public PrincipalPane(JogoObservavel jogoObservavel) {
        this.jogoObservavel = jogoObservavel;

        criarVista();
        registarObservador();
        atualiza();
    }
    private void registarObservador(){
        jogoObservavel.addPropertyChangeListener(PROPRIEDADE_JOGO,
                evt -> atualiza()
        );
    }

    private void criarVista(){
        setBorder(new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID,
                null, new BorderWidths(2))));

        // PAINEIS DOS ESTADOS
        MenuInformativoPane menuInformativoPane = new MenuInformativoPane(jogoObservavel);
        EscolheJogoPane escolheJogoPane = new EscolheJogoPane(jogoObservavel);
        AguardaJogadaPane aguardaJogadaPane = new AguardaJogadaPane(jogoObservavel);
        //dialog boxes
        DecideMiniJogoDialog decideMiniJogoDialog = new DecideMiniJogoDialog(jogoObservavel);
        MiniJogoContasPane miniJogoContasPane = new MiniJogoContasPane(jogoObservavel);
        MiniJogoPalavrasPane miniJogoPalavrasPane = new MiniJogoPalavrasPane(jogoObservavel);
        FimJogoPane fimJogoPane = new FimJogoPane(jogoObservavel);

        getChildren().addAll(aguardaJogadaPane,
                menuInformativoPane,
                escolheJogoPane);
    }

//    public void mostraHist(){
//        Timer temporizador = new Timer();
//
//        temporizador.scheduleAtFixedRate(() - >{
//
//        });
//    }

//    public void para() {
//        temporiz.cancel;
//    }


    private void atualiza() {
        System.out.println("atualiza Vista");
    }
}
