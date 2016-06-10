package main.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by gsun12 on 29.12.2015.
 */
public class Czytelnik {
    private final StringProperty id;
    private final StringProperty imie;
    private final StringProperty nazwisko;
    private final StringProperty pesel;
    private final StringProperty ulica;
    private final StringProperty miasto;
    public Czytelnik() {
        this(null,null,null,null,null, null); }
    public Czytelnik(String id, String imie, String nazwisko,String pesel,String ulica,String miasto) {
        this.id = new SimpleStringProperty(id);
        this.imie = new SimpleStringProperty(imie);
        this.nazwisko =  new SimpleStringProperty(nazwisko);
        this.pesel = new SimpleStringProperty(pesel);
        this.ulica=new SimpleStringProperty(ulica);
        this.miasto=new SimpleStringProperty(miasto);
    }
    public String getId() {
        return id.get();
    }
    public void setId(String id) {
        this.id.set(id);
    }
    public StringProperty idProperty(){
        return id;
    }
//
    public String getImie() {
        return imie.get();
    }
    public void setImie(String imie) {
        this.imie.set(imie);
    }
    public StringProperty imieProperty(){
        return imie;
    }
//
    public String getNazwisko() {
        return nazwisko.get();
    }
    public void setNazwisko(String nazwisko) {
        this.nazwisko.set(nazwisko);
    }
    public StringProperty nazwiskoProperty(){
        return nazwisko;
    }
//
    public String getPesel() {
        return pesel.get();
    }
    public void setPesel(String pesel) {
        this.pesel.set(pesel);
    }
    public StringProperty peselProperty(){
        return pesel;
    }

    public String getUlica(){ return ulica.get();}
    public void setUlica(String ulica){this.ulica.set(ulica);}
    public StringProperty ulicaProperty(){return ulica;}

    public String getMiasto(){return miasto.get();}
    public void setMiasto(String miasto){this.miasto.set(miasto);}
    public StringProperty miastoProperty(){return miasto;}



//    @Override
//    public String toString() {
//        return "["+id+"] - "+imie+" "+nazwisko+" - "+pesel+" "+ulica+" "+miasto;
//    }
}
