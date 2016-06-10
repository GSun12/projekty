package projektJava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class dbcon {
	static String namedb="bazaJava.db";
	public static final String DRIVER = "org.sqlite.JDBC";
	public static final String DB_URL = "jdbc:sqlite:"+namedb+"";
	private Connection conn;
	Statement stat;
//TEST POLACZENIA
	    public dbcon(){
	    	try {
	            Class.forName(dbcon.DRIVER);
	        } catch (ClassNotFoundException e) {
	            System.err.println("Brak sterownika JDBC");
	            e.printStackTrace();
	        	}

	        try {
	            conn = DriverManager.getConnection(DB_URL);
	            stat = conn.createStatement();
	            System.err.println("Pomyslnie polaczono z baza '"+namedb+"'");
	        } catch (SQLException e) {
	            System.err.println("Problem z otwarciem polaczenia");
	            e.printStackTrace();
	        }

	    }
 //TWORZENIE DB JESLI NIE ISTNIEJE
	    public boolean createdb() {
	        String create="CREATE TABLE IF NOT EXISTS electricity(Id_country INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, Country	TEXT NOT NULL UNIQUE, Production	NUMERIC, Consumption NUMERIC,	Balance	NUMERIC, Unit TEXT NOT NULL)";
	    	try {

	        	stat.execute(create);

	        } catch (SQLException e) {
	            System.err.println("Blad przy tworzeniu tabeli");
	            e.printStackTrace();
	            return false;
	        }
	        return true;
	    }
//UZUPELNIANIE DB DANYMI Z PLIKU
	    public boolean insertdb(String country, Double production, Double consumption, String unit ) {
	        try {

	        	stat.executeUpdate("insert into electricity(Country,Production,Consumption,Unit) values('"+country+"',"+production+","+consumption+",'"+unit+"')");

	        } catch (SQLException e) {
	            System.err.println("Blad przy wstawianiu podanej wartości");
	            e.printStackTrace();
	            return false;
	        }
	        return true;
	    }
 //DODANIE DO DANYCH W BAZIE WYLICZONYCH DANYCH
	    public boolean updateBaldb(int id, Double balance) {
	        try {

	        	stat.executeUpdate("update electricity SET Balance="+balance+" where id_country='"+id+"'");

	        } catch (SQLException e) {
	            System.err.println("Blad przy wstawianiu podanej wartości");
	            e.printStackTrace();
	            return false;
	        }
	        return true;
	    }
 //WYYSWIETLANIE CALOSCI DANYCH Z BAZY
	    public boolean selectLista(){

	    	try {
	            ResultSet rs = stat.executeQuery("SELECT * FROM electricity");
	            while(rs.next()){

	                System.out.println("\n\nId_country = " + rs.getInt("id_country"));
	                System.out.println("Country : " + rs.getString("Country"));
	                System.out.println("Production = " + rs.getDouble("Production")+" "+rs.getString("Unit"));
	                System.out.println("Consumption = " + rs.getDouble("Consumption")+" "+rs.getString("Unit"));
	                System.out.println("Balance = " + rs.getDouble("Balance")+" "+rs.getString("Unit"));
	            }
	    	}
	    	catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	    	}
	    	return true;
	    }
 //WYYSWIETLANIE  DANYCH Z BAZY PO ID
	    void selectFromId(int id){

	    	try {
	            ResultSet rs = stat.executeQuery("SELECT * FROM electricity where id_country="+id+"");
	            while(rs.next()){

	                System.out.println("\n\nId_country = " + rs.getInt("id_country"));
	                System.out.println("Country : " + rs.getString("Country"));
	                System.out.println("Production = " + rs.getDouble("Production")+" "+rs.getString("Unit"));
	                System.out.println("Consumption = " + rs.getDouble("Consumption")+" "+rs.getString("Unit"));
	                System.out.println("Balance = " + rs.getDouble("Balance")+" "+rs.getString("Unit"));
	            }
	    	}
	    	catch (SQLException e) {
	            e.printStackTrace();

	    	}

	    }
