package id.ac.ukdw.fti.rpl.panel;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import id.ac.ukdw.fti.rpl.panel.database.Database;
import id.ac.ukdw.fti.rpl.panel.modal.Places;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class PencarianTempatController implements Initializable {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Button buttonHome;

    @FXML
    private TableView<Places> tableView;

    @FXML
    private TableColumn<Places, String> columnTempat;

    @FXML
    private TableColumn<Places, String> columnTokoh;

    @FXML
    private TextField keywordTokoh;

    @FXML
    void goHome(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("goHome.fxml"));
        mainPane.getChildren().setAll(pane);
    }

    ObservableList<Places> listPlaces = FXCollections.observableArrayList();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        Database database = new Database();
        Connection conn = database.getConnection();

        String query =  "SELECT places.displayTitle, places.hasBeenHere FROM places WHERE places.hasBeenHere IS NOT NULL ";

        try {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);
            Places place;
            while (result.next()) {
                String words[] = result.getString("hasBeenHere").split("\\,");
                String newData;

                for(String word:words) {
                    word = word.replaceAll("[^a-zA-Z]","");
                    String uppercase = word.substring(0,1);
                    String sisaKata = word.substring(1);
                    newData = uppercase.toUpperCase() + sisaKata;
                    place = new Places(result.getString("displayTitle"), newData);
                    listPlaces.add(place);  
                }
                
            }
            columnTempat.setCellValueFactory(new PropertyValueFactory<Places,String>("name"));
            columnTokoh.setCellValueFactory(new PropertyValueFactory<Places,String>("people"));
            tableView.setItems(listPlaces);
            
            FilteredList<Places> filteredData = new FilteredList<>(listPlaces, b -> true);
            keywordTokoh.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(alkitabSearch -> {
                    if(newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }

                    String searchKeyword = newValue.toLowerCase();

                    if(alkitabSearch.getName().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true; // menemukan kecocokan
                    }
                    else if (alkitabSearch.getPeople().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    }
                    else {
                        return false;
                    }
                });
            });

            SortedList<Places> sortedData = new SortedList <>(filteredData);
            sortedData.comparatorProperty().bind(tableView.comparatorProperty());

            tableView.setItems(sortedData);
        
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
