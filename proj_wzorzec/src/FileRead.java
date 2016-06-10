import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by gsun12 on 14.05.2016.
 */
public class FileRead {
    FileReader fr = null;
    public void openFile (String fileName){
// OTWIERANIE PLIKU:
        try {
            fr = new FileReader(""+fileName+"");
            System.out.println("otwarto pomyslnie pomyslnie plik tekstowy");
        } catch (FileNotFoundException e) {
            System.out.println("Błąd podczas otwarcia pliku!");

        }
    }
// ODCZYT KOLEJNYCH LINII Z PLIKU:
    char[] readFile(){
        char[] tekst=new char[2048];
        int znak;
        BufferedReader bfr = new BufferedReader(fr);
        StringBuilder sb = new StringBuilder();
        try {
            while((znak = bfr.read(tekst)) != -1){
                    sb.append(tekst,0,znak);
            }
            closeFile();
        } catch (IOException e) {
            System.out.println("Błąd odczytu danych z pliku!");

        }
        return tekst;
    }
    // ZAMYKANIE PLIKU
    void closeFile(){
        try {
            fr.close();
            System.out.println("\nzamknieto pomyslnie plik tekstowy");
        } catch (IOException e) {
            System.err.println("Błąd podczas zamknięcia pliku!");
        }
    }
}



