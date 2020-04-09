#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "filiais.h"


typedef struct qprd{
	char *pid;
	int qN;
	int qP;
	double gN;  
	double gP;    
} Qprd ;


typedef struct mesf{
	int size;
	Qprd *prs;
} Mesf ;

typedef struct fili{
	int used;
	Mesf *mes;
} Fili;


typedef struct cl{
	char *cid;
	Fili *fil;
} Cl;

typedef struct bucketf{
	int size;
	Cl *arr;
} Bucketf ;


typedef struct filial{
	Bucketf *tbl;
} Filial;

int getFilUsed(Filial *f, int k, int ip, int fil){
	return f->tbl[k].arr[ip].fil[fil].used;
}

char* getCLiente(Filial *f, int k, int ip){
	return strdup(f->tbl[k].arr[ip].cid);
}
int getSizeArrClient(Filial *f, int k){
	return f->tbl[k].size;
}

Cl* getArrByLetter(Filial *f, int key){
	return f->tbl[key].arr;
}

int getSizeQprd(Filial *f, int k, int id, int fil, int m){
	return f->tbl[k].arr[id].fil[fil].mes[m].size;
}

int getQuantN(Filial *f, int k, int id, int fil, int m, int pid){
	return f->tbl[k].arr[id].fil[fil].mes[m].prs[pid].qN;
}

int getQuantP(Filial *f, int k, int id, int fil, int m, int pid){
	return f->tbl[k].arr[id].fil[fil].mes[m].prs[pid].qP;
}

int getGastoP(Filial *f, int k, int id, int fil, int m, int pid){
	return f->tbl[k].arr[id].fil[fil].mes[m].prs[pid].gP;
}

int getGastoN(Filial *f, int k, int id, int fil, int m, int pid){
	return f->tbl[k].arr[id].fil[fil].mes[m].prs[pid].gN;
}

char* getOneProd(Filial *f, int k, int id, int fil, int m, int p){
	return f->tbl[k].arr[id].fil[fil].mes[m].prs[p].pid;
}


int hashfil(char *cont){
	int r = cont[0] - 'A';
	return r;
}



Filial* initFilial(){
	int i;
	Filial *h = malloc(sizeof(Filial));
	h->tbl = malloc(26 *sizeof(Bucketf));
	for ( i =0; i < 26 ; i++){
		h->tbl[i].size = 0;
		h->tbl[i].arr = NULL;
		
	}
	return h ;
}


void destroiFilial(Filial *f){
	int i,j,fi,m,p,sizeQprod;
	for (i = 0; i < 26; i++){
		for (j = 0; j < f->tbl[i].size; j++){
			for (fi = 0; fi < 3; fi++){
				for (m = 0; m < 12; m++){
					sizeQprod=getSizeQprd(f, i, j,fi,m);
					for (p = 0; p <sizeQprod ; p++){
						free(f->tbl[i].arr[j].fil[fi].mes[m].prs[p].pid);
					}
					free(f->tbl[i].arr[j].fil[fi].mes[m].prs);
				}
				free(f->tbl[i].arr[j].fil[fi].mes);
			}
			free(f->tbl[i].arr[j].fil);
			free(f->tbl[i].arr[j].cid);
		}
		free(f->tbl[i].arr);
	}
	free(f->tbl);
	free(f);
}


