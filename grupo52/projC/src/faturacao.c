#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "clientes.h"
#include "produtos.h"
#include "vendas.h"
#include "faturacao.h"


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