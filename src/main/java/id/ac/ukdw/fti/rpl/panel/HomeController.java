package id.ac.ukdw.fti.rpl.panel;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class HomeController {

    @FXML
    private AnchorPane sidePane;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private ImageView bibleLogo;

    @FXML
    private Button buttonPencarian;

    @FXML
    private Button buttonPanduan;

    @FXML
    void panduan(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("panduan.fxml"));
        mainPane.getChildren().setAll(pane);
    }

    @FXML
    void pencarian(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("MenuPencarian.fxml"));
        mainPane.getChildren().setAll(pane);
    }

}