//WYYSWIETLANIE  DANYCH Z BAZY PO NAZWIE KRAJU
	    void selectFromCou(String coun){

	    	try {
	            ResultSet rs = stat.executeQuery("SELECT * FROM electricity where Country='"+coun+"'");
	            while(rs.next()){

	                System.out.println("\n\nId_country = " + rs.getInt("id_country"));
	                System.out.println("Country : " + rs.getString("Country"));
	                System.out.println("Production = " + rs.getDouble("Production")+" "+rs.getString("Unit"));
	                System.out.println("Consumption = " + rs.getDouble("Consumption")+" "+rs.getString("Unit"));
	                System.out.println("Balance = " + rs.getDouble("Balance")+" "+rs.getString("Unit"));
	            }
	    	}
	    	catch (SQLException e) {
	    		 e.printStackTrace();

	    	}

	    }
//WYYSWIETLANIE  DANYCH Z BAZY PO WYBRANYCH DANYCH
	    void selectFromProdConsBal(double prod,String sqlName){

	    	try {
	            ResultSet rs = stat.executeQuery("SELECT * FROM electricity where "+sqlName+" = "+prod+"");
	            while(rs.next()){

	                System.out.println("\n\nId_country = " + rs.getInt("id_country"));
	                System.out.println("Country : " + rs.getString("Country"));
	                System.out.println("Production = " + rs.getDouble("Production")+" "+rs.getString("Unit"));
	                System.out.println("Consumption = " + rs.getDouble("Consumption")+" "+rs.getString("Unit"));
	                System.out.println("Balance = " + rs.getDouble("Balance")+" "+rs.getString("Unit"));
	            }
	    	}
	    	catch (SQLException e) {
	    		e.printStackTrace();

	    	}

	    }
//MALEJACO ROSNACO DANE SORT
	    void selectFromDESCASC(String ASC_DESC,String category){

	    	try {
	            ResultSet rs = stat.executeQuery("SELECT * FROM electricity order by "+category+" "+ASC_DESC+"");
	            while(rs.next()){

	                System.out.println("\n\nId_country = " + rs.getInt("id_country"));
	                System.out.println("Country : " + rs.getString("Country"));
	                System.out.println("Production = " + rs.getDouble("Production")+" "+rs.getString("Unit"));
	                System.out.println("Consumption = " + rs.getDouble("Consumption")+" "+rs.getString("Unit"));
	                System.out.println("Balance = " + rs.getDouble("Balance")+" "+rs.getString("Unit"));
	            }
	    	}
	    	catch (SQLException e) {
	            e.printStackTrace();

	    	}

	    }
//POBRANIE CALKOWITEJ ILOSCI INDEKSOW W BAZIE
	    int max_id(){
	    	int max = 0;
	    	try {

	            ResultSet rs = stat.executeQuery("SELECT MAX(id_country) FROM electricity");
	            if ( rs.next() )
	            	    max = rs.getInt(1);

	    	}
	    	catch (SQLException e) {
	            e.printStackTrace();

	    	}
	    	 return max;
	    }
 //POBIERANIE DANYCH POTRZEBNYCH DO OBLICZEN
	    double get_balance_data_prod(int id){
	    	double prod = 0;
	    	try {

	            ResultSet rs = stat.executeQuery("select Production from electricity where id_country="+id+"");
	            if ( rs.next() )
	            	    prod = rs.getDouble("Production");

	    	}
	    	catch (SQLException e) {
	            e.printStackTrace();

	    	}
	    	 return prod;
	     }
	    double get_balance_data_cons(int id){
	    	double cons=0;
	    	try {

	            ResultSet rs = stat.executeQuery("select Consumption from electricity where id_country="+id+"");
	            if ( rs.next() )
	                cons = rs.getDouble("Consumption");

	    	}
	    	catch (SQLException e) {
	            e.printStackTrace();

	    	}
	    	 return cons;
	     }
//USUWANIE WYBRANEGO REKORDU
	    void deleteRecord(int idD){
	    	try {

	           stat.executeUpdate("delete from electricity where id_country = "+idD+";");
	           System.err.println("usunięto pomyślnie!");
	    	}
	    	catch (SQLException e) {
	    		System.err.println("błąd usuwania rekordu!");
	            e.printStackTrace();

	    	}
	    }
//ZAMKNIECIE POLACZENIE Z DB
	    public void closeConnection() {
	        try {
	            conn.close();
	            System.err.println("Polaczenie zamknieto pomyslnie");
	        } catch (SQLException e) {
	            System.err.println("Problem z zamknieciem polaczenia");
	            e.printStackTrace();
	        }
	    }
}

