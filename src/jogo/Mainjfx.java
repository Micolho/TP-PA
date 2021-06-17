package jogo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import jogo.iu.gui.Root;
import jogo.iu.gui.resources.ImageLoader;
import jogo.logica.JogoGestao;
import jogo.logica.JogoObservavel;

import static jogo.iu.gui.ConstantesGUI.*;

public class Mainjfx extends Application {

    @Override
    public void start(Stage primaryStage) {

       try {
        JogoGestao jogoGestao = new JogoGestao();
        JogoObservavel jogoObservavel = new JogoObservavel(jogoGestao);

        Root Root = new Root(jogoObservavel);
        Scene scene = new Scene(Root, DIMX_SCENE, DIMY_SCENE);

        primaryStage.setResizable(false);

        Image img = ImageLoader.getImage(ICON_SCENE);
        if (img != null) {
            primaryStage.getIcons().add(img);
        }
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
