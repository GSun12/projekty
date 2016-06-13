package main;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.model.*;
import main.view.*;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private List<Czytelnik> czy=new LinkedList<>();
    private List<Author> aut=new LinkedList<>();
    private List<Ksiazka>  ks=new LinkedList<>();
    private List<Wypozyczenie>  wy=new LinkedList<>();
    private List<Pracownik> prac =new LinkedList<>();
    private ObservableList<Czytelnik> personData = FXCollections.observableArrayList();
    private ObservableList<Author> authorData = FXCollections.observableArrayList();
    private ObservableList<Ksiazka> ksiazkaData = FXCollections.observableArrayList();
    private ObservableList<Pracownik> pracownikData =FXCollections.observableArrayList();

    public void getCzy(){
        DBcon g=new DBcon();
        g.createTables();
        czy=g.selectCzytelnicy();
        ks=g.selectKsiazki();
        aut=g.selectAuthor();
        prac=g.selectPracownik();
        personData=FXCollections.observableArrayList(czy);
        ksiazkaData=FXCollections.observableArrayList(ks);
        authorData=FXCollections.observableArrayList(aut);
        pracownikData=FXCollections.observableList(prac);

        g.closeConnection();
    }

    public MainApp() {
        getCzy();

    }


    public ObservableList<Czytelnik> getPersonData() {
        return personData;
    }
    public ObservableList<Author> getAuthorData(){return authorData;}
    public ObservableList<Ksiazka> getKsiazkaData(){return ksiazkaData;}
    public ObservableList<Pracownik> getPracownikData(){return  pracownikData;}
   // public ObservableList<Wypozyczenie> getWypozyczenieData(){return wypozyczenieData;}


    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Biblioteka");

        initRootLayout();

//        showMainView ();
    }


    public void initRootLayout() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/BarView.fxml"));
            rootLayout = (BorderPane) loader.load();


            Scene scene = new Scene(rootLayout);

            primaryStage.setScene(scene);
            primaryStage.show();

            MainViewController controller=loader.getController();
           controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean showPersonEditDialog(Czytelnik czytelnik,String dialogName,String pesel,String index) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/PersonEdit.fxml"));
            AnchorPane page = (AnchorPane) loader.load();


            Stage dialogStage = new Stage();
            dialogStage.setTitle(dialogName);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);


            PersonEditController controller = loader.getController();

            controller.setDialogStage(dialogStage);

            controller.setDBindex(index,pesel);

            controller.setPerson(czytelnik);



            dialogStage.showAndWait();



            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean showAuthorEditDialog(Author author){
        try {
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("view/AuthorEdit.fxml"));
            AnchorPane pane=(AnchorPane)loader.load();

            Stage dialogAuthor = new Stage();
            dialogAuthor.setTitle("Nowy Autor");
            dialogAuthor.initModality(Modality.WINDOW_MODAL);
            dialogAuthor.initOwner(primaryStage);
            Scene authorScene = new Scene(pane);
            dialogAuthor.setScene(authorScene);

            AuthorEditController controller= loader.getController();
            controller.setDialogStage(dialogAuthor);
            controller.setAuthor(author);
            dialogAuthor.showAndWait();

            return controller.isOkClicked();
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }

    }
    public boolean showBookEditDialog(Ksiazka ksiazka){
        try {
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("view/BookEdit.fxml"));
            AnchorPane pane=(AnchorPane)loader.load();

            Stage dialogBook = new Stage();
            dialogBook.setTitle("Nowa Książka");
            dialogBook.initModality(Modality.WINDOW_MODAL);
            dialogBook.initOwner(primaryStage);
            Scene bookScene = new Scene(pane);
            dialogBook.setScene(bookScene);

            BookEditController controller= loader.getController();
            controller.setDialogStage(dialogBook);
            controller.setBook(ksiazka);
            controller.setMainApp(this);
            dialogBook.showAndWait();

            return controller.isOkClicked();
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }

    }
    public boolean showPersonBorrow(Wypozyczenie wypozyczenie, String id) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/BorrowView.fxml"));
            AnchorPane page = (AnchorPane) loader.load();


            Stage pBorrowStage = new Stage();
            pBorrowStage.setTitle("Wypożyczenia");
            pBorrowStage.initModality(Modality.WINDOW_MODAL);
            pBorrowStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            pBorrowStage.setScene(scene);


            BorrowViewController controller = loader.getController();

            controller.setDialogStage(pBorrowStage);
            controller.setDBindex(id);
            controller.setBorrow(wypozyczenie);
            controller.setMainApp(this);
            pBorrowStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean showBorrow(Wypozyczenie wyp,String id) {
        try {
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("view/BorrowEdit.fxml"));
            AnchorPane pane=(AnchorPane)loader.load();

            Stage dialogShowBorrow = new Stage();
            dialogShowBorrow.setTitle("Nowe Wypożyczenie");
            dialogShowBorrow.initModality(Modality.WINDOW_MODAL);
            dialogShowBorrow.initOwner(primaryStage);
            Scene ShowBorrow = new Scene(pane);
            dialogShowBorrow.setScene(ShowBorrow);

            BorrowEditController controller = loader.getController();
            controller.setDialogStage(dialogShowBorrow);
            controller.setBorrow(wyp);
            controller.setDBindex(id);
            controller.setMainApp(this);
            dialogShowBorrow.showAndWait();

            return controller.isOkClicked();



        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void showBookDetailDialog(String tittle,String gatunek){
        try {
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("view/DetailsView.fxml"));
            AnchorPane pane=(AnchorPane)loader.load();

            Stage dialogBook = new Stage();
            dialogBook.setTitle("Autorzy");
            dialogBook.initModality(Modality.WINDOW_MODAL);
            dialogBook.initOwner(primaryStage);
            Scene bookDetailScene = new Scene(pane);
            dialogBook.setScene(bookDetailScene);

            DetailsViewContoller controller= loader.getController();
            controller.setDialogStage(dialogBook);
            controller.setMainApp(this);
            controller.getData(tittle,gatunek);
            controller.LoadData();
            dialogBook.showAndWait();

        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public boolean showPracownikEditDialog(Pracownik pracownik,String Dialog_name,String nazwisko,String nr_prac,int index) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/PracownikEdit.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();

            Stage dialogPracownik = new Stage();
            dialogPracownik.setTitle(Dialog_name);
            dialogPracownik.initModality(Modality.WINDOW_MODAL);
            dialogPracownik.initOwner(primaryStage);
            Scene pracownikScene = new Scene(pane);
            dialogPracownik.setScene(pracownikScene);

            PracownikEditController controller = loader.getController();
            controller.setDialogStage(dialogPracownik);
            controller.setPracownik(pracownik);
            controller.setDBdata(index,nazwisko,nr_prac);
            dialogPracownik.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }


    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
