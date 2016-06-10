package projektJava;


import java.io.IOException;

/*
Szymon Sędek
Program pobiera dane z arkusza kalkulacyjnego i wpisuje go do bazy danych następnie wybiera poczególne dane wykonuje
obliczenia i robi update istniejących już rekordów w bazie.
Aby uruchomić prgram należy dodać sqlite do eclipse a nastepnie skopiować plik z danymi do katalogu z projektem.
Można też w klasie switch w String name= podać lokalizację pliku z danych;
należy też zmienić lokalizację w klasie deleteFile w tym miejscu File del_file = new File("bazaJava.db");
Baza danych powinna utworzyć się automatycznie;
W przypadku braku zwrotnej informacji np w postaci błędu oznacza to, że takich danych w bazie nie ma.Np wyszukując kraj po ilości
wyprodukowanych kWh wpisując 123 nie otrzymamy wyniku;
wykonano w :
Eclipse
Version: Mars (4.5)
Build id: I20150603-2000
sqlite-jdbc-3.8.11.2
*/
public class Main {

	public static void main(String[] args) throws IOException {
		new Switch();

	}
}