void acrescenta_cl(Filial *f, char *p){
	int i,j;
	int k = hashfil(p);
	int tam = f->tbl[k].size;
	f->tbl[k].arr = realloc(f->tbl[k].arr, (tam+1)*sizeof(Cl));
	f->tbl[k].arr[tam].cid = strdup(p);
	f->tbl[k].arr[tam].fil = malloc(3*sizeof(Fili));
	for (i = 0; i < 3; ++i)
	{
		f->tbl[k].arr[tam].fil[i].used =0;
		f->tbl[k].arr[tam].fil[i].mes = malloc(12*sizeof(Mesf));
		for (j = 0; j < 12 ; j++)
		{
			f->tbl[k].arr[tam].fil[i].mes[j].size=0;
			f->tbl[k].arr[tam].fil[i].mes[j].prs = NULL;
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
     int inf = 0;     
     int sup = Tam-1; 
     int meio;
     int r=-1;
     while (inf <= sup && r==-1)
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
	int i,r=-1;
     for (i = 0; i < Tam && r == -1; i++){
     	if (strcmp(arr[i].pid, procurado)==0){
     		r=i;
     	}
     }
     return r; 
}



void acrescentaPtoFil(Filial *h, char *p, int it, int a, int f, int m, char e, int qnt, int preco){
	int size = h->tbl[it].arr[a].fil[f].mes[m].size;

	h->tbl[it].arr[a].fil[f].mes[m].prs = realloc(h->tbl[it].arr[a].fil[f].mes[m].prs, (size+1)*sizeof(Qprd));
	h->tbl[it].arr[a].fil[f].mes[m].prs[size].pid = strdup(p);

	h->tbl[it].arr[a].fil[f].mes[m].prs[size].qN = 0;
	h->tbl[it].arr[a].fil[f].mes[m].prs[size].qP = 0;
	h->tbl[it].arr[a].fil[f].mes[m].prs[size].gN = 0;
	h->tbl[it].arr[a].fil[f].mes[m].prs[size].gP = 0;

	if (e == 'N'){
		h->tbl[it].arr[a].fil[f].mes[m].prs[size].qN += qnt;
		h->tbl[it].arr[a].fil[f].mes[m].prs[size].gN += qnt * preco;
	}
	else{
		h->tbl[it].arr[a].fil[f].mes[m].prs[size].qP += qnt;
		h->tbl[it].arr[a].fil[f].mes[m].prs[size].gP += qnt * preco;
	}
	h->tbl[it].arr[a].fil[f].mes[m].size++;
}



void acrescentaFil(Filial *h, char*p, double pr, int q, char e, char *c, int m, int f){
	int k = hashfil(c);
	int tam = getSizeArrClient(h,k);
	int r= existe_fil(getArrByLetter(h,k), c, tam);
	int pi=0, t=0;
	if (r >= 0){
		t = h->tbl[k].arr[r].fil[f-1].mes[m-1].size;
		pi = existe_prod(h->tbl[k].arr[r].fil[f-1].mes[m-1].prs, p, t);
		h->tbl[k].arr[r].fil[f-1].used=1;

		if (pi < 0){
			acrescentaPtoFil(h, p,k,r,f-1,m-1,e,q, pr);
		}else{
			if (e == 'N'){
				h->tbl[k].arr[r].fil[f-1].mes[m-1].prs[pi].qN += q;
				h->tbl[k].arr[r].fil[f-1].mes[m-1].prs[pi].gN += q * pr;
			}
			else{
				h->tbl[k].arr[r].fil[f-1].mes[m-1].prs[pi].qP += q;
				h->tbl[k].arr[r].fil[f-1].mes[m-1].prs[pi].gP += q * pr;
			}
		}
	}
}

char** ClientsOfAllBranches (Filial *f, int *tam){
	int i,j,count =0;
	char **c = NULL;
	for(i=0; i<26; i++)
	{
		int t= f->tbl[i].size;
		for(j=0; j<t;j++)
		{
			if(getFilUsed(f,i,j,0)==1 && getFilUsed(f,i,j,1)==1 && getFilUsed(f,i,j,2)==1){
				c = realloc (c,(count+1) *sizeof (char*));
				c[count] = strdup(getCLiente(f,i,j));
				count++;
			}
		}
	}
	*tam = count;
	return c;
}

int ClientesSemCompras (Filial *f){
	int i,j,t,count =0;
	for(i=0;i<26;i++){
		t = f->tbl[i].size;
		for(j =0; j<t; j++){
			if(getFilUsed(f,i,j,0)==0 && getFilUsed(f,i,j,1)==0 && getFilUsed(f,i,j,2)==0) count++;
		}
	}
	return count;
}




int QuantidadesUmClientePorMes(Filial *f, char *clienteID ,int fil, int mes){
	int i, k = hashfil(clienteID);
	int r = existe_fil(getArrByLetter(f,k), clienteID, getSizeArrClient(f,k));
	int result =0;

	if (r >= 0){
		for (i = 0; i < getSizeQprd(f,k,r,fil,mes); i++)
		{
			result += getQuantN(f,k,r,fil,mes,i) + getQuantP(f,k,r,fil,mes,i);
		}
	}
	return result;
}
