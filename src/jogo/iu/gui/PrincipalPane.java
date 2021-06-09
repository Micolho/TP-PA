package jogo.iu.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import jogo.logica.JogoObservavel;

import javax.swing.plaf.SeparatorUI;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static jogo.iu.gui.ConstantesGUI.PROPRIEDADE_JOGO;

public class PrincipalPane extends StackPane {
    private JogoObservavel jogoObservavel;
    private VistaTabuleiro vistaTabuleiro;
    private Label bottomText;


    public PrincipalPane(JogoObservavel jogoObservavel) {
        this.jogoObservavel = jogoObservavel;

        criarVista();
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

    private void criarVista(){

//        setMaxSize(DIM_X_FRAME, DIM_Y_FRAME);
//        setPrefSize(DIM_X_FRAME, DIM_Y_FRAME);
        setBorder(new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID,
                null, new BorderWidths(2))));

        // Central

        VBox vbox = new VBox();

        vistaTabuleiro = new VistaTabuleiro(jogoObservavel);
        vistaTabuleiro.setAlignment(Pos.CENTER_LEFT);

        Label lb = new Label("teste");
        lb.setAlignment(Pos.CENTER_RIGHT);

        HBox hBoxVistaEOpcoes = new HBox(vistaTabuleiro, lb);
        hBoxVistaEOpcoes.setAlignment(Pos.CENTER_LEFT);

        //Box bottom
        bottomText = new Label("teste 2");
        bottomText.setAlignment(Pos.BOTTOM_CENTER);
        HBox hBoxBottomText = new HBox(bottomText);
        hBoxBottomText.setAlignment(Pos.BOTTOM_CENTER);

        vbox.getChildren().addAll(hBoxVistaEOpcoes, new Separator(), hBoxBottomText);
        vbox.setAlignment(Pos.CENTER);

        getChildren().addAll(vbox);

/*        // BOX DIREITA

        // BOX CENTRAL ENVOLVENTE CONTENDO A ESQUERDA E A DIREITA
        HBox center = new HBox(10);
        center.setBorder(new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID,
                null, new BorderWidths(2))));
        center.setAlignment(Pos.CENTER);
        center.getChildren().addAll(leftBox, rightBox);
        setCenter(center);

        // PAINEIS DOS ESTADOS
        AguardaInicioPane aguardaInicioPane = new AguardaInicioPane(jogoObservavel);
        AguardaApostaPane aguardaApostaPane = new AguardaApostaPane(jogoObservavel);
        AguardaOpcaoPane aguardaOpcaoPane = new AguardaOpcaoPane(jogoObservavel);
        FinalJogoPane finalJogoPane = new FinalJogoPane(jogoObservavel);

        // STACKPANE COM OS PAINEIS DOS ESTADOS
        StackPane bottom = new StackPane(aguardaInicioPane, aguardaApostaPane, aguardaOpcaoPane, finalJogoPane);
        bottom.setBorder(new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID,
                null, new BorderWidths(2))));
        bottom.setPrefSize(DIM_X_BOTTOM_PANEL, DIM_Y_BOTTOM_PANEL);
        bottom.setMinSize(DIM_X_BOTTOM_PANEL, DIM_Y_BOTTOM_PANEL);

        setBottom(bottom);*/
    }

    public void printMsgLog(){
        StringBuilder s = new StringBuilder();

        if(jogoObservavel.getMsgLog().size()>0){

            s.append("\n");

            for(String msg:jogoObservavel.getMsgLog()){
                s.append(msg + "\n");
            }

            jogoObservavel.clearMsgLog();
        }

        bottomText.setText(s.toString());
    }


    private void atualiza() {

        //int nb = jogoObservavel.getNBolasBrancasNoSaco();
        //int np = jogoObservavel.getNBolasPretasNoSaco();

        //vistaTabuleiro.
        //pontuacaoLabel.setText("Pontuacao: " + jogoObservavel.getPontuacao());


    }
}
