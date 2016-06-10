package main.view;

/**
 * Created by gsun12 on 13.01.2016.
 */


        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.fxml.FXML;
        import javafx.scene.control.*;
        import javafx.stage.Stage;
        import main.MainApp;
        import main.model.Czytelnik;
        import main.model.DBcon;
        import main.model.Ksiazka;
        import main.model.Wypozyczenie;

        import java.util.LinkedList;
        import java.util.List;
        import java.util.Optional;

public class BorrowViewController {

    @FXML
    private TableView<Wypozyczenie> Borrow_T;

    @FXML
    private TableColumn<Wypozyczenie, String> TitleColumn;

    @FXML
    private TableColumn<Wypozyczenie, String> DateColumn;

    private List<Ksiazka> ksBorrow=new LinkedList<>();



    private Stage dialogStage;
    private Czytelnik czytelnik;
    private Wypozyczenie wypozyczenie;
    private boolean okClicked = false;
    private String id;
    private MainApp mainApp;

    @FXML
    private void initialize() {
        TitleColumn.setCellValueFactory(cellData -> cellData.getValue().tittleBProperty());
        DateColumn.setCellValueFactory(cellData ->cellData.getValue().data_wypProperty());
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    public boolean isOkClicked() {
        return okClicked;
    }
    public void setDBindex(String index){this.id=index;}

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        getDbBorrowData();

    }
    public void getDbBorrowData(){
         List<Wypozyczenie>  wy=new LinkedList<>();
         ObservableList<Ksiazka> ksiazkaDataBorrow = FXCollections.observableArrayList();

         int[] idBooks =new int[100];
         String[] tittleBooks =new String[100];
        String pomBook;
        int idDB=Integer.parseInt(id);
        int licznik=0;
        DBcon get =new DBcon();

        idBooks=get.selectWypozycz(idDB);
        while(idBooks[licznik]!=0){
            pomBook=get.selectBbook(idBooks[licznik]);
            tittleBooks[licznik]=pomBook;
            licznik++;
        }
        licznik=0;
        while (tittleBooks[licznik]!=null){
            wy=get.selectWy(idDB,idBooks[licznik],tittleBooks[licznik],wy);
            licznik++;
        }


        get.closeConnection();
        setCellValue(wy);

    }
    public void setCellValue(List<Wypozyczenie> wy){
        ObservableList<Wypozyczenie> BorrowData = FXCollections.observableArrayList(wy);
        Borrow_T.setItems(BorrowData);
    }
    public void setBorrow (Wypozyczenie wy) {
        this.wypozyczenie = wy;

    }
    @FXML
    private void buttonNew(){

        Wypozyczenie tempWyp= new Wypozyczenie();
        boolean okBorrowClicked = mainApp.showBorrow(tempWyp,id);
        if(okBorrowClicked)
            getDbBorrowData();
    }
    @FXML
    private void buttonDeleteBorrow(){
        int selectedBorrow=Borrow_T.getSelectionModel().getSelectedIndex();

        if(selectedBorrow>=0){
            int id_borrow=Integer.parseInt(Borrow_T.getSelectionModel().getSelectedItem().getIdWypozyczenie());
            Alert deleteBorrow=new Alert(Alert.AlertType.WARNING);
            deleteBorrow.setTitle("Uwaga!!");
            deleteBorrow.setHeaderText("Czy na pewno chcesz zwrócić książkę?");
            deleteBorrow.setContentText("");
            ButtonType buttonTypeYes = new ButtonType("Tak");
            ButtonType buttonTypeNo = new ButtonType("Nie", ButtonBar.ButtonData.CANCEL_CLOSE);
            deleteBorrow.getButtonTypes().setAll(buttonTypeYes,buttonTypeNo);
            Optional<ButtonType> result = deleteBorrow.showAndWait();
            if (result.get() == buttonTypeYes){
                DBcon delBorrow =new DBcon();
                delBorrow.deleteBorrow(id_borrow);
                delBorrow.closeConnection();
                getDbBorrowData();
            } else {
                // zamkniecie okna dialogowego
            }

        } else {
            // Nic nie wybrane
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Brak danych");
            alert.setHeaderText("Nie zaznaczono książki do zwrotu");
            alert.setContentText("");

            alert.showAndWait();
        }

    }


}