package id.ac.ukdw.fti.rpl.panel;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;


import id.ac.ukdw.fti.rpl.panel.database.Database;
import id.ac.ukdw.fti.rpl.panel.modal.Alkitab;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.TableView;

public class AlkitabController implements Initializable {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private TableView<Alkitab> tableView;

    @FXML
    private Button buttonHome;

    @FXML
    private TableColumn<Alkitab, String> columnKitab;

    @FXML
    private TableColumn<Alkitab, String> columnVerse;

    @FXML
    private TableColumn<Alkitab, String> columnText;

    @FXML
    private TextField filterText;

    @FXML
    private Button viewDetails;

    @FXML
    void viewDetails(ActionEvent event) throws IOException {
        try{
            String verse = tableView.getSelectionModel().getSelectedItem().getVerse().toString();
            String verseText = tableView.getSelectionModel().getSelectedItem().getVerseText().toString();
        
            // AnchorPane pane = FXMLLoader.load(getClass().getResource("details.fxml"));
            // mainPane.getChildren().setAll(pane);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("details.fxml"));
            Parent root = loader.load();

            DetailsController detailsController = loader.getController();
            detailsController.show(verse, verseText);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Details");
            stage.show();
        }
        catch (Exception e) {
            //TODO: handle exception
            Alert dg = new Alert(Alert.AlertType.CONFIRMATION);
            dg.setTitle("Dialog");
            dg.setContentText("Pilih baris yang ingin dilihat terlebih dahulu");
            dg.setHeaderText("PERINGATAN");
            dg.show();
        }
    }

    @FXML
    void goHome(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("goHome.fxml"));
        mainPane.getChildren().setAll(pane);
    }

    ObservableList<Alkitab> listKitab = FXCollections.observableArrayList();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        Database database = new Database();
        Connection conn = database.getConnection();

        String query =  "SELECT books.bookName, verses.osisRef, verses.verseText FROM books INNER JOIN verses ON books.osisName = verses.book ORDER BY verses.verseID";

        try {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);
            Alkitab dataAlkitab;

            while (result.next()) {
                dataAlkitab = new Alkitab(result.getString("bookName"), result.getString("osisRef"), result.getString("verseText"));
                listKitab.add(dataAlkitab);
            }
            columnKitab.setCellValueFactory(new PropertyValueFactory<Alkitab, String>("bookName"));
            columnVerse.setCellValueFactory(new PropertyValueFactory<Alkitab, String>("verse"));
            columnText.setCellValueFactory(new PropertyValueFactory<Alkitab, String>("verseText"));
            tableView.setItems(listKitab);

            FilteredList<Alkitab> filteredData = new FilteredList<>(listKitab, b -> true);
            filterText.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(alkitabSearch -> {
                    if(newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }

                    String searchKeyword = newValue.toLowerCase();

                    if(alkitabSearch.getVerse().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true; // menemukan kecocokan
                    }
                    else if (alkitabSearch.getBookName().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    }
                    else {
                        return false;
                    }
                });
            });

            SortedList<Alkitab> sortedData = new SortedList <>(filteredData);
            sortedData.comparatorProperty().bind(tableView.comparatorProperty());

            tableView.setItems(sortedData);
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }


}
