package main.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by gsun12 on 29.12.2015.
 */
public class Wypozyczenie {
    private final StringProperty idWypozyczenie;
    private final StringProperty idKsiazka;
    private final StringProperty idCzytelnik;
    private final StringProperty data_wyp;
    private final StringProperty ksiazkaB_tytul;


    public Wypozyczenie() {
        this(null, null, null, null,null);
    }
    public Wypozyczenie(String idWypozyczenie,String idKsiazka, String idCzytelnik, String data_wyp,String ksiazkaB_tytul) {
        this.idWypozyczenie= new SimpleStringProperty(idWypozyczenie);
        this.idKsiazka =  new SimpleStringProperty(idKsiazka);
        this.idCzytelnik = new SimpleStringProperty(idCzytelnik);
        this.data_wyp= new SimpleStringProperty(data_wyp);
        this.ksiazkaB_tytul= new SimpleStringProperty(ksiazkaB_tytul);
    }

    public String getIdWypozyczenie() {
        return idWypozyczenie.get();
    }
    public void setIdWypozyczenie(String id) {
        this.idWypozyczenie.set(id);
    }
    public StringProperty idWypozyczenieProperty(){
        return idWypozyczenie;
    }
//
    public String getKsiazka() {
        return idKsiazka.get();
    }
    public void setIdKsiazka(String id) {
        this.idKsiazka.set(id);
    }
    public StringProperty idKsiazkaProperty(){
        return idKsiazka;
    }
//
    public String getIdCzytelnik() {
        return idCzytelnik.get();
    }
    public void setIdCzytelnik(String id) {
        this.idCzytelnik.set(id);
    }
    public StringProperty idCzytelnikProperty(){
        return idCzytelnik;
    }
//
    public String getData_wyp() {
        return data_wyp.get();
    }
    public void setData_wyp(String imie) {
        this.data_wyp.set(imie);
    }
    public StringProperty data_wypProperty(){
        return data_wyp;
    }
//
    public String getBTittle(){return  ksiazkaB_tytul.get();}
    public void setBTitlle(String id){this.ksiazkaB_tytul.set(id);}
    public StringProperty tittleBProperty(){return ksiazkaB_tytul;}


//    @Override
//    public String toString() {
//        return "["+idWypozyczenie+"] - "+idKsiazka+" "+idCzytelnik+"  "+data_wyp+"  "+cena_wyp;
//    }
}
