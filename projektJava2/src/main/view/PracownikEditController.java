package main.view;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.MainApp;
import main.model.DBcon;
import main.model.Pracownik;

import java.util.Optional;

/**
 * Created by gsun12 on 19.05.2016.
 */
public class PracownikEditController {

    @FXML
    private TextField F_name;

    @FXML
    private TextField F_surname;

    @FXML
    private TextField F_stuff;

    @FXML
    private TextField F_idPrac;

    private Stage dialogStage;
    private Pracownik pracownik;
    private boolean okClicked = false;
    private int index;
    private String nazwisko;
    private int nr_prac;
    private MainApp mainApp;
    @FXML
    private void initialize() {
    }

    public void setDBdata(int index,String nazwisko,String nr_prac){
        this.nazwisko=nazwisko;
        this.nr_prac=Integer.parseInt(nr_prac);
        this.index=index;
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    public void setPracownik(Pracownik pracownik) {
        this.pracownik= pracownik;

        F_name.setText(pracownik.getImiePracownik());
        F_surname.setText(pracownik.getNazwiskoPracownik());
        F_stuff.setText(pracownik.getStanowiskoPracownik());
        F_idPrac.setText(pracownik.getNrPracownika());

    }


    public boolean isOkClicked() {
        return okClicked;
    }


    @FXML
    private void handleOk() {
        if (isInputValid()) {
            String imie,nowe_nazwisko,stanowisko;
            int in_t=index;
            pracownik.setImiePracownik(F_name.getText());
            pracownik.setNazwiskoPracownik(F_surname.getText());
            pracownik.setStanowiskoPracownik(F_stuff.getText());
            pracownik.setNrPracownika(F_idPrac.getText());

            imie=pracownik.getImiePracownik();
            nowe_nazwisko=pracownik.getNazwiskoPracownik();
            stanowisko=pracownik.getStanowiskoPracownik();
            int nowy_nrprac=Integer.parseInt(pracownik.getNrPracownika());

            DBcon bi= new DBcon();
            if(in_t==0) {
                bi.insertPracownik(imie, nowe_nazwisko, stanowisko, nowy_nrprac);

            }
            else{

                bi.updatePracownik(imie, nowe_nazwisko,nazwisko,stanowisko, nowy_nrprac, nr_prac);

            }
            bi.closeConnection();
            okClicked = true;
            dialogStage.close();

        }
    }


    @FXML
    private void handleCancel() {
        Alert alert_user =new Alert(Alert.AlertType.CONFIRMATION);
        alert_user.setTitle("Uwaga!");
        alert_user.setHeaderText("Czy chcesz przerwać dodawanie/edycję pracownika?");
        alert_user.setContentText("Czy na pewno chcesz kontynuować?");
        ButtonType buttonTypeYes = new ButtonType("Tak");
        ButtonType buttonTypeNo = new ButtonType("Nie", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert_user.getButtonTypes().setAll(buttonTypeYes,buttonTypeNo);
        Optional<ButtonType> result = alert_user.showAndWait();
        if (result.get() == buttonTypeYes){
            dialogStage.close();
        } else {
            // zamkniecie okna dialogowego

        }

    }


    private boolean isInputValid() {
        String errorMessage = "";

        if (F_name.getText() == null || F_name.getText().length() == 0) {
            errorMessage += "Pole Imię nie może być puste!\n";
        }
        if (F_surname.getText() == null || F_surname.getText().length() == 0) {
            errorMessage += "Pole Nazwisko nie może być puste!\n";
        }
        if (F_stuff.getText() == null || F_stuff.getText().length() == 0) {
            errorMessage += "Pole Stanowisko nie może być puste!\n";
        }

        if (F_idPrac.getText() == null || F_idPrac.getText().length() == 0) {
            errorMessage += "Pole Numer pracownika nie może być puste!\n";
        }
        else if(F_idPrac.getText().length()<4 || F_idPrac.getText().length()>4 ){
                errorMessage+="Numer pracownika powinien posiadać cztery cyfry\n";
        } else  {

            try {
                String wzor=F_idPrac.getText();
                Integer.parseInt(wzor);

            } catch (NumberFormatException e) {
                errorMessage += "Niepoprawny numer pracownika!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Bład");
            alert.setHeaderText("Sprawdź wybrane pola");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
