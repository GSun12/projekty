#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <libpq-fe.h>
void printRecord();
void generateHTML();
void doSQL();
void menu();
void searchBook();
void searchAutor();
void deleteAutor();
void deleteBook();
PGresult* getSqlResult(char *command);
 
PGconn *conn;
int queryStatus = 0;

 
int main() {
    char pom[25];
    char dbconninfo[100]="host=localhost port=5432 dbname=";
	int a;
    printf("Database name: \n");
    	scanf("%s",pom);
  		strcat(dbconninfo,pom);
  		strcat(dbconninfo," user=");
    printf("User:\n");
    	scanf("%s",pom);
    	strcat(dbconninfo,pom);
    	strcat(dbconninfo," password=");
//    printf("Password: \n");
//    scanf("%s",pom);
//    strcat(dbconninfo,pom);
	char* pass = getpass("Password: ");
	strcat(dbconninfo,pass);
  	  // próoba polaczenia
    conn = PQconnectdb(dbconninfo);

    if(PQstatus(conn) == CONNECTION_OK) {
        printf("Polaczenie udane\n\n");
//        generateHTML();
//        printf("\n[KSIAZKA]\n\n");
//        doSQL(conn,"SELECT * FROM ksiazka");
//        printf("\n[AUTOR]\n\n");
//		doSQL(conn,"SELECT * FROM autor");
		menu();
    }
    else
        printf("Blad polaczenia: %s\n", PQerrorMessage(conn));
   
    PQfinish(conn);
    
    return EXIT_SUCCESS;
}
void searchBook(char dataPar[50]){
	char sqlsearchscript[300]="SELECT * FROM ksiazka WHERE tytul='";
	strcat(sqlsearchscript,dataPar);
	strcat(sqlsearchscript,"';");
	doSQL(conn,sqlsearchscript);
	
}
void searchAutor(char dataPar[50]){
	char sqlsearchscript[300]="SELECT * FROM autor WHERE nazwisko='";
	strcat(sqlsearchscript,dataPar);
	strcat(sqlsearchscript,"';");
	doSQL(conn,sqlsearchscript);
}
void deleteAutor(char pom[50]){
	char sqlsearchscript[300]="DELETE  FROM autor WHERE id_autor='";
	strcat(sqlsearchscript,pom);
	strcat(sqlsearchscript,"';");
	doSQL(conn,sqlsearchscript);
	printf("Usunieto pomyslnie !! \n\n");
}
void deleteBook(char pom[50]){
	char sqlsearchscript[300]="DELETE  FROM ksiazka WHERE id_ksiazki='";
	strcat(sqlsearchscript,pom);
	strcat(sqlsearchscript,"';");
	doSQL(conn,sqlsearchscript);
	printf("Usunieto pomyslnie !! \n\n");	
}
void menu(){
int wyb=0;
int end=1;
char pom[20];
while(end){
	char pomInsert[50];
	char pomInsert2[50];
	char pomInsert3[50];
	char sqlscript[300]="INSERT INTO ksiazka(id_autor,tytul,ksiazka_gatunek,cena,rabat)VALUES (";
	char sqlscriptAut[300]="INSERT INTO autor(imie,nazwisko,rok_ur,pochodzenie)VALUES ('"; 
	char sqlscriptUp[300]="UPDATE autor SET ";
		char sqlscriptUpKs[300]="UPDATE ksiazka SET ";
	char sqldeletescript[300]="";
	printf("Co chcesz zrobic ?\n");
	printf("[1] dodaj rekord\n");//jest
	printf("[2] usun rekord\n");//jest
	printf("[3] wyswietl wszystko\n");//jest
	printf("[4] modyfikuj rekord\n");//brak pomyslu
	printf("[5] szukaj\n");//jest
	printf("[6] generuj html\n");//jest
	printf("[7] zakoncz\n");
	printf("Wybierasz : ");
	scanf("%d",&wyb);
	switch(wyb){
		case 1:
			printf("Wybierz tabele\n");
			printf("[1] autor\n");
			printf("[2] ksiazka\n");
			printf("Wybierasz : ");
			scanf("%d",&wyb);
				switch(wyb){
					case 1:
						printf("Podaj imie autora : ");
							scanf("%s",pomInsert);
							strcat(sqlscriptAut,pomInsert);
							strcat(sqlscriptAut,"','");
						printf("Podaj nazwisko autora : ");
							scanf("%s",pomInsert);
							strcat(sqlscriptAut,pomInsert);
							strcat(sqlscriptAut,"','");
						printf("Podaj rok urodzenia : ");
							scanf("%s",pomInsert);
							strcat(sqlscriptAut,pomInsert);
							strcat(sqlscriptAut,"','");
						printf("Podaj pochodzenie : ");
							scanf("%s",pomInsert);
							strcat(sqlscriptAut,pomInsert);
							strcat(sqlscriptAut,"');");
						doSQL(conn,sqlscriptAut);
						
						continue;
					case 2:
						printf("Podaj id autora : ");
							scanf("%s",pomInsert);
							strcat(sqlscript,pomInsert);
							strcat(sqlscript,",'");
						printf("Podaj tytul ksiazki uzwyajac _ zamiast spacji : ");
							scanf("%s",pomInsert);
							strcat(sqlscript,pomInsert);
							strcat(sqlscript,"','");
						printf("Podaj gatunek : ");
							scanf("%s",pomInsert);
							strcat(sqlscript,pomInsert);
							strcat(sqlscript,"','");
						printf("Podaj cene : ");
							scanf("%s",pomInsert);
							strcat(sqlscript,pomInsert);
							strcat(sqlscript,"','");
						printf("Podaj rabat : ");
							scanf("%s",pomInsert);
							strcat(sqlscript,pomInsert);
							strcat(sqlscript,"');");
						doSQL(conn,sqlscript);
						continue;
				}
		case 2:		
			printf("Wybierz tabele\n");
				printf("[1] autor\n");
				printf("[2] ksiazka\n");
				printf("Wybierasz : ");
				scanf("%d",&wyb);
				switch(wyb){
					case 1:
						printf("podaj id autora : ");
						scanf("%s",pomInsert);
						deleteAutor(pomInsert);
						continue;
					case 2:
						printf("podaj id ksiazki : ");
						scanf("%s",pomInsert);
						deleteBook(pomInsert);
						continue;
				}
		case 3:
			printf("\n[KSIAZKA]\n\n");
        		doSQL(conn,"SELECT * FROM ksiazka;");
        	printf("\n[AUTOR]\n\n");
				doSQL(conn,"SELECT * FROM autor;");
			continue;		
		case 4:
			printf("Wybierz tabele\n");
			printf("[1] autor\n");
			printf("[2] ksiazka\n");
			printf("Wybierasz : ");
			scanf("%d",&wyb);
				switch(wyb){
					case 1:
						printf("Podaj nazwisko autora ktorego chcesz edytowac : ");
						scanf("%s",pomInsert2);
						printf("\nCzy zmieniamy imie? Jesli nie wpisz 0 jesli tak podaj nowe imie : ");
						scanf("%s",pomInsert);
							if(strcmp(pomInsert,"0")!=0){
								strcat(sqlscriptUp,"imie='");
								strcat(sqlscriptUp,pomInsert);
								strcat(sqlscriptUp,"'");
							}
						strcpy(pomInsert3, pomInsert);
						
						printf("\nCzy zmieniamy nazwisko? Jesli nie wpisz 0 jesli tak podaj nowe nazwisko : ");
						scanf("%s",pomInsert);
							
							if(strcmp(pomInsert,"0")!=0){
								if(strcmp(pomInsert3,"0")!=0)
									strcat(sqlscriptUp,",");
								strcat(sqlscriptUp," nazwisko='");
								strcat(sqlscriptUp,pomInsert);
								strcat(sqlscriptUp,"'");
								strcpy(pomInsert3, pomInsert);
							}
							
							
						printf("\nCzy zmieniamy rok_urodzenia? Jesli nie wpisz 0 jesli tak podaj nowy rok_rodzenia : ");
						scanf("%s",pomInsert);
							
							if(strcmp(pomInsert,"0")!=0){
								if(strcmp(pomInsert3,"0")!=0)
									strcat(sqlscriptUp,",");
								strcat(sqlscriptUp," rok_ur='");
								strcat(sqlscriptUp,pomInsert);
								strcat(sqlscriptUp,"'");
								strcpy(pomInsert3, pomInsert);
							}
							
							
						printf("\nCzy zmieniamy pochodzenie? Jesli nie wpisz 0 jesli tak podaj nowe pochodzenie : ");
						scanf("%s",pomInsert);
							
							if(strcmp(pomInsert,"0")!=0){
								if(strcmp(pomInsert3,"0")!=0)
									strcat(sqlscriptUp,",");
								strcat(sqlscriptUp," pochodzenie='");
								strcat(sqlscriptUp,pomInsert);
								strcat(sqlscriptUp,"'");
							}
							strcat(sqlscriptUp," WHERE nazwisko='");
							strcat(sqlscriptUp,pomInsert2);
							strcat(sqlscriptUp,"';");
							doSQL(conn,sqlscriptUp);
						continue;
					case 2:
						printf("Podaj tytul ksiazki ktora chcesz edytowac : ");
						scanf("%s",pomInsert2);
						printf("\nCzy zmieniamy tytul? Jesli nie wpisz 0 jesli tak podaj nowy tytul : ");
						scanf("%s",pomInsert);
							if(strcmp(pomInsert,"0")!=0){
								strcat(sqlscriptUpKs,"tytul='");
								strcat(sqlscriptUpKs,pomInsert);
								strcat(sqlscriptUpKs,"'");
							}
						strcpy(pomInsert3, pomInsert);
							
						printf("\nCzy zmieniamy gatunek? Jesli nie wpisz 0 jesli tak podaj nowy gatunek : ");
						scanf("%s",pomInsert);
							
							if(strcmp(pomInsert,"0")!=0){
								if(strcmp(pomInsert3,"0")!=0)
									strcat(sqlscriptUpKs,",");
								strcat(sqlscriptUpKs," ksiazka_gatunek='");
								strcat(sqlscriptUpKs,pomInsert);
								strcat(sqlscriptUpKs,"'");
								strcpy(pomInsert3, pomInsert);
							}
						
							
						printf("\nCzy zmieniamy cene? Jesli nie wpisz 0 jesli tak podaj nowa cene : ");
						scanf("%s",pomInsert);
							
							if(strcmp(pomInsert,"0")!=0){
								if(strcmp(pomInsert3,"0")!=0)
									strcat(sqlscriptUpKs,",");
								strcat(sqlscriptUpKs," cena='");
								strcat(sqlscriptUpKs,pomInsert);
								strcat(sqlscriptUpKs,"'");
								strcpy(pomInsert3, pomInsert);
							}
							
						
						printf("\nCzy zmieniamy rabat? Jesli nie wpisz 0 jesli tak podaj nowy rabat : ");
						scanf("%s",pomInsert);
							
							if(strcmp(pomInsert,"0")!=0){
								if(strcmp(pomInsert3,"0")!=0)
									strcat(sqlscriptUpKs,",");
								strcat(sqlscriptUpKs," rabat='");
								strcat(sqlscriptUpKs,pomInsert);
								strcat(sqlscriptUpKs,"'");
							}
							strcat(sqlscriptUpKs," WHERE tytul='");
							strcat(sqlscriptUpKs,pomInsert2);
							strcat(sqlscriptUpKs,"';");
							doSQL(conn,sqlscriptUpKs);
						continue;
				}
			continue;
		case 5:
				printf("Wybierz tabele\n");
				printf("[1] autor\n");
				printf("[2] ksiazka\n");
				printf("Wybierasz : ");
				scanf("%d",&wyb);
				switch(wyb){
					case 1:
						printf("podaj nazwisko autora : ");
						scanf("%s",pomInsert);
						searchAutor(pomInsert);
						continue;
					case 2:
						printf("podaj tytul : ");
						scanf("%s",pomInsert);
						searchBook(pomInsert);
						continue;
				}
		case 6:
			generateHTML();
			continue;
		case 7:
			end=0;
			printf("\n\t\tKoniec tego dobrego :)\n\n");						
			break;
		default:
			printf("\nBledny wybor sprobuj jeszcze raz.\n");
		}


	}

}
void doSQL(PGconn *conn, char *command){
  PGresult *result;

  //printf("%s\n", command);

  result = PQexec(conn, command);
  //printf("status is     : %s\n", PQresStatus(PQresultStatus(result)));
  //printf("#rows affected: %s\n", PQcmdTuples(result));
  //printf("result message: %s\n\n", PQresultErrorMessage(result));

  switch(PQresultStatus(result)) {
  case PGRES_TUPLES_OK:
    {
      int n = 0, m = 0;
      int nrows   = PQntuples(result);
      int nfields = PQnfields(result);
      //printf("number of rows returned   = %d\n", nrows);
     //printf("number of fields returned = %d\n\n", nfields);
      for(m = 0; m < nrows; m++) {
	for(n = 0; n < nfields; n++)
	  printf(" %-6s = %s |", PQfname(result, n),PQgetvalue(result,m,n));
	printf("\n");
      }
    }
  }
  PQclear(result);
}

