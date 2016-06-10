#include <stdio.h>
#include <stdlib.h>
//dodac drugi punkt i zrobin na double przerobic calu kod na 2 pkt double zrobie wartownik(opcjonalnie)
// wartownik zmniejsza ilosc obrotow petli jest to wartowsc dodana jako pierwsza(jest na koncu listy) i przeszukujac liste szukamy wartownika zamiast sprawdzac czy nie wszyszlismy poza liste
int Pom=0;

struct Lista
{
	double liczba1;
	double liczba2;
	struct Lista *nastepny;

};
struct Lista* dodaj(struct Lista* MojaLista,double a,double b){
	struct Lista* NowyElement=NULL;
	NowyElement=malloc(sizeof(struct Lista));
	NowyElement->liczba1=a;
	NowyElement->liczba2=b;
	NowyElement->nastepny=MojaLista;
	MojaLista=NowyElement;	
	return MojaLista;
}

int szukaj(struct Lista* MojaLista,double arg1,double arg2){
	struct Lista* wsk=MojaLista;
	int licznik=1;
	while(wsk!=NULL){
 		if(((wsk->liczba1==arg1)&&(wsk->liczba2==arg2))){
 		 printf("znaleziono pare (%lf;%lf)\n", arg1,arg2);
 		 Pom=licznik;
         return 1;
        }
    licznik++;
	wsk=wsk->nastepny;
	}
	printf("nie istnieje taka para liczb\n");
	return 0;
}
struct Lista* usun(struct Lista* MojaLista,double arg1,double arg2){
	
	if(szukaj(MojaLista,arg1,arg2)){
		if(Pom==1){
			struct Lista* wsk=MojaLista;
			MojaLista=wsk->nastepny;
			printf("usunieto %d argument o wartociach : %lf;%lf\n",Pom,arg1,arg2);
			return MojaLista;
		}
		if(Pom>=2){
			struct Lista* wsk=MojaLista;
//szukanie elementu n-1
			int licznik_pop=1;
			while(wsk!=NULL){
				if(((wsk->nastepny->liczba1==arg1)&&(wsk->nastepny->liczba2==arg2))) break;
				
				licznik_pop++;
				wsk=wsk->nastepny;
			}
//sprawdzenie czy nie ma konca listy jesli tak to zmieniamy koniec
// jesli nie to przesuwamy wskznik na nastepny element
			if(((wsk->nastepny->nastepny==NULL)||(wsk->nastepny==NULL))) wsk->nastepny=NULL;
			
			else wsk->nastepny=wsk->nastepny->nastepny;
			
			printf("\nusunieto %d argument o wartociach : (%lf;%lf)\n",Pom,arg1,arg2);
			return MojaLista;
		}
	}    
    else
    	printf("dany element nie istnieje");	
}
struct Lista* kasuj(struct Lista* MojaLista){
        //nie kasuje ostatniego elementu
        struct Lista* wsk=MojaLista;
        struct Lista* wsk2;

        while((wsk->nastepny)!=NULL){
            wsk2=wsk;
        	wsk=wsk->nastepny;
            free(wsk2);
        }

	     free(wsk);
	     wsk=NULL;
        //return wsk;

        if(wsk==NULL){
           printf("nie ma co usunac lista jest pusta\n");
		}
	return wsk;	
//	MojaLista=NULL;
//	return MojaLista;
}
                        
void wyswietl(struct Lista* MojaLista){
	//	wyswietlanie listy
struct Lista * wsk;
//ustawienie wskaznika na poczatek listy
	wsk=MojaLista;

	if(wsk==NULL) printf("nie ma co wyswietlic\n");
	while(wsk!=NULL){
 		printf("element %lf %lf\n ", wsk->liczba1,wsk->liczba2);
// 	przeskakujemy do kolejnego elementu
		wsk=wsk->nastepny;
 	}
}
struct Lista* scal(struct Lista* MojaLista,struct Lista* MojaLista1,struct Lista* MojaLista2){
	MojaLista=kasuj(MojaLista);
	struct Lista * wsk=MojaLista2;
//	kopiowanie 1listy do tablicy wynikowej MojaLista
	while(wsk!=NULL){
		MojaLista=dodaj(MojaLista,wsk->liczba1,wsk->liczba2);
		wsk=wsk->nastepny;
	}
	if(wsk==NULL){
		struct Lista * wsk=MojaLista1;
		while(wsk!=NULL){
			MojaLista=dodaj(MojaLista,wsk->liczba1,wsk->liczba2);
			wsk=wsk->nastepny;
		}
	}

	return MojaLista;
}
int main(int argc, char *argv[]) {
//	poczatkowo pusta lista
	struct Lista* MojaLista=NULL;
	struct Lista* MojaLista1=NULL;
	struct Lista* MojaLista2=NULL;
	int i;
	for(i=1;i<11;i++) MojaLista=dodaj(MojaLista,i,i);
	for(i=11;i<21;i++) MojaLista1=dodaj(MojaLista1,i,i);
	for(i=21;i<31;i++) MojaLista2=dodaj(MojaLista2,i,i);
	printf("wypelniamy wszystkie 3 listy i wyswietlamy\n");
	printf("lista1\n");
	wyswietl(MojaLista);
	printf("lista2\n");
	wyswietl(MojaLista1);
	printf("lista3\n");
	wyswietl(MojaLista2);
	printf("szukamy jakas wartosc w liscie\n");
	szukaj(MojaLista,1.0,1.0);
	szukaj(MojaLista,12,14);
	printf("usuwamy jakas wartosc w liscie\n");
	MojaLista=usun(MojaLista,1,1);
	MojaLista=usun(MojaLista,10,10);
	MojaLista=usun(MojaLista,5,5);

	printf("scalamy liste 2 i 3 z zapisem w 1 oraz kasowaniem list\n");
	MojaLista=scal(MojaLista,MojaLista1,MojaLista2);	
	MojaLista1=kasuj(MojaLista1);
	MojaLista2=kasuj(MojaLista2);
	wyswietl(MojaLista);
	wyswietl(MojaLista1);
	wyswietl(MojaLista2);
	return 0;
}
