package projektJava;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;


public class read {

	  FileReader fr = null;
	   String linia = "";
	  public boolean openFile (String fileName){
// OTWIERANIE PLIKU:
		  try {
			  fr = new FileReader(""+fileName+"");
			  System.out.println("otwarto pomyslnie pomyslnie plik tekstowy");
			  readFile();
		  } catch (FileNotFoundException e) {
			  System.out.println("Błąd podczas otwarcia pliku!");
			  return false;
		  }return true;
	  }
	  void readFile(){
		  BufferedReader bfr = new BufferedReader(fr);
		  dbcon db=new dbcon();
		  db.createdb();
// ODCZYT KOLEJNYCH LINII Z PLIKU Z WPISEM DO BAZY DANYCH:
		  try {
			  while((linia = bfr.readLine()) != null){

				  StringTokenizer str= new StringTokenizer(linia, ";");
				  while (str.hasMoreElements()) {

					    String coun = str.nextElement().toString();
					    Double prod = Double.parseDouble(str.nextElement().toString());
					    Double con = Double.parseDouble(str.nextElement().toString());
					    String uni = str.nextElement().toString();

						StringBuilder sb = new StringBuilder();
						sb.append("\nCountry : " + coun);
						sb.append("\nProduction : " + prod);
						sb.append("\nConsumbtion : " + con);
						sb.append("\nUnit : " + uni);
						System.out.println(sb.toString());
						db.insertdb(coun, prod, con, uni);
					   }

			  }
			  closeFile();
			  db.closeConnection();
		  } catch (IOException e) {
			  System.out.println("Błąd odczytu danych z pliku!");

		  }
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