void generateHTML(){
  PGresult* result = getSqlResult("SELECT * FROM ksiazka");

	PGresult* result2 = getSqlResult("SELECT * FROM autor");		


		if(PQresultStatus(result)==PGRES_TUPLES_OK) 
    		{ 

		      FILE * fpx;

		      fpx = fopen ("index.html", "w+");	

		      PQprintOpt pqp;
		      pqp.header = 1;
		      pqp.align = 0;
		      pqp.html3 = 1;
		      pqp.expanded = 0;
		      pqp.pager = 0;
		      pqp.fieldSep = "";
		      pqp.tableOpt = "";
		      pqp.caption = "Ksiazki";
		      pqp.fieldName = NULL;

		      PQprintOpt pqp2;
		      pqp2.header = 1;
		      pqp2.align = 0;
		      pqp2.html3 = 1;
		      pqp2.expanded = 0;
		      pqp2.pager = 0;
		      pqp2.fieldSep = "";
		      pqp2.tableOpt = "";
		      pqp2.caption = "Autor";
		      pqp2.fieldName = NULL;

		      fprintf(fpx,"<!DOCTYPE HTML>\n");
		      fprintf(fpx,"<HTML><HEAD><link rel=\"stylesheet\" href=\"style.css\">\n<meta name=\"Author\" content=\"Szymon Sedek\" /><title>Dane o ksiazkach</title><meta content=\"text/html;charset=utf-8\" http-equiv=\"Content-Type\"></HEAD>\n<BODY>\n");
			  PQprint(fpx, result, &pqp);
			  PQprint(fpx, result2, &pqp2);
		      fprintf(fpx,"</BODY></HTML>\n");
		      fclose(fpx);
			  system("sed -i -e 's/align=\"right\"//g' index.html");			
			  system("sed -i -e 's/align=\"top\"//g' index.html");	
			
	
				fpx = fopen ("style.css", "w+");
				fprintf(fpx,"BODY{font-family: sans-serif; font-size: 15px; background: #666699; }\n");
				fprintf(fpx,"caption{font-size: 25px; font-style: italic; color: #0000FF;}\n");
				fprintf(fpx,"caption:before{content: \"Tabela: \";}\n");
				fprintf(fpx,"table, th, td {text-align: center; border-style: solid; border-color: black; margin-left: 550px; margin-top: 120px;}\n");
				fclose(fpx);
	printf("\n\tPlik html o nazwie index.html wygenerowano pomyslnie !\n\n");
		      
		}
	}
	   
 
PGresult* getSqlResult(char *command) {
    PGresult *result = PQexec(conn, command);
    queryStatus = PQresultStatus(result);
    return result;
}
