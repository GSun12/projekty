package main.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import main.MainApp;
import main.model.Czytelnik;
import main.model.DBcon;
import main.model.Ksiazka;
import main.model.Wypozyczenie;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gsun12 on 16.01.2016.
 */
public class BorrowEditController {
    @FXML
    private TableView<Ksiazka> Ksiazka_T;

    @FXML
    private TableColumn<Ksiazka, String> tittleColumn;

    @FXML
    private TableColumn<Ksiazka, String> gatColumn;

    @FXML
    private TableColumn<Ksiazka, String>costColumn;
    @FXML
    private TableColumn<Ksiazka, String> rabatColumn;
    MainApp mainApp;
    Wypozyczenie wypozyczenie;
    private Stage dialogStage;
    private String id_user;
    private boolean okClicked =false;

    @FXML
    public void initialize(){
        tittleColumn.setCellValueFactory(cellData -> cellData.getValue().tittleProperty());
        gatColumn.setCellValueFactory(cellData -> cellData.getValue().gatKsiazkaProperty());
        costColumn.setCellValueFactory(cellData -> cellData.getValue().ceKsiazkaProperty());
        rabatColumn.setCellValueFactory(cellData -> cellData.getValue().raKsiazkaProperty());
    }
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        Ksiazka_T.setItems(mainApp.getKsiazkaData());
    }
    public void setDBindex(String index){this.id_user=index;}
    public boolean isOkClicked() {
        return okClicked;
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    public void setBorrow (Wypozyczenie wy) {
        this.wypozyczenie = wy;

    }
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            String a,b,c,d,e;
            int aa,bb;
            Ksiazka_T.getSelectionModel().getSelectedItem().getIdKsiazka();
            Ksiazka_T.getSelectionModel().getSelectedItem().getTittle();
            SimpleDateFormat simpleDateHere = new SimpleDateFormat("yyyy-MM-dd");


            a= Ksiazka_T.getSelectionModel().getSelectedItem().getIdKsiazka();
//            System.out.println(a);
            b=id_user;
            c=simpleDateHere.format(new Date());
            aa=Integer.parseInt(a);
            bb=Integer.parseInt(b);
            DBcon bi= new DBcon();
            bi.insertWypozycz(bb, aa, c);
            bi.closeConnection();
            okClicked = true;
            dialogStage.close();

        }
    }
    private boolean isInputValid() {
        String errorMessage = "";
        int selectedIndex = Ksiazka_T.getSelectionModel().getSelectedIndex();
        if (selectedIndex < 0) errorMessage += "Nie wybrałeś Książki\n";
        if (errorMessage.length() == 0) {
            return true;
        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Bład");
            alert.setHeaderText("");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
    @FXML
    private void buttonDetails(){
        int selectedIndex = Ksiazka_T.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            String tittle=Ksiazka_T.getSelectionModel().getSelectedItem().getTittle();
            String gat=Ksiazka_T.getSelectionModel().getSelectedItem().getGatKsiazka();
            mainApp.showBookDetailDialog(tittle,gat);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Brak Danych");
            alert.setHeaderText("");
            alert.setContentText("Nie zaznaczono książki do wyświetlenia szczegółów");

            alert.showAndWait();

        }
    }
}
