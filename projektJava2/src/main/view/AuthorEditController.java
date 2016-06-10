package main.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.model.Author;
import main.model.Czytelnik;
import main.model.DBcon;
import sun.util.calendar.BaseCalendar;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
// TODO: 19.05.2016 edit autora  
/**
 * Created by gsun12 on 14.01.2016.
 */
public class AuthorEditController {
    @FXML
    private TextField F_nameAuthor;

    @FXML
    private TextField F_surnameAuthor;

    @FXML
    private TextField F_birthAuthor;

    @FXML
    private TextField F_poAuthor;

    private Stage dialogAuthor;
    private Author author;
    private boolean okClicked = false;



    @FXML
    private void initialize() {
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogAuthor = dialogStage;
    }
    public void setAuthor(Author author) {
        this.author = author;

        F_nameAuthor.setText(author.getImieAuthor());
        F_surnameAuthor.setText(author.getNazwiskoAuthor());
        F_birthAuthor.setText(author.getRokAuthor());
        F_poAuthor.setText(author.getPochodzenieAuthor());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            String a,b,c,d;
            int f;
            author.setImieAuthor(F_nameAuthor.getText());
            author.setNazwiskoAuthor((F_surnameAuthor.getText()));
            author.setRokAuthor(F_birthAuthor.getText());
            author.setPochodzenieAuthor(F_poAuthor.getText());

            a=author.getImieAuthor();
            b=author.getNazwiskoAuthor();
            c=author.getRokAuthor();
            d=author.getPochodzenieAuthor();
            f=Integer.parseInt(c);
            DBcon bi= new DBcon();
            bi.insertAuthor(a, b, f, d);
            bi.closeConnection();
            okClicked = true;
            dialogAuthor.close();

        }
    }


    @FXML
    private void handleCancel() {
        Alert alert_user = new Alert(Alert.AlertType.CONFIRMATION);
        alert_user.setTitle("Uwaga!");
        alert_user.setHeaderText("Czy chcesz przerwać dodawanie autora?");
        alert_user.setContentText("Czy na pewno chcesz kontynuować?");
        ButtonType buttonTypeYes = new ButtonType("Tak");
        ButtonType buttonTypeNo = new ButtonType("Nie", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert_user.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
        Optional<ButtonType> result = alert_user.showAndWait();
        if (result.get() == buttonTypeYes) {
            dialogAuthor.close();
        } else {
            // zamkniecie okna dialogowego

        }
    }
        private boolean isInputValid() {
            String errorMessage = "";
         
            if (F_nameAuthor.getText() == null || F_nameAuthor.getText().length() == 0) {
                errorMessage += "Pole Imię nie może być puste!\n";
            }
            if (F_surnameAuthor.getText() == null || F_surnameAuthor.getText().length() == 0) {
                errorMessage += "Pole Nazwisko nie może być puste!\n";
            }
            if (F_poAuthor.getText() == null || F_poAuthor.getText().length() == 0) {
                errorMessage += "Pole Pochodznie nie może być puste!\n";
            }

            if (F_birthAuthor.getText() == null || F_birthAuthor.getText().length() == 0) {
                errorMessage += "Pole Data urodzenia nie może być puste!\n";
            }
            else if(F_birthAuthor.getText().length()>4)
                errorMessage+="Niepoprawny rok urodzenia!\n";

            else  {

                try {
                    Integer.parseInt(F_birthAuthor.getText());
                } catch (NumberFormatException e) {
                    errorMessage += "Niepoprawny rok urodzenia!\n";
                }
            }
            if (errorMessage.length() == 0) {
                return true;
            } else {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(dialogAuthor);
                alert.setTitle("Bład");
                alert.setHeaderText("Sprawdź pola w których pojawił się bład");
                alert.setContentText(errorMessage);

                alert.showAndWait();

                return false;
            }
        }
}
