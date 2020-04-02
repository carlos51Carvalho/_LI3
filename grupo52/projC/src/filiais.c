#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "filiais.h"




int hashfil(char *cont){
	int r = cont[0] - 'A';
	return r;
}

Cl* initCl(){
	Cl *p = malloc(sizeof(Cl));
	p->u = 0;
	p->fil = malloc(3*sizeof(Fili));
	for (int i = 0; i < 3; i++)
	{
		p->fil[i].mes = malloc(12*sizeof(Mesf));
		for (int j = 0; j < 12; j++)
		{
			p->fil[i].mes[j].size = 0;
			p->fil[i].mes[j].prs = malloc(sizeof(Qprd));
		}
	}
	return p;
}

Filial* initFilial(){
	int i;
	Filial *h = malloc(sizeof(Filial));
	h->tbl = malloc(26 *sizeof(Bucketf));
	for ( i =0; i < 26 ; i++){
		h->tbl[i].size = 0;
		h->tbl[i].arr = initCl();
		
	}
	return h ;
}

void acrescenta_cl(Filial *f, char *p){
	int k = hashfil(p);
	int tam = f->tbl[k].size;
	f->tbl[k].arr = realloc(f->tbl[k].arr, (tam+1)*sizeof(Cl));
	f->tbl[k].arr[tam].cid = strdup(p);
	f->tbl[k].arr[tam].fil = malloc(3*sizeof(Fili));
	for (int i = 0; i < 3; ++i)
	{
		f->tbl[k].arr[tam].fil[i].mes = malloc(12*sizeof(Mesf));
		for (int j = 0; j < 12 ; j++)
		{
			f->tbl[k].arr[tam].fil[i].mes[j].size=0;
			f->tbl[k].arr[tam].fil[i].mes[j].prs = malloc(sizeof(Qprd));
		}
	}
	f->tbl[k].size++;
}



int acrescenta_cls (Filial *f, char **p, int tam ){
	int i;
	for (i = 0; i < tam; i++){
		acrescenta_cl(f, p[i]);
	}

	return i;
}


int existe_fil(Cl *arr, char *procurado, int Tam)
{
     int inf = 0;     // limite inferior (o primeiro índice de vetor em C é zero          )
     int sup = Tam-1; // limite superior (termina em um número a menos. 0 a 9 são 10 números)
     int meio;
     int r=0;
     while (inf <= sup && r==0)
     {
          meio = inf +(sup- inf)/2;
          if (strcmp(procurado, arr[meio].cid) ==0){
               	r = meio;
          }
          if (strcmp(procurado, arr[meio].cid)<0)
               sup = meio-1;
          else
               inf = meio+1;
     }
     return r; 
}



int existe_prod(Qprd *arr, char *procurado, int Tam)
{
	int r=0;
     for (int i = 0; i < Tam && r == 0; i++){
     	if (strcmp(arr[i].pid, procurado)==0){
     		r=1;
     	}
     }
     return r; 
}



void acrescentaPtoFil(Filial *h, char *p, int it, int a, int f, int m, char e, int qnt){
	int size = h->tbl[it].arr[a].fil[f].mes[m].size;
	h->tbl[it].arr[a].fil[f].mes[m].prs = realloc(h->tbl[it].arr[a].fil[f].mes[m].prs, (size+1)*sizeof(Qprd));
	h->tbl[it].arr[a].fil[f].mes[m].prs[size].pid = strdup(p);
	if (e == 'N'){
		h->tbl[it].arr[a].fil[f].mes[m].prs[size].qN +=qnt;
	}
	else h->tbl[it].arr[a].fil[f].mes[m].prs[size].qP +=qnt;

	h->tbl[it].arr[a].fil[f].mes[m].size++;
}

void acrescentaFil(Filial *h, char*p, double pr, int q, char e, char *c, int m, int f){
	int k = hashfil(c);
	int tam = h->tbl[k].size;
	int  r= existe_fil(h->tbl[k].arr, p, tam);
	int pi=0, t=0;
	if (r > 0){
		h->tbl[k].arr[r].u =1;
		t = h->tbl[k].arr[r].fil[f-1].mes[f-1].size;
		pi = existe_prod(h->tbl[k].arr[r].fil[f-1].mes[f-1].prs, p, t);

		if (pi>0){
			if (e == 'N'){
			h->tbl[k].arr[r].fil[f-1].mes[m-1].prs[pi].qN += q;
			}
			else{
			h->tbl[k].arr[r].fil[f-1].mes[m-1].prs[pi].qP += q;
			}
		}
	}
	else {
		acrescentaPtoFil(h, p,k,r,f-1,m-1,e,q);
	}
}