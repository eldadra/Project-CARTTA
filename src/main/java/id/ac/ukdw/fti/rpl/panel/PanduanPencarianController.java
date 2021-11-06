package id.ac.ukdw.fti.rpl.panel;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class PanduanPencarianController {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Button buttonHome;

    @FXML
    void goHome(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("goHome.fxml"));
        mainPane.getChildren().setAll(pane);
    }

}
