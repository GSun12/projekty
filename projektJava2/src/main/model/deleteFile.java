package main.model;

import javafx.scene.control.Alert;

import java.io.File;

/**
 * Created by gsun12 on 17.01.2016.
 */
public class deleteFile {
         public void del(){
            try{

                File del_file = new File("biblioteka.db");
                if(del_file.delete()){
                    Alert delFile=new Alert(Alert.AlertType.INFORMATION);
                    delFile.setContentText("Bazę danych o nazwie " +del_file.getName() + " usunięto pomyślnie!");
                    delFile.showAndWait();
                }else{
                    Alert delFile=new Alert(Alert.AlertType.WARNING);
                    delFile.setContentText("Operacja usuwania została przerwana");
                    delFile.showAndWait();
                }

            }catch(Exception e){
                System.out.println("bleda mamy");
                e.printStackTrace();

            }
        }
    }


