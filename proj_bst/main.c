#include <stdio.h>
#include <stdlib.h>


//k wartosc
//pom drzewo
struct drzewo{
	char wartosc;
	int ilosc;
	struct drzewo *rodzic;
	struct drzewo *lewy_syn;
	struct drzewo *prawy_syn;
};

struct drzewo* szukaj(struct drzewo *pocz,char wart){
		struct drzewo *pom=pocz;
		while(pom!=NULL && pom->wartosc!=wart){
			if(wart < pom->wartosc) 
				pom=pom->lewy_syn;
			else 
				pom=pom->prawy_syn;
		}
		return pom;
}
int exists(struct drzewo *pocz,char wart){
		struct drzewo *pom=pocz;
		while(pom!=NULL && pom->wartosc!=wart){
			if(wart < pom->wartosc) 
				pom=pom->lewy_syn;
			else 
				pom=pom->prawy_syn;
		}
		if(pom==NULL) 
			return 0;
		else 
			return 1;
    }

void dodaj(struct drzewo*pocz,char element){
	struct drzewo* NowyElement=NULL;
	NowyElement=malloc(sizeof(struct drzewo));
	struct drzewo *pom=pocz;
	struct drzewo *rodzic;
	NowyElement->wartosc=element;
	NowyElement->lewy_syn=NULL;
	NowyElement->prawy_syn=NULL;
	NowyElement->ilosc=1;
	if(exists(pocz,element)){
		struct drzewo *pom_szuk=szukaj(pom,element);
		pom_szuk->ilosc++;
	}
	else{
		while(pom!=NULL){
				rodzic=pom;
				if(element < pom->wartosc)
			 		pom=pom->lewy_syn;
				else
					pom=pom->prawy_syn;
			}
			NowyElement->rodzic=rodzic;
     
			if(rodzic==NULL) pocz=NowyElement;
			else 
			{
				if(NowyElement->wartosc < rodzic->wartosc) 
					rodzic->lewy_syn=NowyElement;
				else 
					rodzic->prawy_syn=NowyElement;
			}
		}
}
struct drzewo* minimum(struct drzewo *pocz){
	struct drzewo* pom=pocz;
	while(pom->lewy_syn!=NULL)
		pom=pom->lewy_syn;
	return pom;
}

int usun(struct drzewo *pocz,char element){
    struct drzewo* szuk=szukaj(pocz,element);
	struct drzewo* pom=malloc(sizeof(struct drzewo));
	struct drzewo* pom2=malloc(sizeof(struct drzewo));
     
	if(szuk->ilosc > 1){
		szuk->ilosc--;
		return 1;
	} 
	else{
		if(szuk->lewy_syn==NULL || szuk->prawy_syn==NULL)
			pom2=szuk;
		else 
			pom2=minimum(szuk->prawy_syn);		// usuwamy pocz z co najwy¿ej jednym synem
		
		if(pom2->lewy_syn!=NULL) 
			pom=pom2->lewy_syn;
		else 
			pom=pom2->prawy_syn;				// pom to jedyny syn pocz b¹dŸ NIL

    
		if(pom!=NULL) 
			pom->rodzic=pom2->rodzic;
		if(pom2->rodzic==NULL)  
			pocz=pom;							// pocz by³ korzeniem
		else{
			if(pom2->rodzic->lewy_syn==pom2) 
				pom2->rodzic->lewy_syn=pom;		// pocz by³ lewym synem
			else
				pom2->rodzic->prawy_syn=pom;	// pocz by³ prawym synem
    
		}
		if(pom2!=szuk)
			szuk->wartosc=pom2->wartosc;		// szuk mia³ dwóch synów
		return 1;
	}
}

void wyswietl(struct drzewo *pocz){
		//struct drzewo *pom=pocz;
		if(pocz!=NULL){
			wyswietl(pocz->lewy_syn);
			printf("Litera:	%c ilosc wystapien:	%d \n",pocz->wartosc,pocz->ilosc);
			wyswietl(pocz->prawy_syn);
		}
}



int main(int argc, char *argv[]) {
struct drzewo *nowy=malloc(sizeof(struct drzewo));
nowy->rodzic=NULL;
nowy->lewy_syn=NULL;
nowy->prawy_syn=NULL;
nowy->ilosc=1;
nowy->wartosc='a';
dodaj(nowy,'a');
dodaj(nowy,'a');
dodaj(nowy,'a');
wyswietl(nowy);
printf("po usunieciu jako 'a'\n");
usun(nowy,'a');
wyswietl(nowy);
printf("po usunieciu jako 97 \n");
usun(nowy,97);
wyswietl(nowy);

	return 0;
}
