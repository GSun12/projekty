package main.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import main.MainApp;
import main.model.Author;
import main.model.DBcon;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by gsun12 on 15.01.2016.
 */
public class DetailsViewContoller {

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

    private String tittle;
    private String gatunek;
    private Stage dialogStage;
    private MainApp mainApp;

    private int[] idAutorses =new int[10];
    private List<Author> authors=new LinkedList<>();
    private ObservableList<Author> authorData = FXCollections.observableArrayList();

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    public void getData(String tittle,String gatunek){
        this.tittle=tittle;
        this.gatunek=gatunek;
    }
    @FXML
    public void initialize() {

    }
    public void LoadData(){
        int licznik=0;
        DBcon getDb=new DBcon();
        idAutorses=getDb.selectBookParam(tittle,gatunek);

//        for (int i: idAutorses) {
//            System.out.println(i);
//        }
        while (idAutorses[licznik]!=0) {
            authors = getDb.selectAuthorById(idAutorses[licznik], authors);
            licznik++;
        }
        getDb.closeConnection();
        authorData=FXCollections.observableArrayList(authors);
        Author_T.setItems(authorData);
        firstAuthorNameColumn.setCellValueFactory(cellData -> cellData.getValue().imieAuthorProperty());
        lastAuthorNameColumn.setCellValueFactory(cellData -> cellData.getValue().nazwiskoAuthorProperty());
        bornColumn.setCellValueFactory(cellData -> cellData.getValue().rokAuthorProperty());
        countryColumn.setCellValueFactory(cellData -> cellData.getValue().pochodzenieAuthorProperty());
    }
}
