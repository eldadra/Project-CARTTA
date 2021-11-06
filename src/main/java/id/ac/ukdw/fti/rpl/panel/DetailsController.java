package id.ac.ukdw.fti.rpl.panel;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class DetailsController {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private Text verseText;

    @FXML
    private Text verse;


    public void show(String verseField,String textArea) {
        verse.setText(verseField);
        verseText.setText(textArea);
    }

    // @Override
    // public void initialize(URL arg0, ResourceBundle arg1) {
    //     // TODO Auto-generated method stub
    //     show(verseField, textArea);
    // }

    
}
