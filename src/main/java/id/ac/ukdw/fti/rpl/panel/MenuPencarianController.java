package id.ac.ukdw.fti.rpl.panel;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class MenuPencarianController {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Button buttonCariTokoh;

    @FXML
    private Button buttonCariTempat;

    @FXML
    private Button buttonHome;

    @FXML
    private Button kitabButton;

    @FXML
    void alkitab(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("alkitab.fxml"));
        mainPane.getChildren().setAll(pane);
    }

    @FXML
    void cariTempat(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("pencarianTempat.fxml"));
        mainPane.getChildren().setAll(pane);
    }

    @FXML
    void cariTokoh(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("pencarianTokoh.fxml"));
        mainPane.getChildren().setAll(pane);
    }

    @FXML
    void goHome(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("goHome.fxml"));
        mainPane.getChildren().setAll(pane);
    }

}
