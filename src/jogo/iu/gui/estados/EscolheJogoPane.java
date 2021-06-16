package jogo.iu.gui.estados;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import javafx.scene.paint.Color;
import jogo.iu.gui.resources.ImageLoader;
import jogo.logica.JogoObservavel;
import jogo.logica.Situacao;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static jogo.iu.gui.ConstantesGUI.*;

public class EscolheJogoPane extends BorderPane {
    private JogoObservavel jogoObservavel;
    private Button btComecar;
    private ToggleGroup group;
    private List<Image> icons = new ArrayList<>();
    private ImageView iconSelected;
    private TextField inputJ1, inputJ2;

    public EscolheJogoPane(JogoObservavel jogoObservavel){
        this.jogoObservavel = jogoObservavel;

        criaVista();
        registaListeners();
        registarObservador();
        atualiza();
    }

    private void criaVista() {
        //vbox, hbox e grupo button
        VBox vboxRadio = new VBox();
        VBox vboxGeral = new VBox();
        HBox boxJ1 = new HBox();
        HBox boxJ2 = new HBox();
        HBox hboxGroupImage = new HBox();
        group = new ToggleGroup();
        Label titulo = new Label("Escolhe o tipo de jogo:");
        titulo.setPadding(new Insets(30,10,10,10));
        titulo.setFont(LETRA_TITULO);

        ///rb1
        RadioButton rb1 = makeRadioButton("Player vs Player",1);
        rb1.setSelected(true);
        icons.add(ImageLoader.getImage(ICON_PLAYERVSPLAYER));
        //rb2
        RadioButton rb2 = makeRadioButton("Player vs PC",2);
        icons.add(ImageLoader.getImage(ICON_PLAYERVSPC));
        //rb3
        RadioButton rb3 = makeRadioButton("PC vs PC",3);
        icons.add(ImageLoader.getImage(ICON_PCVSPC));

        //vbox radios
        vboxRadio.setSpacing(rbSpacing);
        vboxRadio.setPadding(new Insets(30,10,10,10));
        vboxRadio.getChildren().addAll(titulo, rb1,rb2,rb3);
        vboxRadio.setAlignment(Pos.CENTER_LEFT);

        //icon
        iconSelected = new ImageView(icons.get(0));
        iconSelected.setFitHeight(IMAGEVIEW_ICON_RB);
        iconSelected.setFitWidth(IMAGEVIEW_ICON_RB);
        //vboxRadio.setAlignment(Pos.CENTER_RIGHT);

        hboxGroupImage.getChildren().addAll(vboxRadio, iconSelected);
        hboxGroupImage.setAlignment(Pos.CENTER);
        hboxGroupImage.setPrefSize(HBOX_GROUP_IMG_SIZE,HBOX_GROUP_IMG_SIZE);
        //hboxGroupImage.setBorder(new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID,
        //        null, new BorderWidths(2))));

        Label j1 = new Label("Nome 1º jogador:");
        inputJ1 = new TextField();
        j1.setFont(LETRA_J_INPUT);
        Label j2 = new Label("Nome 2º jogador:");
        j2.setFont(LETRA_J_INPUT);
        inputJ2 = new TextField();


        boxJ1.getChildren().addAll(j1, inputJ1);
        boxJ1.setSpacing(5);
        boxJ1.setAlignment(Pos.CENTER);
        boxJ2.getChildren().addAll(j2, inputJ2);
        boxJ2.setSpacing(5);
        boxJ2.setAlignment(Pos.CENTER);

        vboxGeral.getChildren().addAll(hboxGroupImage, boxJ1, boxJ2);
        vboxGeral.setSpacing(5);

        //button
        btComecar = new Button("Começar Jogo");

        //borderPane
        setPrefSize(DIMX_PANE, DIMY_PANE);
        setPadding(new Insets(15));
        setAlignment(titulo, Pos.TOP_CENTER);
        setTop(titulo);
        setAlignment(vboxGeral, Pos.CENTER);
        setCenter(vboxGeral);
        setAlignment(btComecar, Pos.BOTTOM_CENTER);
        setBottom(btComecar);
    }

    private RadioButton makeRadioButton(String tipo, int UserData){
        RadioButton rb = new RadioButton(tipo);
        rb.setToggleGroup(group);
        rb.setUserData(UserData);
        rb.setAlignment(Pos.CENTER_LEFT);
        rb.setFont(LETRA_RB);
        return rb;
    }
    private void registaListeners() {
        //comecarjogo
        btComecar.setOnAction(e -> jogoObservavel.iniciar_jogo(
                (int)group.getSelectedToggle().getUserData(),
                inputJ1.getText(),
                inputJ2.getText()
        ));
        //change iconSelected
        group.selectedToggleProperty().addListener((ObserValue, prevToogle, selectedToogle) -> {
            if (group.getSelectedToggle() != null) {
                iconSelected.setImage(icons.get(((int)
                        group.getSelectedToggle().getUserData()) - 1)
                );
            }
        });
    }

    private void registarObservador() {
        jogoObservavel.addPropertyChangeListener(PROPRIEDADE_JOGO, evt -> atualiza());
    }

    private void dialogError(){
        StringBuilder s = new StringBuilder();
        Alert dialogErro = new Alert(Alert.AlertType.ERROR);
        dialogErro.setHeaderText("Atenção!");

        if(jogoObservavel.getMsgLog().size()>0){

            s.append("\n");

            for(String msg:jogoObservavel.getMsgLog()){
                s.append(msg).append("\n");
            }

            jogoObservavel.clearMsgLog();
        }
        dialogErro.setContentText(s.toString());
        dialogErro.showAndWait();
    }

    private void atualiza() {

        if(jogoObservavel.temErros() &&
                jogoObservavel.getSituacaoAtual() ==  Situacao.ESCOLHE_JOGO){
            dialogError();
            jogoObservavel.setErros(false);
        }
        setVisible(jogoObservavel.getSituacaoAtual() ==  Situacao.ESCOLHE_JOGO);
    }
}
