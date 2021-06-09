package jogo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jogo.iu.gui.Root;
import jogo.logica.JogoGestao;
import jogo.logica.JogoObservavel;

public class Mainjfx extends Application {

    @Override
    public void start(Stage primaryStage) {

       try {
            JogoGestao jogoGestao = new JogoGestao();
            JogoObservavel jogoObservavel = new JogoObservavel(jogoGestao);

            Root Root = new Root(jogoObservavel);
            Scene scene = new Scene(Root, 960, 600);

            primaryStage.setResizable(true);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Connect4");
            primaryStage.setOnCloseRequest(windowEvent -> Platform.exit());
            primaryStage.show();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
