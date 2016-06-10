DROP TABLE ksiazka;
DROP TABLE autor;

CREATE TABLE autor (
  id_autor		SERIAL PRIMARY KEY UNIQUE,
  imie			VARCHAR(255) NOT NULL,
  nazwisko		VARCHAR(255) NOT NULL,
  rok_ur 		CHAR(4) NOT NULL,
  pochodzenie	VARCHAR(255) NOT NULL
  
);

CREATE TABLE ksiazka (
  id_ksiazka		SERIAL PRIMARY KEY UNIQUE,
  id_autor			INTEGER  REFERENCES autor(id_autor) NOT NULL,
  tytul         	VARCHAR(255) CHECK(LENGTH(tytul)>2) NOT NULL,
  ksiazka_gatunek 	VARCHAR(255) NULL,
  cena			DECIMAL   NUll,					
  rabat			FLOAT	  Default 0 NULL
  
);

INSERT INTO autor(imie,nazwisko,rok_ur,pochodzenie)
VALUES ('Mikolaj','Rej','1505','polak');
INSERT INTO autor(imie,nazwisko,rok_ur,pochodzenie)
VALUES ('Adam','Mickiewicz','1798','polak');
INSERT INTO autor(imie,nazwisko,rok_ur,pochodzenie)
VALUES ('Julian','Tuwim','1894','polak');
INSERT INTO autor(imie,nazwisko,rok_ur,pochodzenie)
VALUES ('Jan','Brzechwa','1898','polak');


INSERT INTO ksiazka(id_autor,tytul,ksiazka_gatunek,cena,rabat)
VALUES (1,'Kupiec','Dramat','15',0);
INSERT INTO ksiazka(id_autor,tytul,ksiazka_gatunek,cena,rabat)
VALUES (2,'Oda_do_mlodosci ','Oda','55',0);
INSERT INTO ksiazka(id_autor,tytul,ksiazka_gatunek,cena,rabat)
VALUES (3,'Skamander','satyra','45',0);
INSERT INTO ksiazka(id_autor,tytul,ksiazka_gatunek,cena,rabat)
VALUES (4,'Kaczka_Dziwaczka','wiersz','655',0);
