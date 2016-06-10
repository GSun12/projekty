package main.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by gsun12 on 29.12.2015.
 */
public class Ksiazka {
    private final StringProperty id_ksiazka;
    private final StringProperty id_k_autor;
    private final StringProperty ksiazka_tytul;
    private final StringProperty ksiazka_gatunek;
    private final StringProperty ksiazka_cena;
    private final StringProperty ksiazka_rabat;


   public Ksiazka(){
        this(null,null,null,null,null,null);
    }
    public Ksiazka(String id_ksiazka,String id_k_autor,String ksiazaka_tytul,String ksiazka_gatnek,String ksiazka_cena,String ksiazka_rabat){
        this.id_ksiazka=new SimpleStringProperty(id_ksiazka);
        this.id_k_autor= new SimpleStringProperty(id_k_autor);
        this.ksiazka_tytul=new SimpleStringProperty(ksiazaka_tytul);
        this.ksiazka_gatunek=new SimpleStringProperty(ksiazka_gatnek);
        this.ksiazka_cena= new SimpleStringProperty(ksiazka_cena);
        this.ksiazka_rabat= new SimpleStringProperty(ksiazka_rabat);
    }
    public String getIdKsiazka() {
        return id_ksiazka.get();
    }
    public void setIdIdKsiazka(String id) {
        this.id_ksiazka.set(id);
    }
    public StringProperty idKsiazkaProperty(){
        return id_ksiazka;
    }
//
    public String getIdKAutor(){return  id_k_autor.get();}
    public void setId_k_autor(String id){this.id_k_autor.set(id);}
    public StringProperty idKsiazkaAutorProperty(){return id_k_autor;}
//
    public String getTittle(){return  ksiazka_tytul.get();}
    public void setTitlle(String id){this.ksiazka_tytul.set(id);}
    public StringProperty tittleProperty(){return ksiazka_tytul;}
//
    public String getGatKsiazka(){return  ksiazka_gatunek.get();}
    public void setGatKsiazka(String id){this.ksiazka_gatunek.set(id);}
    public StringProperty gatKsiazkaProperty(){return ksiazka_gatunek;}
//
    public String getCeKsiazka(){return  ksiazka_cena.get();}
    public void setCeKsiazka(String id){this.ksiazka_cena.set(id);}
    public StringProperty ceKsiazkaProperty(){return ksiazka_cena;}
//
    public String getRaKsiazka(){return  ksiazka_rabat.get();}
    public void setRaKsiazka(String id){this.ksiazka_rabat.set(id);}
    public StringProperty raKsiazkaProperty(){return ksiazka_rabat;}










//    @Override
//    public String toString() {
//        return "["+id+"] - "+tytul+" - "+autor;
//    }
}
