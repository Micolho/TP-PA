package jogo.iu.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import jogo.logica.JogoObservavel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import static jogo.iu.gui.ConstantesGUI.*;

public class VistaTabuleiro extends StackPane {
    private JogoObservavel jogoObservavel;
    private GridPane gridTab;
    private GridPane gridOverlay;

    public VistaTabuleiro(JogoObservavel jogoObservavel){
        this.jogoObservavel = jogoObservavel;

        //a fazer
        // meter como background uma imagem do "tabuleiro" do 4 em linha
        // meter as fichas de uma cor e de outra
        // grid para mostrar o jogo
        // metodo para devolver o tabuleiro
        //setTabuleiro();
        //setGridOverlay();
        registarObservador();
        atualiza();
}

    private void setTabuleiro() {

        gridTab = new GridPane();

        gridTab.setMaxSize(maxTabSize, maxTabSize);
        gridTab.setBorder(new Border(new BorderStroke(Color.DARKBLUE, BorderStrokeStyle.SOLID,
                null, new BorderWidths(1))));
        gridTab.setHgap(chipGapSize);
        gridTab.setVgap(chipGapSize);
        gridTab.setPadding(new Insets(tabPadding, tabPadding, tabPadding, tabPadding));
        setAlignment(Pos.CENTER_LEFT);
        gridTab.setAlignment(Pos.CENTER_LEFT);
        gridTab.setBackground(new Background(new BackgroundFill(Color.DEEPSKYBLUE, null, null)));

        desenhaTabuleiro();

        this.getChildren().add(gridTab);
    }

    private void setGridOverlay() {

        gridOverlay = new GridPane();

        gridOverlay.setMinSize(gridTab.getHeight(), gridTab.getWidth());
        gridOverlay.setMaxSize(gridTab.getHeight(), gridTab.getWidth());
        gridOverlay.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                null, new BorderWidths(1))));
        //gridOverlay.setHgap(chipGapSize/2);
        //gridOverlay.setVgap(chipGapSize);
        gridOverlay.setPadding(new Insets(tabPadding-5, tabPadding-5, tabPadding-5, tabPadding-5));
        //setAlignment(Pos.CENTER_LEFT);
        gridOverlay.setAlignment(Pos.CENTER_LEFT);
        //gridOverlay.setBackground(new Background(new BackgroundFill(Color.DEEPSKYBLUE, null, null)));

        makeSelectedOverlay();

        this.getChildren().add(gridOverlay);
    }

    private void desenhaTabuleiro(){
        int [][] tab = jogoObservavel.getTabuleiro();
        Circle c;

        for( int i = 0; i < nLinhas; i++){ // tab.length = nLinhas
            for( int j = 0; j < nColunas; j++){
                c = new Circle(0,0, chipRadius);
                if (tab[i][j] == 0){
                    c.setFill(Color.WHITE);
                    c.setStroke(Color.ANTIQUEWHITE);
                }else{
                    if(tab[i][j] % 2 == 0) {//se par O senao x
                        c.setFill(Color.RED);
                        c.setStroke(Color.DARKRED);
                    }else{
                        c.setFill(Color.LIGHTYELLOW);
                        c.setStroke(Color.YELLOW);
                    }
                }
                c.setStrokeWidth(2f);
                c.setStrokeType(StrokeType.OUTSIDE);
                gridTab.add(c,j,i);
            }
        }
    }

    private void makeSelectedOverlay(){

        List<Rectangle> listRect = new ArrayList<>();

        for(int i = 0; i < nColunas; i++){
            //Rectangle rect = new Rectangle(chipSize/2, (nLinhas + 1) * (chipSize));
            Rectangle rect = new Rectangle((chipRadius +2+5)*2, (nLinhas + 1) * ((chipRadius)*2) + chipGapSize*5);

            rect.setFill(Color.TRANSPARENT);

            rect.setOnMouseEntered(e -> {
                rect.setFill(Color.ORANGERED);
                rect.setOpacity(0.7);

            });
            rect.setOnMouseExited(e -> {
                rect.setFill(Color.TRANSPARENT);
            });

            rect.setOnMouseClicked(e->{
                //if()
                System.out.println(gridOverlay.getColumnIndex(rect));// funciona bem
                jogoObservavel.jogar_peca(gridOverlay.getColumnIndex(rect));
            });

            gridOverlay.add(rect,i,i, 1, nLinhas);
            listRect.add(rect);
        }
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
        System.out.println("atualizaVista");
        this.getChildren().clear();
        setTabuleiro();
        setGridOverlay();
    }
}

