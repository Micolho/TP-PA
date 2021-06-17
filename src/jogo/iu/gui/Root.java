package jogo.iu.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static jogo.iu.gui.ConstantesGUI.*;

public class Root extends VBox {
    private JogoObservavel jogoObservavel;
    private MenuItem novoJogoMI;
    private PrincipalPane principalPane;
    private MenuItem gravarObj;

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

        gravarObj = new MenuItem("Gravar Jogo");
        gravarObj.setAccelerator(new KeyCodeCombination(KeyCode.G, KeyCombination.CONTROL_DOWN));

        MenuItem sairMI = new MenuItem("Sair");
        sairMI.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));

        jogoMenu.getItems().addAll(novoJogoMI, lerObjMI, gravarObj, new SeparatorMenuItem(), sairMI);

        //


        //Accoes do Jogo
        novoJogoMI.setOnAction((e)-> jogoObservavel.opcoesjogo());

        lerObjMI.setOnAction((ActionEvent e) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("./ficheiros/saves"));
            File selectedFile = fileChooser.showOpenDialog(null);
            if(selectedFile != null){
                jogoObservavel.ler(selectedFile);
            }
        });

        gravarObj.setOnAction((ActionEvent e) ->{
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("./ficheiros/saves"));
            File selectedFile = fileChooser.showSaveDialog(null);
            if(selectedFile != null){
                jogoObservavel.guardar(selectedFile);
            }
        });

        sairMI.setOnAction((ActionEvent e) -> {
            Stage janela2 = (Stage) this.getScene().getWindow();

            ButtonType sim = new ButtonType("Sim");
            ButtonType nao = new ButtonType("Nao");

            if(jogoObservavel.getSituacaoAtual() != Situacao.ESCOLHE_JOGO
                    && jogoObservavel.getSituacaoAtual() != Situacao.MENU_INFORMATIVO){
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
                        }

                    }
                });
            }
            jogoObservavel.terminar();
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


                ReplayPane rp = new ReplayPane(jogoObservavel, f);

                Scene replay = new Scene(rp, 600, 600);
                Stage replayWindow = new Stage();
                replayWindow.setTitle("Replay");
                replayWindow.setScene(replay);
                replayWindow.setResizable(false);
                replayWindow.setOnCloseRequest(windowEvent -> replayWindow.close());
                replayWindow.show();

                if(jogoObservavel.temErros()){
                    dialogError();
                    jogoObservavel.setErros(false);
                }
            });
        }

        historicoMenu.getItems().addAll(hist);

        return historicoMenu;
    }

    //private void printList

    private void dialogError(){
        StringBuilder s = new StringBuilder();
        Alert dialogErro = new Alert(Alert.AlertType.WARNING);
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

    private Menu menuAjuda(){
        //Menu Ajuda
        Menu ajudaMenu = new Menu("_Ajuda");

        MenuItem Regras = new MenuItem("Regras do jogo");
        Regras.setAccelerator(new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN));

        MenuItem Minijogos = new MenuItem("Mini jogos");
        Minijogos.setAccelerator(new KeyCodeCombination(KeyCode.M, KeyCombination.CONTROL_DOWN));

        MenuItem PecaEspecial = new MenuItem("Peca Especial");
        PecaEspecial.setAccelerator(new KeyCodeCombination(KeyCode.P, KeyCombination.CONTROL_DOWN));

        ajudaMenu.getItems().addAll(Regras, Minijogos, PecaEspecial);

        //acoes do menu regras
        Regras.setOnAction((ActionEvent e) -> {
            Alert dialogoResultado = new Alert(Alert.AlertType.INFORMATION);
            dialogoResultado.setTitle("Regras");
            dialogoResultado.setHeaderText("Regras");
            dialogoResultado.setContentText("""
                    Apenas se pode jogar 1 peça por jogada.
                    O primeiro jogador a começar é selecionado de forma aleatória.
                    A cada 4 jogadas, o jogador tem a opcao de aceitar participar num mini jogo para obter uma peça especial caso ganhe.
                    Cada jogador Humano, tem 5 creditos que pode usar para desfazer jogadas(ate 5)
                    """);
            dialogoResultado.showAndWait();
        });

        //acoes do menu Minijogos
        Minijogos.setOnAction((ActionEvent e) -> {
            Alert dialogoResultado = new Alert(Alert.AlertType.INFORMATION);
            dialogoResultado.setTitle("Mini jogos");
            dialogoResultado.setHeaderText("Mini jogos");
            dialogoResultado.setContentText("""
                    Contas:
                    Introduza apenas o valor das operacoes aritmeticas. Em caso de divisao, arredondar para o inteiro abaixo.
                    Palavras:
                    Escreva a sequencia de palavras seguidas de acordo como aparecem no ecra, com os mesmos espacos em branco!
                    """);
            dialogoResultado.showAndWait();
        });

        //acoes do menu PecaEspecial
        PecaEspecial.setOnAction((ActionEvent e) -> {
            Alert dialogoResultado = new Alert(Alert.AlertType.INFORMATION);
            dialogoResultado.setTitle("Peça Especial");
            dialogoResultado.setHeaderText("Peça Especial");
            dialogoResultado.setContentText("Ao usar, pode apagar uma coluna de jogadas.");
            dialogoResultado.showAndWait();
        });

        return ajudaMenu;
    }

    private void registarObservador(){
        jogoObservavel.addPropertyChangeListener(PROPRIEDADE_JOGO,
                evt -> atualiza()
        );
    }

    private void atualiza() {
        System.out.println(jogoObservavel.getSituacaoAtual().toString());
        gravarObj.setDisable(jogoObservavel.getSituacaoAtual() ==  Situacao.MENU_INFORMATIVO ||
                jogoObservavel.getSituacaoAtual() ==  Situacao.FIM_JOGO ||
                jogoObservavel.getSituacaoAtual() ==  Situacao.ESCOLHE_JOGO);
    }
}
