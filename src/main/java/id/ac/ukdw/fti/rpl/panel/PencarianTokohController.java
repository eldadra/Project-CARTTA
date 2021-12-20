package id.ac.ukdw.fti.rpl.panel;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import id.ac.ukdw.fti.rpl.panel.database.Database;
import id.ac.ukdw.fti.rpl.panel.modal.People;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class PencarianTokohController {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private TableView<People> tableView;

    @FXML
    private Button buttonCariTokoh;

    @FXML
    private Button buttonHome;

    @FXML
    private TableColumn<People, String> columnTokoh;

    @FXML
    private TableColumn<People, String> columnAyat;


    @FXML
    private TextField keywordTempat;

    

    @FXML
    void cariTokoh(ActionEvent event) {
        showPeople();
    }

    @FXML
    void goHome(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("goHome.fxml"));
        mainPane.getChildren().setAll(pane);
    }

    public ObservableList<People> getPeopleList() {
        ObservableList<People> peopleList = FXCollections.observableArrayList();

        Database database = new Database();
        Connection conn = database.getConnection();
        String keyword = keywordTempat.getText();
        String query = "SELECT places.hasBeenHere FROM places WHERE places.displayTitle= '" + keyword +"'";
        
        Statement statement;
        ResultSet result;

        try {
            statement = conn.createStatement();
            result = statement.executeQuery(query);
            People dataPeople;
            while (result.next()) {
                String words[] = result.getString("hasBeenHere").split("\\,");
                String newData;
                for(String word:words) {
                    word = word.replaceAll("[^a-zA-Z]","");
                    String uppercase = word.substring(0,1);
                    String sisaKata = word.substring(1);
                    newData = uppercase.toUpperCase() + sisaKata + " ";
                    dataPeople = new People(newData);
                    peopleList.add(dataPeople);
                }  
            }
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        return peopleList;
        
    }

    public void showPeople() {
        ObservableList<People> list = getPeopleList();
        columnTokoh.setCellValueFactory(new PropertyValueFactory<People,String>("name"));
        // columnAyat.setCellValueFactory(new PropertyValueFactory<People,String>("verse"));
        tableView.setItems(list);
    }
}
