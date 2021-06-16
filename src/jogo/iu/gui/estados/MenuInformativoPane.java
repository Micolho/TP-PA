package jogo.iu.gui.estados;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import jogo.iu.gui.resources.ImageLoader;
import jogo.logica.JogoObservavel;
import jogo.logica.Situacao;

import static jogo.iu.gui.ConstantesGUI.*;

public class MenuInformativoPane extends BorderPane {
    private JogoObservavel jogoObservavel;
    private Button btJogar;

    public MenuInformativoPane(JogoObservavel jogoObservavel){
        this.jogoObservavel = jogoObservavel;

        criaVista();
        registaListeners();
        registarObservador();
        atualiza();
    }

    private void criaVista() {

        //titulo
        Label titulo = new Label("Bem vindo ao jogo 4 em linha!");
        titulo.setFont(LETRA_TITULO);


        //bck image
        Image img = ImageLoader.getImage(ICON_SCENE);
        if(img != null) {
            BackgroundImage myBI = new BackgroundImage(img,
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    new BackgroundSize(BKGX_PANE, BKGY_PANE, false, false, false, false));
            setBackground(new Background(myBI));
        }
        //button jogar
        btJogar = new Button("Jogar");
        btJogar.setFont(LETRA_BUTTON_MENUINFORMATIVO);
        btJogar.setPrefWidth(BTJOGAR_WIDTH);


        //necessario o setAligment pq o setTop no border pane tem por default top_left
        //https://stackoverflow.com/questions/33773179/center-an-object-in-borderpane
        //borderpane
        setPrefSize(DIMX_PANE, DIMY_PANE);
        setPadding(new Insets(15));
        setAlignment(titulo, Pos.TOP_CENTER);
        setTop(titulo);
        setAlignment(btJogar, Pos.BOTTOM_CENTER);
        setBottom(btJogar);



    }
    private void registaListeners(){
        btJogar.setOnAction(e -> jogoObservavel.opcoesjogo());
    }

    private void registarObservador(){
        jogoObservavel.addPropertyChangeListener(PROPRIEDADE_JOGO, evt -> atualiza());
    }

    private void atualiza() {
        setVisible(jogoObservavel.getSituacaoAtual() ==  Situacao.MENU_INFORMATIVO);
    }
}
