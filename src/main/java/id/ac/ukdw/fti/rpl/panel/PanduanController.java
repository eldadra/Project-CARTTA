package id.ac.ukdw.fti.rpl.panel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

public class PanduanController implements Initializable{

    @FXML
    private AnchorPane mainPane;

    @FXML
    private ComboBox combo;

    @FXML
    void select(ActionEvent event) throws IOException {
        String s = combo.getSelectionModel().getSelectedItem().toString();
        if(s.equals("Panduan Pencarian")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("panduanPencarian.fxml"));
            mainPane.getChildren().setAll(pane);
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        ObservableList<String> list = FXCollections.observableArrayList("Panduan Pencarian","Panduan Mengubah Tampilan Hasil Cari");
        combo.setItems(list);
    }

}
