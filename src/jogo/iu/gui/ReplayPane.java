package jogo.iu.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import jogo.logica.JogoMaqEstados;
import jogo.logica.JogoObservavel;


import java.util.List;

import static jogo.iu.gui.ConstantesGUI.*;

public class ReplayPane extends VBox {
    private GridPane gridTab;
    private JogoObservavel jogoObservavel;
    private Button next;
    private int tam;
    private List<JogoMaqEstados> listMaq;
    private int nJogadasReplay = 0;

    public ReplayPane(JogoObservavel jogoObservavel, String file) {
        this.jogoObservavel = jogoObservavel;
        listMaq = jogoObservavel.leHist(file);
        this.tam = listMaq.size();
        registarObservador();
        setTabuleiro();
        criaVista();
        nextListener();
        atualiza();
    }

    private void criaVista(){
        next = new Button("Proxima jogada");
        next.setFont(LETRA_RB);
        this.getChildren().add(next);
        this.setSpacing(20);
        this.setPadding(new Insets(20));
        this.setAlignment(Pos.CENTER);
    }

    public void atualiza() {
        //System.out.println("fired");
        if(listMaq == null) {
            next.setDisable(true);
            return;
        }
        if (nJogadasReplay == tam-1) {
            next.setDisable(true);
            desenhaTabuleiro(nJogadasReplay++);
            return;
        }
        this.getChildren().clear();
        this.getChildren().addAll(gridTab ,next);
        desenhaTabuleiro(nJogadasReplay++);
        }

    private void setTabuleiro() {

        gridTab = new GridPane();

        gridTab.setMaxSize(maxTabSize, maxTabSize);
        gridTab.setBorder(new Border(new BorderStroke(Color.DARKBLUE, BorderStrokeStyle.SOLID,
                null, new BorderWidths(1))));
        gridTab.setHgap(chipGapSize);
        gridTab.setVgap(chipGapSize);
        gridTab.setPadding(new Insets(tabPadding, tabPadding, tabPadding, tabPadding));
        //setAlignment(Pos.CENTER_LEFT);
        gridTab.setAlignment(Pos.CENTER_LEFT);
        gridTab.setBackground(new Background(new BackgroundFill(Color.DEEPSKYBLUE, null, null)));

        //desenhaTabuleiro();
        this.getChildren().add(gridTab);

    }

    private void registarObservador() {
        jogoObservavel.addPropertyChangeListener(PROPRIEDADE_REPLAY, evt -> atualiza());
    }

    private void nextListener(){
        next.setOnMouseClicked(e-> jogoObservavel.nextPlay());
    }

    private void desenhaTabuleiro ( int indice){
        int[][] tab = listMaq.get(indice).getTabuleiro();
        Circle c;

        for (int i = 0; i < nLinhas; i++) { // tab.length = nLinhas
            for (int j = 0; j < nColunas; j++) {
                c = new Circle(0, 0, chipRadius);
                if (tab[i][j] == 0) {
                    c.setFill(Color.WHITE);
                    c.setStroke(Color.ANTIQUEWHITE);
                } else {
                    if (tab[i][j] % 2 == 0) {//se par O senao x
                        c.setFill(Color.RED);
                        c.setStroke(Color.DARKRED);
                    } else {
                        c.setFill(Color.YELLOW);
                        c.setStroke(Color.YELLOWGREEN);
                    }
                }
                c.setStrokeWidth(2f);
                c.setStrokeType(StrokeType.OUTSIDE);
                gridTab.add(c, j, i);
            }
        }
    }
}

