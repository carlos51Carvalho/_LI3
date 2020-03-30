#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "clientes.h"
#include "faturacao.h"
#include "vendas.h"
/*
typedef struct ft {
	char *p;
	double pr;
	int q;
	char e;
	char *cl;
	int m;
} Ft;

typedef struct bucketv {
	int size;
	Ft *arr;
}Bucketv;


typedef struct fill{
	Bucketv *p;
	Bucketv *c;
} FILL ;


typedef struct fatp{
	FILL *fil;
} FatP;
*/


FatP* initFat(){
	int i;
	FatP *h = malloc(sizeof(FatP));
	h->fil = malloc(3 *sizeof(Fill));
	for ( i =0; i < 3 ; i++){
		h->fil[i].p = malloc(26* sizeof(Bucketv));
		h->fil[i].c = malloc(26* sizeof(Bucketv));
		for (int j = 0; j < 26; j++)
		{
			h->fil[i].p[j].size = 0;
			h->fil[i].p[j].arr = malloc(sizeof(Ft));

			h->fil[i].c[j].size = 0;
			h->fil[i].c[j].arr = malloc(sizeof(Ft));
		}
	}
	return h;
}


void acrescentaFat(FatP *h, char*p, double pr, int q, char e, char *c, int m, int f){
	int kp = hash(p);
	int kc = hash(c);
	int tam = h->fil[f-1].p[kp].size;
	int tamc = h->fil[f-1].c[kc].size;
	h->fil[f-1].p[kp].arr = realloc(h->fil[f-1].p[kp].arr, (tam +1)*sizeof(Ft));
	h->fil[f-1].p[kp].arr[tam].p = strdup(p);
	h->fil[f-1].p[kp].arr[tam].pr = pr;
	h->fil[f-1].p[kp].arr[tam].q = q;
	h->fil[f-1].p[kp].arr[tam].e = e;
	h->fil[f-1].p[kp].arr[tam].cl = strdup(c);
	h->fil[f-1].p[kp].arr[tam].m = m;
	h->fil[f-1].p[kp].size++;

	h->fil[f-1].c[kc].arr = realloc(h->fil[f-1].c[kc].arr, (tamc +1)*sizeof(Ft));
	h->fil[f-1].c[kc].arr[tamc].p = strdup(p);
	h->fil[f-1].c[kc].arr[tamc].pr = pr;
	h->fil[f-1].c[kc].arr[tamc].q = q;
	h->fil[f-1].c[kc].arr[tamc].e = e;
	h->fil[f-1].c[kc].arr[tamc].cl = strdup(c);
	h->fil[f-1].c[kc].arr[tamc].m = m;
	h->fil[f-1].c[kc].size++;

}





void swapf(Ft *arr, int i1, int i2)
{
	//passar arr[i1] para temporarios
    char *tmp = arr[i1].cl;
    double pr = arr[i1].pr;
    int q = arr[i1].q;
    int m = arr[i1].m;
    char e= arr[i1].e;
    char *p = arr[i1].p;
    //passar arr[i2] para arr[i1]
    arr[i1].cl=arr[i2].cl;
    arr[i1].pr= arr[i2].pr;
    arr[i1].q = arr[i2].q;
    arr[i1].m = arr[i2].m;
    arr[i1].e = arr[i2].e;
    arr[i1].p = arr[i2].p;
    //passar tmp para arr[2]
    arr[i2].cl= tmp;
    arr[i2].pr= pr;
    arr[i2].q = q;
    arr[i2].m = m;
    arr[i2].e = e;
    arr[i2].p = p;
}


void quicksortFat(Ft *args, unsigned int len){
    unsigned int i, pvt=0;
    if (len <= 1)
        return;

    for (i=0;i<len-1;++i){
        if (strcmp(args[i].cl, args[len-1].cl) < 0)
            //swapc(args+i->id, args+pvt++->id);
        	swapf(args, i, pvt++);
    }
    swapf(args, pvt, len-1);
    quicksortFat(args, pvt++);
    quicksortFat(args+pvt, len - pvt);
}








/*
Fat* initfat ()
{
	Fat *f = malloc (sizeof (Fat));
	f->total =0;
	f->univ =0;
	f->cliented = 0;
	f->prodc =0;
    return f;
}

void fatt(Fat *f, TVendas *v){
	int i;
	for(i=0; i<v->size;i++){
		f->total += (v->arr[i].preco * v->arr[i].qnt);
	}
}

void univ(Fat *f, TVendas *v){
	int i;
	for(i=0; i<v->size; i++){
		f->univ += v->arr[i].qnt;
	}
}

void cliented(Fat *f, TVendas *v, THash *clientes){
	int i,j;
	char *vis[clientes->size];
	int found=0;
	for(i=0; i<v->size; i++){
		for(j=0; j<clientes->size && !found; j++){
			if ((strcmp(v->arr[i].cliente, vis[j])) == 0){
				found=1;
			}
			else{
				strcpy (vis[j], v->arr[i].cliente);
				f->cliented++;				
			}
		}
	}
}


*/