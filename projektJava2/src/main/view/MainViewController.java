package main.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import main.MainApp;
import main.model.*;
import org.sqlite.core.DB;
import main.model.deleteFile;

import javax.print.attribute.standard.DialogTypeSelection;
import java.lang.annotation.Native;
import java.util.Optional;

public class MainViewController{
    @FXML
    private TableView<Czytelnik> Czytelnik_T;

    @FXML
    private TableColumn<Czytelnik, String> idColumn;

    @FXML
    private TableColumn<Czytelnik, String> firstNameColumn;

    @FXML
    private TableColumn<Czytelnik, String> lastNameColumn;

    @FXML
    private TableColumn<Czytelnik, String> streetColumn;

    @FXML
    private TableColumn<Czytelnik, String> cityColumn;

    @FXML
    private TableColumn<Czytelnik, String> peselColumn;

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
    private TableView<Ksiazka> Ksiazka_T;

    @FXML
    private TableColumn<Ksiazka, String> tittleColumn;

    @FXML
    private TableColumn<Ksiazka, String> gatColumn;

    @FXML
    private TableColumn<Ksiazka, String>costColumn;
    @FXML
    private TableColumn<Ksiazka, String> rabatColumn;
    @FXML
    private TableView<Pracownik> Pracownik_T;

    @FXML
    private TableColumn<Pracownik, String> firstPracownikNameColumn;

    @FXML
    private TableColumn<Pracownik, String> lastPracownikNameColumn;

    @FXML
    private TableColumn<Pracownik, String> staffColumn;

    @FXML
    private TableColumn<Pracownik, String> idetifyColumn;


    public deleteFile delete;
    public MainApp mainApp;
    AuthorViewController authorViewController;
    public MainViewController() {
    }


    @FXML
    public void initialize() {

        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().imieProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().nazwiskoProperty());
        streetColumn.setCellValueFactory(cellData -> cellData.getValue().ulicaProperty());
        cityColumn.setCellValueFactory(cellData -> cellData.getValue().miastoProperty());
        peselColumn.setCellValueFactory(cellData -> cellData.getValue().peselProperty());

        tittleColumn.setCellValueFactory(cellData -> cellData.getValue().tittleProperty());
        gatColumn.setCellValueFactory(cellData -> cellData.getValue().gatKsiazkaProperty());
        costColumn.setCellValueFactory(cellData -> cellData.getValue().ceKsiazkaProperty());
        rabatColumn.setCellValueFactory(cellData -> cellData.getValue().raKsiazkaProperty());

        firstAuthorNameColumn.setCellValueFactory(cellData -> cellData.getValue().imieAuthorProperty());
        lastAuthorNameColumn.setCellValueFactory(cellData -> cellData.getValue().nazwiskoAuthorProperty());
        bornColumn.setCellValueFactory(cellData -> cellData.getValue().rokAuthorProperty());
        countryColumn.setCellValueFactory(cellData -> cellData.getValue().pochodzenieAuthorProperty());

