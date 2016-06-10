#include <stdio.h>
#include <stdlib.h>
#include <string.h>
//#define M 18986
//int M=0;
struct Element
{	int czy_null;
	int liczba;
	char nazwisko[100];
};
void Insert (char nazwisko[100],int liczba_nazw,struct Element *TablicaElementow,int M){
	int i_hash=0,liczba_nazwisko=hash(nazwisko,M);
	int index_tab=0,pom_index_tab=0;
	index_tab=(liczba_nazwisko+i_hash)%M;
	pom_index_tab=index_tab;

	if(TablicaElementow[index_tab].czy_null==0){
		TablicaElementow[index_tab].liczba=liczba_nazw;
		TablicaElementow[index_tab].nazwisko[100]=nazwisko[100];
		TablicaElementow[index_tab].czy_null=1;
	}
	while(TablicaElementow[index_tab].czy_null!=0){
			i_hash=1;
		//	TablicaElementow[index_tab].czy_null++;
			index_tab=(liczba_nazwisko+i_hash)%M;
			if(TablicaElementow[index_tab].czy_null==TablicaElementow[pom_index_tab].czy_null){
				printf("nie mozna wstawic elementu do tablic wszystkie pola sa zajete\n");
				
				 break;
			}
			if(TablicaElementow[index_tab].czy_null==0){
				TablicaElementow[index_tab].liczba=liczba_nazw;
				TablicaElementow[index_tab].nazwisko[100]=nazwisko[100];
				TablicaElementow[index_tab].czy_null=1;
					break;
			}
				
				i_hash++;
	}
	
}
int hash(char nazwisko[100],int M){
	int i, dl_napis=strlen(nazwisko);
	int wynik_hash=0;
	wynik_hash=((256*nazwisko[0])+nazwisko[1]);
	for(i=2;i<dl_napis;i++){
		wynik_hash=wynik_hash^((256*nazwisko[i])+nazwisko[i+1]);
		if(wynik_hash<0) wynik_hash=(wynik_hash*-1)%M;
	}
	
	return wynik_hash;
}
void kolizje(int M,struct Element *TablicaElementow){
	int i,licznik=0;
	for(i=0;i<M;i++){
		if(TablicaElementow[i].czy_null>0)licznik++;
	}
	printf("\nkolizje %d\n",licznik);
}


void wczytaj(int M,struct Element *TablicaElementow){
	int liczba=0,i;
	char a[100];
	char bufor[100];
	
	FILE *plik=fopen("dane.txt", "r");
	if (plik) {
     while(1){
	 
		if(fscanf(plik,"%d",&liczba) ==EOF) break;
			fscanf(plik,"%s",bufor);	
   			printf(" %d || %s\n",liczba, bufor );
   			Insert(bufor,liczba,TablicaElementow,M);
   			
   		
//   			getchar();
		}
	

		fclose (plik); 
	}
	
	else
		printf("blad odczytu pliku");
	

}
//void wyswietl(struct Element *TablicaElementow){
//	int i;
//	for(i=0;i<M;i++){
//		if(TablicaElementow[i].liczba==NULL) break;
//		printf("%d ",TablicaElementow[i].liczba);
//		printf("%s\n",TablicaElementow[i].nazwisko[100]);
//	}
//	
//}
void zeruj_start(int M,struct Element *TablicaElementow){
	int i;
	for(i=0;i<M;i++){
		TablicaElementow[i].czy_null=0;
	}
	wczytaj(M,TablicaElementow);
//	wyswietl(TablicaElementow);
	kolizje(M,TablicaElementow);
	printf("M = %d\n",M);
}

int main(int argc, char *argv[]) {
	
	int M;
	M=9984;
	struct Element TablicaElementow[M];
	zeruj_start(M,TablicaElementow);
	M=8475;
	struct Element TablicaElementow[M];
	zeruj_start(M,TablicaElementow);
	printf("\n-------------------------\n");
	M=3942;
	struct Element TablicaElementow[M];
	zeruj_start(M,TablicaElementow);
	printf("\n-------------------------\n");
	M=3943;
	struct Element TablicaElementow[M];
	zeruj_start(M,TablicaElementow);
	printf("\n-------------------------\n");
	M=6427;
	struct Element TablicaElementow[M];
	zeruj_start(M,TablicaElementow);
	printf("\n-------------------------\n");
	M=6977;
	struct Element TablicaElementow[M];
	zeruj_start(M,TablicaElementow);
	printf("\n-------------------------\n");
	M=6980;
	struct Element TablicaElementow[M];
	zeruj_start(M,TablicaElementow);
	printf("\n-------------------------\n");
	
	
	

	

	return 0;
}

			
			
			
