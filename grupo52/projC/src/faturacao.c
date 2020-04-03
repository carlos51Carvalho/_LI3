#include <stdio.h>
#include <string.h>
#include <stdlib.h>


#include "faturacao.h"


int getPosicaoProd(Fat *fat,char *productID){
	int i,result=-1;
	int hash = hashfat(productID);
	for (i = 0; i < fat->tbl[hash].size; i++){
		if(strcmp(fat->tbl[hash].arr[i].pid, productID)==0)
			result=i;
	}
	return result;
}

int getVendasN(Fat *fat,int h,int pos,int m,int f){
	int result=0;
	if(pos>=0){
		result=fat->tbl[h].arr[pos].fil[f-1].mes[m-1].vN;
	}
	return result;
}
int getVendasP(Fat *fat,int h,int pos,int m,int f){
	int result=0;
	if(pos>=0){
		result=fat->tbl[h].arr[pos].fil[f-1].mes[m-1].vP;
	}
	return result;
}

double getFaturacaoN(Fat *fat,int h,int pos,int m,int f){
	double result=0;
	if(pos>=0){
		result=fat->tbl[h].arr[pos].fil[f-1].mes[m-1].fN;
	}
	return result;
}
double getFaturacaoP(Fat *fat,int h,int pos,int m,int f){
	double result=0;
	if(pos>=0){
		result=fat->tbl[h].arr[pos].fil[f-1].mes[m-1].fP;
	}
	return result;
}


int hashfat(char *cont){
	int r = cont[0] - 'A';
	return r;
}



Fat* initFat(){
	int i;
	Fat *h = malloc(sizeof(Fat));
	h->tbl = malloc(26 *sizeof(Bucketv));
	for ( i =0; i < 26 ; i++){
		h->tbl[i].size = 0;
		h->tbl[i].arr = NULL;
		
	}
	return h ;
}

void acrescenta_prod(Fat *f, char *p){
	int k = hashfat(p);
	int tam = f->tbl[k].size;
	f->tbl[k].arr = realloc(f->tbl[k].arr, (tam+1)*sizeof(Prd));
	f->tbl[k].arr[tam].pid = strdup(p);
	f->tbl[k].arr[tam].fil = malloc(3*sizeof(Fil));
	for (int i = 0; i < 3; ++i)
	{
		f->tbl[k].arr[tam].fil[i].mes = malloc(12*sizeof(Mes));
		for (int j = 0; j < 12; j++)
		{
			f->tbl[k].arr[tam].fil[i].mes[j].fN =0;
			f->tbl[k].arr[tam].fil[i].mes[j].fP =0;
			f->tbl[k].arr[tam].fil[i].mes[j].vN =0;
			f->tbl[k].arr[tam].fil[i].mes[j].vP =0;
		}
	}
	f->tbl[k].size++;
}



int acrescenta_prods (Fat *f, char **p, int tam ){
	int i;
	for (i = 0; i < tam; i++){
		acrescenta_prod(f, p[i]);
	}

	return i;
}


int existe_fat(Prd *arr, char *procurado, int Tam)
{
     int inf = 0;     // limite inferior (o primeiro índice de vetor em C é zero          )
     int sup = Tam-1; // limite superior (termina em um número a menos. 0 a 9 são 10 números)
     int meio;
     int r = -1;
     while (inf <= sup && r==-1)
     {
          meio = inf +(sup- inf)/2;
          if (strcmp(procurado, arr[meio].pid) ==0){
               	r = meio;
          }
          if (strcmp(procurado, arr[meio].pid)<0)
               sup = meio-1;
          else
               inf = meio+1;
     }
     return r; 
}


void acrescentaFat(Fat *h, char*p, double pr, int q, char e, char *c, int m, int f){
	int k = hashfat(p);
	int tam = h->tbl[k].size;
	int  r = existe_fat(h->tbl[k].arr, p, tam);
	if (r >= 0){

		if (e == 'N'){
			h->tbl[k].arr[r].fil[f-1].mes[m-1].vN++;
			h->tbl[k].arr[r].fil[f-1].mes[m-1].fN += pr*q;

		}
		else{
			h->tbl[k].arr[r].fil[f-1].mes[m-1].vP++;
			h->tbl[k].arr[r].fil[f-1].mes[m-1].fP += pr*q;
		}
	}
}