        firstPracownikNameColumn.setCellValueFactory(cellData->cellData.getValue().imiePracownikProperty());
        lastPracownikNameColumn.setCellValueFactory(cellData-> cellData.getValue().nazwiskoPracownikProperty());
        staffColumn.setCellValueFactory(cellData-> cellData.getValue().StanowiskoPracownikProperty());
        idetifyColumn.setCellValueFactory(cellData->cellData.getValue().NrPracownikaProperty());

    }
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        Czytelnik_T.setItems(mainApp.getPersonData());
        Author_T.setItems(mainApp.getAuthorData());
        Ksiazka_T.setItems(mainApp.getKsiazkaData());
        Pracownik_T.setItems(mainApp.getPracownikData());

    }
    public void delCellData(){

        Czytelnik_T.getItems().clear();
        Author_T.getItems().clear();
        Ksiazka_T.getItems().clear();
    }

    @FXML
    private void buttonDeletePerson() {
        int selectedIndex = Czytelnik_T.getSelectionModel().getSelectedIndex();


        if (selectedIndex >= 0) {
            String pesel=Czytelnik_T.getSelectionModel().getSelectedItem().getPesel();
            Alert alert_user =new Alert(Alert.AlertType.CONFIRMATION);
            alert_user.setTitle("Uwaga!");
            alert_user.setHeaderText("Zmiany mogą być nieodwracalne");
            alert_user.setContentText("Czy chcesz kontynuować?");
            ButtonType buttonTypeYes = new ButtonType("Tak");
            ButtonType buttonTypeNo = new ButtonType("Nie", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert_user.getButtonTypes().setAll(buttonTypeYes,buttonTypeNo);
            Optional<ButtonType> result = alert_user.showAndWait();
            if (result.get() == buttonTypeYes){
                DBcon del=new DBcon();
                del.deleteCzytelnik(pesel);
                del.closeConnection();
                Czytelnik_T.getItems().remove(selectedIndex);
            } else {
                // zamkniecie okna dialogowego
            }

        } else {
            // Nic nie wybrane
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Brak danych");
            alert.setHeaderText("Nie zaznaczono czytelnika do usunięcia");
            alert.setContentText("Zaznacz czytelnika i spróbuj ponownie");

            alert.showAndWait();
        }
    }
    @FXML
    private void buttonNewPerson() {
        Czytelnik tempPerson = new Czytelnik();
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson,"Nowy czytelnik","0","0");
        if (okClicked) {
            Alert confirm=new Alert(Alert.AlertType.INFORMATION);
            confirm.initOwner(mainApp.getPrimaryStage());
            confirm.setTitle("Udało się!");
            confirm.setHeaderText("");
            confirm.setContentText("Pomyślnie dodano nowego czytelnika");
            confirm.showAndWait();
            mainApp.getPersonData().add(tempPerson);
            mainApp.getCzy();
            Czytelnik_T.setItems(mainApp.getPersonData());
        }
    }
    @FXML
    private void buttonEditPerson() {
        Czytelnik selectedPerson = Czytelnik_T.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            String selectedIndex =Czytelnik_T.getSelectionModel().getSelectedItem().getPesel();
            String pom=Integer.toString(Czytelnik_T.getSelectionModel().getSelectedIndex());
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson,"Edytuj czytelnika",selectedIndex,pom);

            if (okClicked) {
                Alert confirm=new Alert(Alert.AlertType.INFORMATION);
                confirm.initOwner(mainApp.getPrimaryStage());
                confirm.setTitle("Udało się!");
                confirm.setHeaderText("");
                confirm.setContentText("Pomyślnie zapisano zmiany");
                confirm.showAndWait();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Uwaga!");
            alert.setHeaderText("Nie został wybrany czytelnik do edycji");
            alert.setContentText("Proszę zaznacz czytelnika, którego chcesz edytować");

            alert.showAndWait();
        }
    }
    @FXML
    private void buttonBorrow(){
        Wypozyczenie wypozyczenie =new Wypozyczenie();
        Czytelnik selectedPerson = Czytelnik_T.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            String id =Czytelnik_T.getSelectionModel().getSelectedItem().getId();
            boolean okClicked = mainApp.showPersonBorrow(wypozyczenie,id);
            if (okClicked) {

            }

        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Uwaga!");
            alert.setHeaderText("Nie został wybrany czytelnik do wypożyczenia");
            alert.setContentText("Proszę zaznacz czytelnika, któremu chcesz wypożyczyć książkę");

            alert.showAndWait();
        }
    }

    @FXML
    private void buttonNewAuthor(){
        Author tempAuthor = new Author();
        boolean okClicked = mainApp.showAuthorEditDialog(tempAuthor);
        if (okClicked) {
            Alert confirm=new Alert(Alert.AlertType.INFORMATION);
            confirm.initOwner(mainApp.getPrimaryStage());
            confirm.setTitle("Udało się!");
            confirm.setHeaderText("");
            confirm.setContentText("Pomyślnie dodano nowego autora");
            confirm.showAndWait();
            mainApp.getAuthorData().add(tempAuthor);
            mainApp.getCzy();
            Author_T.setItems(mainApp.getAuthorData());
        }

    }
    @FXML
    private void buttonDeleteAuthor() {
        int selectedIndex = Author_T.getSelectionModel().getSelectedIndex();


        if (selectedIndex >= 0) {
            String imie=Author_T.getSelectionModel().getSelectedItem().getImieAuthor();
            String nazwisko=Author_T.getSelectionModel().getSelectedItem().getNazwiskoAuthor();
            Alert alert_user =new Alert(Alert.AlertType.CONFIRMATION);
            alert_user.setTitle("Uwaga!");
            alert_user.setHeaderText("Zmiany mogą być nieodwracalne");
            alert_user.setContentText("Czy chcesz kontynuować?");
            ButtonType buttonTypeYes = new ButtonType("Tak");
            ButtonType buttonTypeNo = new ButtonType("Nie", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert_user.getButtonTypes().setAll(buttonTypeYes,buttonTypeNo);
            Optional<ButtonType> result = alert_user.showAndWait();
            if (result.get() == buttonTypeYes){
                DBcon del=new DBcon();
                del.deleteAuthor(nazwisko,imie);
                del.closeConnection();
                Author_T.getItems().remove(selectedIndex);
            } else {
                // zamkniecie okna dialogowego
            }

        } else {
            // Nic nie wybrane
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Brak danych");
            alert.setHeaderText("Nie zaznaczono autora do usunięcia");
            alert.setContentText("Zaznacz autora i spróbuj ponownie");

            alert.showAndWait();
        }
    }
    @FXML
    private void buttonNewBook() {
        Ksiazka tempKsiazka = new Ksiazka();
        boolean okClicked = mainApp.showBookEditDialog(tempKsiazka);
        if(okClicked)
            mainApp.getKsiazkaData().add(tempKsiazka);
            mainApp.getCzy();
            Ksiazka_T.setItems(mainApp.getKsiazkaData());

    }
    @FXML
    private void buttonDeleteBook() {
        int selectedIndex = Ksiazka_T.getSelectionModel().getSelectedIndex();


        if (selectedIndex >= 0) {
            String tittle=Ksiazka_T.getSelectionModel().getSelectedItem().getTittle();
            String gat=Ksiazka_T.getSelectionModel().getSelectedItem().getGatKsiazka();
            Alert alert_user =new Alert(Alert.AlertType.CONFIRMATION);
            alert_user.setTitle("Uwaga!");
            alert_user.setHeaderText("Zmiany mogą być nieodwracalne");
            alert_user.setContentText("Czy chcesz kontynuować?");
            ButtonType buttonTypeYes = new ButtonType("Tak");
            ButtonType buttonTypeNo = new ButtonType("Nie", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert_user.getButtonTypes().setAll(buttonTypeYes,buttonTypeNo);
            Optional<ButtonType> result = alert_user.showAndWait();
            if (result.get() == buttonTypeYes){
                DBcon del=new DBcon();
                del.deleteBook(tittle,gat);
                del.closeConnection();
                Ksiazka_T.getItems().remove(selectedIndex);
            } else {
                // zamkniecie okna dialogowego
            }

        } else {
            // Nic nie wybrane
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Brak danych");
            alert.setHeaderText("Nie zaznaczono książki do usunięcia");
            alert.setContentText("Zaznacz książkę i spróbuj ponownie");

            alert.showAndWait();
        }
    }
    @FXML
    private void buttonDetailBook() {
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
    @FXML
    private void buttonAddNextAuthor() {
        Ksiazka selected = Ksiazka_T.getSelectionModel().getSelectedItem();
        if (selected != null) {
            boolean okClicked = mainApp.showBookEditDialog(selected);
        } else {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("");
            alert.setHeaderText("Nie zaznaczono żadnej książki");
            alert.showAndWait();
        }
    }
    @FXML
    private void buttonNewPracownik() {
        Pracownik tempPracownik = new Pracownik();
        boolean okClicked = mainApp.showPracownikEditDialog(tempPracownik, "Dodawanie Pracownika","0","0",0);
        if (okClicked) {
            Alert confirm = new Alert(Alert.AlertType.INFORMATION);
            confirm.initOwner(mainApp.getPrimaryStage());
            confirm.setTitle("Udało się!");
            confirm.setHeaderText("");
            confirm.setContentText("Pomyślnie dodano nowego pracownika");
            confirm.showAndWait();
            mainApp.getPracownikData().add(tempPracownik);
            mainApp.getCzy();
            Pracownik_T.setItems(mainApp.getPracownikData());
        }
    }
//    @FXML
//    private void buttonEditPracownik(){}
//    @FXML
//    private void buttonNewPracownik(){}

    @FXML
    private void buttonEditPracownik(){
        Pracownik selectedPracownik = Pracownik_T.getSelectionModel().getSelectedItem();
        int selectedid=Pracownik_T.getSelectionModel().getSelectedIndex();
        if (selectedPracownik != null) {
            String selectedNazwisko =Pracownik_T.getSelectionModel().getSelectedItem().getNazwiskoPracownik();
            String selectedNrPrac=Pracownik_T.getSelectionModel().getSelectedItem().getNrPracownika();
            boolean okClicked = mainApp.showPracownikEditDialog(selectedPracownik,"Edytuj Pracownika",selectedNazwisko,selectedNrPrac,selectedid);

            if (okClicked) {
                Alert confirm=new Alert(Alert.AlertType.INFORMATION);
                confirm.initOwner(mainApp.getPrimaryStage());
                confirm.setTitle("Udało się!");
                confirm.setHeaderText("");
                confirm.setContentText("Pomyślnie zapisano zmiany");
                confirm.showAndWait();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Uwaga!");
            alert.setHeaderText("Nie został wybrany czytelnik do edycji");
            alert.setContentText("Proszę zaznacz pracownika, którego chcesz edytować");

            alert.showAndWait();
        }
    }
    @FXML
    private void buttondeletePracownik(){
        int selectedIndex = Pracownik_T.getSelectionModel().getSelectedIndex();


        if (selectedIndex >= 0) {
            String nazwisko=Pracownik_T.getSelectionModel().getSelectedItem().getNazwiskoPracownik();
            String nr_prac=Pracownik_T.getSelectionModel().getSelectedItem().getNrPracownika();
            Alert alert_user =new Alert(Alert.AlertType.CONFIRMATION);
            alert_user.setTitle("Uwaga!");
            alert_user.setHeaderText("Zmiany mogą być nieodwracalne");
            alert_user.setContentText("Czy chcesz kontynuować?");
            ButtonType buttonTypeYes = new ButtonType("Tak");
            ButtonType buttonTypeNo = new ButtonType("Nie", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert_user.getButtonTypes().setAll(buttonTypeYes,buttonTypeNo);
            Optional<ButtonType> result = alert_user.showAndWait();
            if (result.get() == buttonTypeYes){
                DBcon del=new DBcon();
                del.deletePracownik(nazwisko,nr_prac);
                del.closeConnection();
                Pracownik_T.getItems().remove(selectedIndex);
            } else {
                // zamkniecie okna dialogowego
            }

        } else {
            // Nic nie wybrane
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Brak danych");
            alert.setHeaderText("Nie zaznaczono autora do usunięcia");
            alert.setContentText("Zaznacz pracownika i spróbuj ponownie");

            alert.showAndWait();
        }
    }


    @FXML
    private void menuCloseDialog(){
        System.exit(0);
    }
    @FXML
    private void menuDropDataBase(){
        DBcon d=new DBcon();
        d.dropTable();
        d.createTables();
        d.closeConnection();
        delCellData();

    }
    @FXML
    private void menuAbout(){
        Alert about =new Alert(Alert.AlertType.INFORMATION);
        about.initOwner(mainApp.getPrimaryStage());
        about.setTitle("O programie");
        about.setHeaderText("");
        about.setContentText("Program napisany na zaliczenie z przedmiotu\n Programowanie Obiektowe Java\n Szymon Sędek");
        about.showAndWait();
    }
}