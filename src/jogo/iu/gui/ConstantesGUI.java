package jogo.iu.gui;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.io.Serializable;

public class ConstantesGUI implements Serializable {
    private ConstantesGUI(){}
    public static String PROPRIEDADE_JOGO = "jogo";
    public final static int nLinhas = 6;
    public final static int nColunas = 7;
    public final static int maxTabSize = 300;
    public final static int chipRadius = 25;
    public final static int tabPadding = 30;
    public final static int chipGapSize = 10;

    public static Font LETRA = Font.font("verdana", FontWeight.NORMAL, FontPosture.ITALIC, 14);

}
