package main.view;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import main.MainApp;
import main.model.Czytelnik;
import main.view.MainViewController;
/**
 * Created by gsun12 on 13.01.2016.
 */
public class BarViewController {
    @FXML
    MainViewController mainViewController;
    AuthorViewController authorViewController;
    private ObservableList<Czytelnik> personData = FXCollections.observableArrayList();
    public MainApp mainApp;
    @FXML public void initialize() {
        System.out.println("Application started");
//        mainViewController.init(this);
//       authorViewController.init(this);



    }
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
//        mainViewController.setMainApp(mainApp);
    }
}
