package jogo.iu.gui.estados;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import jogo.iu.gui.VistaTabuleiro;
import jogo.logica.JogoObservavel;
import jogo.logica.Situacao;

import java.util.ArrayList;
import java.util.List;

import static jogo.iu.gui.ConstantesGUI.*;

public class AguardaJogadaPane extends BorderPane {
    private JogoObservavel jogoObservavel;
    private VistaTabuleiro vistaTabuleiro;
    private Label bottomText, playerName, pecaEspecial, creditos;
    private VBox vboxJogo;
    private ToggleGroup group = new ToggleGroup();
    RadioButton rbPecaNormal, rbPecaEspecial;
    Button btUndo;
    ChoiceBox cbUndo;


    public AguardaJogadaPane(JogoObservavel jogoObservavel){
        this.jogoObservavel = jogoObservavel;

        criaVista();
        registaListeners();
        registarObservador();
        atualiza();
    }




    private void criaVista() {
        //top
        playerName = new Label("");
        playerName.setFont(LETRA_TITULO);


        // Central
        //vboxJogo = new VBox();
        //tabuleiro
        vistaTabuleiro = new VistaTabuleiro(jogoObservavel, group);
        vistaTabuleiro.setAlignment(Pos.CENTER_LEFT);
        //box items aguarda jogada
        VBox items = new VBox();
        //show credits
        creditos = new Label("Créditos: ");
        creditos.setAlignment(Pos.TOP_CENTER);
        creditos.setFont(LETRA_J_INPUT);
        //show peca Especial
        pecaEspecial = new Label("Peça Especial: ");
        pecaEspecial.setAlignment(Pos.TOP_CENTER);
        pecaEspecial.setFont(LETRA_J_INPUT);


        Label lPeca = new Label("Selecione a peca que deseja jogar:");
        lPeca.setFont(LETRA_RB);
        //peca normal
        rbPecaNormal = new RadioButton("Peça Normal");
        rbPecaNormal.setSelected(true);
        rbPecaNormal.setUserData(0);
        rbPecaNormal.setToggleGroup(group);
        rbPecaNormal.setFont(LETRA_RB);
        //peca especial
        rbPecaEspecial = new RadioButton("Peça Especial");
        rbPecaEspecial.setUserData(1);
        rbPecaEspecial.setToggleGroup(group);
        rbPecaEspecial.setFont(LETRA_RB);


        //button undo
        Label lUndo = new Label("Selecione quantas jogadas quer desfazer:");
        lUndo.setFont(LETRA_RB);
        cbUndo = new ChoiceBox();
        cbUndo.setTooltip(new Tooltip("Selecione o numero de vezes a desfazer jogadas"));
        btUndo = new Button("Desfazer jogada");
        btUndo.setFont(LETRA_BUTTON_MENUINFORMATIVO);

        items.getChildren().addAll(creditos,pecaEspecial,
                new Separator() , lPeca, rbPecaNormal, rbPecaEspecial,
                new Separator(),lUndo,cbUndo,btUndo);
        items.setSpacing(10);
        items.setAlignment(Pos.CENTER);
        items.setBorder(new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID,
                null, new BorderWidths(2))));
        items.setMaxSize(maxTabSize,maxTabSize);

        HBox hBoxVistaEOpcoes = new HBox(vistaTabuleiro, items);
        hBoxVistaEOpcoes.setAlignment(Pos.CENTER_LEFT);
        hBoxVistaEOpcoes.setSpacing(25);

        //Box bottom
        bottomText = new Label();
        bottomText.setAlignment(Pos.BOTTOM_CENTER);
        HBox hBoxBottomText = new HBox(bottomText);
        hBoxBottomText.setAlignment(Pos.BOTTOM_CENTER);

        setAlignment(playerName, Pos.TOP_CENTER);
        setTop(playerName);

        setAlignment(hBoxVistaEOpcoes, Pos.CENTER_LEFT);
        setCenter(hBoxVistaEOpcoes);

        setAlignment(hBoxBottomText, Pos.BOTTOM_CENTER);
        setBottom(hBoxBottomText);

        setPadding(new Insets(15));

    }

    private void registaListeners() {
        btUndo.setOnAction(e -> {
            if(cbUndo.getValue()!= null)
                jogoObservavel.undo((int)cbUndo.getValue());
            else{
                Alert dialog = new Alert(Alert.AlertType.WARNING);
                dialog.setTitle("Desfazer jogada");
                dialog.setHeaderText("Desfazer jogada");
                dialog.setContentText("Indique por favor no caixa de escolha acima quantas vezes deseja fazer undo!");
                dialog.showAndWait();
            }
        });
    }

    private void criaChoiceBoxUndo(){
        List<Integer> options = new ArrayList<>();
        for(int i = 1; i <= jogoObservavel.getCreditos(); i++){
            options.add(i);
        }
        cbUndo.setItems(FXCollections.observableArrayList(options));
    }

    private void registarObservador(){
        jogoObservavel.addPropertyChangeListener(PROPRIEDADE_JOGO,
                evt -> atualiza()
        );
    }

    private void dialogError(){
        StringBuilder s = new StringBuilder();
        Alert dialogErro = new Alert(Alert.AlertType.WARNING);
        dialogErro.setHeaderText("Atenção!");
        dialogErro.setTitle("Atenção!");

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

    private void atualiza(){
        if(jogoObservavel.temErros() &&
                (jogoObservavel.getSituacaoAtual() ==  Situacao.AGUARDA_JOGADA_HUMANA ||
                jogoObservavel.getSituacaoAtual() == Situacao.AGUARDA_JOGADA_VIRTUAL)){
            dialogError();
            jogoObservavel.setErros(false);
        }


        if(jogoObservavel.getSituacaoAtual() == Situacao.AGUARDA_JOGADA_VIRTUAL) {
            jogoObservavel.jogar_peca(0);
        }

        rbPecaEspecial.setDisable(jogoObservavel.getSituacaoAtual() == Situacao.AGUARDA_JOGADA_VIRTUAL ||
                jogoObservavel.getSituacaoAtual() == Situacao.FIM_JOGO);
        rbPecaNormal.setDisable(jogoObservavel.getSituacaoAtual() == Situacao.AGUARDA_JOGADA_VIRTUAL ||
                jogoObservavel.getSituacaoAtual() == Situacao.FIM_JOGO);
        cbUndo.setDisable(jogoObservavel.getSituacaoAtual() == Situacao.AGUARDA_JOGADA_VIRTUAL ||
                jogoObservavel.getSituacaoAtual() == Situacao.FIM_JOGO);
        btUndo.setDisable(jogoObservavel.getSituacaoAtual() == Situacao.AGUARDA_JOGADA_VIRTUAL ||
                jogoObservavel.getSituacaoAtual() == Situacao.FIM_JOGO);

        if(jogoObservavel.getSituacaoAtual() ==  Situacao.AGUARDA_JOGADA_HUMANA ||
                jogoObservavel.getSituacaoAtual() == Situacao.AGUARDA_JOGADA_VIRTUAL){
            playerName.setText(jogoObservavel.printNextPlayer());
        }

        if(jogoObservavel.getSituacaoAtual() == Situacao.AGUARDA_JOGADA_HUMANA){
            pecaEspecial.setText("Peça Especial: " + (jogoObservavel.jogadorTemPecaEspecial()? "sim":"não"));
            creditos.setText("Créditos: " + jogoObservavel.getCreditos());
            rbPecaEspecial.setDisable(!(jogoObservavel.jogadorTemPecaEspecial()));
            criaChoiceBoxUndo();
        }

        setVisible(jogoObservavel.getSituacaoAtual() !=  Situacao.ESCOLHE_JOGO &&
                jogoObservavel.getSituacaoAtual() != Situacao.MENU_INFORMATIVO);
    }
}
