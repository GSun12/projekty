package main.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by gsun12 on 18.05.2016.
 */
public class Pracownik {
        private final StringProperty id_pracownik;
        private final StringProperty imie_pracownik;
        private final StringProperty nazwisko_pracownik;
        private final StringProperty stanowisko_pracownik;
        private final StringProperty nr_pracownika;

        public Pracownik(){
            this(null,null,null,null,null);
        }
        public Pracownik(String id_pracownik,String imie_pracownik, String nazwisko_pracownik, String stanowisko_pracownik, String nr_pracownika){
            this.id_pracownik= new SimpleStringProperty(id_pracownik);
            this.imie_pracownik = new SimpleStringProperty(imie_pracownik);
            this.nazwisko_pracownik = new SimpleStringProperty(nazwisko_pracownik);
            this.stanowisko_pracownik = new SimpleStringProperty(stanowisko_pracownik);
            this.nr_pracownika = new SimpleStringProperty(nr_pracownika);

        }
        public String getIdPracownik() {
            return id_pracownik.get();
        }
        public void setIdPracownik(String id) {
            this.id_pracownik.set(id);
        }
        public StringProperty idPracownikProperty(){
            return id_pracownik;
        }

        public  String getImiePracownik(){return imie_pracownik.get();}
        public void setImiePracownik(String imie_author){ this.imie_pracownik.set(imie_author);}
        public StringProperty imiePracownikProperty(){return imie_pracownik;}

        public  String getNazwiskoPracownik(){return nazwisko_pracownik.get();}
        public void setNazwiskoPracownik(String nazwisko_author){ this.nazwisko_pracownik.set(nazwisko_author);}
        public StringProperty nazwiskoPracownikProperty(){return nazwisko_pracownik;}

        public  String getStanowiskoPracownik(){return stanowisko_pracownik.get();}
        public void setStanowiskoPracownik(String rok_ur){ this.stanowisko_pracownik.set(rok_ur);}
        public StringProperty StanowiskoPracownikProperty(){return stanowisko_pracownik;}

        public  String getNrPracownika(){return nr_pracownika.get();}
        public void setNrPracownika(String pochodzenie){ this.nr_pracownika.set(pochodzenie);}
        public StringProperty NrPracownikaProperty(){return nr_pracownika;}

    }

