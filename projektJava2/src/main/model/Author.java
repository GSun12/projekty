package main.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by gsun12 on 13.01.2016.
 */
public class Author {
    private final StringProperty id_author;
    private final StringProperty imie_author;
    private final StringProperty nazwisko_author;
    private final StringProperty rok_ur;
    private final StringProperty pochodzenie_author;

    public Author(){
        this(null,null,null,null,null);
    }
    public Author(String id_author,String imie_autor, String nazwisko_author, String rok_ur, String pochodzenie_author){
        this.id_author= new SimpleStringProperty(id_author);
        this.imie_author= new SimpleStringProperty(imie_autor);
        this.nazwisko_author= new SimpleStringProperty(nazwisko_author);
        this.rok_ur= new SimpleStringProperty(rok_ur);
        this.pochodzenie_author= new SimpleStringProperty(pochodzenie_author);

    }
    public String getIdAuthor() {
        return id_author.get();
    }
    public void setIdAuthor(String id) {
        this.id_author.set(id);
    }
    public StringProperty idAuthorProperty(){
        return id_author;
    }

    public  String getImieAuthor(){return imie_author.get();}
    public void setImieAuthor(String imie_author){ this.imie_author.set(imie_author);}
    public StringProperty imieAuthorProperty(){return imie_author;}

    public  String getNazwiskoAuthor(){return nazwisko_author.get();}
    public void setNazwiskoAuthor(String nazwisko_author){ this.nazwisko_author.set(nazwisko_author);}
    public StringProperty nazwiskoAuthorProperty(){return nazwisko_author;}

    public  String getRokAuthor(){return rok_ur.get();}
    public void setRokAuthor(String rok_ur){ this.rok_ur.set(rok_ur);}
    public StringProperty rokAuthorProperty(){return rok_ur;}

    public  String getPochodzenieAuthor(){return pochodzenie_author.get();}
    public void setPochodzenieAuthor(String pochodzenie){ this.pochodzenie_author.set(pochodzenie);}
    public StringProperty pochodzenieAuthorProperty(){return pochodzenie_author;}

}
