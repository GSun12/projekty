#include <stdio.h>
#include <time.h>
#define C 1
#define MLD 1000000000.0
int podzial(int tablica[], int prawy, int lewy);
void quicksort(int tab[],int prawy,int lewy);
void babelSort(int tab[], int ile);

void zamien(int* a, int* b){
	int pom = *a;
	*a=*b;
	*b=pom;
	
}
int podzial(int tab[], int prawy, int lewy){
	int poz,i,j;
	poz=tab[lewy];
	i=prawy-1;
	for(j=prawy;j<=lewy;j++){
		if(tab[j]<=poz){
			i+=1;
			zamien(&tab[i],&tab[j]);
		}

	}
		if(i<lewy) return i;
		else return i-1;
}

void quicksort(int tab[],int prawy, int lewy){
	if(prawy<lewy){
	
		int q=podzial(tab,prawy,lewy);
		if(lewy-prawy+1<C)
			babelSort(tab,lewy);
		else{
			quicksort(tab,prawy,q);
			quicksort(tab,q+1,lewy);
		}
		
	}
	
}

void babelSort(int tab[], int ile){
	 do{
		int i;
		for (i = 0; i <= ile-1; i++){
			if (tab[i] > tab[i+1]){
			zamien(&tab[i], &tab[i+1]);
  			}
		}
    	ile--;
   	 }while (ile >= 1);
	
}
void wydruk(int kt){
	int tab_r[2500];
	int licz_w, i;
	int  wielkosc_tablicy[]={100,400,800,1200,2500};	
	struct timespec tp0, tp1;
 	double Tn;
		 clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&tp0);
  	 
	for(licz_w=0; licz_w<5; licz_w++) {
    	printf("\t%d ", wielkosc_tablicy[licz_w]);

    	for(i=0; i<=wielkosc_tablicy[licz_w]; i++) 
			tab_r[i]=rand()%100; 
		if(kt==1) quicksort(tab_r, 0, wielkosc_tablicy[licz_w]-1);
    	else babelSort(tab_r, wielkosc_tablicy[licz_w]-1);
   		clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&tp1);
    	Tn=(tp1.tv_sec+tp1.tv_nsec/MLD)-(tp0.tv_sec+tp0.tv_nsec/MLD);
    	printf("\t  | %3.7lf\t\t",Tn);
	
    	for(i=wielkosc_tablicy[licz_w]; i>=1; i--) 
			tab_r[i]=i;
    	quicksort(tab_r, 0, wielkosc_tablicy[licz_w]);
   		clock_gettime(CLOCK_PROCESS_CPUTIME_ID, &tp1);
   		Tn=(tp1.tv_sec+tp1.tv_nsec/MLD)-(tp0.tv_sec+tp0.tv_nsec/MLD);
    	printf("| %3.7lf\t\t", Tn);
    	printf("\n");
  }
}
void wyswietl(int tab[], int ile){
	int i;
	for(i=0; i<=ile;i++){
		printf("%d\t", tab[i]);
	}	puts("");
}	

int main(int argc, char *argv[]) {
	int tab[]={9,8,7,6,5,4,3,2,1};
	int ile=9;
	
 //	babelSort(tab,ile);
 	printf("przykladowe dzialanie dla %d danych\n\n",ile);
	wyswietl(tab,ile-1);
	quicksort(tab,0,ile-1);
	wyswietl(tab,ile-1);
	printf("-------------------------Quicksort--------------------------------------------\n");
	printf("------------------------------------------------------------------------------\n");
  	printf(" Wielkosc tablicy | Dane randomowe (t) \t| Dane uporzadkowane(niekrzystne) (t)\n");
  	printf("------------------------------------------------------------------------------\n");
  	wydruk(1);
  	printf("------------------------------------------------------------------------------\n\n");
  	printf("-------------------------Babelkowe--------------------------------------------\n");
	printf("------------------------------------------------------------------------------\n");
  	printf(" Wielkosc tablicy | Dane randomowe (t) \t| Dane uporzadkowane(niekrzystne) (t)\n");
  	printf("------------------------------------------------------------------------------\n");
  	wydruk(2);
  	printf("------------------------------------------------------------------------------\n");
	return 0;
}
