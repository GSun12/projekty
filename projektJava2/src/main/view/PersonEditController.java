package main.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.MainApp;
import main.model.DBcon;
import main.model.Czytelnik;

import java.util.Optional;

public class PersonEditController {

    @FXML
    private TextField F_name;

    @FXML
    private TextField F_surname;

    @FXML
    private TextField F_street;

    @FXML
    private TextField F_city;

    @FXML
    private TextField F_pesel;

    private Stage dialogStage;
    private Czytelnik czytelnik;
    private boolean okClicked = false;
    private String index;
    private String pesel;
    private MainApp mainApp;
    @FXML
    private void initialize() {
    }

    public void setDBindex(String index,String pesel){

        this.index=index;
        this.pesel=pesel;
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    public void setPerson(Czytelnik person) {
        this.czytelnik = person;

        F_name.setText(person.getImie());
        F_surname.setText(person.getNazwisko());
        F_street.setText(person.getUlica());
        F_pesel.setText(person.getPesel());
        F_city.setText(person.getMiasto());
    }


    public boolean isOkClicked() {
        return okClicked;
    }


    @FXML
    private void handleOk() {
        if (isInputValid()) {
            String a,b,c,d,e;
            int in_t=Integer.parseInt(index);
            czytelnik.setImie(F_name.getText());
            czytelnik.setNazwisko(F_surname.getText());
            czytelnik.setUlica(F_street.getText());
            czytelnik.setMiasto(F_city.getText());
            czytelnik.setPesel(F_pesel.getText());

            a=czytelnik.getImie();
            b=czytelnik.getNazwisko();
            c=czytelnik.getPesel();
            d=czytelnik.getUlica();
            e=czytelnik.getMiasto();

            DBcon bi= new DBcon();
            if(in_t==0) {
                bi.insertCzytelnik(a, b, c, d, e);

            }
            else{

                bi.updateCzytelnik(a, b,c, pesel, d, e);

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
        alert_user.setHeaderText("Czy chcesz przerwać dodawanie/edycję czytelnika?");
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
        if (F_street.getText() == null || F_street.getText().length() == 0) {
            errorMessage += "Pole Ulica nie może być puste!\n";
        }

        if (F_pesel.getText() == null || F_pesel.getText().length() == 0) {
            errorMessage += "Pole Pesel nie może być puste!\n";
        } else if(F_pesel.getText().length()<11 || F_pesel.getText().length()>11 ){
            errorMessage+="Pesel jest za krótki lub za długi!\n";
        } else  {

            try {
                String wzor=F_pesel.getText();
                String a=wzor.substring(0,6);
                String b=wzor.substring(6);
                Integer.parseInt(a);
                Integer.parseInt(b);
            } catch (NumberFormatException e) {
                errorMessage += "Niepoprawny pesel!\n";
            }
        }

        if (F_city.getText() == null || F_city.getText().length() == 0) {
            errorMessage += "Pole Miasto nie może być puste!\n";
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


