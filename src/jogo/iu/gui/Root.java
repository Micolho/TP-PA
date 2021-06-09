package jogo.iu.gui;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import jogo.logica.JogoObservavel;
import jogo.logica.Situacao;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static jogo.iu.gui.ConstantesGUI.PROPRIEDADE_JOGO;

public class Root extends VBox {
    private JogoObservavel jogoObservavel;
    private MenuItem novoJogoMI;
    private PrincipalPane principalPane;

    public Root(JogoObservavel jogoObservavel){
        this.jogoObservavel = jogoObservavel;
        this.principalPane = new PrincipalPane(jogoObservavel);

        menus();
        registarObservador();
        criarVistaCentral();
        atualiza();
    }

    private void criarVistaCentral(){
        getChildren().add(principalPane);
    }

    private void menus() {
        MenuBar menuBar = new MenuBar();
        getChildren().add(menuBar);

        menuBar.getMenus().addAll(menuJogo(), menuHistorico(), menuAjuda());
    }

    private Menu menuJogo(){
        //menu jogo
        Menu jogoMenu = new Menu("_Jogo");

        novoJogoMI = new MenuItem("Novo Jogo");
        novoJogoMI.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));

        MenuItem lerObjMI = new MenuItem("Carregar Jogo");
        lerObjMI.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN));

        MenuItem gravarObj = new MenuItem("Gravar Jogo");
        gravarObj.setAccelerator(new KeyCodeCombination(KeyCode.G, KeyCombination.CONTROL_DOWN));

        MenuItem sairMI = new MenuItem("Sair");
        sairMI.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));

        jogoMenu.getItems().addAll(novoJogoMI, lerObjMI, gravarObj, new SeparatorMenuItem(), sairMI);

        //Accoes do Jogo
        novoJogoMI.setOnAction((e)-> jogoObservavel.terminar());

        lerObjMI.setOnAction((ActionEvent e) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("./"));
            File selectedFile = fileChooser.showOpenDialog(null);
            if(selectedFile != null){
                jogoObservavel.ler(selectedFile);
            }else{
                System.err.println("Leitura Cancelada!");
            }
        });

        gravarObj.setOnAction((ActionEvent e) ->{
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("./"));
            File selectedFile = fileChooser.showSaveDialog(null);
            if(selectedFile != null){
                jogoObservavel.guardar(selectedFile);
            }else{
                System.err.println("Gravacao Cancelada!");
            }
        });

        sairMI.setOnAction((ActionEvent e) -> {
            Stage janela2 = (Stage) this.getScene().getWindow();

            ButtonType sim = new ButtonType("Sim");
            ButtonType nao = new ButtonType("Nao");


            Alert dialogoResultado = new Alert(Alert.AlertType.NONE, "Promote",sim, nao);
            dialogoResultado.setHeaderText("Gravar");
            dialogoResultado.setContentText("Deseja gravar antes de sair?");
            dialogoResultado.showAndWait().ifPresent(response -> {
                if (response == sim) {
                    //codigo para abrir o explorador e gravar em fich
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setInitialDirectory(new File("./"));
                    File selectedFile = fileChooser.showSaveDialog(null);
                    if(selectedFile != null){
                        jogoObservavel.guardar(selectedFile);
                    }else{
                        System.err.println("Gravacao Cancelada!");
                    }

                }
            });
            fireEvent(new WindowEvent(janela2, WindowEvent.WINDOW_CLOSE_REQUEST));
        });

        return jogoMenu;
    }

    private Menu menuHistorico(){
        //Menu Historico
        Menu historicoMenu = new Menu("_Historico");
        List<MenuItem> hist = new ArrayList<>();
        List<String> tmp = jogoObservavel.ListOfFileInDirectory();
        String nameWithoutBin;

        for(String f : tmp){
            nameWithoutBin = f.replace(".bin", "");
            hist.add(new MenuItem(nameWithoutBin));

            hist.get(tmp.indexOf(f)).setOnAction((ActionEvent e) -> {
                //Replay do jogo, por agora improvisado
                System.out.println("hist.get(tmp.indexOf(f)).setOnAction() ---> " + f);
                jogoObservavel.leHist(f);
                for (String s: jogoObservavel.getMsgLog()){
                    System.out.println(s);
                }
            });
        }

        historicoMenu.getItems().addAll(hist);

        return historicoMenu;
    }

    private Menu menuAjuda(){
        //Menu Ajuda
        Menu ajudaMenu = new Menu("_Ajuda");

        MenuItem Regras = new MenuItem("Regras do jogo");
        Regras.setAccelerator(new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN));

        MenuItem Minijogos = new MenuItem("Mini jogos");
        Minijogos.setAccelerator(new KeyCodeCombination(KeyCode.M, KeyCombination.CONTROL_DOWN));

        MenuItem PecaEspecial = new MenuItem("Peca Especial");
        PecaEspecial.setAccelerator(new KeyCodeCombination(KeyCode.P, KeyCombination.CONTROL_DOWN));

        MenuItem Creditos = new MenuItem("Creditos");
        Creditos.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN));

        ajudaMenu.getItems().addAll(Regras, Minijogos, PecaEspecial, new SeparatorMenuItem(), Creditos);

        //acoes do menu ajuda
        Regras.setOnAction((ActionEvent e) -> {
            Alert dialogoResultado = new Alert(Alert.AlertType.INFORMATION);
            dialogoResultado.setHeaderText("Regras");
            dialogoResultado.setContentText("bla bla bla");
            dialogoResultado.showAndWait();
        });

        return ajudaMenu;
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
        principalPane.printMsgLog();
        //novoJogoMI.setDisable(!(jogoObservavel.getSituacaoAtual() ==  Situacao.AGUARDA_JOGADA_VIRTUAL));
    }
}
