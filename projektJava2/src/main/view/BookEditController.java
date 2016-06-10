package main.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.MainApp;
import main.model.Author;
import main.model.DBcon;
import main.model.Ksiazka;

import java.awt.print.Book;
import java.util.Optional;

/**
 * Created by gsun12 on 15.01.2016.
 */
public class BookEditController {
    @FXML
    private TableView<Author> Author_T;

    @FXML
    private TableColumn<Author, String> idAutorColumn;

    @FXML
    private TableColumn<Author, String> firstAuthorNameColumn;

    @FXML
    private TableColumn<Author, String> lastAuthorNameColumn;

    @FXML
    private TableColumn<Author, String> bornColumn;

    @FXML
    private TableColumn<Author, String> countryColumn;
    @FXML
    private TextField F_tittle;

    @FXML
    private TextField F_gat;

    @FXML
    private TextField F_cost;

    @FXML
    private TextField F_rabat;

    private Stage bookStage;
    private Ksiazka ksiazka;
    private MainApp mainApp;
    private boolean okClicked =false;
    @FXML
    private void initialize() {
        firstAuthorNameColumn.setCellValueFactory(cellData -> cellData.getValue().imieAuthorProperty());
        lastAuthorNameColumn.setCellValueFactory(cellData -> cellData.getValue().nazwiskoAuthorProperty());
        bornColumn.setCellValueFactory(cellData -> cellData.getValue().rokAuthorProperty());
        countryColumn.setCellValueFactory(cellData -> cellData.getValue().pochodzenieAuthorProperty());
    }
    public void setDialogStage(Stage dialogStage) {
        this.bookStage = dialogStage;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        Author_T.setItems(mainApp.getAuthorData());

    }
    public void setBook(Ksiazka book) {
        this.ksiazka = book;

        F_tittle.setText(ksiazka.getTittle());
        F_gat.setText(ksiazka.getGatKsiazka());
        F_cost.setText(ksiazka.getCeKsiazka());
        F_rabat.setText(ksiazka.getRaKsiazka());

    }
    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {

        if (isInputValid()) {
            String a,b,c,d,e;
            int f;
            float g;
            double h;
            ksiazka.setTitlle(F_tittle.getText());
            ksiazka.setGatKsiazka(F_gat.getText());
            ksiazka.setCeKsiazka(F_cost.getText());
            ksiazka.setRaKsiazka(F_rabat.getText());

            a=Author_T.getSelectionModel().getSelectedItem().getIdAuthor();
            b=ksiazka.getTittle();
            c=ksiazka.getGatKsiazka();
            d=ksiazka.getCeKsiazka();
            e=ksiazka.getRaKsiazka();
            f=Integer.parseInt(a);
            g=Float.parseFloat(d);
            h=Double.parseDouble(e);
            DBcon bi= new DBcon();
            bi.insertKsiazka(f,b,c,g,h);
            bi.closeConnection();
            okClicked = true;
            bookStage.close();

        }
    }
    @FXML
    private void handleCancel() {
        Alert alert_user = new Alert(Alert.AlertType.CONFIRMATION);
        alert_user.setTitle("Uwaga!");
        alert_user.setHeaderText("Czy chcesz przerwać dodawanie nowej książki?");
        alert_user.setContentText("Czy na pewno chcesz kontynuować?");
        ButtonType buttonTypeYes = new ButtonType("Tak");
        ButtonType buttonTypeNo = new ButtonType("Nie", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert_user.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
        Optional<ButtonType> result = alert_user.showAndWait();
        if (result.get() == buttonTypeYes) {
            bookStage.close();
        } else {
            // zamkniecie okna dialogowego

        }
    }

    private boolean isInputValid() {
        String errorMessage = "";
        int selectedIndex = Author_T.getSelectionModel().getSelectedIndex();
        if(selectedIndex <0 )errorMessage += "Nie wybrałeś Autora\n";

        if (F_tittle.getText() == null || F_tittle.getText().length() == 0) {
            errorMessage += "Pole Tytuł nie może być puste!\n";
        }
        if (F_gat.getText() == null || F_gat.getText().length() == 0) {
            errorMessage += "Pole gatunek nie może być puste!\n";
        }

        if (F_cost.getText() == null || F_cost.getText().length() == 0) {
            errorMessage += "Pole Cena nie może być puste!\n";
        if (F_rabat.getText() == null || F_rabat.getText().length() == 0) {
            errorMessage += "Pole Rabat nie może być puste!\n";
        }

        } else  {

            try {
                Float.parseFloat(F_cost.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Niepoprawa cena! podaj np 12.30 \n";
            }
        if (F_rabat.getText() == null || F_rabat.getText().length() == 0) {
            errorMessage += "Pole Rabat nie może być puste!\n";
        }else try {
                    Double.parseDouble(F_rabat.getText());
            } catch (NumberFormatException e) {
                    errorMessage += "Niepoprawny rabat! podaj np 12.30 \n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(bookStage);
            alert.setTitle("Bład");
            alert.setHeaderText("Sprawdź błędne pola");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
