package main.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by gsun12 on 29.12.2015.
 */
public class DBcon {
    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:biblioteka.db";

    private Connection conn;
    private Statement stat;

    public DBcon() {
        try {
            Class.forName(DBcon.DRIVER);
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection(DB_URL);
            stat = conn.createStatement();
        } catch (SQLException e) {

            e.printStackTrace();
        }


    }

    public boolean createTables()  {
        String createCzytelnicy = "CREATE TABLE IF NOT EXISTS czytelnicy (id_czytelnika INTEGER PRIMARY KEY AUTOINCREMENT, imie varchar(255), nazwisko varchar(255), pesel VARCHAR (12),ulica VARCHAR (255),miasto VARCHAR (255))";
        String createKsiazki = "CREATE TABLE IF NOT EXISTS ksiazki (id_ksiazki INTEGER PRIMARY KEY AUTOINCREMENT, id_k_autor int, tytul varchar(255), ksiazka_gatunek varchar(255),cena DECIMAL,rabat DOUBLE )";
        String createWypozyczenia = "CREATE TABLE IF NOT EXISTS wypozyczenia (id_wypozycz INTEGER PRIMARY KEY AUTOINCREMENT, id_czytelnika int, id_ksiazki int, data_wyp DATE)";
        String createAutor="CREATE TABLE IF NOT EXISTS autor (id_autor INTEGER PRIMARY KEY AUTOINCREMENT, imie varchar(255), nazwisko varchar(255), rok_ur INTEGER, pochodzenie VARCHAR (255))";
        String createPracownik="CREATE TABLE IF NOT EXISTS pracownik (id_pracownik INTEGER PRIMARY KEY AUTOINCREMENT, imie Varchar(255) NOT NULL, nazwisko Varchar(255) NOT NULL, stanowisko Varchar(255) NOT NULL, nr_pracownika INTEGER NOT NULL)";
        try {
            stat.execute(createCzytelnicy);
            stat.execute(createKsiazki);
            stat.execute(createWypozyczenia);
            stat.execute(createAutor);
            stat.execute(createPracownik);
        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean insertCzytelnik(String imie, String nazwisko, String pesel,String ulica,String miasto) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "insert into czytelnicy values (NULL, ?, ?, ?,?,?);");
            prepStmt.setString(1, imie);
            prepStmt.setString(2, nazwisko);
            prepStmt.setString(3, pesel);
            prepStmt.setString(4, ulica);
            prepStmt.setString(5,miasto);
            prepStmt.execute();
        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
        return true;
    }
    public void updateCzytelnik(String imie, String nazwisko,String new_pesel, String pesel,String ulica,String miasto){
        try{
            stat.executeUpdate("UPDATE czytelnicy set imie='"+imie+"',nazwisko='"+nazwisko+"',pesel='"+new_pesel+"',ulica='"+ulica+"',miasto='"+miasto+"' WHERE pesel='"+pesel+"';");
        }
        catch (SQLException e){
            e.printStackTrace();

        }

    }
    public boolean insertAuthor(String imie, String nazwisko, int roku_ur,String pochodzenie){
        try{
            PreparedStatement prep =conn.prepareStatement(
                    "INSERT INTO autor VALUES (NULL,?,?,?,?);");
            prep.setString(1,imie);
            prep.setString(2,nazwisko);
            prep.setInt(3,roku_ur);
            prep.setString(4,pochodzenie);
            prep.execute();
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public boolean insertKsiazka(int id_k_autor ,String tytul, String ksiazka_gatunek,float cena,double rabat) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "insert into ksiazki values (NULL, ?, ?, ?, ?, ?);");
            prepStmt.setInt(1, id_k_autor);
            prepStmt.setString(2, tytul);
            prepStmt.setString(3, ksiazka_gatunek);
            prepStmt.setFloat(4, cena);
            prepStmt.setDouble(5,rabat);
            prepStmt.execute();
        } catch (SQLException e) {

            return false;
        }
        return true;
    }

    public boolean insertWypozycz(int idCzytelnik, int idKsiazka, String data_wyp) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "insert into wypozyczenia values (NULL, ?, ?, ?);");
            prepStmt.setInt(1, idCzytelnik);
            prepStmt.setInt(2, idKsiazka);
            prepStmt.setString(3, data_wyp);
            prepStmt.execute();
        } catch (SQLException e) {

            return false;
        }
        return true;
    }
    public int[] selectWypozycz(int id_user){
        int[] id_ksiazek=new int[100];
        int licznik=0;
       try {
           ResultSet result = stat.executeQuery("SELECT * FROM wypozyczenia where id_czytelnika="+id_user+";");
           String id_w, id_k, id_c,tytul;
           String data_w;
           while (result.next()) {

             id_ksiazek[licznik]=result.getInt("id_ksiazki");

                licznik++;

           }
       }catch (SQLException e) {
           e.printStackTrace();
           return null;
       }

        return id_ksiazek;
    }

    public String selectBbook(int id_book){
        String tittleBook="";

        try {
            ResultSet resultBook = stat.executeQuery("SELECT * FROM ksiazki WHERE id_ksiazki = "+id_book+";");
            while (resultBook.next()) {
                tittleBook=resultBook.getString("tytul");

            }
        }catch (SQLException e){
            e.printStackTrace();

        }
        return tittleBook;
    }
    public List<Wypozyczenie> selectWy(int id_user,int id_ks,String tittle,List<Wypozyczenie> pom){
        List<Wypozyczenie> wypozyczenia =new ArrayList<Wypozyczenie>();
        wypozyczenia=pom;
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM wypozyczenia where id_czytelnika="+id_user+" and id_ksiazki="+id_ks+";");
            String id_w, id_k, id_c;
            String data_w;
            while (result.next()){
                id_w = Integer.toString(result.getInt("id_wypozycz"));
                id_k=Integer.toString(result.getInt("id_ksiazki"));
                id_c=Integer.toString(result.getInt("id_czytelnika"));
                data_w=result.getString("data_wyp");
                wypozyczenia.add(new Wypozyczenie(id_w,id_k,id_c,data_w,tittle));

            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return wypozyczenia;
    }
    public List<Czytelnik> selectCzytelnicy() {
         List<Czytelnik> czytelnicy = new ArrayList<Czytelnik>();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM czytelnicy");
            int id;
            String imie, nazwisko,ulica,miasto,id_s,pesel;
            while(result.next()) {

                id = result.getInt("id_czytelnika");
                imie = result.getString("imie");
                nazwisko = result.getString("nazwisko");
                pesel = result.getString("pesel");
                ulica= result.getString("ulica");
                miasto =result.getString("miasto");
                id_s=Integer.toString(id);
                czytelnicy.add(new Czytelnik(id_s, imie, nazwisko, pesel,ulica,miasto));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
      return czytelnicy;
    }
    public List<Author> selectAuthor() {
        List<Author> authors = new ArrayList<Author>();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM autor");

            String imie, nazwisko,ulica,id,rok_ur;
            while(result.next()) {

                id =Integer.toString(result.getInt("id_autor"));
                imie = result.getString("imie");
                nazwisko = result.getString("nazwisko");
                rok_ur = Integer.toString(result.getInt("rok_ur"));
                ulica= result.getString("pochodzenie");
                authors.add(new Author(id, imie, nazwisko, rok_ur,ulica));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return authors;
    }
    public List<Author> selectAuthorById(int id_a,List<Author> aut) {
        List<Author> authorsFiltred = new ArrayList<Author>();
        authorsFiltred=aut;
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM autor where id_autor="+id_a+";");

            String imie, nazwisko,ulica,id,rok_ur;
            while(result.next()) {

                id =Integer.toString(result.getInt("id_autor"));
                imie = result.getString("imie");
                nazwisko = result.getString("nazwisko");
                rok_ur = Integer.toString(result.getInt("rok_ur"));
                ulica= result.getString("pochodzenie");
                authorsFiltred.add(new Author(id, imie, nazwisko, rok_ur,ulica));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return authorsFiltred;
    }

    public List<Ksiazka> selectKsiazki() {
        List<Ksiazka> ksiazki = new LinkedList<Ksiazka>();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM ksiazki ORDER BY tytul ASC ");

            String id,id_k_aut, tytul, gatunek;
            String cena,rabat;
            String pom_tytul="a",pom_gatunek="a",pom_cenaDb;
            while(result.next()) {
                id = Integer.toString(result.getInt("id_ksiazki"));
                id_k_aut=Integer.toString(result.getInt("id_k_autor"));
                tytul = result.getString("tytul");
                gatunek = result.getString("ksiazka_gatunek");
                cena=Float.toString(result.getFloat("cena"));
                rabat=Double.toString(result.getDouble("rabat"));
                if(!(tytul.equals(pom_tytul)) || !(gatunek.equals(pom_gatunek)  )) {
                    ksiazki.add(new Ksiazka(id, id_k_aut, tytul, gatunek, cena, rabat));
                    pom_tytul=tytul;
                    pom_gatunek=gatunek;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return ksiazki;
    }

    public int[] selectBookParam(String tittle,String gatunek) {
        int []idauthors = new int[10];
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM ksiazki where tytul= '"+tittle+"' and ksiazka_gatunek='"+gatunek+"'; ");

            int id,licz=0;
            while(result.next()) {

                id =result.getInt("id_k_autor");
                idauthors[licz]=id;
                licz++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return idauthors;
    }
    public void insertPracownik(String imie,String nazwisko,String stanowisko,int nr_prac){
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "insert into pracownik values (NULL, ?, ?, ?, ?);");
            prepStmt.setString(1, imie);
            prepStmt.setString(2, nazwisko);
            prepStmt.setString(3, stanowisko);
            prepStmt.setInt(4,nr_prac);
            prepStmt.execute();
        } catch (SQLException e) {


        }

    }
    public List<Pracownik> selectPracownik() {
        List<Pracownik> pracownicy = new ArrayList<Pracownik>();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM pracownik");

            String imie, nazwisko,stanowisko,id,nr_prac;
            while(result.next()) {

                id =Integer.toString(result.getInt("id_pracownik"));
                imie = result.getString("imie");
                nazwisko = result.getString("nazwisko");
                stanowisko = result.getString("stanowisko");
                nr_prac= Integer.toString(result.getInt("nr_pracownika"));
                pracownicy.add(new Pracownik(id, imie, nazwisko, stanowisko,nr_prac));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return pracownicy;
    }
    public void updatePracownik(String imie, String nazwisko,String new_nazwisko, String stanowisko,int nr_prac,int new_nrprac){
        try{
            stat.executeUpdate("UPDATE pracownik set imie='"+imie+"',nazwisko='"+new_nazwisko+"',stanowisko='"+stanowisko+"',nr_pracownika="+new_nrprac+" WHERE nazwisko='"+nazwisko+"' AND nr_pracownika="+nr_prac+";");
        }
        catch (SQLException e){
            e.printStackTrace();

        }

    }
    public void deletePracownik(String nazwisko,String nr_prac){
        try{
            stat.executeUpdate("delete from pracownik where nazwisko= '"+nazwisko+"' and nr_pracownika="+nr_prac+";");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void deleteBook(String tittle,String gatunek){
        try{
            stat.executeUpdate("delete from ksiazki where tytul= '"+tittle+"' and ksiazka_gatunek='"+gatunek+"';");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void deleteBorrow(int id_borrow){
        try{
            stat.executeUpdate("delete from wypozyczenia where id_wypozycz="+id_borrow+";");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteCzytelnik(String pesel){
        try{
            stat.executeUpdate("delete from czytelnicy where pesel= '"+pesel+"';");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void deleteAuthor(String nazwisko,String imie){
        try{
            stat.executeUpdate("DELETE from autor where nazwisko='"+nazwisko+"' and imie='"+imie+"' ; ");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void dropTable(){
        try{
            stat.executeUpdate("Drop Table IF EXISTS autor");
            stat.executeUpdate("Drop Table IF EXISTS czytelnicy");
            stat.executeUpdate("Drop Table IF EXISTS wypozyczenia");
            stat.executeUpdate("Drop Table IF EXISTS ksiazki ");
            stat.executeUpdate("DROP TABLE IF EXISTS  pracownik");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}