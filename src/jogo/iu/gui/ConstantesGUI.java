package jogo.iu.gui;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.io.Serializable;

public class ConstantesGUI implements Serializable {
    private ConstantesGUI(){}
    public static String PROPRIEDADE_JOGO = "jogo";
    public static String PROPRIEDADE_REPLAY = "replay";
    public final static int nLinhas = 6;
    public final static int nColunas = 7;
    public final static int maxTabSize = 300;
    public final static int chipRadius = 25;
    public final static int tabPadding = 30;
    public final static int rbSpacing = 30;
    public final static int chipGapSize = 10;
    public static String ICON_SCENE = "connect4.png";
    public static String ICON_PCVSPC = "pc_vs_pc.png";
    public static String ICON_PLAYERVSPC = "player_vs_pc.png";
    public static String ICON_PLAYERVSPLAYER = "player_vs_player.png";
    public final static int DIMX_SCENE = 960;
    public final static int DIMY_SCENE = 600;
    public final static int DIMX_PANE = 960;
    public final static int DIMY_PANE = 570;
    public final static int BKGX_PANE = 450;
    public final static int BKGY_PANE = 450;
    public final static int BTJOGAR_WIDTH = 150;
    public final static int HBOX_GROUP_IMG_SIZE = 300;
    public final static int IMAGEVIEW_ICON_RB = 130;


    public static Font LETRA_TITULO = Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20);
    public static Font LETRA_BUTTON_MENUINFORMATIVO = Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 16);
    public static Font LETRA_RB = Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 14);
    public static Font LETRA_J_INPUT = Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 14);

}
