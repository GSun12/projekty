#include <stdio.h>
#include <stdlib.h>
#define ILE 3
int wczytaj_kwote(){
	int a=1,kwota=0;
	while(a){
	
	printf("podaj kwote : ");//wydruk na ekran
   	scanf("%d",&kwota);//wprowadzenie do programu
   	if(kwota%10!=0)
   		printf("podales niepoprawa kwote sprobuj jeszcze raz\n");
   		
   	else
	   break;	 		
	}
	
	return kwota;
	
}
void wczytaj_zasobnik(int bank[]){
	int i=0,pom=0;
	int wzorzec[]={ 200, 100, 50, 20, 10};
	int a=1,licznik=0;
	while(a){
		printf("podaj kwote banknotu do wprowadzenia: ");//wydruk na ekran
	   	scanf("%d",&pom);//wprowadzenie do programu
	   	if(pom%10!=0)
	   		printf("podales niepoprawa wartosc banknotu sprobuj jeszcze raz\n");
	   	else{
		   	
			for(i=0;i<5;i++){
				if(pom==wzorzec[i]){
					bank[licznik]=pom;
					licznik++;
					break;
				}
				
				else if(i==4)
					printf("podales niepoprawa wartosc banknotu sprobuj jeszcze raz\n");		
			}
	}
			
		if(pom==-1)		break;//wychodzi ze slepego while w tej metodzie i kod leci dalej
	}
	
	
}

void wyswietl_bank(int bank[],int ile){
	int i;
	for(i=0;i<ile;i++)
		printf("%d\n", bank[i]);
	
	
	
}
int ile_jest(int ile_banknot,int bank[]){
	int i,licznik=0;
	for(i=0;i<ILE;i++){
		if(ile_banknot==bank[i])
			licznik++;
	}
	
	
	return licznik;	
	
}
void usun_z_banku(int bank[],int ile bank){
	// todo napisac fkcje usuwajaca banknoty z banku
}
void wyplata(int do_wyplaty_bank[],int do_wyplaty_il[],int bank[] ){
	int i;
	for(i=0;i<ILE;i++)
			printf("ile_sztuk %d bank %d \n",do_wyplaty_il[i], do_wyplaty_bank[i]);
			
}



 
int main(){
   int bank[ILE];
  wczytaj_zasobnik(bank);
  wyswietl_bank(bank,ILE);
   int kwota;
   int licznik_wystapien;
   int czy_wyplacic=0;
   int do_wyplaty_bank[ILE];
   int do_wyplaty_il[ILE];
   int czy_wyswietlic=0;
   int a=1;
   while(a){
   		czy_wyplacic=0;
		kwota=wczytaj_kwote();
	   int licznik=0,ile_banknot=0;
	   for(licznik=0,ile_banknot=0;kwota;kwota-=ile_banknot*bank[licznik++]){
	   		ile_banknot=kwota/bank[licznik];
	   		if(ile_banknot!=0){
	   			licznik_wystapien=ile_jest(bank[licznik],bank);
	   			if(licznik_wystapien<ile_banknot){
				   czy_wyswietlic++;
					printf("nie moge wyplacic podanej kwoty\n");
				//	break;
				}
				else 
					do_wyplaty_bank[czy_wyplacic]=bank[licznik];
					do_wyplaty_il[czy_wyplacic]=ile_banknot;
					czy_wyplacic++;				
				
			}
		}	
		if(czy_wyswietlic==0)
			wyplata(do_wyplaty_bank,do_wyplaty_il);
	}
   return 0;
  }	
	
